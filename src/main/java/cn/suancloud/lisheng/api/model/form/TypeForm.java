package cn.suancloud.lisheng.api.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Data
public class TypeForm implements Serializable {
    //private UUID id;
    @NotBlank(message = "类型名称不能为空")
    private String name;
    @NotBlank(message = "请描述类型的说明")
    private String descr;
    private String protocol;

    public TypeForm(){}
    public TypeForm(String name,String descr,String protocol){
        this.name = name;
        this.descr = descr;
        this.protocol = protocol;
    }
}
