package cn.suancloud.lisheng.api.controller;

import cn.suancloud.lisheng.api.model.Type;
import cn.suancloud.lisheng.api.model.TypeDetail;
import cn.suancloud.lisheng.api.model.form.TypeDetailForm;
import cn.suancloud.lisheng.api.service.TypeDetailService;
import cn.suancloud.lisheng.api.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/typedetail")
public class TypeDetailController extends BaseController{
    @Autowired
    private TypeDetailService typeDetailService;
    @Autowired
    private TypeService typeService;

    /**
     * 根据类型ID获得类型详情
     * @param typeId
     * @return
     */
    @GetMapping(value = "/getType/{type_id}",produces = "application/json;charset=UTF-8")
    public ResponseEntity getType(@PathVariable("type_id")UUID typeId){
        Type type = typeService.findById(typeId);
        List<TypeDetail> typeDetailList = typeDetailService.findAllByTypeId(typeId);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("type_data",type);
        map.put("type_detail_data",typeDetailList);
        map.put("message","请求成功");
        return ResponseEntity.ok(map);
    }

    /**
     * 新增一条类型详情
     * @param typeDetailForm
     * @param result
     * @return
     */
    @PostMapping(value = "/saveTypeDetail",produces = "application/json;charset=UTF-8")///{typeId}
    public ResponseEntity save(@RequestBody @Valid TypeDetailForm typeDetailForm, BindingResult result){
        hasErrors(result);
        typeDetailService.save(typeDetailForm);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","添加成功");
        return ResponseEntity.ok(map);
    }

    /**
     * 更新一条类型详情
     * @param typeDetailForm
     * @param type_detail_id
     * @param result
     * @return
     */
    @PutMapping(value = "/update/{type_detail_id}",produces = "application/json;charset=UTF-8")
    public ResponseEntity updateTypeDetail(@RequestBody @Valid TypeDetailForm typeDetailForm,@PathVariable UUID type_detail_id,BindingResult result){
        hasErrors(result);
        typeDetailService.update(typeDetailForm,type_detail_id);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","修改成功");
        return ResponseEntity.ok(map);
    }

    /**
     * 删除一条详情
     * @param type_detail_id
     * @return
     */
    @DeleteMapping(value = "/deleteTypeDetail/{type_detail_id}",produces = "application/json;charset=UTF-8")
    public ResponseEntity deleteTypeDetail(@PathVariable UUID type_detail_id){
        typeDetailService.deleteById(type_detail_id);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","删除成功!");
        return ResponseEntity.ok(map);
    }

    /**
     * 批量删除详情
     * @param uuids
     * @return
     */
    @DeleteMapping(value = "/deleteTypeDetails",produces = "application/json;charset=UTF-8")
    public ResponseEntity deletes(@RequestBody String[] uuids){
        typeDetailService.deleteByList(uuids);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","成功删除多个ID");
        return ResponseEntity.ok(map);
    }
}
