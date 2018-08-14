package citi.funcModule.item;

import citi.persist.mapper.CouponMapper;
import citi.persist.mapper.ItemMapper;
import citi.persist.mapper.UserMapper;
import citi.support.status.Status;
import citi.vo.Item;
import citi.vo.UserCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private UserMapper userMapper;

    public List<Item> getItems(int start,int length){
        return itemMapper.getItem(start,length);
    }

    public Status<Boolean,String> buy(String userID, String itemID,int count){
        if (count<=0)return ItemStatus.INVALID;
        Item item=itemMapper.getItemByItemID(itemID);
        if (item==null){
            return ItemStatus.INVALID;
        }
        if (item.getStock()-count<0){
            return ItemStatus.EMPTY;
        }
        if (itemMapper.updateItemStockByID(itemID,item.getStock()-count)==1){
            UserCoupon userCoupon=new UserCoupon(userID,itemID,UserCoupon.CouponState.UNUSED);
            userMapper.useGeneralPoints(userID,item.getPoints()*count);
            for (int i=0;i<count;i++){
                couponMapper.addUserCoupon(userCoupon);
            }
            return ItemStatus.SUCCESS;
        }
        return ItemStatus.OTHER;
    }

    public List<Item> getMerchantItems(String merchantID,int start,int length){
        return itemMapper.getItemByMerchantID(merchantID,start,length);
    }

    public Item getItem(String itemID){
        return itemMapper.getItemByItemID(itemID);
    }
}
