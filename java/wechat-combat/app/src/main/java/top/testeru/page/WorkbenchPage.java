package top.testeru.page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import static java.lang.Thread.sleep;

//工作台页面
public class WorkbenchPage extends AppBasePage{

    public WorkbenchPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }


    public String daka(){

        for (int i = 0; i < 10; i++) {
            try{
                find(AppiumBy.cssSelector("[text=\"打卡\"]"));
                break;
            }catch (Exception e){
                upAndDown(0.7, 0.3);
            }
        }

        //4、点击打卡
        click(AppiumBy.xpath("//*[@text=\"打卡\"]"));

        //5、显示等待动态加载   你已在打卡范围内
        waitUtil().until(webDriver -> webDriver.getPageSource().contains("你已在打卡范围内"));

        //6、点击上班打卡/下班打卡   contains 班打卡
        click(AppiumBy.xpath("//*[contains(@text,\"班打卡\")]"),1);

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //7、获取pagesource 正常    获取元素包含  正常
        String text = text(AppiumBy.xpath("//android.view.View/following-sibling::*//*[@class=\"android.widget.TextView\"]"));
        return text;
    }
}
