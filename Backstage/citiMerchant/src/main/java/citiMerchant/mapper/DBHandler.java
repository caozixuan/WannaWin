

package citiMerchant.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DBHandler {

//    static final private String resource = "./src/main/java/citiMerchant/mapper/citiMerchant/mapper.xml";

    public static OrderMapper orderMapper;
    public static ItemMapper itemMapper;
    public static StrategyMapper strategyMapper;
    public static RecordMapper recordMapper;

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        DBHandler.orderMapper = orderMapper;
    }

    @Autowired
    public void setItemMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Autowired
    public void setStrategyMapper(StrategyMapper strategyMapper) {
        this.strategyMapper = strategyMapper;
    }

    @Autowired
    public void setRecordMapper(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }


//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;
//
//    public DBHandler() {
//        try {
//            //init sqlsession
//            Reader reader = Resources.getResourceAsReader(resource);
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//        } catch (IOException e) {
//            //e.printStackTrace();
//            System.out.println("\n************   fail to read mapper.xml, but it doesn't matter   ************\n");
//        }
//    }


    /****************************        初始化完成        ****************************/


    /****************************       用于商户后台的接口     ****************************/

    /*
     * 返回一个列表：包含该商家的“优惠商品”，“减免策略”，“历史订单”的数量。
     */
    public ArrayList<Integer> getAmountByMerchantID(String merchantID) {
        //long time = System.currentTimeMillis();
        int itemAmount = itemMapper.getItemAmountByMerchantID(merchantID);
        int stategyAmount = strategyMapper.getStrategyAmountByMerchantID(merchantID);
        int orderAmount = orderMapper.getOrderAmount(merchantID);
        //Log.log("getAmountByMerchantID", time);
        List<Integer> amount = Arrays.asList(itemAmount, stategyAmount, orderAmount);
        return new ArrayList(amount);
    }


//    /*
//     * 用于统计商家的积分流水信息，包含兑入和兑出。
//     */
//
//    //针对某一商家的积分流入信息
//    static public enum Record_IN {
//        //返回coupon的积分兑入总和
//        Coupon,
//        //返回order的积分使用总和
//        Order;
//        static Map<String, Record_IN> enumMap1 = new HashMap<>();
//        static Map<Record_IN, String> enumMap2 = new HashMap<>();
//
//        static {
//            enumMap1.put("coupon_record", Coupon);
//            enumMap1.put("order_record", Order);
//
//            enumMap2.put(Coupon, "coupon_record");
//            enumMap2.put(Order, "order_record");
//        }
//
//        public static Record_IN getRecord_TYPE(String record_type_s) {
//            return enumMap1.get(record_type_s);
//        }
//
//        public static String getRecord_TYPE_S(Record_IN record_type) {
//            return enumMap2.get(record_type);
//        }
//    }
//
//    public class Record {
//
//        /*
//         * 调用格式：
//         * 返回7天之内商家"00001"的优惠券积分流入
//         * long totalPoints = DBHandler.Record.points_in_record("00001", 7, DBHandler.Record_IN.Coupon);
//         */
//        public long points_in_record(String IN_MerchantID, int IN_intervalDate, Record_IN record_type) {
//            Map<String, Object> map = new HashMap<>();
//            long totalPoints = -1;
//            map.put("IN_MerchantID", (Object) IN_MerchantID);
//            map.put("IN_intervalDate", (Object) IN_intervalDate);
//            map.put("totalPoints", (Object) totalPoints);
//            long time = System.currentTimeMillis();
//            SqlSession session = sqlSessionFactory.openSession();
//            final String record_type_s = Record_IN.getRecord_TYPE_S(record_type);
//            session.selectOne("citiMerchant.mapper.DBHandler." + record_type_s, map);
//            totalPoints = (Integer) map.get("totalPoints");
//            session.commit();
//            session.close();
//            Log.log("STORED PROCEDURE " + record_type_s, time);
//            return totalPoints;
//        }//end method points_in_record(String, int, Record_IN);
//
//
//    }//end class Record - 统计商家积分流水


}
