package com.bitgo.cryptoNotification.controller;

import com.bitgo.cryptoNotification.facade.NotificationFacade;
import com.bitgo.cryptoNotification.model.CryptoNotification;
import com.bitgo.cryptoNotification.model.NotificationCreationRequest;
import com.bitgo.cryptoNotification.util.NotificationStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class NotificationController {

    private final NotificationFacade notificationFacade;

    @Autowired
    public NotificationController(NotificationFacade notificationFacade){
        this.notificationFacade = notificationFacade;
    }
    @PostMapping("/notification")
    public void createNotification(@RequestBody NotificationCreationRequest notificationCreationRequest) throws Exception{
        log.info("Request to create notification {}", notificationCreationRequest.toString());
        notificationFacade.createNotification(notificationCreationRequest);
    }

    @DeleteMapping("/notification/{notificationId}")
    public void deactivateNotification(@PathVariable Integer notificationId) throws Exception {
        log.info("Request to deactivate notification {}", notificationId);
        notificationFacade.deactivateNotification(notificationId);
    }

    @PutMapping("/notification/send/{notificationId}")
    public void sendNotification(@PathVariable Integer notificationId, @RequestBody List<String> emailIdList) throws Exception {
        log.info("Request to send notification {}", notificationId);
        notificationFacade.sendNotifications(notificationId, emailIdList);
    }

    @GetMapping("/notification/{status}")
    public List<CryptoNotification> getNotificationsWithStatus(@PathVariable NotificationStatus status) throws Exception {
        log.info("Request to retrieve notifications with status {}", status);
        return notificationFacade.getNotificationsWithStatus(status);
    }

    @GetMapping("/notification/all")
    public List<CryptoNotification> getAllNotifications() throws Exception {
        log.info("Request to retrieve all notifications ");
        return notificationFacade.getAllNotifications();
    }
}
