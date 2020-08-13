package tw.ouyang.simplebatchplatform;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tw.ouyang.simplebatchplatform.model.Batch;

@Configuration
public class PlatformContainer {

    @Bean
    public ExecutorService executorService() {

        return Executors.newFixedThreadPool(10);

    }

    @Bean(name = "runningBatchFutures")
    public List<Future<String>> runningBatchFutures() {

        return new ArrayList<>();

    }

    @Bean(name = "waitingToRunBatchs")
    public List<Batch> waitingToRunBatchs() {

        return new ArrayList<Batch>();

    }

    @Bean(name = "runningBatchIds")
    public List<String> runningBatchIds() {

        return new ArrayList<String>();
    }

    @Bean(name = "completedBatchIds")
    public List<String> completedBatchIds() {

        return new ArrayList<String>();

    }

}
