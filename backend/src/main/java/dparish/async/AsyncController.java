package dparish.async;

import dparish.model.Person;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author dparish
 */
@Controller
public class AsyncController {

    /**
     * Returns a person as a stomp message.
     * @param person the person
     * @return the person
     */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Person getPerson(final Person person) {
        return person;
    }
}
