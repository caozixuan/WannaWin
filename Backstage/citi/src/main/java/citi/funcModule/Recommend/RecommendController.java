package citi.funcModule.Recommend;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 *协同过滤的推荐算法
 */

@RequestMapping()
@Controller
public class RecommendController {
    @Autowired
    Gson gson;
    @Autowired
    RecommendService recommendService;


    /**
     * 用户第一次登录后，初始化自己的偏好
     * @param prefList 偏好列表
     * @Return status [{"status":"true/false"}]
     */
    @ResponseBody
    @RequestMapping("/initPref")
    public String initPref(ArrayList<String> prefList){
        if(recommendService.initPref(prefList))
            return "[{\"status\":\"true\"}]";
        else
            return "[{\"status\":\"false\"}]";
    }


    /**
     * 记录用户的浏览记录
     * @param1 ItemID 被浏览商品的ID
     * @Return status [{"status":"true/false"}]
     */
    @ResponseBody
    @RequestMapping("/addRecord")
    public String addRecord(String itemID){
        if(recommendService.addRecord(itemID))
            return "[{\"status\":\"true\"}]";
        else
            return "[{\"status\":\"false\"}]";
    }



    /**
     * 获取用户的商品推荐列表(暂定只推荐5个)
     * @Return itemList
     */
    @ResponseBody
    @RequestMapping("/getRecommendedItems")
    public ArrayList<Item> getRecommendedItems(){
        if(recommendService.addRecord(itemID))
            return "[{\"status\":\"true\"}]";
        else
            return "[{\"status\":\"false\"}]";
    }


//    /**
//     * 获取用户的商户推荐列表(暂定只推荐5个)
//     * @Return itemList
//     */
//    @ResponseBody
//    @RequestMapping("/getRecommendedItems")
//    public String getRecommendedItems(){
//        if(recommendService.addRecord(itemID))
//            return "[{\"status\":\"true\"}]";
//        else
//            return "[{\"status\":\"false\"}]";
//    }
}
