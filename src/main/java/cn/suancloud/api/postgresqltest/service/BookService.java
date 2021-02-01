package cn.suancloud.api.postgresqltest.service;

import cn.suancloud.api.postgresqltest.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BookService {
    List<Book> findAll1();
    Book findById(Long id);
    Page<Book> findAll(Specification specification, Pageable pageable);
}
