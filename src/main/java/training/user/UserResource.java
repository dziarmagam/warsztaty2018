package training.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import training.exception.ModelNotFound;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
class UserResource {

    private final UserService userService;
    private final Example example;
    private static final Logger LOGGER = LoggerFactory.getLogger("hello");

    public UserResource(UserService userService, Example example) {
        this.userService = userService;
        this.example = example;
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    ResponseEntity getUser(@PathVariable Long id) {
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/type/{userType}")
    ResponseEntity getUserByType(@PathVariable String userType, Pageable pageable) {
        List<UserDto> users = userService.findByUserType(userType);
        return ResponseEntity.ok(users);
    }

    @GetMapping
    ResponseEntity getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    ResponseEntity createUsers(@RequestBody CreateUserDto createUserDto) {
        Long userId = userService.createUser(createUserDto);
        LOGGER.info("User created with id: " + userId);
        return ResponseEntity.ok(userId);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    ResponseEntity updateUser(@Valid @RequestBody UserDto userDto) {
        userService.update(userDto);
        boolean wasUpdateOk = false;
        if (wasUpdateOk) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("message");
        }
    }

    @GetMapping("/ids/")
    ResponseEntity getUserIds() {
        example.findIdBySth();
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(ModelNotFound.class)
    ResponseEntity handleException(ModelNotFound e) {
        LOGGER.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}
