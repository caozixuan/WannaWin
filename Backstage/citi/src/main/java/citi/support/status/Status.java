package citi.support.status;

import javafx.util.Pair;

/**
 * Created by zhong on 2018/7/18 9:41
 */
public class Status<State,Reason> {
    private State state;
    private Reason reason;

    public Status(State state,Reason reason){
        this.state=state;
        this.reason=reason;
    }

    public Status(){

    }

    public Reason getReason() {
        return reason;
    }

    public State getState() {
        return state;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public void setState(State state) {
        this.state = state;
    }
}
