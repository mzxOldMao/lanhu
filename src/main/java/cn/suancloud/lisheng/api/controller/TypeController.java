package cn.suancloud.lisheng.api.controller;

import cn.suancloud.lisheng.api.exception.IllegalCallException;
import cn.suancloud.lisheng.api.model.Type;
import cn.suancloud.lisheng.api.model.form.TypeForm;
import cn.suancloud.lisheng.api.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/type")
public class TypeController extends BaseController{
    @Autowired
    private TypeService typeService;

    /**
     * 获得所有类型信息，填写typeName时可做查询
     * @param typeName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getAllTypes",produces = "application/json;charset=UTF-8")
    public ResponseEntity getAllType(@RequestParam(value = "typeName",required = false)String typeName,
                                     @RequestParam(value = "pageNum",required = false,defaultValue = "0")String pageNum,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10")String pageSize){
        int num = Integer.parseInt(pageNum);
        int size = Integer.parseInt(pageSize);
        Page<Type> page = typeService.findAll(typeName, num, size);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("data",page);
        map.put("message","请求成功");
        return ResponseEntity.ok(map);
    }

    /**
     * 新增类型
     * @param typeForm
     * @param result
     * @return
     */
    @PostMapping(value = "/saveType",produces = "application/json;charset=UTF-8")
    public ResponseEntity saveType(@RequestBody @Valid TypeForm typeForm, BindingResult result){
        hasErrors(result);
        typeService.save(typeForm);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","添加成功");
        return ResponseEntity.ok(map);
    }

    /**
     * 批量删除类型
     * @param uuids
     * @return
     */
    @DeleteMapping(value = "/deleteTypes",produces = "application/json;charset=UTF-8")
    public ResponseEntity deleteTypes(@RequestBody String[] uuids){
        typeService.deleteByList(uuids);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","成功删除多条记录");
        return ResponseEntity.ok(map);
    }

    /**
     * 删除一条类型信息
     * @param type_id
     * @return
     */
    @DeleteMapping(value = "/deleteType/{type_id}",produces = "application/json;charset=UTF-8")
    public ResponseEntity deleteType(@PathVariable Long type_id){
        typeService.deleteById(type_id);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","删除成功");
        return ResponseEntity.ok(map);
    }

/*    @PutMapping("/updateType/{type_id}")
    public ResponseEntity update(@PathVariable UUID type_id,@RequestBody @Valid TypeForm typeForm,BindingResult result){
        hasErrors(result);
        typeService.updateType(typeForm,type_id);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","修改成功");
        return ResponseEntity.ok(map);
    }*/

    /**
     * 更新类型信息
     * @param type_id
     * @param name
     * @param descr
     * @return
     */
    @PutMapping(value = "/updateType/{type_id}",produces = "application/json;charset=UTF-8")
    public ResponseEntity update1(@PathVariable Long type_id,@RequestParam(value = "name")String name,
                                 @RequestParam(value = "descr")String descr){
        if (name == null && name == "") {
            throw new IllegalCallException("类型名称不能为空");
        }if (descr==null&&descr==""){
            throw new IllegalCallException("类型说明不能为空");
        }
        typeService.updateType1(name,descr,type_id);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","修改成功");
        return ResponseEntity.ok(map);
    }
}
