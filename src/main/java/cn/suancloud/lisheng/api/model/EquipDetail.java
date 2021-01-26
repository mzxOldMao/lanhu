package cn.suancloud.lisheng.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "equip_detail")
public class EquipDetail implements Serializable {
    @Id
    /*@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")*/
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "id1")
    private UUID id1;
    @Column(name = "id2")
    private UUID id2;
    @Column
    private String type;
    @Column(name = "record_code")
    private String recordCode;
    @Column(name = "u_zero")
    private String uZero;
    @Column(name = "upload_time")
    @JsonIgnore
    private Long uploadTime;
    @Column(name = "equip_id")
    private UUID equipId;
    @Transient
    private String upload_date;
    @Column(name = "record_time")
    @JsonIgnore
    private Long recordTime;
    @Transient
    private String record_date;

}
