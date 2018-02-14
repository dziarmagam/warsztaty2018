package training.user;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false, unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    User(String name, String surname, String email) {
        Objects.requireNonNull(surname);
        Objects.requireNonNull(name);
        Objects.requireNonNull(email);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userType = UserType.ADMIN;
    }

    private User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }
}
