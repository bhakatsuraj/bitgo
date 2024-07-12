package com.bitgo.cryptoNotification.util;

import com.bitgo.cryptoNotification.model.CryptoNotification;
import com.bitgo.cryptoNotification.model.NotificationCreationRequest;
import org.springframework.stereotype.Component;

@Component
public class NotificationTransformer {

    public CryptoNotification transformToCryptoNotification(NotificationCreationRequest notificationCreationRequest){
        CryptoNotification cryptoNotification = new CryptoNotification();

        cryptoNotification.setActive(true);
        cryptoNotification.setStatus(NotificationStatus.CREATED);
        cryptoNotification.setBitcoinName(notificationCreationRequest.getBitcoinName());
        cryptoNotification.setDailyChange(notificationCreationRequest.getDailyChange());
        cryptoNotification.setTradingVolume(notificationCreationRequest.getTradingVolume());
        cryptoNotification.setPriceOfBitcoin(notificationCreationRequest.getPriceOfBitcoin());

        cryptoNotification.setNotificationId(notificationCreationRequest.hashCode());

        return cryptoNotification;
    }
}
