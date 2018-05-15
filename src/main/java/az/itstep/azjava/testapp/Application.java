package az.itstep.azjava.testapp;

import az.itstep.azjava.testapp.model.Comment;
import az.itstep.azjava.testapp.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@SpringBootApplication
public class Application {

    /*
    User {
        username, password, role (admin/user)
    } qeydiyyat, girish
    Book {
        name, author, List<Comments>
    } CRUD
    Comment {
        title, text, user
    } CRUD
    Admin rolu olan user qeydiyyatdan kece bilmez
    Qeydiyatdan kecende role user olmalidi
    Kitabi Create, Update, Delete ancaq admin rolu olan
    user
    Comment-i her bir tokeni olan user yaza bilir

     */

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public Map<String, User> getAuthorizedUsers() {
        return new HashMap<>();
    }
}

