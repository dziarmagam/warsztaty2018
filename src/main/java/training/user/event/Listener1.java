package training.user.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import training.email.EmailSender;
import training.user.UserDto;

@Component
public class Listener1 {

    private final EmailSender emailSender;
    private final ObjectMapper objectMapper;

    public Listener1(EmailSender emailSender, ObjectMapper objectMapper) {
        this.emailSender = emailSender;
        this.objectMapper = objectMapper;
    }


    @JmsListener(destination = "userCreated.topic", containerFactory = "myFactory")
    public void receiveMessage(UserDto dto) throws JsonProcessingException {
        emailSender.sendSimpleMessage("codersworkshop2018@gmail.com",
                "User created, Listener 2"
                , objectMapper.writeValueAsString(dto));
        System.out.println("Received 1 <" + dto + ">");
    }
}
