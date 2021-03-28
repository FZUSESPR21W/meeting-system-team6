package com.eepractice.demo.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthForm {
    private String username;
    private String password;
    private String role;
    public boolean empty(){
        return this.username.trim().equals("") || this.password.trim().equals("") || this.role.equals("");
    }
}
