package citiMerchant.activity;

import citiMerchant.uitl.JsonResult;
import citiMerchant.vo.Activity;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    public ModelAndView getActivities(){
        List<Activity> activities=activityService.getActivities();
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
    public String submitEdit(Activity activity){
        if (activity.getActivityID()==null){
            activityService.insertActivity(activity);
        }else {
            activityService.updateActivity(activity);
        }
        return JsonResult.SUCCESS;
    }

    public String deleteActivity(String activityID){
        activityService.deleteActivity(activityID);
        return JsonResult.SUCCESS;
    }


}
