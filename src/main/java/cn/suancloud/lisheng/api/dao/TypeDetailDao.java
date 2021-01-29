package cn.suancloud.lisheng.api.dao;

import cn.suancloud.lisheng.api.model.TypeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDetailDao extends JpaRepository<TypeDetail, Long> {
    List<TypeDetail> findTypeDetailById(Long type_id);
}
