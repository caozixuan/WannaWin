package citiMerchant.activity;

import citiMerchant.mapper.ActivityMapper;
import citiMerchant.vo.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhong on 2018/8/28 10:31
 */
@Service
public class ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    public List<Activity> getActivities(){
        return activityMapper.getAllActivity();
    }

    public Activity getActivity(String activityID){
        return activityMapper.getActivityByActivityID(activityID);
    }
}
