package tw.ouyang.simplebatchplatform.api;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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

    @GetMapping("/")
    public Map<String, List<String>> showBatchsInfo() {

        Map<String, List<String>> batchMap = new TreeMap<>();

        batchMap.put("waiting",
                waitingBatchList
                        .stream()
                        .map(batch -> batch.getJarName())
                        .collect(Collectors.toList()));

        batchMap.put("running", runningBatchList);
        batchMap.put("completed", completedBatchList);

        return batchMap;

    }

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
