package cn.suancloud.lisheng.api.dao;

import cn.suancloud.lisheng.api.model.EquipDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipDetailDao extends JpaRepository<EquipDetail, Long> {
    //查询设备历史数据
    Page<EquipDetail> findAll(Specification specification, Pageable pageable);
}
