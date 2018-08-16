package citi.funcModule.activity;

import citi.persist.mapper.ActivityMapper;
import citi.persist.mapper.MerchantMapper;
import citi.vo.Activity;
import citi.vo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private MerchantMapper merchantMapper;

    public List<Activity> getMerchantActivities(String merchantID){
        return activityMapper.getActivitiesByMerchantID(merchantID);
    }

    public Activity getActiity(String activityID){
        return activityMapper.getActivityByActivityID(activityID);
    }
}
