package cn.suancloud.api.postgresqltest.dao;

import cn.suancloud.api.postgresqltest.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompanyDao extends JpaRepository<Company,Long> {
    Company findByName(String name);
    Company findCompanyById(Long id);

    //void deleteInBatch(List<Long> uuids);
}
