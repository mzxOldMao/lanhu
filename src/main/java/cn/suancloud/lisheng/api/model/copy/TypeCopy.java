package cn.suancloud.lisheng.api.model.copy;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "equip_type")
public class TypeCopy implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    private UUID id;
    @Column
    private String name;
    @Column
    private String descr;
    @Column
    private String protocol;
    @Column
    private Boolean status;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "equip_type_id")
    private List<EquipCopy> equipCopies;
}
