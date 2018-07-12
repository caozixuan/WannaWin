package citi.vo;

import java.sql.Timestamp;

public class VCode {

    private String phoneNum;
    private String VCode;
    private Timestamp Time;

    public VCode(String phoneNum, String VCode, Timestamp time) {
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

    public Timestamp getTime() {
        return Time;
    }
}
