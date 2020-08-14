package tw.ouyang.simplebatchplatform.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Predicate;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import tw.ouyang.simplebatchplatform.model.Batch;

@Component
public class BatchExecutor {

    private static Logger logger = LoggerFactory.getLogger(BatchExecutor.class);

    @Value("${batch.directory}")
    private File directory;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    @Qualifier("waitingBatchList")
    private List<Batch> waitingBatchList;

    @Autowired
    @Qualifier("runningBatchList")
    private List<String> runningBatchList;

    @Autowired
    @Qualifier("runningBatchFutureList")
    private List<Future<String>> runningBatchFutureList;

    @Autowired
    @Qualifier("completedBatchList")
    private List<String> completedBatchList;

    public void harvestCompletedBatches() {

        Predicate<Future<String>> isDone = batchFuture -> batchFuture.isDone();

        runningBatchFutureList
                .stream()
                .filter(isDone)
                .forEach(batchFuture -> {
                    try {
                        String jarName = batchFuture.get();
                        completedBatchList.add(jarName);
                        runningBatchList.remove(jarName);
                        logger.info(String.format("Put '%s' to Completed-Batch-List", jarName));
                        logger.info(String.format("Remove '%s' from Running-Batch-List", jarName));
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });

        runningBatchFutureList.removeIf(isDone);

    }

    public void executeWaitingBatches(DateTime dateTime) {

        Iterator<Batch> batchIterator = waitingBatchList.iterator();

        while (batchIterator.hasNext()) {

            Batch batch = batchIterator.next();
            String jarName = batch.getJarName();

            if (canExecute(dateTime, batch)) {

                BatchRunner runner = new BatchRunner(jarName, directory);
                Future<String> batchFuture = executorService.submit(runner, jarName);
                runningBatchList.add(jarName);
                runningBatchFutureList.add(batchFuture);
                batchIterator.remove();
                logger.info(String.format("Put '%s' to Running-Batch-List", jarName));
                logger.info(String.format("Remove '%s' from Waiting-Batch-List", jarName));

            }
        }
    }

    private boolean canExecute(DateTime dateTime, Batch batch) {

        return isTimeUp(dateTime, batch) && (noWaitingBatch(batch) || waitingBatchCompleted(batch));

    }

    private boolean isTimeUp(DateTime dateTime, Batch batch) {

        return dateTime.getHourOfDay() >= batch.getHour() && dateTime.getMinuteOfHour() >= batch.getMinute();

    }

    private boolean noWaitingBatch(Batch batch) {

        return batch.getWaitingJar() == null;

    }

    private boolean waitingBatchCompleted(Batch batch) {

        return completedBatchList.contains(batch.getWaitingJar());

    }

}