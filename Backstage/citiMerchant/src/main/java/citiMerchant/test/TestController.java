package citiMerchant.test;

import citiMerchant.vo.Record;
import citiMerchant.vo.RecordOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Controller
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/test")
    public void getRecord() {
        System.out.println("\n------------------------------------------------------------\n");
        Record record1 = testService.getCouponRecord("123", 7);
        System.out.println("totalPoints of coupons in 7 days of merchantID\"123\": " + record1.getTotalPoints());

        System.out.println("\n------------------------------------------------------------\n");
        Record record2 = testService.getOrderRecord("00002", 7);
        System.out.println("totalPoints of orders in 7 days of merchantID\"00002\": " + record2.getTotalPoints());

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("\ntotalPoints of orders in 2018 of merchantID\"00002\":\n");
        List<Long> points = show_order_points_chronology("00002", "2018");
        for (Long l : points) {
            System.out.print(l + "\t");
        }
        System.out.println("\n------------------------------------------------------------\n");

    }


    //year should be like "2017", "2018", the caller should promise the "year" meets the format.
    //return 12 Integers, which represents the total points of 12 months respectively.
    public List<Long> show_order_points_chronology(final String merchantID, final String year) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        try {
            date = format.parse(year + "-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        List<Long> points = new ArrayList<>(12);

        for (int i = 0; i < 12; ++i) {
            String start_day = format.format(calendar.getTime());
            calendar.add(Calendar.MONTH, 1);
            String end_day = format.format(calendar.getTime());
            java.sql.Date start = new java.sql.Date(testService.Date2UNIXTime(start_day));
            java.sql.Date end = new java.sql.Date(testService.Date2UNIXTime(end_day));
            RecordOrder recordOrder = testService.getOrderRecord_ByDate(merchantID, start, end);
            //System.out.println(start_day + " to " + end_day + " " + recordOrder.getTotalPoints());//
            points.add(recordOrder.getTotalPoints() == null ? 0 : recordOrder.getTotalPoints());
        }
        return points;
    }

}
