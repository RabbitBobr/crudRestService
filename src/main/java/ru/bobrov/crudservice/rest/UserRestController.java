package ru.bobrov.crudservice.rest;

/**
 * REST controller for {@link User} connected requests.
 *
 * @author Sergey Bobrov
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bobrov.crudservice.entity.User;
import ru.bobrov.crudservice.repository.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/users/")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable("id") Integer userId) {
        ResponseEntity<User> result;
        if (userId == null) {
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Optional<User> optionalUser = this.userRepository.findById(userId);
            result = optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        ResponseEntity<User> result;
        if (user == null) {
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.userRepository.save(user);

        result = new ResponseEntity<>(user, HttpStatus.CREATED);

        return result;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer userId, @RequestBody User user) {

        ResponseEntity<User> result;

        if (user == null || userId == null) {
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Optional<User> optionalUser = this.userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                User userUpdate = optionalUser.get();
                userUpdate.setName(user.getName());
                userUpdate.setEmail(user.getEmail());
                this.userRepository.save(userUpdate);
                result = new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }

        return result;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
        ResponseEntity<User> result;

        if (id == null)
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            this.userRepository.delete(optionalUser.get());
            result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return result;
    }
}
