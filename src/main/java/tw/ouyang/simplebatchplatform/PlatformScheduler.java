package tw.ouyang.simplebatchplatform;

import java.util.List;

import org.joda.time.DateTime;
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

    @Autowired
    private BatchExecutor batchExecutor;

    @Autowired
    private BatchDao batchDao;

    @Autowired
    @Qualifier("waitingToRunBatchs")
    private List<Batch> waitingToRunBatchs;

    @Scheduled(cron = "0 40 15 * * *")
    public void getTodayBatchs() {

        waitingToRunBatchs.addAll(batchDao.queryBatchsToRun());

    }

    @Scheduled(fixedRate = 10000)
    public void checkAndRunBatchs() {

        batchExecutor.execute(new DateTime());

    }

}
