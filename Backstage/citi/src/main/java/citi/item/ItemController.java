package citi.item;

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

    public void buy(String userID,String itemID){

    }





}
