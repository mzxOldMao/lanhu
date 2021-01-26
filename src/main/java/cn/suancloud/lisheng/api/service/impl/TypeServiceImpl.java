package cn.suancloud.lisheng.api.service.impl;

import cn.suancloud.lisheng.api.dao.TypeDao;
import cn.suancloud.lisheng.api.model.Type;
import cn.suancloud.lisheng.api.model.form.TypeForm;
import cn.suancloud.lisheng.api.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.UUID;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<Type> findAll(String typeName, int num, int size) {
        Specification<Type> specification = new Specification<Type>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                if (typeName==null){

                }else {
                    Path<String> name = root.get("name");
                    predicate = criteriaBuilder.like(name.as(String.class),"%"+typeName+"%");
                }
                return predicate;
            }
        };
        Page<Type> page = typeDao.findAll(specification, PageRequest.of(num, size, Sort.Direction.ASC, "id"));
        return page;
    }

    @Override
    public void save(TypeForm typeForm) {
        Type type = new Type();
        if (!ObjectUtils.isEmpty(typeForm.getName())){
            type.setName(typeForm.getName());
        }
        if (!ObjectUtils.isEmpty(typeForm.getProtocol())){
            type.setProtocol(typeForm.getProtocol());
        }
        if (!ObjectUtils.isEmpty(typeForm.getDescr())){
            type.setDescr(typeForm.getDescr());
        }
        typeDao.save(type);
    }

/*    @Override
    public void update(TypeForm typeForm) {
        Type type = new Type();
        type.setName(typeForm.getName());
        type.setDescr(typeForm.getDescr());
        typeDao.save()
    }*/

    @Override
    public Type findById(UUID uuid) {
        return typeDao.findTypeById(uuid);
    }

    @Override
    public void deleteByList(String[] uuids) {
        String sql = "delete from equip_type where id in (";
        for (int i = 0; i < uuids.length; i++) {
            if (i==(uuids.length-1)){
                sql+="'"+uuids[i]+"');";
                break;
            }
            sql+="'"+uuids[i]+"',";
        }
        //System.out.println(sql);
        int update = jdbcTemplate.update(sql);
    }

    @Override
    public void deleteById(UUID uuid) {
        typeDao.deleteById(uuid);
    }

    @Override
    public void updateType(TypeForm typeForm,UUID uuid) {
        Type type = new Type();
        type.setId(uuid);
        if (!ObjectUtils.isEmpty(typeForm.getName())){
            type.setName(typeForm.getName());
        }
        if (!ObjectUtils.isEmpty(typeForm.getDescr())){
            type.setDescr(typeForm.getDescr());
        }
        type.setProtocol(typeForm.getProtocol());
        typeDao.save(type);
    }

    @Override
    public void updateType1(String name, String descr, UUID uuid) {
        String sql = "update equip_type set name = '"+name+"',descr = '"+descr+"' where id = '"+uuid+"';";
        //System.out.println(sql);
        jdbcTemplate.update(sql);
    }
}
