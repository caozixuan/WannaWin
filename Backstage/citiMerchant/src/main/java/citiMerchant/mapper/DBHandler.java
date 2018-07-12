package citiMerchant.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DBHandler {
    @Autowired
    public static OrderMapper orderMapper;
    @Autowired
    public static ItemMapper itemMapper;
    @Autowired
    public static StrategyMapper strategyMapper;

    static final private String resource = "mapper.xml";
    static private SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
    static private SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("\nfail to read mapper.xml\n");
        }
    }

    static public ArrayList<Integer> getAmountByMerchantID(String merchantID) {
        int itemAmount = itemMapper.getItemAmountByMerchantID(merchantID);
        int stategyAmount = strategyMapper.getStrategyAmountByMerchantID(merchantID);
        int orderAmount = orderMapper.getOrderAmount(merchantID);
        List<Integer> amount = Arrays.asList(itemAmount, stategyAmount, orderAmount);
        return new ArrayList(amount);
    }


    static public class Record {
        static public void coupon_record() {
            SqlSession session = sqlSessionFactory.openSession();


            session.commit();
            session.close();
        }
    }


}
