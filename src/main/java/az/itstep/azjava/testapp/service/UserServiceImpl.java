package az.itstep.azjava.testapp.service;

import az.itstep.azjava.testapp.exceptions.IdNotFoundException;
import az.itstep.azjava.testapp.exceptions.WrongUserDataException;
import az.itstep.azjava.testapp.model.User;
import az.itstep.azjava.testapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private Map<String, User> authorizedUsers;

    @Override
    public User getById(Integer id) {
        if(Objects.isNull(id)) throw new IdNotFoundException("No id entered");
        if(id<0) throw new RuntimeException("Must be bigger than -1");
        Optional<User> user =  userRepository.findById(id);
        if(!user.isPresent()) throw new RuntimeException("User not found");
        return user.get();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public String signIn(User user) {
        Optional<User> dbUser = userRepository.findByUsername(user.getUsername());
        if(dbUser.isPresent()) {
            String password = dbUser.get().getPassword();
            if(Objects.equals(password, user.getPassword())) {
                String token = UUID.randomUUID().toString();
                authorizedUsers.put(token, dbUser.get());
                return token;
            }
        }
        throw new WrongUserDataException("wrong username or password");
    }

    @Override
    public User findByToken(String token) {
        return authorizedUsers.get(token);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setAuthorizedUsers(Map<String, User> authorizedUsers) {
        this.authorizedUsers = authorizedUsers;
    }
}
