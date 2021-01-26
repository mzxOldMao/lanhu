package cn.suancloud.lisheng.api.model.form;

import lombok.Data;

@Data
public class DateForm extends PageForm{
    private String startTime;
    private String endTime;
}
