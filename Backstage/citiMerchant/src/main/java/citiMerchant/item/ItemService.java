package citiMerchant.item;

import citiMerchant.mapper.ItemMapper;
import citiMerchant.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2018/7/11 19:51
 */
@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;

    public List<Item> getInfo(String merchantID){
        List<Item> items = itemMapper.getItemByMerchantID(merchantID);
        return items;
    }

    public Item getItem(String itemID){
        Item item = itemMapper.getItemByItemID(itemID);
        return item;
    }

    public void updateItem(Item item){
        itemMapper.updateItemByID(item);
    }

    public void deleteItem(String itemID){
        itemMapper.deleteItemByID(itemID);
    }

    public void addItem(Item item){
        itemMapper.addItem(item);
    }
}
