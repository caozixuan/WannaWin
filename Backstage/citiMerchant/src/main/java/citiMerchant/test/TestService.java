package citiMerchant.test;

import citiMerchant.mapper.RecordMapper;
import citiMerchant.vo.Record;
import citiMerchant.vo.RecordOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

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


}
