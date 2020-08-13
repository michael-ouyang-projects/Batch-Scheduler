package tw.ouyang.simplebatchplatform.service;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BatchRunner implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(BatchRunner.class);
    private String jarName;
    private File directory;

    public BatchRunner(String jarName, File directory) {

        this.jarName = jarName;
        this.directory = directory;

    }

    @Override
    public void run() {

        try {

            String command = String.format("java -jar %s.jar", jarName);
            logger.info(String.format("Run '%s' in '%s'.", command, directory));
            // Runtime.getRuntime().exec(command, null, directory);
            TimeUnit.SECONDS.sleep(15);
            logger.info(String.format("Completed running '%s' in '%s'.", command, directory));

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
