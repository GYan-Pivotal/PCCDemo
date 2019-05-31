package io.pivotal.pcc.demo.client.repositories.jpa;

import io.pivotal.pcc.demo.client.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("JPABookRepository")
public interface JPABookRepository extends CrudRepository<Book, String> {

}
