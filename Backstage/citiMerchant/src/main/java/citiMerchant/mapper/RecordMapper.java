package citiMerchant.mapper;

import citiMerchant.vo.Points_history_card;
import citiMerchant.vo.Points_history_user;
import citiMerchant.vo.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordMapper {

    final String coupon_record = "CALL coupon_record(#{IN_MerchantID, mode = IN, jdbcType = VARCHAR }, #{IN_intervalDate, mode = IN, jdbcType = INTEGER}, #{totalPoints, mode = OUT, jdbcType = BIGINT})";
    final String order_record = "CALL order_record(#{IN_MerchantID, mode = IN, jdbcType = VARCHAR }, #{IN_intervalDate, mode = IN, jdbcType = INTEGER}, #{totalPoints, mode = OUT, jdbcType = BIGINT})";
    final String _points_history_user = "CALL points_record_user(#{IN_cardID, mode = IN, jdbcType = VARCHAR}, #{IN_intervalDate, mode = IN, jdbcType = INTEGER}, #{totalPoints_card, mode = OUT, jdbcType = BIGINT}, #{totalPoints_citi, mode = OUT, jdbcType = BIGINT})";
    final String _points_history_card = "CALL points_record_card(#{IN_userID, mode = IN, jdbcType = VARCHAR}, #{IN_merchantID, mode = IN, jdbcType = VARCHAR}, #{IN_intervalDate, mode = IN, jdbcType = INTEGER}, #{totalPoints_card, mode = OUT, jdbcType = BIGINT}, #{totalPoints_citi, mode = OUT, jdbcType = BIGINT})";
    final String merchant_out_points_record = "CALL merchant_out_points_record(#{IN_MerchantID, mode = IN, jdbcType = VARCHAR }, #{IN_intervalDate, mode = IN, jdbcType = INTEGER}, #{totalPoints, mode = OUT, jdbcType = BIGINT})";

    //根据商户id查询优惠券积分使用
    @Insert(value = coupon_record)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(Record.class)
    void coupon_record(Record record);

    //根据商户id查询订单积分使用
    @Insert(value = order_record)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(Record.class)
    void order_record(Record record);

    //根据用户id查询积分兑换情况
    @Insert(_points_history_user)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(Points_history_user.class)
    void points_history_user(Points_history_user points_history_user);

    //根据用户id和商户id查询会员卡积分兑换情况
    @Insert(value = _points_history_card)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(Points_history_card.class)
    void points_history_card(Points_history_card points_history_card);

    //根据商户id查询用户用了多少商家积分去兑换
    @Insert(value = merchant_out_points_record)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(Record.class)
    void merchant_out_points_record(Record record);

}


