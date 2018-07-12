package citiMerchant.item;

import citiMerchant.vo.Item;
import citiMerchant.vo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by zhong on 2018/7/11 19:51
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("getItem")
    public ModelAndView getItemList(String merchantID){
        ModelAndView mv =new ModelAndView();
        ArrayList<Item> items = itemService.getInfo(merchantID);
        mv.addObject("items",items);
        mv.setViewName("showItem");
        return mv;
    }

    public ModelAndView editItem(String itemID){
        ModelAndView mv =new ModelAndView();
        Item item = itemService.getItem(itemID);
        mv.addObject("item",item);
        mv.setViewName("addItem");
        return mv;
    }

    public void submitEdit(Item item){

    }

    public ModelAndView deleteItem(String merchantID, String itemID){
        itemService.deleteItem(itemID);
        ModelAndView mv =new ModelAndView();
        ArrayList<Item> items = itemService.getInfo(merchantID);
        mv.addObject("items",items);
        mv.setViewName("showItem");
        return mv;
    }

    public void addItem(Item item){

    }

}
