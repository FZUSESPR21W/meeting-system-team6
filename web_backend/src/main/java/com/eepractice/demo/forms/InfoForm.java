package com.eepractice.demo.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class InfoForm {
    private Integer id;
    private String title;
    private String chairman;
    private String secretary;
    private Integer memberNum;
    private String startDate;
}
