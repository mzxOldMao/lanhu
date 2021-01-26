package cn.suancloud.lisheng.api.dao;

import cn.suancloud.lisheng.api.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TypeDao extends JpaRepository<Type, UUID> {
    Page<Type> findAll(Specification specification, Pageable pageable);
    Type findTypeById(UUID uuid);
}
