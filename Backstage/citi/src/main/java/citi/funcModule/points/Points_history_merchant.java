package citi.funcModule.points;

import citi.vo.Points_history;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author:曹子轩
 */
public class Points_history_merchant extends Points_history {
    private String merchantName;
    public Points_history_merchant(String userID, String merchantIDs, int points_card, double points_citi, String cause, Timestamp time, String merchantName){
        super(userID, merchantIDs,points_card, points_citi, cause, time);
        this.merchantName = merchantName;
    }

    public Points_history_merchant(Points_history points_history, String merchantName){
        super(points_history.getUserID(),points_history.getMerchantID(),points_history.getPoints_card(),points_history.getPoints_citi(),points_history.getCause().toString(),Timestamp.valueOf(points_history.getTime()));
        this.merchantName=merchantName;
    }
}
