package az.itstep.azjava.testapp.service;

import az.itstep.azjava.testapp.exceptions.*;
import az.itstep.azjava.testapp.model.Book;

import az.itstep.azjava.testapp.model.User;
import az.itstep.azjava.testapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {


    private UserService userService;
    private BookRepository bookRepository;


    @Autowired
    public BookServiceImpl(UserService userService, BookRepository bookRepository) {
        this.userService = userService;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll(String token) {
        User user = userService.findByToken(token);
        if (Objects.isNull(user))
            throw new WrongUserDataException("Token Not Found");
        if (user.getRole().equals("USER"))
            throw new WrongRoleException("Role 'USER' is Not Allowed");
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Book save(Book book, String token) {
        User user = userService.findByToken(token);
        if (Objects.isNull(user))
            throw new WrongUserDataException("User Not Found");
        if (user.getRole().equals("USER"))
            throw new WrongRoleException("Role 'USER' is Not Allowed");

        if (Objects.isNull(book))
            throw new BookNotFoundException("Book Not Found");
        if (Objects.isNull(book.getAuthor())
                || Objects.isNull(book.getName()))
            throw new WrongBookDataException("Wrong Book Data");
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book, String token) {
        User user = userService.findByToken(token);
        if (Objects.isNull(user))
            throw new WrongUserDataException("User Not Found");
        if (user.getRole().equals("USER"))
            throw new WrongRoleException("Role 'USER' is Not Allowed");

        if (Objects.isNull(book.getId()))
            return null;
        return bookRepository.save(book);
    }

    @Override
    public Book getById(Integer id, String token) {
        User user = userService.findByToken(token);
        if (Objects.isNull(user))
            throw new WrongUserDataException("User Not Found");
        Book bookTemp = bookRepository.findById(id).orElse(null);
        if (Objects.isNull(bookTemp))
            throw new WrongBookDataException("Book not found");

        return bookTemp;
    }

    @Override
    public void delete(Integer id, String token) {
        User user = userService.findByToken(token);
        if (Objects.isNull(user))
            throw new WrongUserDataException("User Not Found");
        if (user.getRole().equals("USER"))
            throw new WrongRoleException("Role 'USER' is Not Allowed");
        bookRepository.deleteById(id);

    }
}
