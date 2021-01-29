package cn.suancloud.lisheng.api.service;

import cn.suancloud.lisheng.api.model.EquipDetail;
import cn.suancloud.lisheng.api.model.form.DateForm;
import org.springframework.data.domain.Page;




public interface EquipDetailService {
    Page<EquipDetail> findAll(Long id, int num, int size);
    Page<EquipDetail> findAllByTimes(DateForm dateForm,Long id);
}
