package top.testeru;

import org.slf4j.Logger;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class Calculator {
    //获得具有所需名称的记录器
    static final Logger logger = getLogger(lookup().lookupClass());
    //用例名
    String name;
    //唯一ID标识
    String id;

    public Calculator(String name) {
        this.name = name;
        logger.info("创建 {} ", name);
    }


    public void initId(){
        id = UUID.randomUUID().toString();
        logger.info("生成ID：{} 并绑定", id);

    }
    public void destroyId() {
        if (id == null) {
            throw new IllegalArgumentException(name + " 没有初始化对应ID");
        }
        logger.info("ID: {} 释放", id);
        id = null;
    }

    public void close() {
        logger.info("关闭 {} ", name);
    }

    //连续添加
    public int sum(int... numbers) {
        if(Arrays.stream(numbers).anyMatch(u -> u == 100)){
            logger.warn("加数不可以是100！");
            //拦截，重置，发送某些消息给其他服务端
            throw new NumberFormatException("加数不可以是100！");
        }else if(Arrays.stream(numbers).anyMatch(u -> u > 99) | Arrays.stream(numbers).anyMatch(u -> u < -99)){
            logger.warn("请输入[-99,99]范围内的整数");
            throw new IllegalArgumentException("请输入[-99,99]范围内的整数！");
        }else {
            //[-99,99]
            return IntStream.of(numbers).sum();
        }
    }

    //连续添加
    public double sum(double... numbers) {
        if(Arrays.stream(numbers).anyMatch(u -> u == 100)){
            logger.warn("加数不可以是100！");
            //拦截，重置，发送某些消息给其他服务端
            throw new NumberFormatException("加数不可以是100！");
        }else if(Arrays.stream(numbers).anyMatch(u -> u > 99) | Arrays.stream(numbers).anyMatch(u -> u < -99)){
            logger.warn("请输入[-99,99]范围内的数");
            throw new IllegalArgumentException("请输入[-99,99]范围内的数！");
        }else {
            //[-99,99]
            return DoubleStream.of(numbers).sum();
        }
    }

    //从num进行减法
    public double sub(double num, double... numbers) {
        if(Arrays.stream(numbers).allMatch(u -> u > 99) | Arrays.stream(numbers).allMatch(u -> u < -99)){
            logger.warn("请输入[-99,99]范围内的整数");
            throw new IllegalArgumentException("请输入[-99,99]范围内的整数！");
        }else {
            return DoubleStream.of(numbers).reduce(num, (a, b) -> a-b);
        }
    }

    //从100进行减法
    public double sub(double... numbers) {
       return sub(100,numbers);
    }

    public int sub(int x, int y) {
        if(x>99 | x<-99 | y>99 | y<-99){
            logger.warn("请输入[-99,99]范围内的整数");
            //100-8
            return 0;
        }else {
            //99-8
            return x-y;
        }
    }

    //平均值 average
    public double average(double... numbers) {
        if(Arrays.stream(numbers).allMatch(u -> u > 99) | Arrays.stream(numbers).allMatch(u -> u < -99)){
            logger.warn("请输入[-99,99]范围内的整数");
            return 0;
        }else {
            return DoubleStream.of(numbers).average().getAsDouble();
        }
    }
    //连续拼接
    public String concatStr(String... words) {
        return String.join(" ", words);
    }
}
