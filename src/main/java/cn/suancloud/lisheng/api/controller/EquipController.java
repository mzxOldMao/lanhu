package cn.suancloud.lisheng.api.controller;

import cn.suancloud.lisheng.api.model.Equip;
import cn.suancloud.lisheng.api.model.Type;
import cn.suancloud.lisheng.api.model.copy.EquipCopy;
import cn.suancloud.lisheng.api.model.copy.TypeCopy;
import cn.suancloud.lisheng.api.model.form.EquipAddForm;
import cn.suancloud.lisheng.api.model.form.EquipForm;
import cn.suancloud.lisheng.api.model.form.EquipUpdateForm;
import cn.suancloud.lisheng.api.service.EquipService;
import cn.suancloud.lisheng.api.service.TypeCopyService;
import cn.suancloud.lisheng.api.service.TypeService;
import cn.suancloud.lisheng.api.utils.PageListGer;
import cn.suancloud.lisheng.api.utils.TimeStampUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/equip")
public class EquipController extends BaseController{
    @Autowired
    private EquipService equipService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TypeCopyService typeCopyService;
    /**
     * 分页查询
     */
    @GetMapping(value = "/paging",produces = "application/json;charset=UTF-8")
    public ResponseEntity paging(@RequestParam(value = "pageNum",required = false,defaultValue = "0")String pageNum,
                                 @RequestParam(value = "pageSize",required = false,defaultValue = "10")String pageSize){
        int pagenum = Integer.parseInt(pageNum);
        int pagesize = Integer.parseInt(pageSize);
        Page<Equip> page = equipService.findAll(pagenum, pagesize);
        for (Equip equip:page){
            if (equip.getLastTime()!=null)
            equip.setDate(TimeStampUtils.longForString(equip.getLastTime()));
        }
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("data",page);
        map.put("message","请求成功");
        return ResponseEntity.ok(map);
    }

    /**
     * 根据条件查询设备信息
     * @param
     * @return
     */
    @GetMapping(value = "/like",produces = "application/json;charset=UTF-8")
    public ResponseEntity query(EquipForm equipForm){
        PageListGer pageListGer = equipService.findByQuery(equipForm);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("data",pageListGer);
        map.put("message","请求成功");
        return ResponseEntity.ok(map);
    }

    /**
     * 获得所有类型以及对应的设备信息
     * @return
     */
    @GetMapping(value = "/findEquipAndTypes",produces = "application/json;charset=UTF-8")
    public ResponseEntity findAllType(){
        List<TypeCopy> all = typeCopyService.findAll();
        for (TypeCopy typeCopy:all){
            for (EquipCopy equipCopy:typeCopy.getEquipCopies()){
                if (equipCopy.getLastTime()!=null){
                    equipCopy.setDate(TimeStampUtils.longForString(equipCopy.getLastTime()));
                }
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("data",all);
        map.put("message","请求成功");
        return ResponseEntity.ok(map);
    }

    /**
     * 新增一个设备
     *
     * @return
     */
    @PostMapping(value = "/saveEquip", produces = "application/json;charset=UTF-8")
    public ResponseEntity saveEquip(@RequestBody @Valid EquipAddForm equipAddForm, BindingResult result) {
        hasErrors(result);
        Type type = typeService.findById(equipAddForm.getTypeId());
        if (type.getProtocol().equals("MQTT")){
            equipService.save(equipAddForm,true);
        }else {
            equipService.save(equipAddForm,false);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "新增设备成功");
        return ResponseEntity.ok(map);
    }

    /**
     * 删除一个设备
     * @param equip_id
     * @return
     */
    @DeleteMapping(value = "/deleteEquip/{equip_id}",produces = "application/json;charset=UTF-8")
    public ResponseEntity deleteEquip(@PathVariable UUID equip_id){
        equipService.deleteEquip(equip_id);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","删除设备成功");
        return ResponseEntity.ok(map);
    }

    /**
     * 批量删除设备
     */
    @DeleteMapping(value = "/deleteEquips", produces = "application/json;charset=UTF-8")
    public ResponseEntity deleteEquips(@RequestBody String[] uuids) {
        equipService.deleteByList(uuids);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("message", "批量删除设备成功");
        return ResponseEntity.ok(map);
    }
    /**
     * 修改一个设备
     */
    @PutMapping(value = "/updateEquip",produces = "application/json;charset=UTF-8")
    public ResponseEntity updateEquip(@RequestBody @Valid EquipUpdateForm equipUpdateForm, BindingResult result){
        hasErrors(result);
        equipService.updateEquip(equipUpdateForm);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","修改设备成功");
        return ResponseEntity.ok(map);
    }
}
