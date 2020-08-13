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

    @Bean(name = "waitingBatchList")
    public List<Batch> waitingBatchList() {

        return new ArrayList<Batch>();

    }

    @Bean(name = "runningBatchList")
    public List<String> runningBatchList() {

        return new ArrayList<String>();
    }

    @Bean(name = "runningBatchFutureList")
    public List<Future<String>> runningBatchFutureList() {

        return new ArrayList<>();

    }

    @Bean(name = "completedBatchList")
    public List<String> completedBatchList() {

        return new ArrayList<String>();

    }

}
