package training.user;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import training.Application;

@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
@SqlGroup({
        @Sql(scripts = "/clean.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
@TestPropertySource("/application.properties")
public class UserServiceIT {

    @Autowired
    private UserService userService;

    @Test
    public void given_CreateUserDto_when_creating_user_then_user_is_created(){
        //given
        CreateUserDto createUserDto =
                new CreateUserDto("Name","Surname","email@com.pl");
        //when
        Long userId = userService.createUser(createUserDto);
        UserDto user = userService.getUser(userId);
        //then
        Assertions.assertThat(user.getEmail()).isEqualTo(createUserDto.getEmail());
        Assertions.assertThat(user.getName()).isEqualTo(createUserDto.getName());
        Assertions.assertThat(user.getSurname()).isEqualTo(createUserDto.getSurname());
        Assertions.assertThat(user.getId()).isEqualTo(userId);
        Assertions.assertThat(user.getUserType()).isEqualTo(UserType.ADMIN);
    }

    @Test(expected = NullPointerException.class)
    public void given_CreateUserDto_with_empty_name_when_creating_user_then_null_pointer_execption_is_thrown(){
        //given
        CreateUserDto createUserDto =
                new CreateUserDto(null,"Surname","email@com.pl");
        //when
        Long userId = userService.createUser(createUserDto);
        Assert.fail();
        //then exception is thrown
    }

}