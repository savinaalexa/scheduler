package com.savina.scheduler.service;

import com.savina.scheduler.data.Credentials;
import com.savina.scheduler.data.User;
import com.savina.scheduler.data.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //в данном классе реализована логика обработки клиентских запросов
public class UserController {

    private final UserService userService;

    @Autowired  //зависимость от UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //ResponseEntity — специальный класс для возврата ответов

    @PostMapping(value = "/users/create")
    public ResponseEntity<?> create(@RequestBody UserCredentials userCredentials) {
        return userService.create(userCredentials)
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserCredentials>> read() {
        final List<UserCredentials> users = userService.readAll();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@GetMapping(value = "/users/{id}")
    public ResponseEntity<User> read(@PathVariable(name = "id") int id) {
        final User user = userService.read(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    @PostMapping(value = "/users/login")
    public ResponseEntity<User> login(@RequestBody Credentials credentials) {
        final UserCredentials uc = userService.findByCredentials(credentials);

        return uc != null
                ? new ResponseEntity<>(new User(uc.getId(), uc.getName(), uc.getSurname(), uc.getEmail(), uc.getPhone()), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody UserCredentials user) {
        final UserCredentials updated = userService.update(user, id);

        return updated != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = userService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
