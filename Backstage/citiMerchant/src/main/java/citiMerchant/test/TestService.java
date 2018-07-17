package citiMerchant.test;

import citiMerchant.mapper.RecordMapper;
import citiMerchant.vo.Record;
import citiMerchant.vo.RecordOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Service
public class TestService {
    @Autowired
    RecordMapper recordMapper;

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
        return 0l;
    }

    //return "...ms" since 1970-01-01
    public static long Date2UNIXTime(String dateStr) {
        return Date2TimeStamp(dateStr, "yyyy-MM-dd");
    }


}
