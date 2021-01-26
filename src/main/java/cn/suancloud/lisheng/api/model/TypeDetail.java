package cn.suancloud.lisheng.api.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "equip_type_detail")
public class TypeDetail implements Serializable {
    @Id
    /*@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")*/
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
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
    @Column(name = "type_id")
    private UUID typeId;
}
