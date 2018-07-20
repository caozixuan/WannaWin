package citiMerchant.mapper;

import citiMerchant.vo.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RecordMapper {

    final String coupon_record = "CALL coupon_record(#{IN_MerchantID, mode = IN, jdbcType = VARCHAR }, #{IN_intervalDate, mode = IN, jdbcType = INTEGER}, #{totalPoints, mode = OUT, jdbcType = BIGINT})";
    final String order_record = "CALL order_record(#{IN_MerchantID, mode = IN, jdbcType = VARCHAR }, #{IN_intervalDate, mode = IN, jdbcType = INTEGER}, #{totalPoints, mode = OUT, jdbcType = BIGINT})";
    final String order_record_date = "CALL order_record_date(#{IN_MerchantID, mode = IN, jdbcType = VARCHAR}, #{IN_start_date, mode = IN, jdbcType = DATE}, #{IN_end_date, mode = IN, jdbcType = DATE}, #{totalPoints, mode = OUT, jdbcType = BIGINT})";
    final String _points_history_user = "CALL points_record_user(#{IN_cardID, mode = IN, jdbcType = VARCHAR}, #{IN_intervalDate, mode = IN, jdbcType = INTEGER}, #{totalPoints_card, mode = OUT, jdbcType = BIGINT}, #{totalPoints_citi, mode = OUT, jdbcType = BIGINT})";
    final String _points_history_card = "CALL points_record_card(#{IN_userID, mode = IN, jdbcType = VARCHAR}, #{IN_merchantID, mode = IN, jdbcType = VARCHAR}, #{IN_intervalDate, mode = IN, jdbcType = INTEGER}, #{totalPoints_card, mode = OUT, jdbcType = BIGINT}, #{totalPoints_citi, mode = OUT, jdbcType = BIGINT})";
    final String merchant_out_points_record = "CALL merchant_out_points_record(#{IN_MerchantID, mode = IN, jdbcType = VARCHAR }, #{IN_intervalDate, mode = IN, jdbcType = INTEGER}, #{totalPoints, mode = OUT, jdbcType = BIGINT})";
    final String MSCard_record_date = "CALL MSCard_record_date(#{IN_MerchantID, mode = IN, jdbcType = VARCHAR}, #{IN_start_date, mode = IN, jdbcType = DATE}, #{IN_end_date, mode = IN, jdbcType = DATE}, #{totalPoints, mode = OUT, jdbcType = BIGINT})";
    final String merchant_coupon_record_date = "SELECT name, SUM(points) AS totalPoints " +
            "FROM coupon_view NATURAL JOIN (SELECT ItemID, item.name, points FROM item WHERE MerchantID = #{merchantID}) AS item_tmp " +
            "WHERE DATE(getTime) BETWEEN #{start_date} AND #{end_date} " +
            "GROUP BY ItemID, name";

    //根据"商户id" 和 "间隔时间" 查询使用积分兑换优惠券情况
    @Insert(value = coupon_record)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(Record.class)
    void coupon_record(Record record);

    //根据 "商户id" 和 "间隔时间" 查询使用积分抵扣订单情况
    @Insert(value = order_record)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(Record.class)
    void order_record(Record record);

    //根据 "商户id"，"起始时间"，"终止时间" 查询订单积分使用
    @Insert(value = order_record_date)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(RecordOrder.class)
    void order_record_date(RecordOrder recordOrder);

    //根据 "用户id" 和 "间隔时间" 查询积分兑换情况
    @Insert(value = _points_history_user)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(Points_history_user.class)
    void points_history_user(Points_history_user points_history_user);

    //根据 "用户id", "商户id" 和 "间隔时间" 查询会员卡积分兑换情况
    @Insert(value = _points_history_card)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(Points_history_card.class)
    void points_history_card(Points_history_card points_history_card);

    //根据商户id查询用户用了多少商家积分去兑换
    @Insert(value = merchant_out_points_record)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(Record.class)
    void merchant_out_points_record(Record record);

    //根据 "商户id"，"起始时间"，"终止时间" 查询会员卡积分兑换情况
    @Insert(value = MSCard_record_date)
    @Options(statementType = StatementType.CALLABLE)
    @ResultType(RecordOrder.class)
    void MSCard_record_date(RecordOrder eecordOrder);

    //根据 "商户id"，"起始时间"，"终止时间" 查询使用积分兑换优惠券情况
    @Select(merchant_coupon_record_date)
    List<Merchant_coupon_record> merchant_coupon_record_date(@Param("merchantID") String merchantID, @Param("start_date") Date start_date, @Param("end_date") Date end_date);

}
