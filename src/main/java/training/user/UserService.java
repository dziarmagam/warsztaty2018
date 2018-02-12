package training.user;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import training.exception.ModelNotFound;
import training.user.event.UserCreateEvent;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
class UserService {

    private final UserRepository userRepository;
    private final JmsTemplate jmsTemplate;

    UserService(UserRepository userRepository, JmsTemplate jmsTemplate) {
        this.userRepository = userRepository;
        this.jmsTemplate = jmsTemplate;
    }

    Long createUser(CreateUserDto createUserDto){
        User user = userRepository.save(UserMapper.toEntity(createUserDto));

        jmsTemplate.convertAndSend("userCreated", new UserCreateEvent(UserMapper.toDto(user)));
        return user.getId();
    }

    UserDto getUser(Long id){
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new ModelNotFound("User", id));
    }

    List<UserDto> getUsers(){
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ModelNotFound("User", id));

        userRepository.delete(user);
    }

    void update(UserDto userDto){
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ModelNotFound("User", userDto.getId()));
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        userRepository.save(user);
    }

}
