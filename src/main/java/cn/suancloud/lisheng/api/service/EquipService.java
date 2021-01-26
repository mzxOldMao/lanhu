package cn.suancloud.lisheng.api.service;

import cn.suancloud.lisheng.api.model.Equip;
import cn.suancloud.lisheng.api.model.form.EquipAddForm;
import cn.suancloud.lisheng.api.model.form.EquipForm;
import cn.suancloud.lisheng.api.model.form.EquipUpdateForm;
import cn.suancloud.lisheng.api.utils.PageListGer;
import org.springframework.data.domain.Page;

import java.util.UUID;


public interface EquipService {
    //分页
    Page<Equip> findAll(int pagenum,int pagesize);
    //分页加查询
    PageListGer findByQuery(EquipForm equipForm);
    //新增设备
    void save(EquipAddForm equipAddForm,boolean flag);
    //删除一个设备
    void deleteEquip(UUID uuid);
    //批量删除设备
    void deleteByList(String[] uuids);
    //修改设备信息
    void updateEquip(EquipUpdateForm equipUpdateForm);
}
