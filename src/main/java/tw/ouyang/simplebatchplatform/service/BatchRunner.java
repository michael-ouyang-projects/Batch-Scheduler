package tw.ouyang.simplebatchplatform.service;

import java.io.File;

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
            // Runtime.getRuntime().exec(command, null, directory);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
