package cn.suancloud.lisheng.api.model.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class EquipQuery implements Serializable {
    private String typeName;
    private String equipName;
    private String protocol;
    private String clientID;
    private String userName;
    private String password;
    private Long add_time;
    private Long equipId;
    private Boolean status;
    private String last_date;
    private Long last_time;
}
