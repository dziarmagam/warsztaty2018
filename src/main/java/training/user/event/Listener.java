package training.user.event;

import org.hibernate.validator.constraints.Email;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    @JmsListener(destination = "userCreated", containerFactory = "myFactory")
    public void receiveMessage(UserCreateEvent created) {
        System.out.println("Received <" + created + ">");
    }
}
