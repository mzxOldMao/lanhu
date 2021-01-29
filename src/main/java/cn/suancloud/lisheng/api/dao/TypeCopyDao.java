package cn.suancloud.lisheng.api.dao;

import cn.suancloud.lisheng.api.model.copy.TypeCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeCopyDao extends JpaRepository<TypeCopy, Long> {

}


