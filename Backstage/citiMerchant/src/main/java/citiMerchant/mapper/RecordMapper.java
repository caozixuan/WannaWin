package citiMerchant.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordMapper {

    final String coupon_record = "call coupon_record(#{IN_MerchantID, mode = IN, jdbcType = VARCHAR }, #{IN_intervalDate, mode = INT, jdbcType = INT}, #{totalPoints, mode = OUT, jdbcType = BIGINT})";
    final String order_record = "call order_record(#{IN_MerchantID, mode = IN, jdbcType = VARCHAR }, #{IN_intervalDate, mode = INT, jdbcType = INT}, #{totalPoints, mode = OUT, jdbcType = BIGINT})";


    @Select(coupon_record)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(Record.class)
    void coupon_record(Record record);

    @Select(order_record)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(Record.class)
    void order_record(Record record);

}

class Record {

    public String IN_MerchantID;
    public int IN_intervalDate;
    public long totalPoints;

    public Record(long totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Record(String IN_MerchantID, int IN_intervalDate) {
        this.IN_MerchantID = IN_MerchantID;
        this.IN_intervalDate = IN_intervalDate;
        this.totalPoints = -1;
    }

}


