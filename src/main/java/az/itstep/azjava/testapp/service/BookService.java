package az.itstep.azjava.testapp.service;

import az.itstep.azjava.testapp.model.Book;

import java.util.List;


public interface BookService {

    Book save(Book book, String token);
    Book update(Book book, String token);
    Book getById(Integer id, String token);
    void delete(Integer id, String token);
    List<Book> getAll(String token);
}
