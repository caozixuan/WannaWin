package citiMerchant.test;

import citiMerchant.vo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TestController {
    @Autowired
    TestService testService;


    @RequestMapping("/test")
    public void getRecord() {
        System.out.println("aaaaa");
        Record record1 = testService.getCouponRecord("00001", 7);
        System.out.println("totalPoints of coupons in 7 days: " + record1.getTotalPoints());
        Record record2 = testService.getOrderRecord("00002", 7);
        System.out.println("totalPoints of orders in 7 days: " + record2.getTotalPoints());

    }


}
