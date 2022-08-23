package tacos.web.api;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.Order;
import tacos.User;
import tacos.data.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping(path="/users", produces="application/json") //Обрабатывает запросы на /design
@CrossOrigin(origins="*")
public class UserController {

    private UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping(produces="application/json")
    public Iterable<User> allUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> orderById(@PathVariable("id") Long id) {
        Optional<User> optUser = userRepo.findById(id);
        if (optUser.isPresent()) {
            return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") Long userId) {
        try {
            userRepo.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {}
    }
}
