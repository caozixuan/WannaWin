package citiMerchant.showData;

import citiMerchant.mapper.MerchantMapper;
import citiMerchant.vo.Merchant;
import citiMerchant.vo.Record;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class TestController {


    final TestService testService = new TestService();
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private Gson gson;


    @RequestMapping("/showData")
    public ModelAndView getInfo(HttpSession session) {

        Merchant merchant = merchantMapper.selectByID((String) session.getAttribute("merchantID"));
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/table/showData");

        return mv;
    }

    @RequestMapping("/tableData")
    @ResponseBody
    public String tableData(HttpSession session){
        Prepare_info prepare = new Prepare_info();
        prepare.set((String) session.getAttribute("merchantID"));
        new Thread(prepare).start();

        Map<String,Object> data=new HashMap<>();
        data.put("xAxis",session.getAttribute("timeStamp"));
        data.put("points",session.getAttribute("points"));
        data.put("points_exchange",session.getAttribute("points_exchange"));
        data.put("total_order_points",session.getAttribute("total_order_points"));
        data.put("total_points_exchange",session.getAttribute("total_points_exchange"));
        data.put("total_merchant_coupon_record",session.getAttribute("total_merchant_coupon_record"));

        return gson.toJson(data);
    }


    /*
     * only for test
     */
    @RequestMapping("/tes")
    public void testRecord() {
        System.out.println("\n------------------------------------------------------------\n");
        Record record1 = testService.getCouponRecord("123", 7);
        System.out.println("totalPoints of coupons in 7 days of merchantID\"123\": " + record1.getTotalPoints());

        System.out.println("\n------------------------------------------------------------\n");
        Record record2 = testService.getOrderRecord("00002", 7);
        System.out.println("totalPoints of orders in 7 days of merchantID\"00002\": " + record2.getTotalPoints());

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("\ntotalPoints of orders in 2018 of merchantID\"00002\":\n");
        List<Long> points = testService.show_order_points_chronology("00002", "2018");
        for (Long l : points) {
            System.out.print(l + "\t");
        }
        System.out.println("\n------------------------------------------------------------\n");

    }//end method testRecord();


}
