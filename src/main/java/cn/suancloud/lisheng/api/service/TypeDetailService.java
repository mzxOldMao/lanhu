package cn.suancloud.lisheng.api.service;

import cn.suancloud.lisheng.api.model.TypeDetail;
import cn.suancloud.lisheng.api.model.form.TypeDetailForm;

import java.util.List;

public interface TypeDetailService {
    List<TypeDetail> findAllByTypeId(Long typeId);
    void save(TypeDetailForm typeDetailForm);
    void update(TypeDetailForm typeDetailForm,Long uuid);
    void deleteById(Long uuid);
    void deleteByList(String[] uuids);
}
