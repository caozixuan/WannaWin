package citiMerchant.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class DBHandler {
    @Autowired
    public static OrderMapper orderMapper;
    @Autowired
    public static ItemMapper itemMapper;
    @Autowired
    public static StrategyMapper strategyMapper;

    static final private String resource = "./citiMerchant/mapper.xml";
    static private SqlSessionFactory sqlSessionFactory;

    static private String pathname = "./citiMerchant/log.txt";
    static private File log_file;
    static private BufferedWriter log_writer;

    static {
        try {

            //init log_file
            log_file = new File(pathname);

            //init sqlsession
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("\nfail to read mapper.xml\n");
        }
    }

    /****************************        初始化完成        ****************************/

    /*
     * 用于日志
     */
    static private void log(String method, long time) {
        try {
            log_writer = new BufferedWriter(new FileWriter(log_file));
            log_writer.write("\"" + method + "\" elapsed time: " + (System.currentTimeMillis() - time) + "ms\n");
            log_writer.flush();
            log_writer.close();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("\nfail to log \"" + method + "\"\n");
        }
    }

    /****************************       用于商户后台的接口     ****************************/

    /*
     * 返回一个列表：包含该商家的“优惠商品”，“减免策略”，“历史订单”的数量。
     */
    static public ArrayList<Integer> getAmountByMerchantID(String merchantID) {
        long time = System.currentTimeMillis();
        int itemAmount = itemMapper.getItemAmountByMerchantID(merchantID);
        int stategyAmount = strategyMapper.getStrategyAmountByMerchantID(merchantID);
        int orderAmount = orderMapper.getOrderAmount(merchantID);
        log("getAmountByMerchantID", time);
        List<Integer> amount = Arrays.asList(itemAmount, stategyAmount, orderAmount);
        return new ArrayList(amount);
    }


    /*
     * 用于统计商家的积分流水信息，包含兑入和兑出。
     */
    static public class Record {

        //返回coupon的积分兑入总和
        static public long coupon_record(String IN_MerchantID, int IN_intervalDate) {
            Map<String, Object> map = new HashMap<String, Object>();
            long totalPoints = -1;
            map.put("IN_MerchantID", (Object) IN_MerchantID);
            map.put("IN_intervalDate", (Object) IN_intervalDate);
            map.put("totalPoints", (Object) totalPoints);
            long time = System.currentTimeMillis();
            SqlSession session = sqlSessionFactory.openSession();
            session.selectOne("citiMerchant.mapper.DBHandler.coupon_record", map);
            totalPoints = (Integer) map.get("totalPoints");
            session.commit();
            session.close();
            log("STORED PROCEDURE coupon_record", time);
            return totalPoints;
        }

        //返回order的积分使用总和
        static public long order_record(String IN_MerchantID, int IN_intervalDate) {
            Map<String, Object> map = new HashMap<String, Object>();
            long totalPoints = -1;
            map.put("IN_MerchantID", (Object) IN_MerchantID);
            map.put("IN_intervalDate", (Object) IN_intervalDate);
            map.put("totalPoints", (Object) totalPoints);
            long time = System.currentTimeMillis();
            SqlSession session = sqlSessionFactory.openSession();
            session.selectOne("citiMerchant.mapper.DBHandler.order_record", map);
            totalPoints = (Integer) map.get("totalPoints");
            session.commit();
            session.close();
            log("STORED PROCEDURE order_record", time);
            return totalPoints;
        }


    }


}
