package citi.persist.procedure.probean;

import citi.vo.Activity;

import java.sql.Date;

public class ActivityBean extends Activity {
    String merchantName;
    String merchantLogoURL;

    public ActivityBean(Activity activity, String merchantName, String merchantLogoURL) {
        super(activity.getActivityID(), activity.getMerchantID(), activity.getName(), activity.getDescription(), activity.getStartDate(), activity.getEndDate(), activity.getImageURL());
        this.merchantName = merchantName;
        this.merchantLogoURL = merchantLogoURL;
    }
}
