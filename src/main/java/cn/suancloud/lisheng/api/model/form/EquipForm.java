package cn.suancloud.lisheng.api.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class EquipForm extends DateForm {
    private UUID typeId;
    private String equipName;
}
