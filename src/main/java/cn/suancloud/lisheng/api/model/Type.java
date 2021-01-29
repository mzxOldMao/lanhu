package cn.suancloud.lisheng.api.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "equip_type")
public class Type implements Serializable {
    @Id
    /*@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String descr;
    @Column
    private String protocol;
    @Column
    private Boolean status;
}
