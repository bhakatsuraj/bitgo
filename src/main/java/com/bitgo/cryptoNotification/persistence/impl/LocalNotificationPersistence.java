package com.bitgo.cryptoNotification.persistence.impl;

import com.bitgo.cryptoNotification.model.CryptoNotification;
import com.bitgo.cryptoNotification.persistence.INotificationPersistence;
import com.bitgo.cryptoNotification.util.NotificationStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LocalNotificationPersistence implements INotificationPersistence {

    private Map<Integer, CryptoNotification> notificationDatabase;

    public LocalNotificationPersistence(){
        this.notificationDatabase = new HashMap<>();
    }

    @Override
    public void createNotification(CryptoNotification cryptoNotification){
        notificationDatabase.put(cryptoNotification.getNotificationId(), cryptoNotification);
    }

    @Override
    public void updateNotification(CryptoNotification cryptoNotification) {
        notificationDatabase.put(cryptoNotification.getNotificationId(), cryptoNotification);
    }

    @Override
    public Optional<CryptoNotification> getNotification(Integer notificationId) {
        if(notificationDatabase.containsKey(notificationId)){
            return Optional.of(notificationDatabase.get(notificationId));
        }

        return Optional.empty();

    }

    @Override
    public Optional<List<CryptoNotification>> getAllNotifications() {

        if(notificationDatabase.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(new ArrayList<>(notificationDatabase.values()));
    }

    @Override
    public Optional<List<CryptoNotification>> getAllNotificationsWithStatus(NotificationStatus notificationStatus) {
        if (notificationDatabase.isEmpty()){
            return Optional.empty();
        }

        List<CryptoNotification> filteredNotifications = notificationDatabase.values().stream()
                .filter(notification -> notification.getStatus().equals(notificationStatus))
                .collect(Collectors.toList());

        if(filteredNotifications.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(filteredNotifications);
    }

}
