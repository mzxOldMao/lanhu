package cn.suancloud.lisheng.api.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PageListGer<T> {

	private int number;   //当前页
    private int totalElements;   //总行数
    private int totalPages;    //总页数
    private List<T> content=new ArrayList<>();
}
