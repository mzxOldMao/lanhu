package cn.suancloud.lisheng.api.controller;

import cn.suancloud.lisheng.api.model.EquipDetail;
import cn.suancloud.lisheng.api.model.form.DateForm;
import cn.suancloud.lisheng.api.service.EquipDetailService;
import cn.suancloud.lisheng.api.utils.TimeStampUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/equipdetail")
public class EquipDetailController {
    @Autowired
    private EquipDetailService equipDetailService;
    /**
     * 设备详情（历史数据）
     */
    @GetMapping(value = "/getEquip/{equip_id}",produces = "application/json;charset=UTF-8")
    public ResponseEntity getequip(@PathVariable("equip_id") Long uuid,
                                   @RequestParam(value = "pageNum",required = false,defaultValue = "0")String pageNum,
                                   @RequestParam(value = "pageSize",required = false,defaultValue = "10")String pageSize){
        int num = Integer.parseInt(pageNum);
        int page = Integer.parseInt(pageSize);
        Page<EquipDetail> all = equipDetailService.findAll(uuid, num, page);
        for(EquipDetail equipDetail:all){
            equipDetail.setUpload_date(TimeStampUtils.longForString(equipDetail.getUploadTime()));
            equipDetail.setRecord_date(TimeStampUtils.longForString(equipDetail.getRecordTime()));
        }
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("data",all);
        map.put("message","获取成功");
        return ResponseEntity.ok(map);
    }

    /**
     * 根据设备历史数据中的时间段查询
     */
    @GetMapping(value = "/getEquipBytimes/{equip_id}",produces = "application/json;charset=UTF-8")
    public ResponseEntity findByTime(DateForm dateForm,
                                     @PathVariable("equip_id") Long id){
        Page<EquipDetail> page = equipDetailService.findAllByTimes(dateForm,id);
        for (EquipDetail equipDetail:page){
            equipDetail.setUpload_date(TimeStampUtils.longForString(equipDetail.getUploadTime()));
            equipDetail.setRecord_date(TimeStampUtils.longForString(equipDetail.getRecordTime()));
        }
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("data",page);
        map.put("message","获取成功");
        return ResponseEntity.ok(map);
    }
}
