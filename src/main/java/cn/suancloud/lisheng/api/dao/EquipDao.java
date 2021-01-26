package cn.suancloud.lisheng.api.dao;

import cn.suancloud.lisheng.api.model.Equip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipDao extends JpaRepository<Equip, UUID> {

}
