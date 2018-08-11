package citi.funcModule.Recommend;

import Jama.Matrix;
import citi.persist.mapper.ItemMapper;
import citi.persist.mapper.MerchantMapper;
import citi.persist.mapper.OrderMapper;
import citi.persist.mapper.UserMapper;
import citi.vo.Item;
import citi.vo.Merchant;
import citi.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RecommendService {
    //需要访问到用户的喜好、用户的访问记录、商品的基本信息
    @Autowired
    private PrefMapper prefMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private ItemMapper itemMapper;

    /**
     * 初始化用户的偏好列表
     * @param prefList
     * @return true/false
     */
    public boolean initPref(String userID,ArrayList<String> prefList){
        boolean flag = true;
        for(int i=0;i<prefList.size();i++){
            /**
             * 接口一：PrefMapper-----addPref()
             * 参数一：userID,参数二：一个偏好（字符串形式）
             * 返回受影响的行数
             */
            if(prefMapper.addPref(userID,prefList.get(i))!=1){
                flag = false;
                break;
            }
        }
        return flag;
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
      if(recordMapper.addRecord(userID,itemID,time)!=1)
          return false;
      return true;
    }


    /**
     * 返回用户的推荐商品列表
     * @param userID
     * @return ArrayList<Item>
     */
    public ArrayList<Item> getRecommendedItems(String userID){

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
        // TODO: 这里缺用户浏览和用户积分消费的接口
        List<Order> orderList = orderMapper.getOrderByUserID(userID,"+010101010101");
        points = 5*orderList.size();
        return points;
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
                return 1;
            }
            else return -1;
        }
    }

    class MerchantSimilarity{
        String merchantID1;
        String merchantID2;
        double similarity;

        public MerchantSimilarity(String merchantID1, String merchantID2, double similarity) {
            this.merchantID1 = merchantID1;
            this.merchantID2 = merchantID2;
            this.similarity = similarity;
        }
    }
    /*
     * 获取用户商户评分数组
     */
    public ArrayList<MerchantPoints> getMerchantPointsArray(){
        ArrayList<MerchantPoints> results = new ArrayList<MerchantPoints>();
        // TODO:这里缺获取所用用户userID的方法
        ArrayList<String> userIDs = new ArrayList<String>();
        ArrayList<String> merchantIDs = new ArrayList<String>();
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

    public ArrayList<MerchantSimilarity> getMerchantSimilarities(){
        ArrayList<MerchantSimilarity> results = new ArrayList<MerchantSimilarity>();
        ArrayList<MerchantPoints> merchantPoints = getMerchantPointsArray();
        for(int i=0;i<merchantPoints.size()-1;i++){
            for(int j=i;j<merchantPoints.size();j++){
                double similarity = cosineSimilarity(merchantPoints.get(i).points,merchantPoints.get(i).points);
                String merchantID1 = merchantPoints.get(i).merchantID;
                String merchantID2 = merchantPoints.get(j).merchantID;
                results.add(new MerchantSimilarity(merchantID1,merchantID2, similarity));
            }
        }
        return results;
    }

    /*
     * 返回用户对商户的评分
     */
    public ArrayList<UserMerchantPoints> getUserPointsToMerchants(String userID){
        ArrayList<UserMerchantPoints> results = new ArrayList<UserMerchantPoints>();
        // TODO:这里通过数据库获取所有商户ID
        ArrayList<Merchant> merchants = new ArrayList<Merchant>();
        for(Merchant merchant:merchants){
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
