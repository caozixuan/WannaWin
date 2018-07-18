package citi.item;

import citi.mapper.CouponMapper;
import citi.mapper.ItemMapper;
import citi.vo.Item;
import citi.vo.UserCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2018/7/17 18:52
 */
@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private CouponMapper couponMapper;

    public List<Item> getItems(int start,int length){
        List<Item> items=new ArrayList<>();

        return items;
    }

    public boolean bug(String userID,String itemID){
        Item item=itemMapper.getItemByItemID(itemID);
        if (item==null){
            return false;
        }
        if (item.getStock()<=0){
            return false;
        }
        if (itemMapper.updateItemStockByID(itemID,item.getStock()-1)==1){
            UserCoupon userCoupon=new UserCoupon(userID,itemID,UserCoupon.CouponState.UNUSED);
            if (couponMapper.addUserCoupon(userCoupon)==1){
                return true;
            }
        }
        return false;
    }
}
