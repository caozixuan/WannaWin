package citiMerchant.item;

import citiMerchant.vo.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2018/7/11 19:51
 */
public class ItemService {

    public ArrayList<Item> getInfo(String merchantID){
        ArrayList<Item> items = new ArrayList<Item>(); //此处应该调用数据库，根据merchantID获取
        return items;
    }

    public Item getItem(String itemID){
        Item item = new Item();     //此处也是应该调用数据库
        return item;
    }

    public void deleteItem(String itemID){
                                   // 此处调用删除方法
    }
}
