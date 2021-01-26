package cn.suancloud.lisheng.api.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
public class EquipUpdateForm implements Serializable {
    @NotNull(message = "设备ID不能为空")
    private UUID equipID;
    @NotBlank(message = "设备名称不能为空")
    private String equipName;
    private String ClientID;
    private String username;
    private String password;
}
