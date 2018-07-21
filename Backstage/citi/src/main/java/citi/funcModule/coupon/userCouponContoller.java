package citi.funcModule.coupon;


import citi.persist.mapper.CouponMapper;
import citi.support.resultjson.ResultJson;
import citi.vo.UserCoupon;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/userCoupon"},produces = {"text/html;charset=UTF-8"})
public class userCouponContoller {

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    userCouponService userCouponService;

    @Autowired
    Gson gson;
    @ResponseBody
    @RequestMapping("/getUnusedCoupons")
    public String getUnusedCoupons(String userID){
        List<UserCoupon> userCoupons = couponMapper.getCouponsByUserID(userID);
        ArrayList<ReturnUserCoupon> returnUserCoupons = userCouponService.changeToReturnUserCoupon(userCoupons);
        return gson.toJson(returnUserCoupons);
    }

    @ResponseBody
    @RequestMapping("/getUsedCoupons")
    public String getUsedCoupons(String userID){
        List<UserCoupon> userCoupons = couponMapper.get_USED_Coupon(userID);
        ArrayList<ReturnUserCoupon> returnUserCoupons = userCouponService.changeToReturnUserCoupon(userCoupons);
        return gson.toJson(returnUserCoupons);
    }


    @ResponseBody
    @RequestMapping("/getOverduedCoupons")
    public String getOverduedCoupons(String userID){
        List<UserCoupon> userCoupons = couponMapper.get_OVERDUED_Coupon(userID);
        ArrayList<ReturnUserCoupon> returnUserCoupons = userCouponService.changeToReturnUserCoupon(userCoupons);
        return gson.toJson(returnUserCoupons);
    }

    @ResponseBody
    @RequestMapping("/use")
    public String use(String userID,String itemID){
        if (userCouponService.use(userID,itemID)){
            return ResultJson.SUCCESS;
        }else {
            return ResultJson.FAILURE;
        }
    }
}
