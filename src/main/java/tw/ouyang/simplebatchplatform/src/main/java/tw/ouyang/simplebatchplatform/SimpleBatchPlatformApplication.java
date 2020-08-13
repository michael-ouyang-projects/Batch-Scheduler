package tw.ouyang.simplebatchplatform;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleBatchPlatformApplication {

    @Autowired
    private PlatformScheduler platformScheduler;

    public static void main(String[] args) {
        SpringApplication.run(SimpleBatchPlatformApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {

        return args -> {

            TimeUnit.SECONDS.sleep(2);
            platformScheduler.getTodayBatchs();

        };

    }

}