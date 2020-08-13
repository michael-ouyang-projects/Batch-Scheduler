package tw.ouyang.simplebatchplatform;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import tw.ouyang.simplebatchplatform.dao.BatchDao;
import tw.ouyang.simplebatchplatform.model.Batch;
import tw.ouyang.simplebatchplatform.service.BatchExecutor;

@Configuration
@EnableScheduling
public class PlatformScheduler {

    private static Logger logger = LoggerFactory.getLogger(PlatformScheduler.class);

    @Autowired
    private BatchExecutor batchExecutor;

    @Autowired
    private BatchDao batchDao;

    @Autowired
    @Qualifier("waitingBatchList")
    private List<Batch> waitingBatchList;

    @Scheduled(cron = "0 0 0 * * *")
    public void getTodayBatchs() {

        logger.info("Get batchs to run.");
        waitingBatchList.addAll(batchDao.queryBatchsToRun());
        logger.info(String.format("%d batchs to run today.", waitingBatchList.size()));

        waitingBatchList.forEach(batch -> {
            logger.info(String.format("Put '%s' to Waiting-Batch-List", batch.getJarName()));
        });

    }

    @Scheduled(fixedRate = 10000, initialDelay = 5000)
    private void harvestCompletedBatchs() {

        batchExecutor.harvestCompletedBatchs();

    }

    @Scheduled(fixedRate = 10000, initialDelay = 5000)
    private void executeWaitingBatchs() {

        batchExecutor.executeWaitingBatchs(new DateTime());

    }

}