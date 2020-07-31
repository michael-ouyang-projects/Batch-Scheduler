package tw.ouyang.simplebatchplatform;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tw.ouyang.simplebatchplatform.model.Batch;

@Configuration
public class PlatformContainer {

    @Bean
    public ExecutorService executorService() {

        return Executors.newFixedThreadPool(10);

    }

    @Bean(name = "waitingToRunBatchs")
    public List<Batch> waitingToRunBatchs() {

        return new ArrayList<Batch>();

    }

    @Bean(name = "runningBatchs")
    public List<Batch> runningBatchs() {

        return new ArrayList<Batch>();
    }

    @Bean(name = "completedBatchs")
    public List<Batch> completedBatchs() {

        return new ArrayList<Batch>();

    }

}
