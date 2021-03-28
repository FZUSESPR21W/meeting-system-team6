package com.eepractice.demo.controller;

import com.eepractice.demo.bean.CommonResponse;
import com.eepractice.demo.bean.Notification;
import com.eepractice.demo.bean.SubForum;
import com.eepractice.demo.bean.User;
import com.eepractice.demo.forms.InfoForm;
import com.eepractice.demo.properties.CodeProperty;
import com.eepractice.demo.properties.RoleProperty;
import com.eepractice.demo.repository.NotificationRepository;
import com.eepractice.demo.repository.SubForumRepository;
import com.eepractice.demo.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/default")
@CrossOrigin(origins = "*")
public class DefaultController {

    @Autowired
    CodeProperty codeProperty;

    @Autowired
    RoleProperty roleProperty;

    @Autowired
    SubForumRepository subForumRepository;

    @Autowired
    CommonUtils commonUtils;

    @Autowired
    NotificationRepository notificationRepository;
    /**
     *  获取首页需要的数据
     * @return
     */

    @GetMapping(value = "/info",produces = "application/json")
    public ResponseEntity<?> processInfo(){
        List<InfoForm> infoFormList = new ArrayList<>();
        Map<String,Object> responseMap = new HashMap<>();
        // 分论坛信息
        Iterable<SubForum> subForums = subForumRepository.findAll();
        for (SubForum subForum : subForums) {
            infoFormList.add(commonUtils.transformSubForum(subForum));
        }
        responseMap.put("data",infoFormList);
        return ResponseEntity.ok(responseMap);
    }


    /**
     *  发布通知，只有主席或秘书才能发布
     */
    @PostMapping(value = "/add/{id}",consumes = "application/json")
    public ResponseEntity<?> processAddNotification(@RequestBody Notification notification,
                                                    @RequestHeader("Authorization") String token,
                                                    @PathVariable("id") Integer id) {
        String newToken = token.replaceAll("Basic ","");
        // 用户验证信息无效
        User user = commonUtils.parseJwtToken(newToken);
        System.out.println(user.getId());
        if (user == null) return CommonResponse.getMap(codeProperty.TOKEN_INVALID,"用户验证信息无效",null);
        SubForum subForum = subForumRepository.findById(id).get();
        // 只有分论坛的主席和秘书能发布通知
        if (user.getId().equals(subForum.getChairManId()) || user.getId().equals(subForum.getSecretaryId())) {
            // 用户有效，发布通知
            notification.setSubForumId(id);
            List<User> members = subForum.getMembers();
            String unreadUsers = commonUtils.parseUserList(members,subForum.getChairManId(),subForum.getSecretaryId());
            notification.setUnreadUsers(unreadUsers);
            notificationRepository.save(notification);
            return CommonResponse.getMap(codeProperty.SUCCESS,"发布成功!",null);
        }
        return CommonResponse.getMap(codeProperty.NO_PERMISSIONS,"用户权限不足",null);
    }


}
