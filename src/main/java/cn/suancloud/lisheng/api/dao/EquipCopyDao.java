package cn.suancloud.lisheng.api.dao;

import cn.suancloud.lisheng.api.model.copy.EquipCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipCopyDao extends JpaRepository<EquipCopy, UUID> {
}
