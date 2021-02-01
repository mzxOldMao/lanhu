package cn.suancloud.api.postgresqltest.controller;

import cn.suancloud.api.postgresqltest.model.Book;
import cn.suancloud.api.postgresqltest.model.Data;
import cn.suancloud.api.postgresqltest.service.BookService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public ResponseEntity findAll(){
        Map<String,Object> map = new HashMap<>();
        List<Book> all = bookService.findAll1();
        List<Data> data = new ArrayList<>();
        for (Book book:all){
            JSONObject object = JSONObject.parseObject(book.getData());
            Data data1 = JSONObject.toJavaObject(object,Data.class);
            //JSONObject object1 = object.getJSONObject("name");
            data.add(data1);
        }
        map.put("code",200);
        map.put("data",all);
        map.put("datalist",data);
        map.put("message","请求成功");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/like")
    public ResponseEntity likeandpage(@RequestParam(value = "likeName")String name,
                                      @RequestParam(value = "pageNum",required = false,defaultValue = "0")String pageNum,
                                      @RequestParam(value = "pageSize",required = false,defaultValue = "2")String pageSize){
        int num = Integer.parseInt(pageNum);
        int size = Integer.parseInt(pageSize);
        Specification<Book> specification = new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> name1 = root.get("name");
                Predicate predicate = null;
                if (!ObjectUtils.isEmpty(name1)){
                    predicate = criteriaBuilder.like(name1.as(String.class),"%"+name+"%");
                }
                return predicate;
            }
        };
        Page<Book> page = bookService.findAll(specification, PageRequest.of(num, size, Sort.Direction.ASC, "id"));
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("data",page);
        map.put("message","请求成功");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/id")
    public ResponseEntity findById(@RequestParam Long id){
        Book book = bookService.findById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("data",book);
        map.put("message","请求成功");
        return ResponseEntity.ok(map);
    }
}
