package cn.suancloud.api.postgresqltest.model;



import java.io.Serializable;

@lombok.Data
public class Data implements Serializable {
    private Long id;
    private String student;
    private String author;
    private String publish;
}
