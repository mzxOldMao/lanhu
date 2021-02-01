package cn.suancloud.api.postgresqltest.dao;

import cn.suancloud.api.postgresqltest.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookDao extends JpaRepository<Book,Long> {
    Page<Book> findAll(Specification specification, Pageable pageable);
    Book findBookById(Long id);
}
