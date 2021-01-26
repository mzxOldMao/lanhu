package cn.suancloud.lisheng.api.dao;

import cn.suancloud.lisheng.api.model.Type;
import cn.suancloud.lisheng.api.model.copy.TypeCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TypeCopyDao extends JpaRepository<TypeCopy, UUID> {

}


