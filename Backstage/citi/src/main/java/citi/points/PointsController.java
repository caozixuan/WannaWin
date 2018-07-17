package citi.points;

import citi.mapper.MSCardMapper;
import citi.mapper.MerchantMapper;
import citi.mapper.PointsHistoryMapper;
import citi.mapper.UserMapper;
import citi.vo.MSCard;
import citi.vo.Merchant;
import citi.vo.Points_history;
import citi.vo.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2018/7/14 10:26
 */
@RequestMapping("/points")
@Controller
public class PointsController {
    @Autowired
    private Gson gson;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MSCardMapper msCardMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private PointsService pointsService;

    @Autowired
    private PointsHistoryMapper pointsHistoryMapper;

    @ResponseBody
    @RequestMapping(value = "/changePoints",produces={"text/html;charset=UTF-8","application/json"})
    public String changePoints(@RequestBody ResultBean resultBean){
        //ResultBean resultBean = new Gson().fromJson(information,ResultBean.class);
        List<ResultBean.MerchantBean> merchantBeanList = resultBean.getMerchants();
        User user = userMapper.getInfoByUserID(resultBean.getUserID());
        List<MSCard> msCards = msCardMapper.select(user.getUserID());
        ArrayList<String> ids = new ArrayList<String>();
        ArrayList<ReturnMerchant> returnMerchants = new ArrayList<ReturnMerchant>();
        for(int i=0;i<msCards.size();i++){
           ids.add(msCards.get(i).getMerchantId());
        }
        boolean isCanChange = pointsService.isCanChange(merchantBeanList,user,returnMerchants,ids);
        if(isCanChange){
            pointsService.deductPoints(user,merchantBeanList);
        }
        return gson.toJson(returnMerchants);
    }

    /*
     * @url:/points/generalPoints
     * @param: userID
     * @return: {"generalPoints": 0.0}
     */
    @ResponseBody
    @RequestMapping("/generalPoints")
    public String getGeneralPoints(String userID){
        User user = userMapper.getInfoByUserID(userID);
        double points = user.getGeneralPoints();
        return "{\"generalPoints\": "+points+"}";
    }

    /*
     * @url:/points/availablePoints
     * @param: userID
     * @return: {"availablePoints": 0.0}
     */
    @ResponseBody
    @RequestMapping("/availablePoints")
    public String getAvailablePoints(String userID){
        User user = userMapper.getInfoByUserID(userID);
        double availablePoints = user.getAvailablePoints();
        return "{\"availablePoints\": "+availablePoints+"}";
    }

    /*
     * url: /points/getPointsHistoryByID
     * param: userID
     * return:[{"userID":"5503b50f-2312-4156-92b3-ec6dcea74656","merchantID":"4","points_card":295,"points_citi":0.0,"cause":"EXCHANGE","time":"Jul 14, 2018 2:41:44 PM"}]
     */
    @ResponseBody
    @RequestMapping("/getPointsHistoryByID")
    public String getPointsHistoryByID(String userID){
        List<Points_history> pointsHistories = pointsHistoryMapper.getPointsHistoryByID(userID);
        ArrayList<Points_history> returnHistories = new ArrayList<Points_history>();
        for(Points_history points_history:pointsHistories){
            if(points_history.getCause().equals(Points_history.Cause.EXCHANGE)){
                returnHistories.add(points_history);
            }
        }
        if(returnHistories==null)
            return "[]";
        return gson.toJson(returnHistories);
    }

    /*
     * url: /points/getPointsHistoryByMerchantID
     * param: userID, merchantID
     * return: [{"userID":"5503b50f-2312-4156-92b3-ec6dcea74656","merchantID":"4","points_card":295,"points_citi":0.0,"cause":"EXCHANGE","time":"Jul 14, 2018 2:41:44 PM"}]
     */
    @ResponseBody
    @RequestMapping("/getPointsHistoryByMerchantID")
    public String getPointsHistoryByID(String userID, String merchantID){
        List<Points_history> pointsHistories = pointsHistoryMapper.getPointsHistoryBy_userID_AND_merchantID(userID, merchantID);
        ArrayList<Points_history_merchant> returnHistories = new ArrayList<Points_history_merchant>();
        for(Points_history points_history:pointsHistories){
            if(points_history.getCause().equals(Points_history.Cause.EXCHANGE)){
                Points_history_merchant points_history_merchant = new Points_history_merchant(points_history, merchantID);
                returnHistories.add(points_history_merchant);
            }
        }
        if(returnHistories==null)
            return "[]";
        return gson.toJson(returnHistories);
    }
}
