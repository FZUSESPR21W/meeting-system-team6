package com.eepractice.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private Integer subForumId;

    private String unreadUsers;


    public void handleMsg(int id) {
        String temp="";
        String[]ss = unreadUsers.split(",");
        if (ss.length == 1){
            unreadUsers = unreadUsers.replace(id+"","");
        }else{
            for(int i=0;i<ss.length;i++)
            {
                if(!Integer.valueOf(id).toString().equals(ss[i])) {
                    temp += ss[i];
                    temp+=",";
                }
            }
            temp=temp.substring(0,temp.length()-1);
            unreadUsers=temp;
        }

    }
}
