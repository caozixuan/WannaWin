package citi.vo;

public class PointInformation {
    double availablePointBalance;
    double programConversionRate;
    String  currencyCode;
    double maximumPointsToRedeem;
    double minimumPointsToRedeem;

    public PointInformation(double availablePointBalance, double programConversionRate, String currencyCode, double maximumPointsToRedeem, double minimumPointsToRedeem) {
        this.availablePointBalance = availablePointBalance;
        this.programConversionRate = programConversionRate;
        this.currencyCode = currencyCode;
        this.maximumPointsToRedeem = maximumPointsToRedeem;
        this.minimumPointsToRedeem = minimumPointsToRedeem;
    }

    public double getAvailablePointBalance() {
        return availablePointBalance;
    }

    public double getProgramConversionRate() {
        return programConversionRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getMaximumPointsToRedeem() {
        return maximumPointsToRedeem;
    }

    public double getMinimumPointsToRedeem() {
        return minimumPointsToRedeem;
    }
}
