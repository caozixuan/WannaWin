package citi.userCoupon;

import citi.mapper.CouponMapper;
import citi.mapper.ItemMapper;
import citi.vo.UserCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class userCouponService {

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    ItemMapper itemMapper;
    //long couponID, String userID, String itemID, String state, Timestamp getTime, Timestamp useTime, String itemName
    public ArrayList<ReturnUserCoupon> changeToReturnUserCoupon(List<UserCoupon> userCoupons){
        ArrayList<ReturnUserCoupon> returnUserCoupons = new ArrayList<ReturnUserCoupon>();
        for(UserCoupon userCoupon:userCoupons){
            returnUserCoupons.add(new ReturnUserCoupon(userCoupon.getCouponID(),userCoupon.getUserID(),userCoupon.getItemID(),userCoupon.getState().toString(),userCoupon.getGetTime(),userCoupon.getUseTime(),itemMapper.getItemByItemID(userCoupon.getItemID()).getName()));
        }
        return returnUserCoupons;
    }
}
