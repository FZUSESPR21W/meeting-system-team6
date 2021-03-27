package com.eepractice.demo.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class NotificationForm {
    Integer id;
    String title;
    String content;
    String belongSubForum;
    String belongChairMan;
}
