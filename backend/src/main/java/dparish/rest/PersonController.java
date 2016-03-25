package dparish.rest;

import dparish.conf.Constants;
import dparish.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.TopicProcessor;

import javax.annotation.PreDestroy;

/**
 * @author dparish
 */
@RestController
@RequestMapping(value = Constants.REST_BASE_PATH + "/person")
public class PersonController {

    private TopicProcessor<Person> personPublisher;

    /**
     * Constructor for injection.
     * @param personPublisher the person publisher
     */
    @Autowired
    public PersonController(final TopicProcessor<Person> personPublisher) {
        this.personPublisher = personPublisher;
    }

    /**
     * Adds a person.
     * @param person the person
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addPerson(@RequestBody() final Person person) {
        personPublisher.onNext(person);
    }

    /**
     * Cleanup on end.
     */
    @PreDestroy
    private void destroy() {
        personPublisher.onComplete();
    }
}
