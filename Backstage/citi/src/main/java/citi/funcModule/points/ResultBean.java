package citi.funcModule.points;

import java.util.List;

public class ResultBean {
    private String userID;
    private List<MerchantBean> merchants;
    public class MerchantBean{
        private String merchantID ;
        private String selectedMSCardPoints;

        public MerchantBean(String merchantID, String selectedMSCardPoints) {
            this.merchantID = merchantID;
            this.selectedMSCardPoints = selectedMSCardPoints;
        }

        public String getMerchantID() {
            return merchantID;
        }

        public String getSelectedMSCardPoints() {
            return selectedMSCardPoints;
        }

        public void setMerchantID(String merchantID) {
            this.merchantID = merchantID;
        }

        public void setSelectedMSCardPoints(String selectedMSCardPoints) {
            this.selectedMSCardPoints = selectedMSCardPoints;
        }
    }

    public ResultBean(String userID, List<MerchantBean> merchants) {
        this.userID = userID;
        this.merchants = merchants;
    }

    public String getUserID() {
        return userID;
    }

    public List<MerchantBean> getMerchants() {
        return merchants;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setMerchants(List<MerchantBean> merchants) {
        this.merchants = merchants;
    }
}
