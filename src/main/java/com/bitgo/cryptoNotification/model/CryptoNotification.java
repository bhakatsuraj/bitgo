package com.bitgo.cryptoNotification.model;

import com.bitgo.cryptoNotification.util.NotificationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CryptoNotification {

    private Integer notificationId;
    private Double priceOfBitcoin;
    private String bitcoinName;
    private Double dailyChange;
    private Double tradingVolume;

    private NotificationStatus status;

    private Boolean active;

}
