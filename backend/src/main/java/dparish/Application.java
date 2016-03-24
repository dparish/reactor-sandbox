package dparish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dparish
 */
@SpringBootApplication
public class Application {

    /**
     * Prevent other classes from using a constructor for this class.
     */
    protected Application() {
    }

    /**
     * Spring boot start.
     * @param args the args
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
