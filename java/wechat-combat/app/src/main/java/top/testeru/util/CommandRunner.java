package top.testeru.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//线程池管理线程
public class CommandRunner {

    private ExecutorService executor;
    public CommandRunner() {
        // 创建固定大小的线程池，这里设置最多同时执行3个线程
        executor = Executors.newFixedThreadPool(3);
    }

    public List<String> runCommand(String command) {
        List<String> result = new ArrayList<>();

        executor.execute(() -> {
            try {
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    // 处理命令输出
                    System.out.println("命令输出:" + line);

                    if (line.contains("LISTEN")) {
                        System.out.println("有端口使用");
                        // 提取PID
                        String[] columns = line.split("\\s+");
                        System.out.println("columns: " + columns);
                        String pid = columns[1];
                        System.out.println(pid);
                    }

                    result.add(line);
                }

                reader.close();
                process.destroy();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("result:" + result);
        return result;
    }

    public void shutdown() {
        // 关闭线程池
        executor.shutdown();
    }

    public static void main(String[] args) {
        CommandRunner commandRunner = new CommandRunner();

        // 提交要执行的命令
        commandRunner.runCommand("ls -l");
        commandRunner.runCommand("ps aux");
        commandRunner.runCommand("echo Hello, World!");

        // 关闭线程池
        commandRunner.shutdown();
    }
}

