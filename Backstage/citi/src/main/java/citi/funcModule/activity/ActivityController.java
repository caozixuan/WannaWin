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
}
