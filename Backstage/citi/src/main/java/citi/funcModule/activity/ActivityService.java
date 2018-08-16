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
}
