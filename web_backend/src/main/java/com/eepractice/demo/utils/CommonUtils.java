package com.eepractice.demo.utils;

import com.eepractice.demo.bean.SubForum;
import com.eepractice.demo.bean.User;
import com.eepractice.demo.forms.InfoForm;
import com.eepractice.demo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class CommonUtils {

    @Autowired
    UserRepository userRepository;


    private static final String SECRET_KEY = "secret-key";


    /**
     * 生成token，token中包含用户的id和用户名
     * @param user
     * @return
     */
    public String generateJwtToken(User user){
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(user.getId() + "")
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY);
        return jwtBuilder.compact();
    }

    /**
     * 验证token，验证成功，返回User，失败，返回null
     * @param token
     * @return
     */
    public User parseJwtToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        // 从解析结果中获得键值对信息
        Integer userId = Integer.valueOf(claims.getId());
        // 从数据库中查找User
        return userRepository.findById(userId).orElse(null);
    }



    /**
     *  SubForum对象包装成InfoForm对象
     */

    public InfoForm transformSubForum(SubForum subForum){

        InfoForm infoForm = new InfoForm();
        // 分论坛的主席id
        Integer chairManId = subForum.getChairManId();
        Optional<User> chairmanUser = userRepository.findById(chairManId);
        // 分论坛的秘书id
        Integer secretaryId = subForum.getSecretaryId();
        Optional<User> secretaryUser = userRepository.findById(secretaryId);
        infoForm.setId(subForum.getId())
                .setTitle(subForum.getTitle())
                .setChairman(chairmanUser.get().getUsername())
                .setSecretary(secretaryUser.get().getUsername())
                .setMemberNum(subForum.getMembers().size())
                .setStartDate(Date2Str(subForum.getDate()));
        return infoForm;
    }

    /**
     * Date类型对象转化为String
     * @param date
     * @return
     */
    public String Date2Str(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        String s = format.format(date);
        return s;
    }

    /**
     * String转为Date对象
     * String : 2000.04.16 15:22
     */
    public Date Str2Date(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        return format.parse(date);
    }

    /**
     *  List<User> ----> String
     */
    public String parseUserList(List<User> userList,Integer cid,Integer sid){
        StringBuilder stringBuilder = new StringBuilder();

        if (userList.size() == 0){
            stringBuilder.append(cid).append(",").append(sid);
        }else{
            stringBuilder.append(cid).append(",").append(sid).append(",");
        }

        int total = userList.size();
        int i = 0;
        for (User user : userList) {
            if (i != total){
                stringBuilder.append(user.getId()).append(",");
            }
            else{
                stringBuilder.append(user.getId());
            }
            i++;
        }
        return stringBuilder.toString();
    }
}
