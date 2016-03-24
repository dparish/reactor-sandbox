package dparish.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dparish.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.SchedulerGroup;
import reactor.core.publisher.TopicProcessor;

import javax.annotation.PostConstruct;

/**
 * @author dparish
 */
@Service
public class PersonReceiver {

    private TopicProcessor<Person> personPublisher;
    private SchedulerGroup schedulerGroup;
    private ObjectMapper objectMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonReceiver.class);

    /**
     * The injected constructor.
     * @param personPublisher the person publisher
     * @param schedulerGroup the scheduler
     * @param objectMapper the mapper
     */
    @Autowired
    public PersonReceiver(
            final TopicProcessor<Person> personPublisher,
            final SchedulerGroup schedulerGroup,
            final ObjectMapper objectMapper) {
        this.personPublisher = personPublisher;
        this.schedulerGroup = schedulerGroup;
        this.objectMapper = objectMapper;
    }

    /**
     * Setup receiver.
     */
    @PostConstruct
    private void init() {
        personPublisher
                .map(s -> getPersonString(s))
                .publishOn(schedulerGroup)
                .consume(System.out::println);
    }

    /**
     * Constructor for injection.
     * @param personPublisher the publisher
     * @param schedulerGroup the scheduler
     */
    public PersonReceiver(
            final TopicProcessor<Person> personPublisher,
            final SchedulerGroup schedulerGroup) {
        this.personPublisher = personPublisher;
        this.schedulerGroup = schedulerGroup;
    }

    /**
     * Not sure if I like this, it's to make the lambda work that throws an exception.
     * @param p the person
     * @return a string of the person
     */
    private String getPersonString(final Person p) {
        try {
            return objectMapper.writeValueAsString(p);
        } catch (JsonProcessingException e) {
            LOGGER.error("Unable to parse person", e);
        }
        return "";
    }
}
