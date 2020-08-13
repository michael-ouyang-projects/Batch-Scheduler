package tw.ouyang.simplebatchplatform.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.ouyang.simplebatchplatform.model.Batch;

@RestController
public class ShowInfoApi {

    @Autowired
    @Qualifier("waitingBatchList")
    private List<Batch> waitingBatchList;

    @Autowired
    @Qualifier("runningBatchList")
    private List<String> runningBatchList;

    @Autowired
    @Qualifier("completedBatchList")
    private List<String> completedBatchList;

    @GetMapping("/waiting")
    public List<Batch> showWaitingBatchsInfo() {

        return waitingBatchList;

    }

    @GetMapping("/running")
    public List<String> showRunningBatchsInfo() {

        return runningBatchList;

    }

    @GetMapping("/completed")
    public List<String> showCompletedBatchsInfo() {

        return completedBatchList;

    }

}
