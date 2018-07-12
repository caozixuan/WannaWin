package citiMerchant.vo;

public class Phone {
    private String phoneType;
    private String areaCode;
    private int extension;
    private int phoneKey;
    private int phoneNumber;
    private int phoneCountryCode;

    public Phone(String phoneType, String areaCode, int extension, int phoneKey, int phoneNum, int phoneCountryCode) {
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

    public int getExtension() {
        return extension;
    }

    public int getPhoneKey() {
        return phoneKey;
    }

    public int getPhoneNum() {
        return phoneNumber;
    }

    public int getPhoneCountryCode() {
        return phoneCountryCode;
    }
}
