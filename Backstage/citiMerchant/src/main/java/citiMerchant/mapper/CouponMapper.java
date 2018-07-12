package citiMerchant.mapper;

import citiMerchant.vo.UserCoupon;
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
    final String addUserCoupon = "INSERT INTO user_coupon(couponID, userID, ItemID, state, getTime, useTime) " +
            "VALUES(NULL, #{userID}, #{ItemID}, #{state}, now(), null)";
    final String deleteOneUserCouponBy_UserID_AND_ItemID = "UPDATE user_coupon SET state = 'USED', useTime = now() " +
            "WHERE userID = #{userID} AND ItemID = #{itemID} AND state = 'UNUSED'" +
            "ORDER BY userID ASC LIMIT 1";

    @Select(getCouponsByUserID)
    List<UserCoupon> getCouponsByUserID(String userID);

    @Select(getCouponsBy_UserID_AND_ItemID)
    List<UserCoupon> getCouponsBy_UserID_AND_ItemID(@Param("userID") String userID, @Param("ItemID") String ItemID);

    @Insert(addUserCoupon)
    int addUserCoupon(UserCoupon userCoupon);

    @Update(deleteOneUserCouponBy_UserID_AND_ItemID)
    int deleteOneUserCouponBy_UserID_AND_ItemID(@Param("userID") String userID, @Param("ItemID") String itemID);


}
