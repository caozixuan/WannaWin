package citi.funcModule.coupon;

import citi.persist.mapper.CouponMapper;
import citi.persist.mapper.ItemMapper;
import citi.persist.mapper.MerchantMapper;
import citi.vo.Item;
import citi.vo.Merchant;
import citi.vo.UserCoupon;
import citi.persist.procedure.probean.UserCoupon_record;
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
        UserCoupon_record userCoupon_record=new UserCoupon_record(userID,itemID);
        couponMapper.deleteOneUserCouponBy_UserID_AND_ItemID(userCoupon_record);
        if (userCoupon_record.getIfUsed()==1){
            return true;
        }else {
            return false;
        }
    }
}
