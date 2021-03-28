package com.eepractice.demo.controller;

import com.eepractice.demo.bean.AuthResponse;
import com.eepractice.demo.bean.User;
import com.eepractice.demo.forms.AuthForm;
import com.eepractice.demo.properties.CodeProperty;
import com.eepractice.demo.service.AuthServiceImpl;
import com.eepractice.demo.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {


    @Autowired
    CodeProperty codeProperty;


    @Autowired
    AuthServiceImpl authService;

    @Autowired
    CommonUtils commonUtils;

    /**
     * 处理登录的控制器
     * @param authForm
     * @return
     */
    @PostMapping(value = "/login",produces = "application/json")
    public ResponseEntity<?> processLogin(@RequestBody AuthForm authForm){

        System.out.println(authForm);
        // authForm为登录数据表单
        Map<String ,Object> responseMap = new HashMap<>();
        // 用户名或密码为空
        if(authForm.empty()){
            responseMap.put("result", new AuthResponse(null,codeProperty.PARAMETER_ERROR,"参数错误",null));
        }else{
            Optional<User> userOptional = authService.login(authForm.getUsername(), authForm.getPassword(),authForm.getRole());
            // 登录失败
            if (!userOptional.isPresent()){
                responseMap.put("result",new AuthResponse(null,codeProperty.LOGIN_FAILED,"用户名不存在或密码错误",null));
            }else{
                // 登录成功
                User user = userOptional.get();
                //生成token
                responseMap.put("result",new AuthResponse(user,codeProperty.SUCCESS,"登录成功!", commonUtils.generateJwtToken(user)));
            }
        }
        return ResponseEntity.ok(responseMap);
    }


    @PostMapping(value = "/register",produces = "application/json")
    public ResponseEntity<?> processRegister(@RequestBody AuthForm authForm){
        // authForm为登录数据表单
        Map<String ,Object> responseMap = new HashMap<>();
        // 用户名或密码为空
        if(authForm.empty()){
            responseMap.put("result", new AuthResponse(null,codeProperty.PARAMETER_ERROR,"参数错误",null));
        }else{
            boolean registerResult = authService.register(authForm.getUsername(), authForm.getPassword());
            if (!registerResult) responseMap.put("result",new AuthResponse(null,codeProperty.REGISTER_FAILED,"注册失败",null));
            else{
                responseMap.put("result",new AuthResponse(null,codeProperty.SUCCESS,"注册成功",null));
            }
        }
        return ResponseEntity.ok(responseMap);
    }
}
