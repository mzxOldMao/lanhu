package cn.suancloud.api.postgresqltest.service.impl;

import cn.suancloud.api.postgresqltest.dao.CompanyDao;
import cn.suancloud.api.postgresqltest.model.Company;
import cn.suancloud.api.postgresqltest.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService
{
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Company> findAll1() {
        return companyDao.findAll();
    }

    @Override
    public void save(Company company) {
        companyDao.save(company);
    }

    @Override
    public void delete(Long id) {
        companyDao.deleteById(id);
    }

    @Override
    public void update(Company company) {
        companyDao.save(company);
    }

    @Override
    public Company findByName(String name) {
        return companyDao.findByName(name);
    }

    @Override
    public Company findById(Long id) {
        return companyDao.findCompanyById(id);
    }

    @Override
    public Page<Company> findAll2(Pageable pageable) {
        return companyDao.findAll(pageable);
    }

    @Override
    public void deleteByList(String[] uuids) {
        String sql = "delete from company where id in (";
        for (int i = 0; i < uuids.length; i++) {
            if (i==(uuids.length-1)){
                sql+=uuids[i]+");";
                break;
            }
            sql+=uuids[i]+",";
        }
        System.out.println(sql);
        int update = jdbcTemplate.update(sql);
    }
}
