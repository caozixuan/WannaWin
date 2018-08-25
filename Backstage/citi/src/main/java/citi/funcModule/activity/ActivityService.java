package citi.funcModule.activity;

import citi.persist.mapper.ActivityMapper;
import citi.persist.mapper.MerchantMapper;
import citi.persist.procedure.probean.ActivityBean;
import citi.vo.Activity;
import citi.vo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private MerchantMapper merchantMapper;

    public ArrayList<ActivityBean> getMerchantActivities(String merchantID){
        List<Activity> activities = activityMapper.getActivitiesByMerchantID(merchantID);
        ArrayList<ActivityBean> activityBeans = new ArrayList<ActivityBean>();
        for(Activity activity:activities){
            Merchant merchant = merchantMapper.selectByID(activity.getMerchantID());
            activityBeans.add(new ActivityBean(activity,merchant.getName(),merchant.getMerchantLogoURL()));
        }
        return activityBeans;
    }

    public ActivityBean getActiity(String activityID){
        Activity activity = activityMapper.getActivityByActivityID(activityID);
        Merchant merchant = merchantMapper.selectByID(activity.getMerchantID());
        ActivityBean activityBean = new ActivityBean(activity,merchant.getName(),merchant.getMerchantLogoURL());
        return activityBean;
    }

    public ArrayList<ActivityBean> search(String searchString){
        List<Activity> activities = activityMapper.getAllActivity();
        ArrayList<ActivityBean> results = new ArrayList<ActivityBean>();
        for(Activity activity:activities){
            if(activity.getName().contains(searchString)){
                Merchant merchant = merchantMapper.selectByID(activity.getMerchantID());
                ActivityBean activityBean = new ActivityBean(activity,merchant.getName(),merchant.getMerchantLogoURL());
                results.add(activityBean);
            }
        }
        if(results.size()==0){
            for(Activity activity:activities){
                if(activity.getDescription().contains(searchString)){
                    Merchant merchant = merchantMapper.selectByID(activity.getMerchantID());
                    ActivityBean activityBean = new ActivityBean(activity,merchant.getName(),merchant.getMerchantLogoURL());
                    results.add(activityBean);
                }
            }
        }
        return results;
    }

    public int searchNum(String searchString){
        int counter = 0;
        List<Activity> activities = activityMapper.getAllActivity();
        ArrayList<ActivityBean> results = new ArrayList<ActivityBean>();
        for(Activity activity:activities){
            if(activity.getName().contains(searchString)){
                counter++;
            }
        }
        if(counter==0){
            for(Activity activity:activities){
                if(activity.getDescription().contains(searchString)){
                    counter++;
                }
            }
        }
        return counter;
    }
}
