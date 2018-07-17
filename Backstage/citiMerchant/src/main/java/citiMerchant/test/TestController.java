package citiMerchant.test;

import citiMerchant.vo.Record;
import citiMerchant.vo.RecordOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


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

        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        try {
            date = format.parse("2018-07-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String start_day = format.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        String end_day = format.format(calendar.getTime());

        java.sql.Date start = new java.sql.Date(testService.Date2UNIXTime(start_day));
        java.sql.Date end = new java.sql.Date(testService.Date2UNIXTime(end_day));
        RecordOrder recordOrder = testService.getOrderRecord_ByDate("00002", start, end);
        System.out.println("totalPoints of orders from 2018-07-09 to 2018-07-12 of merchantID\"00002\": " + recordOrder.getTotalPoints());


    }




}
