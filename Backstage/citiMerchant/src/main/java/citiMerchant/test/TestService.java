package citiMerchant.test;

import citiMerchant.mapper.Record;
import citiMerchant.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    RecordMapper recordMapper;

    public Record getCouponRecord(String merchantID, int intervalDate) {
        Record record = new Record(merchantID, intervalDate);
        recordMapper.coupon_record(record);
        return record;
    }

    public Record getOrderRecord(String merchantID, int intervalDate) {
        Record record = new Record(merchantID, intervalDate);
        recordMapper.order_record(record);
        return record;
    }


}
