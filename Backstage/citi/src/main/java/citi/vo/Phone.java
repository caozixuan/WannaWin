package citi.vo;

public class Phone {
    String phoneType;
    String areaCode;
    int extension;
    int phoneKey;
    int phoneNum;
    int phoneCountryCode;

    public Phone(String phoneType, String areaCode, int extension, int phoneKey, int phoneNum, int phoneCountryCode) {
        this.phoneType = phoneType;
        this.areaCode = areaCode;
        this.extension = extension;
        this.phoneKey = phoneKey;
        this.phoneNum = phoneNum;
        this.phoneCountryCode = phoneCountryCode;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public int getExtension() {
        return extension;
    }

    public int getPhoneKey() {
        return phoneKey;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public int getPhoneCountryCode() {
        return phoneCountryCode;
    }
}
