package tw.ouyang.simplebatchplatform.service;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import tw.ouyang.simplebatchplatform.model.Batch;

@Component
public class BatchExecutor {

    @Autowired
    private ExecutorService executorService;

    @Value("${batch.directory}")
    private File directory;

    @Autowired
    @Qualifier("waitingToRunBatchs")
    private List<Batch> waitingToRunBatchs;

    @Autowired
    @Qualifier("runningBatchs")
    private List<Batch> runningBatchs;

    @Autowired
    @Qualifier("completedBatchs")
    private List<Batch> completedBatchs;

    public void execute(DateTime dateTime) {

        System.out.println(waitingToRunBatchs.size());
        
        for (Batch batch : waitingToRunBatchs) {

            if (dateTime.getHourOfDay() >= batch.getHour() && dateTime.getMinuteOfHour() >= batch.getMinute()) {

                BatchRunner runner = new BatchRunner(batch.getJarName(), directory);
                executorService.execute(runner);

            }

        }

    }

}
