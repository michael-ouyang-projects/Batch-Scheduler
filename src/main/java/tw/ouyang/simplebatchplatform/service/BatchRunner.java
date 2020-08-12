package tw.ouyang.simplebatchplatform.service;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BatchRunner implements Runnable {

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
            System.out.println("Run '" + command + "' in " + directory);
            TimeUnit.SECONDS.sleep(15);
            // Runtime.getRuntime().exec(command, null, directory);
            System.out.println("Completed '" + command + "' in " + directory);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
