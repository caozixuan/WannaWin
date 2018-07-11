package citi.mybatismapper;

import citi.vo.UserCoupon;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponMapper {

    //property 'state' can only be 'UNUSED' or 'USED'

    final String getCouponsByUserID = "SELECT * FROM user_coupon " +
            "WHERE userID = #{userID} AND state = 'UNUSED'";
    final String getCouponsBy_UserID_AND_ItemID = "SELECT * FROM user_coupon " +
            "WHERE userID = #{userID} AND ItemID = #{ItemID} AND state = 'UNUSED'";
    final String addUserCoupon = "INSERT INTO user_coupon(couponID, userID, ItemID, state) " +
            "VALUES(NULL, #{userID}, #{ItemID}, #{state})";
    final String deleteOneUserCouponByUserID = "UPDATE user_coupon SET state = 'USED' " +
            "WHERE userID = #{userID} AND couponID ";

    @Select(getCouponsByUserID)
    List<UserCoupon> getCouponsByUserID(String userID);

    @Select(getCouponsBy_UserID_AND_ItemID)
    List<UserCoupon> getCouponsBy_UserID_AND_ItemID(@Param("userID") String userID, @Param("ItemID") String ItemID);

    @Insert(addUserCoupon)
    int addUserCoupon(UserCoupon msCardType);

    @Update(deleteOneUserCouponByUserID)
    int deleteOneUserCouponByUserID(String userID);

}
