package cn.suancloud.lisheng.api.service;

import cn.suancloud.lisheng.api.model.Type;
import cn.suancloud.lisheng.api.model.form.TypeForm;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface TypeService {
    //分页查询  模糊查询
    Page<Type> findAll(String typeName,int num,int size);

    //新增类型
    void save(TypeForm typeForm);
/*    //修改类型
    void update(TypeForm typeForm);*/
    //根据type_id查询type
    Type findById(UUID uuid);

    //删除
    void deleteByList(String[] uuids);
    void deleteById(UUID uuid);
    //修改
    void updateType(TypeForm typeForm,UUID uuid);
    void updateType1(String name,String descr,UUID uuid);
}
