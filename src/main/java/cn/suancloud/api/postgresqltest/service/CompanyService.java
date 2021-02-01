package cn.suancloud.api.postgresqltest.service;

import cn.suancloud.api.postgresqltest.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {
    List<Company> findAll1();
    void save(Company company);
    void delete(Long id);
    void update(Company company);
    Company findByName(String name);
    Company findById(Long id);
    Page<Company> findAll2(Pageable pageable);
    void deleteByList(String[] uuids);
}
