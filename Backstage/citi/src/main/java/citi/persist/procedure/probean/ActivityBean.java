package citi.persist.procedure.probean;

import citi.vo.Activity;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ActivityBean extends Activity {
    String merchantName;
    String merchantLogoURL;

    public ActivityBean(Activity activity, String merchantName, String merchantLogoURL) {
        super(activity.getActivityID(), activity.getMerchantID(), activity.getName(), activity.getDescription(), Date.valueOf(activity.getStartDate()), Date.valueOf(activity.getEndDate()), activity.getImageURL());
        this.merchantName = merchantName;
        this.merchantLogoURL = merchantLogoURL;
    }
}
