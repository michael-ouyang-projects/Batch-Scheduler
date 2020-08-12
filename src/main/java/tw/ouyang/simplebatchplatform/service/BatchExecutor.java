package tw.ouyang.simplebatchplatform.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import tw.ouyang.simplebatchplatform.model.Batch;

@Component
public class BatchExecutor {

    @Value("${batch.directory}")
    private File directory;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    @Qualifier("runningBatchsFuture")
    private List<Future<String>> runningBatchsFuture;

    @Autowired
    @Qualifier("waitingToRunBatchs")
    private List<Batch> waitingToRunBatchs;

    @Autowired
    @Qualifier("runningBatchsId")
    private List<String> runningBatchsId;

    @Autowired
    @Qualifier("completedBatchsId")
    private List<String> completedBatchsId;

    public void harvestCompletedBatchs() {

        runningBatchsFuture
                .stream()
                .filter(batchFuture -> {
                    return batchFuture.isDone();
                }).forEach(batchFuture -> {
                    try {
                        completedBatchsId.add(batchFuture.get());
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });

    }

    public void executeWaitingBatchs(DateTime dateTime) {

        Iterator<Batch> batchIterator = waitingToRunBatchs.iterator();

        while (batchIterator.hasNext()) {

            Batch batch = batchIterator.next();

            if (isTimeUp(dateTime, batch)) {

                if (noWaitingBatch(batch) || waitingBatchCompleted(batch)) {

                    BatchRunner runner = new BatchRunner(batch.getJarName(), directory);
                    Future<String> batchFuture = executorService.submit(runner, batch.getJarName());
                    runningBatchsFuture.add(batchFuture);
                    runningBatchsId.add(batch.getJarName());
                    batchIterator.remove();

                }
            }
        }
    }

    private boolean isTimeUp(DateTime dateTime, Batch batch) {

        return dateTime.getHourOfDay() >= batch.getHour() && dateTime.getMinuteOfHour() >= batch.getMinute();

    }

    private boolean noWaitingBatch(Batch batch) {

        return batch.getWaitingJar() == null;

    }

    private boolean waitingBatchCompleted(Batch batch) {

        return completedBatchsId.contains(batch.getWaitingJar());

    }

}
