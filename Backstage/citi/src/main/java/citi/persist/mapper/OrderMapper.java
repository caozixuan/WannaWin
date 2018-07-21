package citi.persist.mapper;

import citi.vo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

//import citi.dao.OrderStateTypeHandler;

@Repository
public interface OrderMapper {


    final String getByOrderID = "SELECT * FROM huaqi.order WHERE OrderID = #{orderID} ORDER BY Time DESC";
    final String addOrder = "INSERT INTO huaqi.order(orderID, originalPrice, priceAfter, pointsNeeded, userID, state, merchantID, time) " +
            "VALUES(#{orderId}, #{originalPrice}, #{priceAfter}, #{pointsNeeded}, #{userId}, #{state}, #{merchantId}, #{time})";
    final String getOrderIDByUserID = "SELECT orderID FROM huaqi.order WHERE userID = #{userID} AND Time >= now() - #{intervalTime} AND Time <= now()";
    final String getOrderIDByMerchantID = "SELECT orderID FROM huaqi.order WHERE MerchantID = #{merchantID} AND Time >= now() - #{intervalTime} AND Time <= now()";
    final String getOrderByUserID = "SELECT * FROM huaqi.order WHERE userID = #{userID} AND Time >= now() - #{intervalTime} AND Time <= now() ORDER BY Time DESC";
    final String getOrderByMerchantID = "SELECT * FROM huaqi.order WHERE MerchantID = #{merchantID} AND Time >= now() - #{intervalTime} AND Time <= now() ORDER BY Time DESC";

    @Select(getByOrderID)
    Order selectOrderByID(String orderID);

    @Insert(addOrder)
    int addOrder(Order order);


    /*
     * 当我们在给now（）+-一个时间的时候，其实应该这样理解的：
     *
     * +1/+01：加1秒钟
     *
     * +101/+0101：加1分钟1秒钟
     *
     * +10101/+010101：加1小时1分钟1秒钟
     *
     * +1010101/+01010101：加1天1分钟1秒钟
     *
     * +101010101/+0101010101：加1月1天1分钟1秒钟
     *
     * +1101010101/+010101010101：加1年1月1天1分钟1秒钟,这里要注意下，年这个部分可以是4位(高位没有的话会补零)：
     *
     */
    @Select(getOrderIDByUserID)
    List<String> getOrderIDByUserID(@Param("userID") String userID, @Param("intervalTime") String intervalTime);

    @Select(getOrderIDByMerchantID)
    List<String> getOrderIDByMerchantID(@Param("merchantID") String merchantID, @Param("intervalTime") String intervalTime);

    @Select(getOrderByUserID)
    List<Order> getOrderByUserID(@Param("userID") String userID, @Param("intervalTime") String intervalTime);

    @Select(getOrderByMerchantID)
    List<Order> getOrderByMerchantID(@Param("merchantID") String merchantID, @Param("intervalTime") String intervalTime);


    final String getOrderAmount = "SELECT COUNT(*) FROM huaqi.order WHERE MerchantID = #{merchantID}";

    @Select(getOrderAmount)
    int getOrderAmount(String merchantID);

    final String getOrderBy_ID_AND_TIMESTAMP = "SELECT * FROM huaqi.order WHERE UserID = #{userID} AND time = #{timestamp}";

    @Select(getOrderBy_ID_AND_TIMESTAMP)
    Order getOrderBy_ID_AND_TIMESTAMP(@Param("userID") String userID, @Param("timestamp") Timestamp timestamp);

}
