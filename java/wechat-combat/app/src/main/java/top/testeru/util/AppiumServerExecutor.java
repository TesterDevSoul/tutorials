package top.testeru.util;

import org.apache.commons.exec.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppiumServerExecutor {
    private ExecutorService executor;

    public AppiumServerExecutor() {
        executor = Executors.newFixedThreadPool(1);
    }

    public void startAppiumServer(String commandLine) {
        executor.execute(() -> {
            try {
                // 执行启动命令
                Process process = Runtime.getRuntime().exec(commandLine);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                reader.close();
                process.destroy();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
//        executor.execute(() -> {
//            ProcessBuilder processBuilder = new ProcessBuilder(commandLine);
//            try {
//                Process process = processBuilder.start();
//                // 可以在这里处理进程的输入输出流
//
//                int exitCode = process.waitFor();
//                System.out.println("Appium server has exited with code: " + exitCode);
//            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
    }

    public void shutdown() {
        executor.shutdown();
    }

}
