package dparish.conf;

import dparish.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.SchedulerGroup;
import reactor.core.publisher.TopicProcessor;

/**
 * @author dparish
 */
@Configuration
public class AsyncConfig {

    /**
     * Publishes a person.
     *
     * @return The person publisher
     */
    @Bean()
    public TopicProcessor<Person> personPublisher() {
        return TopicProcessor.create();
    }

    /**
     * Get's a scheduler group
     *
     * @return the scheduler group
     */
    @Bean()
    public SchedulerGroup schedulerGroup() {
        return SchedulerGroup.async();
    }
}
