package citi.vo;

public class VCode {

    private String phoneNum;
    private String VCode;
    private String Time;

    public VCode(String phoneNum, String VCode, String time) {
        this.phoneNum = phoneNum;
        this.VCode = VCode;
        Time = time;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getVCode() {
        return VCode;
    }

    public String getTime() {
        return Time;
    }
}
