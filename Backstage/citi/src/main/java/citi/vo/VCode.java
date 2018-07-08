package citi.vo;

public class VCode {

    private String phoneNum;
    private String VCode;

    public VCode(String phoneNum, String VCode) {
        this.phoneNum = phoneNum;
        this.VCode = VCode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getVCode() {
        return VCode;
    }

}
