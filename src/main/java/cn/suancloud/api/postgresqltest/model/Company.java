package cn.suancloud.api.postgresqltest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "company")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private String address;

    @Column
    private Integer salary;

    @Column(name = "createAt")
    @JsonIgnore
    private Long createAt;

    @Transient
    //@JsonIgnore
    private String date;

    /*@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="book_id")
    private Book book;*/

    /*@ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name="book_id")
    private Book book;*/
}
