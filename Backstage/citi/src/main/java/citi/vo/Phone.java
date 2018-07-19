package citi.vo;

public class Phone {
    private String phoneType;
    private String areaCode;
    private Integer extension;
    private Integer phoneKey;
    private Integer phoneNumber;
    private Integer phoneCountryCode;

    public Phone(String phoneType, String areaCode, Integer extension, Integer phoneKey, Integer phoneNum, Integer phoneCountryCode) {
        this.phoneType = phoneType;
        this.areaCode = areaCode;
        this.extension = extension;
        this.phoneKey = phoneKey;
        this.phoneNumber = phoneNum;
        this.phoneCountryCode = phoneCountryCode;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public Integer getExtension() {
        return extension;
    }

    public Integer getPhoneKey() {
        return phoneKey;
    }

    public Integer getPhoneNum() {
        return phoneNumber;
    }

    public Integer getPhoneCountryCode() {
        return phoneCountryCode;
    }

}
