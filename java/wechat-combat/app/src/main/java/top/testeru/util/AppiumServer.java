package top.testeru.util;


import org.slf4j.Logger;
import top.testeru.util.AppiumServerExecutor;
import top.testeru.util.DirectoryUtil;
import top.testeru.util.ProcessKiller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class AppiumServer{
    static final Logger logger = getLogger(lookup().lookupClass());


    private static AppiumServer instance;
    private static AppiumServerExecutor appiumServerExecutor;
    public AppiumServer() {
        appiumServerExecutor = new AppiumServerExecutor();
    }

    // 创建一个线程来执行命令
    String port = "4723";

    public static AppiumServer getInstance() {
        if (instance == null) {
            // 如果 instance 为空，创建一个实例
            synchronized (AppiumServer.class) {
                if (instance == null) {
                    instance = new AppiumServer();
                }
            }
        }
        return instance;
    }
    private void killPort(String port){
        String os = System.getProperty("os.name");
        logger.info("当前操作系统：{}", os);
        if (os.toLowerCase().contains("mac")){
            // 使用lsof命令获取指定端口的进程PID
            String command = "lsof -i tcp:" + port;
            // 使用lsof命令获取指定端口的进程PID
            String  killCommand = "kill -9 ";
            String pid = ProcessKiller.getProcessId(command);
            if (pid != null) {
                ProcessKiller.killProcess(killCommand, pid);
            }
        }else if(os.toLowerCase().contains("windows")){
            String command = "cmd.exe /c netstat -ano | findstr : " + port;
            String killCommand = "cmd.exe /c taskkill /F /PID ";
            String pid = ProcessKiller.getProcessId(command);
            if (pid != null) {
                ProcessKiller.killProcess(killCommand, pid);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void startServer(){
        //查看appium端口是否被占用，如果占用删除
        killPort(port);
        long l = System.currentTimeMillis();

        //命令行启动appium server
        String commandLine = "appium -g appiumserver" + l + ".log --port " + port;

        appiumServerExecutor.startAppiumServer(commandLine);

        // 等待一段时间，让 Appium 服务器运行
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown(){
        appiumServerExecutor.shutdown();
        //查看appium端口是否被占用，如果占用删除
        killPort(port);
    }
}
