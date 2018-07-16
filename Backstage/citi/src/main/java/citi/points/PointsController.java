package citi.points;

import citi.mapper.MSCardMapper;
import citi.mapper.MerchantMapper;
import citi.mapper.UserMapper;
import citi.vo.MSCard;
import citi.vo.Merchant;
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
}
