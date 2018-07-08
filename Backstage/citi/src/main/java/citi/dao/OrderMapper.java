package citi.dao;

import citi.vo.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    Order select(String orderID);
}
