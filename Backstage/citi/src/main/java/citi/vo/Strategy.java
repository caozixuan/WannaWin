package citi.vo;

import java.util.ArrayList;

/*
 * 作者：曹子轩
 * 制定消费策略
 */
public class Strategy {
    private String merchantId;
    ArrayList<String> discountCommodityIDs;
    // 默认这三个应该是递增顺序
    ArrayList<Double> priceOriginal;
    ArrayList<Double> pricePaid;
    ArrayList<Integer> pointsNeed;

    public Strategy()
    {}


    public Strategy(String merchantId, ArrayList<String> discountCommodityIDs, ArrayList<Double> priceOriginal, ArrayList<Double> pricePaid, ArrayList<Integer> pointsNeed) {
        this.merchantId = merchantId;
        this.discountCommodityIDs = discountCommodityIDs;
        this.priceOriginal = priceOriginal;
        this.pricePaid = pricePaid;
        this.pointsNeed = pointsNeed;
    }
    // TODO:这里需要数据库相关操作，找到订单
    public static Strategy getStrategy(Order order){
        return new Strategy();
    }
    public static int isPointsEnough(int points,User user){
        if(points>user.getGeneralPoints()){
            return -2;
        }
        else if(points>user.getAvailablePoints()){
            return -1;
        }
        else if(points<=user.getAvailablePoints()){
            return points;
        }
        return points;
    }
    // TODO:这里返回一个json格式会更合理
    public static int returnPointsTobePaid(Order order, User user){
        Strategy strategy = getStrategy(order);
        int points = 0;
        if(order.getOriginalPrice()>=strategy.priceOriginal.get(strategy.priceOriginal.size()-1)){
            points = strategy.pointsNeed.get(strategy.pointsNeed.size()-1);
            points = isPointsEnough(points, user);
            order.setPointsNeeded(points);
        }
        for(int i=0; i<strategy.priceOriginal.size()-1;i++){
            if(order.getOriginalPrice()>=strategy.priceOriginal.get(i)&&order.getOriginalPrice()<strategy.priceOriginal.get(i+1)){
                points = strategy.pointsNeed.get(i);
                points = isPointsEnough(points, user);
                order.setPointsNeeded(points);
            }
        }
        return points;
    }
}
