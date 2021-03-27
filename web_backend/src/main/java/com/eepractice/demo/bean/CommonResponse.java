package com.eepractice.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private int code;
    private String msg;
    private Object data;

    public static ResponseEntity<?> getMap(int code, String msg, Object data){
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("data",new CommonResponse(code,msg,data));
        return ResponseEntity.ok(responseMap);
    }
}
