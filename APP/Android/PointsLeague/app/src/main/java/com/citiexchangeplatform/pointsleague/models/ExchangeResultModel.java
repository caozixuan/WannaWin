package com.citiexchangeplatform.pointsleague.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ExchangeResultModel implements Parcelable {
    private String reason;
    private String merchantName;
    private String usePoints;

    public ExchangeResultModel() {

    }

    public ExchangeResultModel(String reason, String merchantName, String usePoints) {
        this.reason = reason;
        this.merchantName = merchantName;
        this.usePoints = usePoints;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getUsePoints() {
        return usePoints;
    }

    public void setUsePoints(String usePoints) {
        this.usePoints = usePoints;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(reason);
        dest.writeString(merchantName);
        dest.writeString(usePoints);
    }

    public static final Parcelable.Creator<ExchangeResultModel> CREATOR = new Creator(){

        @Override
        public ExchangeResultModel createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
            ExchangeResultModel p = new ExchangeResultModel();
            p.setReason(source.readString());
            p.setMerchantName(source.readString());
            p.setUsePoints(source.readString());
            return p;
        }

        @Override
        public ExchangeResultModel[] newArray(int size) {
            // TODO Auto-generated method stub
            return new ExchangeResultModel[size];
        }
    };
}
