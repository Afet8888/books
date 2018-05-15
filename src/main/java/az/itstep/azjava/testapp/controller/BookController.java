package az.itstep.azjava.testapp.controller;

import az.itstep.azjava.testapp.model.Book;
import az.itstep.azjava.testapp.model.User;
import az.itstep.azjava.testapp.service.BookService;
import az.itstep.azjava.testapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;
    private UserService userService;

    @GetMapping
    List<Book> getAll (@RequestHeader HttpHeaders headers) {
        return bookService.getAll(headers.get("token").get(0));
    }

    @PostMapping
    Book save(@RequestBody Book book, @RequestHeader HttpHeaders headers) {
        return bookService.save(book,headers.get("token").get(0));
    }

    @DeleteMapping ("/{id}")
     void delete(@PathVariable Integer id, @RequestHeader HttpHeaders headers) {

        bookService.delete(id, headers.get("token").get(0));
    }

    @PutMapping
    Book update(@RequestBody Book book, @RequestHeader HttpHeaders headers) {

        return bookService.update(book, headers.get("token").get(0));
    }

    @GetMapping ("/{id}")
    Book getById(@PathVariable Integer id, @RequestHeader HttpHeaders headers) {
        return bookService.getById(id, headers.get("token").get(0)); }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
