package cn.suancloud.api.postgresqltest.service.impl;

import cn.suancloud.api.postgresqltest.dao.BookDao;
import cn.suancloud.api.postgresqltest.model.Book;
import cn.suancloud.api.postgresqltest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Override
    public List<Book> findAll1() {
        return bookDao.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookDao.findBookById(id);
    }

    @Override
    public Page<Book> findAll(Specification specification, Pageable pageable) {
        return bookDao.findAll(specification,pageable);
    }
}
