package training.user;

import javax.validation.constraints.NotNull;

public class CreateUserDto {

    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String email;

    CreateUserDto(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public CreateUserDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
