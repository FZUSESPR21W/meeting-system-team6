package com.eepractice.demo.controller;

import com.eepractice.demo.bean.CommonResponse;
import com.eepractice.demo.bean.Notification;
import com.eepractice.demo.bean.SubForum;
import com.eepractice.demo.bean.User;
import com.eepractice.demo.forms.NotificationForm;
import com.eepractice.demo.properties.CodeProperty;
import com.eepractice.demo.repository.NotificationRepository;
import com.eepractice.demo.repository.SubForumRepository;
import com.eepractice.demo.repository.UserRepository;
import com.eepractice.demo.utils.CommonUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/note")
@CrossOrigin(origins = "*")
public class NotificationController {


    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubForumRepository subForumRepository;

    @Autowired
    CommonUtils commonUtils;

    @Autowired
    CodeProperty codeProperty;

    /**
     * 接受用户id查看是否有通知
     * @return
     */
    @GetMapping(value = "/has_note/{userId}",produces = "application/json")
    public ResponseEntity<?> processHasNote(@PathVariable("userId") Integer userId){
        String uId = String.valueOf(userId);
        List<Notification> notifications = notificationRepository.findNotificationsByUnreadUsersContains(uId);
        List<NotificationForm> resultList = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationForm notificationForm = new NotificationForm();
            notificationForm.setTitle(notification.getTitle()).setContent(notification.getContent());
            SubForum subForum = subForumRepository.findById(notification.getSubForumId()).get();
            User chairman = userRepository.findById(subForum.getId()).get();
            notificationForm.setBelongSubForum(subForum.getTitle()).setBelongChairMan(chairman.getUsername())
            .setId(notification.getId());
            resultList.add(notificationForm);
        }
        return ResponseEntity.ok(resultList);
    }


    @GetMapping(value = "/read/{noteId}",produces = "application/json")
    public ResponseEntity<?> processReadNote(@PathVariable("noteId") Integer noteId,@RequestHeader("Authorization") String token){
        User user = commonUtils.parseJwtToken(token);
        if (user == null) return CommonResponse.getMap(codeProperty.TOKEN_INVALID,"用户验证信息无效",null);
        // 该用户已读通知
        Notification notification = notificationRepository.findById(noteId).get();
        notification.handleMsg(user.getId());
        notificationRepository.save(notification);
        return CommonResponse.getMap(codeProperty.SUCCESS,"操作成功！",null);
    }

}
