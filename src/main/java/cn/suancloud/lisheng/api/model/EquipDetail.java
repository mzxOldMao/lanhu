package cn.suancloud.lisheng.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "equip_detail")
public class EquipDetail implements Serializable {
    @Id
    /*@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id1")
    private Long id1;
    @Column(name = "id2")
    private Long id2;
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
    private Long equipId;
    @Transient
    private String upload_date;
    @Column(name = "record_time")
    @JsonIgnore
    private Long recordTime;
    @Transient
    private String record_date;

    //电压
    @Column(name = "tgUa")
    private Float tgUa;
    @Column(name = "tgUb")
    private Float tgUb;
    @Column(name = "tgUc")
    private Float tgUc;
    @Column(name = "tgUab")
    private Float tgUab;
    @Column(name = "tgUbc")
    private Float tgUbc;
    @Column(name = "tgUac")
    private Float tgUac;
    //电流
    @Column(name = "tgIa")
    private Float tgIa;
    @Column(name = "tgIb")
    private Float tgIb;
    @Column(name = "tgIc")
    private Float tgIc;
    //功率
    @Column(name = "tgPa")
    private Float tgPa;
    @Column(name = "tgPb")
    private Float tgPb;
    @Column(name = "tgPc")
    private Float tgPc;
    @Column(name = "tgP")
    private Float tgP;
    @Column(name = "tgQa")
    private Float tgQa;
    @Column(name = "tgQb")
    private Float tgQb;
    @Column(name = "tgQc")
    private Float tgQc;
    @Column(name = "tgQ")
    private Float tgQ;
    @Column(name = "tgSa")
    private Float tgSa;
    @Column(name = "tgSb")
    private Float tgSb;
    @Column(name = "tgSc")
    private Float tgSc;
    @Column(name = "tgSs")
    private Float tgSs;
    //功率因数
    @Column(name = "tgPFa")
    private Float tgPFa;
    @Column(name = "tgPFb")
    private Float tgPFb;
    @Column(name = "tgPFc")
    private Float tgPFc;
    @Column(name = "tgPF")
    private Float tgPF;
    //频率
    @Column(name = "tgHz")
    private Float tgHz;
    //电能示值
    @Column(name = "tgSupWh")
    private Float tgSupWh;
    @Column(name = "tgVarh1")
    private Float tgVarh1;
    //尖时段有功总电能
    @Column(name = "tgshaTp")
    private Float tgshaTp;
    //峰时段有功总电能
    @Column(name = "tgpea")
    private Float tgpea;
    //平时段有功总电能
    @Column(name = "tgoffp")
    private Float tgoffp;
    //谷时段有功总电能
    @Column(name = "tgsho")
    private Float tgsho;
}
