package citiMerchant.test;

import citiMerchant.vo.Record;
import citiMerchant.vo.RecordOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;


@Controller
public class TestController {
    @Autowired
    TestService testService;


    @RequestMapping("/test")
    public void getRecord() {
        System.out.println("------------------------------------------------------------\n");
        Record record1 = testService.getCouponRecord("00001", 7);
        System.out.println("totalPoints of coupons in 7 days of merchantID\"00001\": " + record1.getTotalPoints());
        Record record2 = testService.getOrderRecord("00002", 7);
        System.out.println("totalPoints of orders in 7 days of merchantID\"00002\": " + record2.getTotalPoints());
        Date start = new Date(1531065600L * 1000);
        Date end = new Date(1531324800L * 1000);
        RecordOrder recordOrder = testService.getOrderRecord_ByDate("00002", start, end);
        System.out.println("totalPoints of orders from 2018-07-09 to 2018-07-12 of merchantID\"00002\": " + recordOrder.getTotalPoints());
    }


}
