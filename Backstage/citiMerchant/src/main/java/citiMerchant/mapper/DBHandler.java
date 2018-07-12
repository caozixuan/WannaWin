package citiMerchant.mapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBHandler {
    @Autowired
    public static OrderMapper orderMapper;
    @Autowired
    public static ItemMapper itemMapper;
    @Autowired
    public static StrategyMapper strategyMapper;

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession session = sqlSessionFactory.openSession();

    static public ArrayList<Integer> getAmountByMerchantID(String merchantID) {
        int itemAmount = itemMapper.getItemAmountByMerchantID(merchantID);
        int stategyAmount = strategyMapper.getStrategyAmountByMerchantID(merchantID);
        int orderAmount = orderMapper.getOrderAmount(merchantID);
        List<Integer> amount = Arrays.asList(itemAmount, stategyAmount, orderAmount);
        return new ArrayList(amount);
    }


    static public class Record {

/*
        //select a particular student  by  id
        Student student = (Student) session.selectOne("Student.callById", 3);

        //Print the student details
      System.out.println("Details of the student are:: ");
      System.out.println("Id :"+student.getId());
      System.out.println("Name :"+student.getName());
      System.out.println("Branch :"+student.getBranch());
      System.out.println("Percentage :"+student.getPercentage());
      System.out.println("Email :"+student.getEmail());
      System.out.println("Phone :"+student.getPhone());
      session.commit();
      session.close();
*/
    }


}
