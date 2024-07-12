package com.bitgo.cryptoNotification.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotificationCreationRequest {
    private Double priceOfBitcoin;
    private String bitcoinName;
    private Double dailyChange;
    private Double tradingVolume;

}
