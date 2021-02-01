package cn.suancloud.api.postgresqltest.controller;

import cn.suancloud.api.postgresqltest.model.Company;
import cn.suancloud.api.postgresqltest.service.CompanyService;
import cn.suancloud.api.postgresqltest.utils.TimeStampUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate1;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        List<Company> all = companyService.findAll1();
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("data",all);
        map.put("message","请求成功");
        return ResponseEntity.ok(map);
    }

    @PostMapping("/save")
    public ResponseEntity saveCompany(@RequestBody Company company){
        Map<String,Object> map = new HashMap<>();
        company.setCreateAt(System.currentTimeMillis());
        System.out.println(company);
        companyService.save(company);
        Company company1 = companyService.findByName(company.getName());
        company1.setDate(TimeStampUtils.longForString(company1.getCreateAt()));
        map.put("code",200);
        map.put("data",company1);
        map.put("message","请求成功");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/delete")
    public ResponseEntity deleteCompany(@RequestParam Long id){
        Map<String,Object> map = new HashMap<>();
        companyService.delete(id);
        List<Company> all1 = companyService.findAll1();
        map.put("code",200);
        map.put("data",all1);
        map.put("message","请求成功");
        return ResponseEntity.ok(map);
    }

    @PostMapping("/update")
    public ResponseEntity updateCompany(@RequestBody Company company){
        Map<String,Object> map = new HashMap<>();
        company.setCreateAt(System.currentTimeMillis());
        companyService.update(company);
        Company company1 = companyService.findById(company.getId());
        company1.setDate(TimeStampUtils.longForString(company1.getCreateAt()));
        map.put("code",200);
        map.put("data",company1);
        map.put("message","请求成功");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/page")
    public ResponseEntity paging(@RequestParam(value = "pageNum",required = false,defaultValue = "0")String pageNum,
                                 @RequestParam(value = "pageSize",required = false,defaultValue = "2")String pageSize){
        int num = Integer.parseInt(pageNum);
        int size = Integer.parseInt(pageSize);
        Page<Company> page = companyService.findAll2(PageRequest.of(num, size, Sort.Direction.ASC, "id"));
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("data",page);
        map.put("message","请求成功");
        return ResponseEntity.ok(map);
    }
    @GetMapping("/page1")
    public ResponseEntity paging1(@RequestParam(value = "pageNum",required = false,defaultValue = "0")String pageNum,
                                 @RequestParam(value = "pageSize",required = false,defaultValue = "2")String pageSize){
        int num = Integer.parseInt(pageNum);
        int size = Integer.parseInt(pageSize);
        String sql = "select count(id) from company;";
        Map<String,Object> objectMap = new HashMap<>();
        int rows = jdbcTemplate1.queryForObject(sql, objectMap, Integer.class);
        int pages = 0;
        if (rows%size==0){
            pages = rows/size;
        }else {
            pages = rows/size + 1;
        }
        String sqllimit = "select * from company limit ";
        if (num<1){
            sqllimit +=size+";";
        }else {
            sqllimit += size+" offset "+(num*size)+";";
        }
        System.out.println(sqllimit);
        List<Company> query = jdbcTemplate.query(sqllimit, new BeanPropertyRowMapper<Company>(Company.class));
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("data",query);
        map.put("message","请求成功");
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/deletes")
    public ResponseEntity delete(@RequestBody String[] uuids){
        //String[] uuids = companyTest.getUuids();
        System.out.println(uuids.toString());
        for (int i = 0; i < uuids.length; i++) {
            System.out.println(uuids[i]);
        }
        companyService.deleteByList(uuids);
        return ResponseEntity.ok("删除成功");
    }
}
