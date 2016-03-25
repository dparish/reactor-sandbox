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
        TopicProcessor<Person> processor = TopicProcessor.create();
        processor.publishOn(schedulerGroup());
        return processor;
    }

    /**
     * Get's a scheduler group.
     *
     * @return the scheduler group
     */
    @Bean()
    public SchedulerGroup schedulerGroup() {
        return SchedulerGroup.async();
    }
}
