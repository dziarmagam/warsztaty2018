package training.user.event;


import training.user.UserDto;

public class UserCreateEvent {
    private UserDto userDto;

    public UserCreateEvent(UserDto userDto) {
        this.userDto = userDto;
    }

    public UserCreateEvent() {
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
