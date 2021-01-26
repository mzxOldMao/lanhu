package cn.suancloud.lisheng.api.service;

import cn.suancloud.lisheng.api.model.TypeDetail;
import cn.suancloud.lisheng.api.model.form.TypeDetailForm;

import java.util.List;
import java.util.UUID;

public interface TypeDetailService {
    List<TypeDetail> findAllByTypeId(UUID typeId);
    void save(TypeDetailForm typeDetailForm);
    void update(TypeDetailForm typeDetailForm,UUID uuid);
    void deleteById(UUID uuid);
    void deleteByList(String[] uuids);
}
