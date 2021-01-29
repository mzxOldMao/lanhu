package cn.suancloud.lisheng.api.model.form;

import lombok.Data;


@Data
public class EquipForm extends DateForm {
    private Long typeId;
    private String equipName;
}
