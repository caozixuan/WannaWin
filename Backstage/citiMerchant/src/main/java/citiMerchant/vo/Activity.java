package citiMerchant.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class Activity {

    private String activityID;
    private String merchantID;
    private String name;
    private String description;
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    private String imageURL;

    public Activity(String activityID, String merchantID, String name, String description, Date startDate, Date endDate, String imageURL) {
        this.activityID = activityID;
        this.merchantID = merchantID;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageURL = imageURL;
    }

    public Activity(){
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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
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
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }
}
