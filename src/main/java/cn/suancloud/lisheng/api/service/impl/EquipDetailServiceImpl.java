package cn.suancloud.lisheng.api.service.impl;

import cn.suancloud.lisheng.api.dao.EquipDetailDao;
import cn.suancloud.lisheng.api.model.EquipDetail;
import cn.suancloud.lisheng.api.model.form.DateForm;
import cn.suancloud.lisheng.api.service.EquipDetailService;
import cn.suancloud.lisheng.api.utils.TimeStampUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.*;

@Service
public class EquipDetailServiceImpl implements EquipDetailService {
    @Autowired
    private EquipDetailDao equipDetailDao;

    @Override
    public Page<EquipDetail> findAll(Long id,int num,int size) {
        Specification<EquipDetail> specification = new Specification<EquipDetail>(){
            @Override
            public Predicate toPredicate(Root<EquipDetail> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Long> uuidPath = root.get("equipId");
                Predicate predicate = null;
                if (!ObjectUtils.isEmpty(uuidPath)){
                    predicate = criteriaBuilder.equal(uuidPath.as(Long.class),id);
                }
                return predicate;
            }
        };
        Page<EquipDetail> page = equipDetailDao.findAll(specification,PageRequest.of(num,size, Sort.Direction.DESC,"uploadTime"));
        return page;
    }

    @Override
    public Page<EquipDetail> findAllByTimes(DateForm dateForm,Long id) {
        //转换成时间戳
        Long start = 0L; //不输入时间段默认从1970年算起
        Long end = System.currentTimeMillis();
        if(dateForm.getStartTime()!=null&&dateForm.getStartTime()!=""&&dateForm.getEndTime()!=null&&dateForm.getEndTime()!=""){
            start = TimeStampUtils.stringForlong1(dateForm.getStartTime());
            end = TimeStampUtils.stringForlong1(dateForm.getEndTime()) + 86399000;   //加上23：59：59时间戳大小
        }
        //System.out.println(start + "   "+ end);
        Long finalStart = start;
        Long finalEnd = end;
        Specification<EquipDetail> specification = new Specification<EquipDetail>() {
            @Override
            public Predicate toPredicate(Root<EquipDetail> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Long> uuidPath = root.get("equipId");
                Path<Long> time = root.get("uploadTime");
                Predicate predicate3 = null;
                Predicate predicate1 = null;
                Predicate predicate2 = null;
                predicate3 = criteriaBuilder.equal(uuidPath.as(Long.class),id);
                if (!ObjectUtils.isEmpty(time)){
                    predicate1 = criteriaBuilder.gt(time.as(Long.class), finalStart);
                    predicate2 = criteriaBuilder.le(time.as(Long.class), finalEnd);
                }
                Predicate predicate = criteriaBuilder.and(predicate3,predicate1,predicate2);
                return predicate;
            }
        };
        Page<EquipDetail> page = equipDetailDao.findAll(specification, PageRequest.of(dateForm.getPageNum(), dateForm.getPageSize(), Sort.Direction.DESC, "uploadTime"));
        return page;
    }
}
