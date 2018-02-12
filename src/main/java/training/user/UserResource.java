package training.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping
    ResponseEntity getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    ResponseEntity createUsers(@RequestBody CreateUserDto createUserDto){
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

}
