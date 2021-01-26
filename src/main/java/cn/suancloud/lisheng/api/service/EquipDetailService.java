package cn.suancloud.lisheng.api.service;

import cn.suancloud.lisheng.api.model.EquipDetail;
import cn.suancloud.lisheng.api.model.form.DateForm;
import org.springframework.data.domain.Page;


import java.util.UUID;


public interface EquipDetailService {
    Page<EquipDetail> findAll(UUID uuid, int num, int size);
    Page<EquipDetail> findAllByTimes(DateForm dateForm,UUID uuid);
}
