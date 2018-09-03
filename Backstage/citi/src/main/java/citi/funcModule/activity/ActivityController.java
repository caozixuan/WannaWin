package citi.funcModule.activity;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String search(String keyword){
        if(keyword==null)
            return "[]";
        return gson.toJson(activityService.search(keyword));
    }

    @ResponseBody
    @RequestMapping("/searchNum")
    public String searchNum(String keyword){
        int num = activityService.searchNum(keyword);
        return "{\"num\":"+num+"}";
    }
}
