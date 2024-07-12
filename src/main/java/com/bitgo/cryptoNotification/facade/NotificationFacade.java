package com.bitgo.cryptoNotification.facade;

import com.bitgo.cryptoNotification.exception.BadRequestException;
import com.bitgo.cryptoNotification.exception.ConflictException;
import com.bitgo.cryptoNotification.exception.NotfoundException;
import com.bitgo.cryptoNotification.model.CryptoNotification;
import com.bitgo.cryptoNotification.model.NotificationCreationRequest;
import com.bitgo.cryptoNotification.persistence.INotificationPersistence;
import com.bitgo.cryptoNotification.util.NotificationStatus;
import com.bitgo.cryptoNotification.util.NotificationTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class NotificationFacade {

    private final INotificationPersistence notificationPersistence;
    private final NotificationTransformer notificationTransformer;
    @Autowired
    public NotificationFacade(INotificationPersistence notificationPersistence,
                              NotificationTransformer notificationTransformer){

        this.notificationPersistence = notificationPersistence;
        this.notificationTransformer = notificationTransformer;
    }
    public void createNotification(NotificationCreationRequest notificationCreationRequest) throws Exception {

        CryptoNotification cryptoNotification = notificationTransformer.transformToCryptoNotification(notificationCreationRequest);

        Optional<CryptoNotification> existingNotification = notificationPersistence.getNotification(cryptoNotification.getNotificationId());

        if(existingNotification.isPresent()){
            throw new ConflictException("Notification already exists");
        }

        notificationPersistence.createNotification(cryptoNotification);
    }

    public void deactivateNotification(Integer notificationId) throws Exception {
        Optional<CryptoNotification> optionalCryptoNotification = notificationPersistence.getNotification(notificationId);

        if(optionalCryptoNotification.isEmpty()){
            throw new NotfoundException("No notifications found");
        }

        if (!optionalCryptoNotification.get().getActive()){
            throw new BadRequestException("Notification already inactive");
        }

        CryptoNotification cryptoNotification = optionalCryptoNotification.get();;

        cryptoNotification.setActive(false);
        notificationPersistence.updateNotification(cryptoNotification);
    }

    public List<CryptoNotification> getNotificationsWithStatus (NotificationStatus notificationStatus) throws Exception {
        Optional<List<CryptoNotification> >optionalNotifications = notificationPersistence.getAllNotificationsWithStatus(notificationStatus);

        if(optionalNotifications.isEmpty()){
            throw new NotfoundException("No notifications found");
        }

        return optionalNotifications.get();
    }

    public List<CryptoNotification> getAllNotifications() throws Exception{
        Optional<List<CryptoNotification>> allNotifications = notificationPersistence.getAllNotifications();

        if(allNotifications.isEmpty()){
            throw new NotfoundException("No notifications found");
        }

        return allNotifications.get();
    }

    public void sendNotifications (Integer notificationId, List<String> emailIdList) throws Exception {

        Optional<CryptoNotification> optionalNotification = notificationPersistence.getNotification(notificationId);

        if(optionalNotification.isEmpty()){
            throw new NotfoundException("No notification found");
        }

        for(String email : emailIdList){
            log.info("Sending notification {} to {}",notificationId, email);
        }

        CryptoNotification notification = optionalNotification.get();
        notification.setStatus(NotificationStatus.SENT);
        notificationPersistence.updateNotification(notification);
    }
}
