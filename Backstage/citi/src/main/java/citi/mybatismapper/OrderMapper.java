package citi.mybatismapper;

import citi.dao.OrderDAO;
//import citi.dao.OrderStateTypeHandler;
import citi.vo.OrderItem;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    final String getByOrderID = "SELECT * FROM order WHERE OrderID = #{orderID}";
    final String addOrder = "INSERT INTO order(orderID, originalPrice, priceAfter, pointsNeeded, userID, state, merchantID, time) " +
            "VALUES(#{orderId}, #{originalPrice}, #{priceAfter}, #{pointsNeeded}, #{userId}, #{state}, #{merchantId}, #{time})";
    final String getOrderIDByUserID = "SELECT orderID FROM order WHERE userID = #{userID}";

    @Select(getByOrderID)
//    @Results({
//            @Result(column = "state", property = "state", jdbcType = JdbcType.VARCHAR, javaType = OrderDAO.OrderState.class,
//                    typeHandler = OrderStateTypeHandler.class)
//    })
    OrderDAO selectOrderByID(String orderID);

    @Insert(addOrder)
//    @Results({
//            @Result(column = "state", property = "state", jdbcType = JdbcType.VARCHAR, javaType = OrderDAO.OrderState.class,
//                    typeHandler = OrderStateTypeHandler.class)
//    })
    int addOrder(OrderDAO order);

    @Select(getOrderIDByUserID)
    List<String> getOrderIDByUserID(String userID);

    /* Below are used for handle items in one order.
     *     addOrderItem      :  add OrderItems in one order.
     *     getItemByOrderID  :  get all OrderItems in one order.
     */

    final String addOrderItem = "INSERT INTO orderitem(OrderID, ItemID, Nums) " +
            "VALUES(#{orderID}, #{itemID}, #{num})";
    final String getItemByOrderID = "SELECT * FROM orderitem WHERE OrderID = #{orderID}";

    @Insert(addOrder)
    int addOrderItem(OrderItem orderItem);

    @Select(getItemByOrderID)
    List<OrderItem> getItemByOrderID(String orderID);

}
