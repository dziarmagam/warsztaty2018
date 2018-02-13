package training.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    ResponseEntity getUser(@PathVariable Long id){
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/type/{userType}")
    ResponseEntity getUserByType(@PathVariable String userType){
        List<UserDto> users = userService.findByUserType(userType);
        return ResponseEntity.ok(users);
    }

    @GetMapping
    ResponseEntity getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    ResponseEntity createUsers(@RequestBody CreateUserDto createUserDto){
        Objects.requireNonNull(createUserDto.getEmail());
        Objects.requireNonNull(createUserDto.getName());
        Objects.requireNonNull(createUserDto.getSurname());
        Long userId = userService.createUser(createUserDto);
        return ResponseEntity.ok(userId);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping
    ResponseEntity updateUser(@Valid @RequestBody UserDto userDto){
        userService.update(userDto);
        return ResponseEntity.accepted().build();
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity handleException(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}
