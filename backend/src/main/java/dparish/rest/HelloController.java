package dparish.rest;

import dparish.conf.Constants;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SchedulerGroup;
import reactor.core.publisher.TopicProcessor;

import java.time.Duration;

/**
 * @author dparish
 */
@RestController
@RequestMapping(value = Constants.REST_BASE_PATH)
public class HelloController {

    /**
     * Echo's back what you send.
     * @param echo The string to display
     * @return The echoed string
     */
    @RequestMapping(value = "/echo/{echo}", method = RequestMethod.GET)
    public String echo(@PathVariable("echo") final String echo) {
        return "You said:" + echo;
    }

    /**
     * Kicks off an async job.
     * @param echo The string to send to the job
     */
    @RequestMapping(value = "/publish/{echo}", method = RequestMethod.GET)
    public void test(@PathVariable("echo") final String echo) {
        // Create an async message-passing Processor exposing a Flux API
        TopicProcessor<String> sink = TopicProcessor.create();
        SchedulerGroup eventLoops = SchedulerGroup.async();

        // Scatter Gather the input sequence
        sink
                .map(String::toUpperCase)
                .flatMap(s ->
                                Mono.fromCallable(() -> echo + s)
                                        .timeout(Duration.ofSeconds(2))
                                        .publishOn(eventLoops)
                )
                .consume(System.out::println);

        // Sink values asynchronously
        sink.onNext("Rx");
        sink.onNext("ReactiveStreams");
        sink.onNext("ReactiveStreamsCommons");
        sink.onNext("RingBuffer");

        //Shutdown and clean async resources
        sink.onComplete();
    }
}
