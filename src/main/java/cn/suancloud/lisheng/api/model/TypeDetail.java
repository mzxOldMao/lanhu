package cn.suancloud.lisheng.api.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "equip_type_detail")
public class TypeDetail implements Serializable {
    @Id
    /*@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String address;
    @Column(name = "data_type")
    private String dataType;
    @Column(name = "parse_type")
    private String parseType;
    @Column
    private Integer multi;
    @Column(name = "show_id")
    private String showId;
    @Column
    private String remark;
    @Column(name = "equip_type_id")
    private Long typeId;
}
