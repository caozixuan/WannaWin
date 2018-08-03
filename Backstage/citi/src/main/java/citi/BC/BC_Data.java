package citi.BC;

public class BC_Data {

    final public String merchantID;
    final public String encrypted_data;

    private BC_Data(String merchantID, String encrypted_data) {
        this.merchantID = merchantID;
        this.encrypted_data = encrypted_data;
    }

    static public BC_Data Data2BC_Data(DealData data) {
        BC_Data bcData = new BC_Data(data.merchantID, data.encrypt());
        return bcData;
    }

    // TODO： 如何解析
    @Override
    public String toString() {
        return "BC_Data{" +
                "merchantID='" + merchantID + '\'' +
                ", encrypted_data='" + encrypted_data + '\'' +
                '}';
    }

}
