package citi.vo;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Activity {

    private String activityID;
    private String merchantID;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private String imageURL;

    public Activity(String activityID, String merchantID, String name, String description, Date startDate, Date endDate, String imageURL) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString = formatter.format(startDate);
        String endDateString = formatter.format(endDate);
        this.activityID = activityID;
        this.merchantID = merchantID;
        this.name = name;
        this.description = description;
        this.startDate = startDateString;
        this.endDate = endDateString;
        this.imageURL = imageURL;
    }

    public String getActivityID() {
        return activityID;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(Date startDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString = formatter.format(startDate);
        this.startDate = startDateString;
    }

    public void setEndDate(Date endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String endDateString = formatter.format(endDate);
        this.endDate = endDateString;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

}
