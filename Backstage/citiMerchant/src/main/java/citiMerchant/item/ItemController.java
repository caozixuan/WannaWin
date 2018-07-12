package citiMerchant.item;

import citiMerchant.vo.Item;
import citiMerchant.vo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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
        List<Item> items = itemService.getInfo(merchantID);
        mv.addObject("items",items);
        mv.setViewName("showItem");
        return mv;
    }

    @RequestMapping("editItem")
    public ModelAndView editItem(String itemID){
        ModelAndView mv =new ModelAndView();
        Item item = itemService.getItem(itemID);
        mv.addObject("item",item);
        mv.setViewName("addItem");
        return mv;
    }

    @RequestMapping("submitEdit")
    public ModelAndView submitEdit(String merchantID, Item item){
        ModelAndView mv =new ModelAndView();
        itemService.updateItem(item);
        List<Item> items = itemService.getInfo(merchantID);
        mv.addObject("items",items);
        mv.setViewName("showItem");
        return mv;
    }

    @RequestMapping("deleteItem")
    public ModelAndView deleteItem(String merchantID, String itemID){
        itemService.deleteItem(itemID);
        ModelAndView mv =new ModelAndView();
        List<Item> items = itemService.getInfo(merchantID);
        mv.addObject("items",items);
        mv.setViewName("showItem");
        return mv;
    }

    @RequestMapping("addItem")
    public ModelAndView addItem(String merchantID, Item item){
        itemService.addItem(item);
        ModelAndView mv =new ModelAndView();
        List<Item> items = itemService.getInfo(merchantID);
        mv.addObject("items",items);
        mv.setViewName("showItem");
        return mv;
    }

}
