package citiMerchant.item;

import citiMerchant.vo.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2018/7/11 19:51
 */
public class ItemService {
    ArrayList<Item> items;

    public ArrayList<Item> getInfo(String merchantID){
        items = new ArrayList<Item>(); //此处应该调用数据库，根据merchantID获取
        return items;
    }
}
