package citi.funcModule.activity;

import citi.persist.procedure.probean.ActivityBean;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@RequestMapping(value = {"/activity"},produces = {"text/html;charset=UTF-8"})
@Controller
public class ActivityController {

    @Autowired
    private Gson gson;

    @Autowired
    private ActivityService activityService;

    @ResponseBody
    @RequestMapping("/getMerchantActivities")
    public String getMerchantActivities(String merchantID){
        return gson.toJson(activityService.getMerchantActivities(merchantID));
    }

    @ResponseBody
    @RequestMapping("/getActivity")
    public String getActivity(String activityID){
        return gson.toJson(activityService.getActiity(activityID));
    }

    @ResponseBody
    @RequestMapping("/search")
    public String search(String keyword, String start, String end){
        if(Integer.valueOf(start)>Integer.valueOf(end)||keyword==null||keyword.equals(""))
            return "[]";
        ArrayList<ActivityBean> activityBeans = activityService.search(keyword);
        int returnNum = 0;
        if(activityBeans.size()<Integer.valueOf(end))
            returnNum = activityBeans.size();
        else
            returnNum = Integer.valueOf(end);
        ArrayList<ActivityBean> returnActivityBeans = new ArrayList<>();
        for(int i=Integer.valueOf(start);i<returnNum;i++){
            returnActivityBeans.add(activityBeans.get(i));
        }
        return gson.toJson(returnActivityBeans);
    }

    @ResponseBody
    @RequestMapping("/searchNum")
    public String searchNum(String keyword){
        int num = activityService.searchNum(keyword);
        return "{\"num\":"+num+"}";
    }
}
