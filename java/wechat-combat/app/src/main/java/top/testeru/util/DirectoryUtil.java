package top.testeru.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class DirectoryUtil {
    static final Logger logger = getLogger(lookup().lookupClass());

    public void deletedir(String path) {
        try {
            File file = Paths.get(path).toFile();
//            File file = Paths.get("png").toFile();
            if(file.exists() && file.isDirectory()){
                FileUtils.deleteDirectory(file);
                logger.info("{} 文件夹删除", path);
//                logger.info(" png 文件夹删除");
            }else {
                logger.info("{} 文件夹不存在", path);
//                logger.info(" png 文件夹不存在");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mkdir(String path) {
        File file = Paths.get(path).toFile();
//        File file = Paths.get("png").toFile();
        if(!file.exists() || !file.isDirectory()){
            file.mkdirs();
            logger.info("创建 {} 文件夹", path);
//            logger.info("创建 png 文件夹");
        }else {
            logger.info("{} 文件夹已经存在", path);
//            logger.info(" png 文件夹已经存在");
        }
    }

}
