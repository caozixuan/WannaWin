package citi.vo;

import java.util.List;

public class UserPref {

    private String userID;
    private String prefType;

    //for DB
    public UserPref(String userID, String prefType) {
        this.userID = userID;
        this.prefType = prefType;
    }

    //for programmer
    public UserPref(String userID, List<Type.ItemType> prefTypes) {
        this.userID = userID;
        this.prefType = Type.ItemType.enum2DBStr(prefTypes);
    }

    //for programmer
    public UserPref(String userID, Type.TypeWrapper tw) {
        this.userID = userID;
        this.prefType = tw.toString();
    }

    public String getUserID() {
        return userID;
    }

    public List<Type.ItemType> getPrefType() {
        return Type.ItemType.DBStr2enum(prefType);
    }


}
