package com.eepractice.demo.repository;

import com.eepractice.demo.bean.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification,Integer> {

    List<Notification> findNotificationsByUnreadUsersContains(String id);

}
