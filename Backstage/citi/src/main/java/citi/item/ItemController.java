package citi.item;

import citi.support.resultjson.ResultJson;
import citi.vo.Item;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhong on 2018/7/17 18:50
 */
@RequestMapping("/item")
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private Gson gson;

    @ResponseBody
    @RequestMapping("/getItems")
    public String getItems(int start,int length){
        List<Item> items=itemService.getItems(start,length);
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
    public String getMerchantItems(String merchantID,int start,int length){
        List<Item> items=itemService.getMerchantItems(merchantID,start,length);
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





}
