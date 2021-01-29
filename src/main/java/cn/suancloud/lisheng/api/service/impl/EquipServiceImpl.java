package cn.suancloud.lisheng.api.service.impl;

import cn.suancloud.lisheng.api.dao.EquipCopyDao;
import cn.suancloud.lisheng.api.dao.EquipDao;
import cn.suancloud.lisheng.api.exception.FormException;
import cn.suancloud.lisheng.api.model.Equip;
import cn.suancloud.lisheng.api.model.copy.EquipCopy;
import cn.suancloud.lisheng.api.model.form.EquipAddForm;
import cn.suancloud.lisheng.api.model.form.EquipForm;
import cn.suancloud.lisheng.api.model.form.EquipUpdateForm;
import cn.suancloud.lisheng.api.model.query.EquipQuery;
import cn.suancloud.lisheng.api.service.EquipService;
import cn.suancloud.lisheng.api.utils.PageListGer;
import cn.suancloud.lisheng.api.utils.TimeStampUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EquipServiceImpl implements EquipService {
    @Autowired
    private EquipDao equipDao;
    @Autowired
    private EquipCopyDao equipCopyDao;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<Equip> findAll(int pagenum, int pagesize) {
        return equipDao.findAll(PageRequest.of(pagenum,pagesize, Sort.Direction.ASC,"id"));
    }

    @Override
    public PageListGer findByQuery(EquipForm equipForm) {
        String sql = "select et.name as typeName,et.protocol as protocol,e.name as equipName,e.id as equipId,e.status,e.add_time,e.last_time,e.client_id as clientID,e.username as userName,e.password as password from equip e inner join equip_type et on e.equip_type_id = et.id ";
        //and et.name = '类型名称B' and e.name like '%设备%';
        if (equipForm.getTypeId()!=null){
            sql+="and et.id = '"+equipForm.getTypeId()+"' ";
        }
        if (equipForm.getEquipName()!=null && equipForm.getEquipName()!=""){
            sql+="and e.name like '%"+equipForm.getEquipName()+"%' ";
        }
        /*if (equipForm.getStartTime()!=null && equipForm.getStartTime()!="" && equipForm.getEndTime()!=null && equipForm.getEndTime()!=""){
            sql+="and e.id in (select ed.equip_id from equip_detail ed where ed.equip_id = e.id and ed.upload_time > "+TimeStampUtils.stringForlong1(equipForm.getStartTime())+" and ed.upload_time <= "+TimeStampUtils.stringForlong1(equipForm.getEndTime())+") ";
        }*/
        if (!ObjectUtils.isEmpty(equipForm.getStartTime()) && !ObjectUtils.isEmpty(equipForm.getEndTime())){
            sql+="and e.id in (select ed.equip_id from equip_detail ed where ed.equip_id = e.id and ed.upload_time > "+TimeStampUtils.stringForlong1(equipForm.getStartTime())+" and ed.upload_time <= "+TimeStampUtils.stringForlong1(equipForm.getEndTime())+") ";
        }
        String sqlName = "select count(equipId) from ("+sql+") s;";
        //System.out.println(sqlName);
        Map<String,Object> map = new HashMap<>();
        int rows = namedParameterJdbcTemplate.queryForObject(sqlName,map,Integer.class);
        int pages = 0;
        int num = equipForm.getPageNum();
        int size = equipForm.getPageSize();
        if (rows % size ==0){
            pages = rows/size;
        }else{
            pages = rows/size + 1;
        }
        if (num<1){
            sql+="limit "+size+";";
        }else {
            sql+="limit "+size+" offset "+(num*size)+";";
        }
        //System.out.println(sql);
        List<EquipQuery> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<EquipQuery>(EquipQuery.class));
        for (EquipQuery equipQuery:query){
            if (equipQuery.getLast_time()!=null){
                equipQuery.setLast_date(TimeStampUtils.longForString(equipQuery.getLast_time()));
            }
        }
        PageListGer pageListGer = new PageListGer<>();
        pageListGer.setContent(query);
        pageListGer.setTotalPages(pages);
        pageListGer.setTotalElements(rows);
        pageListGer.setNumber(num);
        return pageListGer;
    }

    @Override
    public void save(EquipAddForm equipAddForm,boolean flag) { //flag为true时，说明新增的协议类型为MQTT
        EquipCopy equip = new EquipCopy();
        equip.setName(equipAddForm.getEquipName());
        equip.setTypeID(equipAddForm.getTypeId());
        if (flag==true){
            if (equipAddForm.getClientID()!=null && equipAddForm.getClientID()!=""){
                equip.setClientId(equipAddForm.getClientID());
            }else{
                throw new FormException("clientID不能空");
            }
            if (equipAddForm.getUsername()!=null && equipAddForm.getUsername()!=""){
                equip.setUsername(equipAddForm.getUsername());
            }
            else {
                throw new FormException("username不能为空");
            }
            if (equipAddForm.getPassword()!=null && equipAddForm.getPassword()!=""){
                equip.setPassword(equipAddForm.getPassword());
            }else {
                throw new FormException("password不能为空");
            }
            equipCopyDao.save(equip);
        }else{
            equipCopyDao.save(equip);
        }
    }

    @Override
    public void deleteEquip(Long uuid) {
        equipCopyDao.deleteById(uuid);
    }

    @Override
    public void deleteByList(String[] uuids) {
        String sql = "delete from equip where id in (";
        for (int i = 0; i < uuids.length; i++) {
            if (i==(uuids.length-1)){
                sql+="'"+uuids[i]+"');";
                break;
            }
            sql+="'"+uuids[i]+"',";
        }
        jdbcTemplate.update(sql);
    }

    @Override
    public void updateEquip(EquipUpdateForm equipUpdateForm) {
        String sql = "update equip set name = '"+equipUpdateForm.getEquipName()+"'";
        if (equipUpdateForm.getClientID()!=null&&equipUpdateForm.getClientID()!=""){
            sql+=",client_id = '"+equipUpdateForm.getClientID()+"'";
        }
        if (equipUpdateForm.getUsername()!=null&&equipUpdateForm.getUsername()!=""){
            sql+=",username = '"+equipUpdateForm.getUsername()+"'";
        }
        if (equipUpdateForm.getPassword()!=null&&equipUpdateForm.getPassword()!=""){
            sql+=",password = '"+equipUpdateForm.getPassword()+"'";
        }
        sql+=" where id = '"+equipUpdateForm.getEquipID()+"';";
        jdbcTemplate.update(sql);
    }
}
