package citiMerchant.mapper;

import citiMerchant.vo.Record;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordMapper {

    final String coupon_record = "call coupon_record(#{IN_MerchantID, mode = IN, jdbcType = VARCHAR }, #{IN_intervalDate, mode = INT, jdbcType = INT}, #{totalPoints, mode = OUT, jdbcType = BIGINT})";
    final String order_record = "call order_record(#{IN_MerchantID, mode = IN, jdbcType = VARCHAR }, #{IN_intervalDate, mode = INT, jdbcType = INT}, #{totalPoints, mode = OUT, jdbcType = BIGINT})";

    @Select(value = coupon_record)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(Record.class)
    void coupon_record(Record record);

    @Select(value = order_record)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(Record.class)
    void order_record(Record record);


    

}


