/***********     [[Deprecated]]     ***********
 * This file has been deprecated,
 * since the type of state has been changed
 * from "OrderState" to "String".
------------------------------------------------------------------------
package citi.dao;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TypeHandler for enum-type OrderState
public class OrderStateTypeHandler extends BaseTypeHandler<Order.OrderState> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, Order.OrderState orderState, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(columnIndex, Order.OrderState.getStateString(orderState));
    }

    @Override
    public Order.OrderState getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放String类型
        String value = resultSet.getString(columnName);
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的value值，定位enum
            return Order.OrderState.getOrderState(value);
        }
    }

    @Override
    public Order.OrderState getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放String类型
        String value = resultSet.getString(columnIndex);
        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的value值，定位enum
            return Order.OrderState.getOrderState(value);
        }
    }

    @Override
    public Order.OrderState getNullableResult(CallableStatement callableStatement, int columnIndex) throws
            SQLException {
        String value = callableStatement.getString(columnIndex);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            // 根据数据库中的value值，定位enum
            return Order.OrderState.getOrderState(value);

        }
    }
}
*/