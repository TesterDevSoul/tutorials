package top.testeru.appium;

import org.junit.jupiter.api.Test;
import top.testeru.util.DirectoryUtil;

//截图文件清空
public class PngTest {

    DirectoryUtil directoryUtil = new DirectoryUtil();
    String pngPath = "png";
    @Test
    public void deMkdir(){
        //查看截图文件是否存在，如果存在删除
        directoryUtil.deletedir(pngPath);
        //创建截图文件
        directoryUtil.mkdir(pngPath);
    }


}

