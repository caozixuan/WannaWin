package citi.item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhong on 2018/7/17 18:50
 */
@RequestMapping("/item")
@Controller
public class ItemController {

    @RequestMapping("/getItems")
    public void getItems(){


    }

    public void buy(String userID,String itemID){

    }


}
