package citi.funcModule.recommend;


import citi.support.resultjson.SerializeGson;
import citi.vo.Item;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;


/**
 *协同过滤的推荐算法
 */

@RequestMapping(value = {"/recommend"},produces = {"text/json;charset=UTF-8"})
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
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String userID = (String)session.getAttribute("userID");
        if(recommendService.initPref(userID,prefList))
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
    public String addRecord(String itemID, Timestamp time){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String userID = (String)session.getAttribute("userID");
        if(recommendService.addRecord(userID,itemID,time))
            return "[{\"status\":\"true\"}]";
        else
            return "[{\"status\":\"false\"}]";
    }



    /**
     * 获取用户的商品推荐列表(暂定只推荐2个)
     * @Return itemList
     */
    @ResponseBody
    @RequestMapping("/getRecommendedItems")
    public String getRecommendedItems(String userID){
        if(userID==null){
            userID = "06bef837-9ad4-4e8e-9d3d-f20275d6fcb5";
        }
        return gson.toJson(recommendService.getRecommendedItems(userID,3));
    }

    @ResponseBody
    @RequestMapping("/getRecommendedMerchants")
    public String getRecommendedMerchants(String userID){
        if(userID==null){
            userID = "06bef837-9ad4-4e8e-9d3d-f20275d6fcb5";
        }
        return SerializeGson.GSON.toJson(recommendService.getRecommendedMerchants(userID));
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
