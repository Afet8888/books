package az.itstep.azjava.testapp.service;

import az.itstep.azjava.testapp.model.User;

public interface UserService {
    User save(User user);
    User getById(Integer id);
    String signIn(User user);
    User findByToken(String token);

}
