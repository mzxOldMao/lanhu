package cn.suancloud.lisheng.api.service.impl;

import cn.suancloud.lisheng.api.dao.TypeDetailDao;
import cn.suancloud.lisheng.api.model.TypeDetail;
import cn.suancloud.lisheng.api.model.form.TypeDetailForm;
import cn.suancloud.lisheng.api.service.TypeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TypeDetailServiceImpl implements TypeDetailService {
    @Autowired
    private TypeDetailDao typeDetailDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TypeDetail> findAllByTypeId(UUID typeId) {
        String sql = "select id,address,data_type,parse_type,multi,show_id,remark,type_id from equip_type_detail where type_id = '"+typeId+"';";
        List<TypeDetail> typeDetailList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<TypeDetail>(TypeDetail.class));
        return typeDetailList;
    }

    @Override
    public void save(TypeDetailForm typeDetailForm) {
        TypeDetail typeDetail = new TypeDetail();
        if (typeDetailForm.getAddress()!=null){
            typeDetail.setAddress(typeDetailForm.getAddress());
        }
        if (typeDetailForm.getDataType()!=null){
            typeDetail.setDataType(typeDetailForm.getDataType());
        }
        if (typeDetailForm.getParseType()!=null){
            typeDetail.setParseType(typeDetailForm.getParseType());
        }
        if (typeDetailForm.getMulti()!=null){
            typeDetail.setMulti(typeDetailForm.getMulti());
        }
        if (typeDetailForm.getShowId()!=null){
            typeDetail.setShowId(typeDetailForm.getShowId());
        }
        if (typeDetailForm.getRemark()!=null){
            typeDetail.setRemark(typeDetailForm.getRemark());
        }
        if (typeDetailForm.getTypeId()!=null){
            typeDetail.setTypeId(typeDetailForm.getTypeId());
        }
        typeDetailDao.save(typeDetail);
    }

    @Override
    public void update(TypeDetailForm typeDetailForm,UUID uuid) {
        TypeDetail typeDetail = new TypeDetail();
        if (typeDetailForm.getAddress()!=null){
            typeDetail.setAddress(typeDetailForm.getAddress());
        }
        if (typeDetailForm.getDataType()!=null){
            typeDetail.setDataType(typeDetailForm.getDataType());
        }
        if (typeDetailForm.getParseType()!=null){
            typeDetail.setParseType(typeDetailForm.getParseType());
        }
        if (typeDetailForm.getMulti()!=null){
            typeDetail.setMulti(typeDetailForm.getMulti());
        }
        if (typeDetailForm.getShowId()!=null){
            typeDetail.setShowId(typeDetailForm.getShowId());
        }
        if (typeDetailForm.getRemark()!=null){
            typeDetail.setRemark(typeDetailForm.getRemark());
        }
        if (typeDetailForm.getTypeId()!=null){
            typeDetail.setTypeId(typeDetailForm.getTypeId());
        }
        typeDetail.setId(uuid);
        typeDetailDao.save(typeDetail);
    }

    @Override
    public void deleteById(UUID uuid) {
        typeDetailDao.deleteById(uuid);
    }

    @Override
    public void deleteByList(String[] uuids) {
        //String sql = "delete from equip_type where id in (";
        String sql = "delete from equip_type_detail where id in (";
        for (int i = 0; i < uuids.length; i++) {
            if (i==(uuids.length-1)){
                sql+="'"+uuids[i]+"');";
                break;
            }
            sql+="'"+uuids[i]+"',";
        }
        //System.out.println(sql);
        jdbcTemplate.update(sql);
    }
}
