package citi.funcModule.Recommend;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class RecommendTask {
    @Scheduled(cron="0/30 * * * * ? ") //间隔5秒执行
    public void test(){
        System.out.println("定时任务开始啦，哈哈哈");
        System.out.println("<<<---------结束执行HR数据同步任务--------->>>");
    }
}
