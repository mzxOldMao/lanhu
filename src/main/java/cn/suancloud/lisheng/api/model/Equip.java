package cn.suancloud.lisheng.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "equip")
public class Equip implements Serializable {
    @Id
    /*@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")*/
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String name;
    @Column
    private Boolean status;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "login_time")
    private Long loginTime;
    @Column(name = "last_time")
    @JsonIgnore
    //@JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Long lastTime;
    @Transient
    private String date;
    @Column(name = "add_time")
    private Long addTime;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="equip_type_id")
    private Type type;
}
