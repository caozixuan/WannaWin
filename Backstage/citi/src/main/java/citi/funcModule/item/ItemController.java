package citi.funcModule.item;

import citi.support.resultjson.ResultJson;
import citi.vo.Item;
import citi.vo.Merchant;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhong on 2018/7/17 18:50
 */
@RequestMapping(value = {"/item"},produces = {"text/html;charset=UTF-8"})
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private Gson gson;

    @ResponseBody
    @RequestMapping("/getItems")
    public String getItems(int start,int n){
        List<Item> items=itemService.getItems(start,n);
        return gson.toJson(items);
    }

    @ResponseBody
    @RequestMapping("/buy")
    public String buy(String userID,String itemID){
        if (itemService.buy(userID,itemID,1).getState()){
            return ResultJson.SUCCESS;
        }else {
            return ResultJson.FAILURE;
        }
    }
    @ResponseBody
    @RequestMapping("/getMerchantItems")
    public String getMerchantItems(String merchantID,int start,int n){
        List<Item> items=itemService.getMerchantItems(merchantID,start,n);
        return gson.toJson(items);
    }

    @ResponseBody
    @RequestMapping("/buyMultiple")
    public String buy(String userID,String itemID,int count){
        if (itemService.buy(userID,itemID,count).getState()){
            return ResultJson.SUCCESS;
        }else {
            return ResultJson.FAILURE;
        }
    }

    @ResponseBody
    @RequestMapping("/itemDetail")
    public String getItemInfo(String itemID){
        Item item = itemService.getItem(itemID);
        return gson.toJson(itemService.getItem(itemID));
    }




}
