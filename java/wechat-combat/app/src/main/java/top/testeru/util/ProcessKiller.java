package top.testeru.util;

import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class ProcessKiller {
    static final Logger logger = getLogger(lookup().lookupClass());

    public static String getProcessId(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                logger.info("line: {}",line);
                // 根据命令输出获取 PID
                if (line.contains("LISTEN")) {
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length > 1) {
                        logger.info("parts[1]: {}",parts[1]);

                        return parts[1];
                    }
                }
            }

            reader.close();
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void killProcess(String cmd, String pid) {
//        String command = "kill -9 " + pid;
        String command = cmd + pid;
        logger.info("killProcess:{}", command);
        try {
            Process process = Runtime.getRuntime().exec(command);
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                logger.info("Process with PID {} has been killed.", pid);
            } else {
                logger.info("Failed to kill process with PID {}", pid);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
