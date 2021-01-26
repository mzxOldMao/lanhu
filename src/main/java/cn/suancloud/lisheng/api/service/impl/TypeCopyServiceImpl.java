package cn.suancloud.lisheng.api.service.impl;

import cn.suancloud.lisheng.api.dao.TypeCopyDao;
import cn.suancloud.lisheng.api.model.copy.EquipCopy;
import cn.suancloud.lisheng.api.model.copy.TypeCopy;
import cn.suancloud.lisheng.api.model.form.EquipAddForm;
import cn.suancloud.lisheng.api.service.TypeCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCopyServiceImpl implements TypeCopyService {
    @Autowired
    private TypeCopyDao typeCopyDao;

    @Override
    public List<TypeCopy> findAll() {
        return typeCopyDao.findAll();
    }


}
