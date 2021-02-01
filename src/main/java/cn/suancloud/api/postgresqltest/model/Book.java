package cn.suancloud.api.postgresqltest.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "book",schema = "public")
//@org.hibernate.annotations.Table(appliesTo = "book",comment = "fvdsfgsy")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String desc;

    @Column
    private String author;

    @Column
    //@TableField(typeHandler = JacksonTypeHandler.class)
    private String data;
/*
    @Column(name = "publish")
    private String publish;*/

/*    @Column(name = "a_id",nullable = false)
    private UUID aid;*/

 /*   @Column(name = "number")
    private Long number;
*/
    /*@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private List<Company> companies;*/
}
