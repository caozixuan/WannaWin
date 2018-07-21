package citi.funcModule.order;



import citi.vo.Order;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/order"},produces = {"text/html;charset=UTF-8"})
public class OrderController {

    @Autowired
    private Gson gson;
    @Autowired
    private OrderService orderService;

    /*
     * url:order/getOrders
     * params: userID, intervalTime
     * return:[{"merchantName":"KFC","orderId":"0002","originalPrice":400.0,"priceAfter":333.0,"pointsNeeded":200.0,"userId":"5503b50f-2312-4156-92b3-ec6dcea74656","state":"SUCCESS","merchantId":"00002","time":"Jul 9, 2018 3:30:00 PM"}]
     */
    @ResponseBody
    @RequestMapping("/getOrders")
    public String getOrders(String userID, String intervalTime){
        List<Order> orders = orderService.getOrders(userID,intervalTime);
        ArrayList<ReturnOrder> returnOrders = orderService.changeToReturnOrders(orders);
        if(orders==null)
            return "[]";
        return gson.toJson(returnOrders);
    }

    @ResponseBody
    @RequestMapping("/getSuccessOrders")
    public String getSuccessOrders(String userID, String intervalTime){
        List<Order> orders = orderService.getOrders(userID,intervalTime);
        ArrayList<ReturnOrder> returnOrders = orderService.changeToReturnOrders(orders);
        ArrayList<ReturnOrder> returnSuccessOrders = new ArrayList<ReturnOrder>();
        for(ReturnOrder returnOrder:returnOrders){
            if(returnOrder.getState()== Order.OrderState.SUCCESS){
                returnSuccessOrders.add(returnOrder);
            }
        }
        if(orders==null)
            return "[]";
        return gson.toJson(returnSuccessOrders);
    }

    @ResponseBody
    @RequestMapping("/getToBeFinishedOrders")
    public String getUnfinishedOrders(String userID, String intervalTime){
        List<Order> orders = orderService.getOrders(userID,intervalTime);
        ArrayList<ReturnOrder> returnOrders = orderService.changeToReturnOrders(orders);
        ArrayList<ReturnOrder> returnToBeFinishedOrders = new ArrayList<ReturnOrder>();
        for(ReturnOrder returnOrder:returnOrders){
            if(returnOrder.getState()== Order.OrderState.TOBEFINISHED){
                returnToBeFinishedOrders.add(returnOrder);
            }
        }
        if(orders==null)
            return "[]";
        return gson.toJson(returnToBeFinishedOrders);
    }

    @ResponseBody
    @RequestMapping("/getFailOrders")
    public String getFailOrders(String userID, String intervalTime){
        List<Order> orders = orderService.getOrders(userID,intervalTime);
        ArrayList<ReturnOrder> returnOrders = orderService.changeToReturnOrders(orders);
        ArrayList<ReturnOrder> returnFailOrders = new ArrayList<ReturnOrder>();
        for(ReturnOrder returnOrder:returnOrders){
            if(returnOrder.getState()== Order.OrderState.FAIL){
                returnFailOrders.add(returnOrder);
            }
        }
        if(orders==null)
            return "[]";
        return gson.toJson(returnFailOrders);
    }
}
