package dparish.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Publishes messages to stomp web clients.
 * TODO: We can probably replace this with a pure reactor impl
 * @author dparish
 **/
@Service
public class StompPublisher {

    private SimpMessagingTemplate messagingTemplate;

    /**
     * Constructor for injection.
     * @param messagingTemplate the messaging template
     */
    @Autowired
    public StompPublisher(final SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Sends a message. This is really not the right way to do things. They
     * really should be strongly typed I think.
     * @param message the message to send
     */
    public void sendMessage(final String message) {
        messagingTemplate.convertAndSend("/topic/greetings", message);
    }
}
