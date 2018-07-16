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
        Record record = testService.getCouponRecord("00001", 7);
        System.out.println(record.getTotalPoints());
    }


}
