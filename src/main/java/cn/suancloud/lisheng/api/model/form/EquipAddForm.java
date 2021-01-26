package cn.suancloud.lisheng.api.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
public class EquipAddForm implements Serializable {
    @NotBlank(message = "设备名称不能为空")
    private String equipName;
    @NotNull(message = "设备类型名称不能为空")
    private UUID typeId;
    //@NotBlank(message = "ClientID不能为空")
    private String ClientID;
    //@NotBlank(message = "username不能为空")
    private String username;
    //@NotBlank(message = "password不能为空")
    private String password;
}
