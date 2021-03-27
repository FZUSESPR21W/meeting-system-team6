package com.eepractice.demo;

import com.eepractice.demo.bean.Notification;
import com.eepractice.demo.bean.User;
import com.eepractice.demo.repository.NotificationRepository;
import com.eepractice.demo.repository.UserRepository;
import com.eepractice.demo.utils.CommonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    CommonUtils commonUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Test
    void contextLoads() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        String s = format.format(date);
        System.out.println(s);
    }

    @Test
    void t2(){
        Optional<User> userRepositoryById = userRepository.findById(1);
        User user = userRepositoryById.get();
        String s = commonUtils.generateJwtToken(user);
        System.out.println(s);
        User user1 = commonUtils.parseJwtToken(s);
        System.out.println(user1.getUsername());
    }

    @Test
    public void t3(){
        List<Notification> notifications = notificationRepository.findNotificationsByUnreadUsersContains("1");
        notifications.forEach(System.out::println);
    }

}
