package citi.funcModule.Recommend;

import Jama.Matrix;
import citi.persist.mapper.ItemMapper;
import citi.persist.mapper.UserMapper;
import citi.vo.Item;
import citi.vo.Merchant;
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

@Service
public class RecommendService {
    //需要访问到用户的喜好、用户的访问记录、商品的基本信息
    @Autowired
    private PrefMapper prefMapper;
    @Autowired
    private RecordMapper recordMapper;
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
     * @param merchantID
     * @return ArrayList<Merchant>
     */
    public ArrayList<Merchant> getRecommendedMerchants(String merchantID){
        return null;
    }

    /**
     * 返回用户的商户喜好评分
     * @param userID
     * @return ArrayList<Merchant>
     */
    public double getMerchantPoints(String userID){
        double points = 10;
        // 目前制定的积分策略：购买一件物品得5分+对应的浏览得1分+消费点数除以10
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
        double[] points1 = {1,2,3};
        double[] points2 = {2,3,4};
        double[] points3 = {3,4,5};
        MerchantPoints merchantPoints1 = new MerchantPoints("1",points1);
        MerchantPoints merchantPoints2 = new MerchantPoints("2",points2);
        MerchantPoints merchantPoints3 = new MerchantPoints("3",points3);
        ArrayList<MerchantPoints> results = new ArrayList<MerchantPoints>();
        results.add(merchantPoints1);
        results.add(merchantPoints2);
        results.add(merchantPoints3);
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
        results.add(new UserMerchantPoints("1",1));
        results.add(new UserMerchantPoints("2",3));
        results.add(new UserMerchantPoints("3",2));
        return results;
    }

    /*
     * 获得最终用户对商品的喜好评分，并从大到小排序
     */

    public ArrayList<UserMerchantPoints> getUserInterestToMerchants(){
        ArrayList<MerchantSimilarity> similarities = getMerchantSimilarities();
        ArrayList<UserMerchantPoints> userMerchantPointsArrayList = getUserPointsToMerchants("1");
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
