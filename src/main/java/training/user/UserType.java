package training.user;

enum UserType {
    ADMIN("cos"),
    NORMAL("asd"),
    CUSTOMER("asd");

    String value;

    UserType(String value) {
        this.value = value;
    }
}



