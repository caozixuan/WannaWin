package citi.userCoupon;

import citi.mapper.CouponMapper;
import citi.mapper.ItemMapper;
import citi.mapper.MerchantMapper;
import citi.vo.Item;
import citi.vo.Merchant;
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
    MerchantMapper merchantMapper;

    @Autowired
    ItemMapper itemMapper;
    //long couponID, String userID, String itemID, String state, Timestamp getTime, Timestamp useTime, String itemName
    public ArrayList<ReturnUserCoupon> changeToReturnUserCoupon(List<UserCoupon> userCoupons){
        ArrayList<ReturnUserCoupon> returnUserCoupons = new ArrayList<ReturnUserCoupon>();
        for(UserCoupon userCoupon:userCoupons){
            Item item = itemMapper.getItemByItemID(userCoupon.getItemID());
            Merchant merchant = merchantMapper.selectByID(item.getMerchantID());
            returnUserCoupons.add(new ReturnUserCoupon(userCoupon.getCouponID(),userCoupon.getUserID(),userCoupon.getItemID(),userCoupon.getState().toString(),userCoupon.getGetTime(),userCoupon.getUseTime(),itemMapper.getItemByItemID(userCoupon.getItemID()).getName(),item.getPoints(),item.getDescription(),item.getOverdueTime(),merchant.getMerchantLogoURL()));
        }
        return returnUserCoupons;
    }

    public boolean use(String userID,String itemID){
        if (couponMapper.deleteOneUserCouponBy_UserID_AND_ItemID(userID,itemID)==1){
            return true;
        }else {
            return false;
        }
    }
}
