package citiMerchant.vo;

import java.sql.Date;

public class Activity {

    private String activityID;
    private String merchantID;
    private String name;
    private String description;
    private Date startDate;
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

}
