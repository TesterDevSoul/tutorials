/*
 * @Author: 霍格沃兹测试开发学社-盖盖
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */
package top.testeru.member;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class FileTest {

    @Test
    public void delete() throws IOException {
        File file = Paths.get("png").toFile();
        if(file.exists() && file.isDirectory()){
            System.out.println("png");
//            boolean delete = file.delete();
            FileUtils.deleteDirectory(file);
//            System.out.println(delete);
//            logger.info(" png 文件夹删除");
        }else {
            System.out.println("png 文件夹删除失败");
//            logger.info(" png 文件夹删除失败");

        }
    }
}
