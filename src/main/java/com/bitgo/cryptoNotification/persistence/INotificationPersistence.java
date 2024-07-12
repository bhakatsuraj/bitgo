package com.bitgo.cryptoNotification.persistence;

import com.bitgo.cryptoNotification.model.CryptoNotification;
import com.bitgo.cryptoNotification.util.NotificationStatus;

import java.util.List;
import java.util.Optional;

public interface INotificationPersistence {
    void createNotification(CryptoNotification cryptoNotification);

    void updateNotification(CryptoNotification cryptoNotification);
    Optional<CryptoNotification> getNotification(Integer notificationId );

    Optional<List<CryptoNotification>> getAllNotifications() ;


    Optional<List<CryptoNotification>> getAllNotificationsWithStatus(NotificationStatus notificationStatus);
}
