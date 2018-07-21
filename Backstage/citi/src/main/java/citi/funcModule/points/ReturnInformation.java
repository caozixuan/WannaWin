package citi.funcModule.points;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ReturnInformation implements Comparable<ReturnInformation>{
    public ArrayList<Points_history_merchant> points_history_merchants;
    public double totalPoints;

    public ReturnInformation(ArrayList<Points_history_merchant> points_history_merchants) {
        this.points_history_merchants = points_history_merchants;
        totalPoints = 0;
        for(Points_history_merchant points_history_merchant:points_history_merchants){
            totalPoints +=points_history_merchant.getPoints_citi();
        }
    }

    public ReturnInformation(){
        points_history_merchants = new ArrayList<Points_history_merchant>();
        totalPoints = 0;
    }

    public void setTotalPoints(){
        totalPoints = 0;
        for(Points_history_merchant points_history_merchant:points_history_merchants){
            totalPoints +=points_history_merchant.getPoints_citi();
        }
    }

    @Override
    public int compareTo(ReturnInformation o) {
        Timestamp timestamp1 = this.points_history_merchants.get(0).getTime();
        Timestamp timestamp2 = o.points_history_merchants.get(0).getTime();
        if(timestamp1.after(timestamp2))
            return -1;
        // TODO Auto-generated method stub
        return 1;
    }
}
