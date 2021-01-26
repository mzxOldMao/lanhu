package cn.suancloud.lisheng.api.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
public class TypeDetailForm implements Serializable {
    @NotBlank(message = "地址不能为空")
    private String address;
    @NotBlank(message = "请选择数据类型")
    private String dataType;
    @NotBlank(message = "请选择解析类型")
    private String parseType;
    @NotNull(message = "请填写倍速")
    private Integer multi;
    @NotBlank(message = "请填写展示ID")
    private String showId;
    private String remark;
    @NotNull(message = "请填写类型ID")
    private UUID typeId;
}
