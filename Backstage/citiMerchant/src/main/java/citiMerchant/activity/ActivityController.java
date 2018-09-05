package citiMerchant.activity;

import citiMerchant.uitl.JsonResult;
import citiMerchant.vo.Activity;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhong on 2018/8/21 16:27
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private Gson gson;

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/all")
    public ModelAndView getActivities(HttpSession session){
        String merchantID=session.getAttribute("merchantID").toString();
        List<Activity> activities=activityService.getMerchantActivities(merchantID);
        ModelAndView modelAndView=new ModelAndView("table/activityTable");
        modelAndView.addObject("activities",activities);
        return modelAndView;
    }

    @RequestMapping("/editActivity")
    public ModelAndView editActivity(String activityID){
        ModelAndView modelAndView=new ModelAndView("table/editActivity");
        Activity activity=activityService.getActivity(activityID);
        modelAndView.addObject("activity",activity);
        return modelAndView;
    }

    @RequestMapping("/addActivity")
    public ModelAndView addActivity(){
        ModelAndView modelAndView=new ModelAndView("table/editActivity");
        modelAndView.addObject("activity",null);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/submit")
    public String submitEdit(String activityID, String name, String description, String startDate, String endDate, String imageURL,HttpSession session){
        String merchantID=session.getAttribute("merchantID").toString();
        if (activityID.equals("")){
            Activity activity=new Activity(UUID.randomUUID().toString(),merchantID,name,description, Date.valueOf(startDate),Date.valueOf(endDate),imageURL);
            activityService.insertActivity(activity);
        }else {
            Activity activity=new Activity(activityID,merchantID,name,description, Date.valueOf(startDate),Date.valueOf(endDate),imageURL);
            activityService.updateActivity(activity);
        }
        return JsonResult.SUCCESS;
    }

    @RequestMapping("/delete")
    public String deleteActivity(String activityID){
        activityService.deleteActivity(activityID);
        return "forward:all";
    }


}
