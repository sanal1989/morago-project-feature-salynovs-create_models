package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Notification;
import com.habsida.moragoproject.model.input.NotificationInput;
import com.habsida.moragoproject.repository.NotificationRepository;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class NotificationService {

    NotificationRepository notificationRepository;
    UserRepository userRepository;

    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public List<Notification> findAll(){
        return notificationRepository.findAll();
    }

    public Notification findById(Long id){
        return notificationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Notification -> Notification doesn't find by Id"));
    }

    public Notification createNotification(NotificationInput notificationInput){
        Notification notification = new Notification();
        if(!isNull(notificationInput.getText()) && !notificationInput.getText().isEmpty()){
            notification.setText(notificationInput.getText());
        }else{
            notification.setText("EMPTY");
        }
        if(!isNull(notificationInput.getTitle()) && !notificationInput.getTitle().isEmpty()){
            notification.setTitle(notificationInput.getTitle());
        }else{
            notification.setTitle("EMPTY");
        }
        if(!isNull(notificationInput.getUser())){
            notification.setUser(userRepository.findById(notificationInput.getUser())
                    .orElseThrow(()->new RuntimeException("Notification -> User doesn't find by Id")));
        }else{
            notification.setTitle("EMPTY");
        }
        return notificationRepository.save(notification);
    }

    public void deleteNotificationById(Long id){
        notificationRepository.deleteById(id);
    }

    public Notification updateNotification(Long id, NotificationInput notificationInput){
        Notification notification = notificationRepository.findById(id).get();
        if(!isNull(notificationInput.getText()) && !notificationInput.getText().isEmpty()){
            notification.setText(notificationInput.getText());
        }else{
            notification.setText("EMPTY");
        }
        if(!isNull(notificationInput.getTitle()) && !notificationInput.getTitle().isEmpty()){
            notification.setTitle(notificationInput.getTitle());
        }else{
            notification.setTitle("EMPTY");
        }
        if(!isNull(notificationInput.getUser())){
            notification.setUser(userRepository.findById(notificationInput.getUser())
                    .orElseThrow(()->new RuntimeException("Notification -> User doesn't find by Id")));
        }else{
            notification.setTitle("EMPTY");
        }
        return notificationRepository.save(notification);
    }
}