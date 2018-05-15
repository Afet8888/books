package az.itstep.azjava.testapp.controller;

import az.itstep.azjava.testapp.model.User;
import az.itstep.azjava.testapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    @PostMapping
    User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping
    String signIn(@RequestBody User user) {
        return userService.signIn(user);
    }

    @GetMapping ("/{id}")
    User getById(@PathVariable Integer id) { return userService.getById(id); }

    @GetMapping
    User findByToken(@RequestBody String token) { return userService.findByToken(token);}

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
