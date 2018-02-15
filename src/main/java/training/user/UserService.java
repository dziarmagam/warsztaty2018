package training.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import training.exception.ModelNotFound;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JmsTemplate jmsTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger("");

    UserService(UserRepository userRepository, JmsTemplate jmsTemplate) {
        this.userRepository = userRepository;
        this.jmsTemplate = jmsTemplate;
    }

    public Optional<UserDto> findUserByName(String name) {
        return userRepository.findByName(name)
                .map(UserMapper::toDto);
    }

    /**
     * This method create new {@link User}
     * <p/>
     * Hello
     *
     * @param createUserDto dto to create user
     * @return return new entity id
     */
    public Long createUser(CreateUserDto createUserDto) {
        Objects.requireNonNull(createUserDto.getEmail());
        Objects.requireNonNull(createUserDto.getName());
        Objects.requireNonNull(createUserDto.getSurname());
        User user = userRepository.save(UserMapper.toEntity(createUserDto));
        jmsTemplate.convertAndSend("userCreated.topic", UserMapper.toDto(user));
        return user.getId();
    }

    UserDto getUser(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new ModelNotFound("User", id));
    }

    List<UserDto> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ModelNotFound("User", id));

        userRepository.delete(user);
    }

    void update(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ModelNotFound("User", userDto.getId()));
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        if (!user.getSurname().equals(userDto.getSurname())) {
            user.setSurname(userDto.getSurname());
        }
        userRepository.save(user);
    }

    List<UserDto> findByUserType(String userType) {
        UserType userTypeEnum = UserType.valueOf(userType);
        return StreamSupport.stream(
                userRepository.findByUserType(userTypeEnum).spliterator(), false)
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

}
