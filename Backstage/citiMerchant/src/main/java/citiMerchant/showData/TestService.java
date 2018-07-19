package citiMerchant.showData;

import citiMerchant.mapper.DBHandler;
import citiMerchant.mapper.RecordMapper;
import citiMerchant.vo.Merchant_coupon_record;
import citiMerchant.vo.Record;
import citiMerchant.vo.RecordOrder;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class TestService {

    final RecordMapper recordMapper = DBHandler.recordMapper;

    final Gson gson = new Gson();


    public Record getCouponRecord(String merchantID, int intervalDate) {
        Record record = new Record(merchantID, intervalDate);
        recordMapper.coupon_record(record);
        return record;
    }


    public RecordOrder getOrderRecord_ByDate(String merchantID, Date start_time, Date end_time) {
        RecordOrder recordOrder = new RecordOrder(merchantID, start_time, end_time);
        recordMapper.order_record_date(recordOrder);
        return recordOrder;
    }


    public Record getOrderRecord(String merchantID, int intervalDate) {
        Record record = new Record(merchantID, intervalDate);
        recordMapper.order_record(record);
        return record;
    }


    public RecordOrder getPointsHistory(String merchantID, Date start_time, Date end_time) {
        RecordOrder recordOrder = new RecordOrder(merchantID, start_time, end_time);
        recordMapper.MSCard_record_date(recordOrder);
        return recordOrder;
    }


    //format is set default to "yyyy-MM-dd HH:mm:ss"
    //@return "...ms" since 1970-01-01
    public static long Date2TimeStamp(String dateStr, String format) {
        if (format == null)
            format = "yyyy-MM-dd HH:mm:ss";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr).getTime();// / 1000;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Date2TimeStamp fails\n");
        }
        return 0L;
    }

    //return "...ms" since 1970-01-01
    public static long Date2UNIXTime(String dateStr) {
        return Date2TimeStamp(dateStr, "yyyy-MM-dd");
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
            java.sql.Date start = new java.sql.Date(Date2UNIXTime(start_day));
            java.sql.Date end = new java.sql.Date(Date2UNIXTime(end_day));
            RecordOrder recordOrder = getOrderRecord_ByDate(merchantID, start, end);
            //System.out.println(start_day + " to " + end_day + " " + recordOrder.getTotalPoints());//
            points.add(recordOrder.getTotalPoints() == null ? 0 : recordOrder.getTotalPoints());
        }
        return points;
    }


    public List<Long> getMonthTimeStamp(String year) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        try {
            date = format.parse(year + "-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Long> timestamp = new ArrayList<>(12);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 0; i < 12; ++i) {
            String start_day = format.format(calendar.getTime());
            calendar.add(Calendar.MONTH, 1);
            timestamp.add(Date2UNIXTime(start_day));
        }
        return timestamp;
    }


    public List<Long> show_points_exchange_chronology(final String merchantID, final String year) {
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
            java.sql.Date start = new java.sql.Date(Date2UNIXTime(start_day));
            java.sql.Date end = new java.sql.Date(Date2UNIXTime(end_day));
            RecordOrder recordOrder = getPointsHistory(merchantID, start, end);
            //System.out.println(start_day + " to " + end_day + " " + recordOrder.getTotalPoints());//
            points.add(recordOrder.getTotalPoints() == null ? 0 : recordOrder.getTotalPoints());
        }
        return points;
    }


    //
    public List<List<Merchant_coupon_record>> show_Merchant_coupon_record(final String merchantID, final String year) {
        List<List<Merchant_coupon_record>> merchant_coupon_record = new ArrayList<>();
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        try {
            date = format.parse(year + "-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 0; i < 12; ++i) {
            String start_day = format.format(calendar.getTime());
            calendar.add(Calendar.MONTH, 1);
            String end_day = format.format(calendar.getTime());
            java.sql.Date start = new java.sql.Date(Date2UNIXTime(start_day));
            java.sql.Date end = new java.sql.Date(Date2UNIXTime(end_day));
            List<Merchant_coupon_record> month_conpon_record = recordMapper.merchant_coupon_record_date(merchantID, start, end);
            merchant_coupon_record.add(month_conpon_record);
        }
        return merchant_coupon_record;
    }


    public boolean isPrapared(String merchantID) {
        return Session_store.getSession(merchantID).getAttribute("method_prepare") != null;
    }


    //prepare satistics information
    public void prepare(String merchantID) {

        HttpSession session = Session_store.getSession(merchantID);
        if (session.getAttribute("method_prepare") != null)
            return;

        //judge whether the method has been invoked
        session.setAttribute("method_prepare", "233");


        //prepare statistics information
        String year = "" + Calendar.getInstance().get(Calendar.YEAR);

        List<Long> points = show_order_points_chronology(merchantID, year);
        List<Long> timestamp = getMonthTimeStamp(year);
        List<Long> points_exchange = show_points_exchange_chronology(merchantID, year);

        Long total_order_points = 0L;
        Long total_points_exchange = 0L;
        for (Long l : points) total_order_points += l;
        for (Long l : points_exchange) total_points_exchange += l;

        List<List<Merchant_coupon_record>> merchant_coupon_record = show_Merchant_coupon_record(merchantID, year);
        Long total_merchant_coupon_record = 0L;
        for (List<Merchant_coupon_record> list : merchant_coupon_record)
            for (Merchant_coupon_record record : list)
                total_merchant_coupon_record += record.getTotalPoints();


        //set attribute
        String points_json = gson.toJson(points);
        session.setAttribute("points_json", points_json);

        String timeStamp_json = gson.toJson(timestamp);
        session.setAttribute("timeStamp_json", timeStamp_json);

        String points_exchange_json = gson.toJson(points_exchange);
        session.setAttribute("points_exchange_json", points_exchange_json);

        String total_order_points_json = gson.toJson(total_order_points);
        session.setAttribute("total_order_points_json", total_order_points_json);
        String total_points_exchange_json = gson.toJson(total_points_exchange);
        session.setAttribute("total_points_exchange_json", total_points_exchange_json);
        String total_merchant_coupon_record_json = gson.toJson(total_merchant_coupon_record);
        session.setAttribute("total_merchant_coupon_record_json", total_merchant_coupon_record_json);

        //String merchant_coupon_record_json = gson.toJson(merchant_coupon_record);
        //session.setAttribute("merchant_coupon_record_json", merchant_coupon_record_json);

    }//end method void prepare(HttpSession session);


}
