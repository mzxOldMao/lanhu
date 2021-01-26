package cn.suancloud.lisheng.api.model.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageForm implements Serializable {
    private int pageNum = 0;
    private int pageSize = 10;
    public PageForm(){
    }
    public PageForm(int pageNum,int pageSize){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
