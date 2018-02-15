package training.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jms.core.JmsTemplate;

import java.util.Optional;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;
    private JmsTemplate jmsTemplate;

    @Before
    public void setup(){
        userRepository = Mockito.mock(UserRepository.class);
        jmsTemplate = Mockito.mock(JmsTemplate.class);
        userService = new UserService(userRepository, jmsTemplate);
    }

    @Test
    public void given_CreateUserDto_with_empty_name_when_creating_user_then_null_pointer_execption_is_thrown(){
        //given
        CreateUserDto createUserDto =
                new CreateUserDto(null,"Surname","email@com.pl");
        //when
        try{
            Long userId = userService.createUser(createUserDto);
            Assert.fail();
        }catch (NullPointerException e){
            //then exception is thrown
        }
    }
    
    @Test
    public void given_user_should_return_userDto(){
        User user = new User("Marcin","Surname","email@com.pl");
        Mockito.when(userRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(user));
        UserDto userDto = userService.getUser(1L);
        Assert.assertEquals(user.getName(), userDto.getName());
        Assert.assertEquals(user.getSurname(), userDto.getSurname());
        Assert.assertEquals(user.getEmail(), userDto.getEmail());
    }
}
