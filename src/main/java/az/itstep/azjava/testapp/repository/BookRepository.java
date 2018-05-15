package az.itstep.azjava.testapp.repository;


import org.springframework.data.repository.CrudRepository;

import az.itstep.azjava.testapp.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{
}
