package training.user;

final class UserMapper {
    private UserMapper(){}

    static UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setUserType(user.getUserType());
        return userDto;
    }
    static User toEntity(CreateUserDto dto){
        return new User(dto.getName(), dto.getSurname(), dto.getEmail());
    }


}
