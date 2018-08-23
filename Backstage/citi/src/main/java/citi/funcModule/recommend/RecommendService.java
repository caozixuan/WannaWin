package citi.funcModule.recommend;

import citi.persist.mapper.*;
import citi.persist.procedure.probean.ItemBean;
import citi.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RecommendService {
    //需要访问到用户的喜好、用户的访问记录、商品的基本信息
    //@Autowired
    //private PrefMapper prefMapper;
    //@Autowired
    //private RecordMapper recordMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private VisitRecordMapper visitRecordMapper;
    @Autowired
    private VisitRecordUtil visitRecordUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private  UserPrefMapper userPrefMapper;

    /**
     * 初始化用户的偏好列表
     * @param prefList
     * @return true/false
     */
    public boolean initPref(String userID,ArrayList<Type.ItemType> prefList){
        if(userPrefMapper.addUserPref(new UserPref(userID,prefList))!=1){
            return false;
        }
        return true;
    }

    public boolean isInvestigated(String userID){
        UserPref userPref = userPrefMapper.getUserPref(userID);
        if(userPref!=null){
            List<Type.ItemType> itemTypes = userPref.getPrefTypeList();
            for(Type.ItemType itemType:itemTypes){
                System.out.println(itemType);
            }
            return true;
        }
        return false;
    }

    /**
     * 添加用户浏览记录
     * @param userID
     * @param itemID
     * @param time
     * @return true/false
     */
    public boolean addRecord(String userID, String itemID, Timestamp time){
        /**
         * 接口一：RecordMapper-----addRecord()
         * 参数一：userID,参数二：itemID,参数三：该浏览记录的时间戳
         * 返回受影响的行数
         */
      //if(recordMapper.addRecord(userID,itemID,time)!=1)
        //  return false;
      return true;
    }


    /**
     * 返回用户的推荐商品列表
     * @param userID
     * @param num
     * @return ArrayList<Item>
     */
    public ArrayList<ItemBean> getRecommendedItems(String userID,int num){
        ArrayList<UserItemPoints> userItemPointsArrayList = getUserInterestToItems(userID);
        ArrayList<ItemBean> items = new ArrayList<ItemBean>();
        for(int i =0;i<num;i++){
            String itemID = userItemPointsArrayList.get(i).itemID;
            Item item = itemMapper.getItemByItemID(itemID);
            Merchant merchant = merchantMapper.selectByID(item.getMerchantID());
            ItemBean itemBean = new ItemBean(item,merchant.getName(),merchant.getMerchantLogoURL());
            items.add(itemBean);
        }
        return items;
    }
    /**
     * 返回用户对商品喜好评分
     * @param userID
     * @return ArrayList<Merchant>
     */
    public double getItemPoints(String userID, String itemID){
        /*


        double points = 0;
        // 目前制定的积分策略：购买一次得5分+浏览一次得1分+每符合一个喜好得5分
        // TODO:这里需要数据库写两个vo类还有对应mapper里的方法
        List<Order> orderList = orderMapper.getOrderByUserAndItemID(userID,"+010101010101");
        List<UserPref> prefList = prefMapper.getPrefByUserID(userID);
        List<Record> recordList = recordMapper.getRecordByUserID(userID);
        Item item = itemMapper.getItemByItemID(itemID);
        points += 5*orderList.size();
        points += recordList.size();
        for(int i=0;i<prefList.size();i++){
            if(prefList.get(i).getPref().equals(item.getItemType())){
                points+=5;
            }
        }
        return points;
        */
        double points = 0;
        double buy_times = 0;
        Item item = itemMapper.getItemByItemID(itemID);
        Integer visitTimes = VisitRecordUtil.getVisitTimesBy_userID_AND_itemID(userID,itemID);
        List<UserCoupon> userCoupons = couponMapper.getCouponsByUserID(userID);
        for(UserCoupon userCoupon:userCoupons){
            if(userCoupon.getItemID().equals(itemID)){
                buy_times+=1;
            }
        }
        if(visitTimes!=null){
            points = visitTimes + 5*buy_times;
        }
        else{
            points = 5*buy_times;
        }
        return points;
    }

    class ItemPoints{
        String itemID;
        double[] points;

        public ItemPoints(String itemID, double[] points) {
            this.itemID = itemID;
            this.points = points;
        }
    }
    class UserItemPoints implements Comparable<UserItemPoints>{
        String itemID;
        double points;

        public UserItemPoints(String itemID, double points) {
            this.itemID = itemID;
            this.points = points;
        }

        @Override
        public int compareTo(UserItemPoints o) {
            if((this.points - o.points)>=0){
                return -1;
            }
            else return 1;
        }
    }


    /*
    * 获取用户商品评分数组
    */
    public ArrayList<ItemPoints> getItemPointsArray(){
        ArrayList<ItemPoints> results = new ArrayList<ItemPoints>();
        List<String> userIDs = userMapper.getAllUserID();
        List<String> itemIDs = itemMapper.getAllItemID();
        for(String itemID:itemIDs){
            ArrayList<Double> points = new ArrayList<Double>();
            for(String userID: userIDs){
                points.add(getItemPoints(userID,itemID));
            }
            double[] pointsArray = new double[points.size()];
            for(int i=0;i<points.size();i++){
                pointsArray[i]=points.get(i);
            }
            ItemPoints itemPoints = new ItemPoints(itemID,pointsArray);
            results.add(itemPoints);
        }
        return results;
    }
    public ArrayList<ItemSimilarity> getItemSimilarities(){
        ArrayList<ItemSimilarity> results = new ArrayList<ItemSimilarity>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(this.getClass().getClassLoader().getResourceAsStream("ItemSimilarity.txt"));
            while (true) {
                ItemSimilarity itemSimilarity = (ItemSimilarity) ois.readObject();
                results.add(itemSimilarity);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return results;
    }


    /*
   * 返回用户对商品的评分
   */
    public ArrayList<UserItemPoints> getUserPointsToItems(String userID){
        ArrayList<UserItemPoints> results = new ArrayList<UserItemPoints>();
        List<String> itemIDs = itemMapper.getAllItemID();
        ArrayList<Item> items = new ArrayList<Item>();
        for(String id:itemIDs){
            items.add(itemMapper.getItemByItemID(id));
        }
        for(Item item:items){
            double points = getItemPoints(userID,item.getItemID());
            results.add(new UserItemPoints(item.getItemID(),points));
        }
        return results;
    }
 /*
     * 获得最终用户对商品的喜好评分，并从大到小排序
     */

    public ArrayList<UserItemPoints> getUserInterestToItems(String userID){
        ArrayList<ItemSimilarity> similarities = getItemSimilarities();
        ArrayList<UserItemPoints> userItemPointsArrayList = getUserPointsToItems(userID);
        ArrayList<UserItemPoints> results = new ArrayList<UserItemPoints>();
        for(UserItemPoints userItemPoints:userItemPointsArrayList){
            double points = 0;
            for(ItemSimilarity similarity:similarities){
                if(similarity.itemID1.equals(userItemPoints.itemID)||similarity.itemID2.equals(userItemPoints.itemID)){
                    points+=userItemPoints.points*similarity.similarity;
                }
            }
            results.add(new UserItemPoints(userItemPoints.itemID,points));
        }
        Collections.sort(results);
        return results;
    }


    /**
     * 返回用户的推荐商户列表
     * @param userID
     * @return ArrayList<Merchant>
     */
    public ArrayList<Merchant> getRecommendedMerchants(String userID){
        ArrayList<UserMerchantPoints> userMerchantPointsArrayList = getUserInterestToMerchants(userID);
        ArrayList<Merchant> merchants = new ArrayList<Merchant>();
        for(UserMerchantPoints userMerchantPoints:userMerchantPointsArrayList){
            merchants.add(merchantMapper.selectByID(userMerchantPoints.merchantID));
        }
        return merchants;
    }

    /**
     * 返回用户对商户喜好评分
     * @param userID
     * @return ArrayList<Merchant>
     */
    public double getMerchantPoints(String userID, String merchantID){
        double points = 0;
        // 目前制定的积分策略：购买一件物品得5分+对应的浏览得1分+消费点数除以10

        List<Item> items = itemMapper.getItemByMerchantID(merchantID,0,itemMapper.getItemAmountByMerchantID(merchantID));
        Integer visitTimes = 0;
        for(Item item:items){
            if(VisitRecordUtil.getVisitTimesBy_userID_AND_itemID(userID,item.getItemID())!=null){
                visitTimes+=VisitRecordUtil.getVisitTimesBy_userID_AND_itemID(userID,item.getItemID());
            }
            //visitTimes+=VisitRecordUtil.getVisitTimesBy_userID_AND_itemID(userID,item.getItemID());
        }
        List<Order> orderList = orderMapper.getOrderByUserID(userID,"+010101010101");
        ArrayList<Order> filterOrders = filterOrder(orderList,merchantID);
        double consume_points = 0;
        for(Order order:filterOrders){
            consume_points+=order.getPointsNeeded();
        }
        List<UserCoupon> userCoupons1 = couponMapper.getCouponsByUserID(userID);
        List<UserCoupon> userCoupons2 = couponMapper.get_USED_Coupon(userID);
        List<UserCoupon> userCoupons3 = couponMapper.get_OVERDUED_Coupon(userID);
        ArrayList<UserCoupon> filterUserCoupons1 = filterUserCoupon(userCoupons1,merchantID);
        ArrayList<UserCoupon> filterUserCoupons2 = filterUserCoupon(userCoupons2,merchantID);
        ArrayList<UserCoupon> filterUserCoupons3 = filterUserCoupon(userCoupons3,merchantID);
        points = consume_points/10 + filterUserCoupons1.size()*5+ filterUserCoupons2.size()*5+ filterUserCoupons3.size()*5 + visitTimes;
        return points;
    }

    public ArrayList<Order> filterOrder(List<Order> orderList, String merchantID){
        ArrayList<Order> results = new  ArrayList<Order>();
        for(Order order:orderList){
            if(order.getMerchantId().equals(merchantID)){
                results.add(order);
            }
        }
        return results;
    }
    public ArrayList<UserCoupon> filterUserCoupon(List<UserCoupon> userCoupons, String merchantID){
        ArrayList<UserCoupon> results = new  ArrayList<UserCoupon>();
        for(UserCoupon userCoupon:userCoupons){
            Item item = itemMapper.getItemByItemID(userCoupon.getItemID());
            if(item.getMerchantID().equals(merchantID)){
                results.add(userCoupon);
            }
        }
        return results;
    }

    class MerchantPoints{
        String merchantID;
        double[] points;

        public MerchantPoints(String merchantID, double[] points) {
            this.merchantID = merchantID;
            this.points = points;
        }
    }

    class UserMerchantPoints implements Comparable<UserMerchantPoints>{
        String merchantID;
        double points;

        public UserMerchantPoints(String merchantID, double points) {
            this.merchantID = merchantID;
            this.points = points;
        }

        @Override
        public int compareTo(UserMerchantPoints o) {
            if((this.points - o.points)>=0){
                return -1;
            }
            else return 1;
        }
    }

    /*
     * 获取用户商户评分数组
     */
    public ArrayList<MerchantPoints> getMerchantPointsArray(){
        ArrayList<MerchantPoints> results = new ArrayList<MerchantPoints>();
        List<String> userIDs = userMapper.getAllUserID();
        List<String> merchantIDs = merchantMapper.getAllMerchantID();
        for(String merchantID:merchantIDs){
            ArrayList<Double> points = new ArrayList<Double>();
            for(String userID: userIDs){
                points.add(getMerchantPoints(userID,merchantID));
            }
            double[] pointsArray = new double[points.size()];
            for(int i=0;i<points.size();i++){
                pointsArray[i]=points.get(i);
            }
            MerchantPoints merchantPoints = new MerchantPoints(merchantID,pointsArray);
            results.add(merchantPoints);
        }
        return results;
    }

    
    public static double cosineSimilarity(double[] A, double[] B) {
        if (A.length != B.length) {
            return 2.0000;
        }
        if (A == null || B == null) {
            return 2.0000;
        }
        long fenzi = 0;
        for (int i = 0; i < A.length; i++) {
            fenzi += A[i] * B[i];
        }
        long left = 0;
        long right = 0;
        for (int i = 0; i < A.length; i++) {
            left += A[i] * A[i];
            right += B[i] * B[i];
        }
        if (left * right == 0) {
            return 2.0000;
        }
        double result = fenzi / Math.sqrt(left * right);
        DecimalFormat df = new DecimalFormat("#.####");
        return Double.parseDouble(df.format(result));
    }

    public ArrayList<MerchantSimilarity> getMerchantSimilarities() {
        ArrayList<MerchantSimilarity> results = new ArrayList<MerchantSimilarity>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(this.getClass().getClassLoader().getResourceAsStream("MerchantSimilarity.txt"));
            System.out.println(this.getClass().getClassLoader().getResource(".").toString());
            while (true) {
                    MerchantSimilarity merchantSimilarity = (MerchantSimilarity) ois.readObject();
                    results.add(merchantSimilarity);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return results;
    }


    /*
     * 返回用户对商户的评分
     */
    public ArrayList<UserMerchantPoints> getUserPointsToMerchants(String userID){
        ArrayList<UserMerchantPoints> results = new ArrayList<UserMerchantPoints>();
        List<String> merchantIDs = merchantMapper.getAllMerchantID();
        for(String merchantID:merchantIDs){
            Merchant merchant = merchantMapper.selectByID(merchantID);
            double points = getMerchantPoints(userID,merchant.getMerchantID());
            results.add(new UserMerchantPoints(merchant.getMerchantID(),points));
        }
        return results;
    }

    /*
     * 获得最终用户对商品的喜好评分，并从大到小排序
     */

    public ArrayList<UserMerchantPoints> getUserInterestToMerchants(String userID){
        ArrayList<MerchantSimilarity> similarities = getMerchantSimilarities();
        ArrayList<UserMerchantPoints> userMerchantPointsArrayList = getUserPointsToMerchants(userID);
        ArrayList<UserMerchantPoints> results = new ArrayList<UserMerchantPoints>();
        for(UserMerchantPoints userMerchantPoints:userMerchantPointsArrayList){
            double points = 0;
            for(MerchantSimilarity similarity:similarities){
                if(similarity.merchantID1.equals(userMerchantPoints.merchantID)||similarity.merchantID2.equals(userMerchantPoints.merchantID)){
                    points+=userMerchantPoints.points*similarity.similarity;
                }
            }
            results.add(new UserMerchantPoints(userMerchantPoints.merchantID,points));
        }
        Collections.sort(results);
        return results;
    }




}
