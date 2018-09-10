package citi.funcModule.item;

import citi.persist.procedure.probean.ItemBean;
import citi.support.resultjson.ResultJson;
import citi.vo.Item;
import citi.vo.Merchant;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhong on 2018/7/17 18:50
 */
@RequestMapping(value = {"/item"},produces = {"text/json;charset=UTF-8"})
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
    public String getItemInfo(String itemID, String userID){
        if(userID!=null){
            Timestamp nowTimestamp = new Timestamp(new Date().getTime());
            itemService.addRecord(userID,itemID,nowTimestamp);
        }
        return gson.toJson(itemService.getItem(itemID));
    }

    @ResponseBody
    @RequestMapping("/search")
    public String search(String keyword, String start, String end){
        if(Integer.valueOf(start)>=Integer.valueOf(end)||keyword==null||keyword.equals(""))
            return "[]";
        String [] keywords = keyword.split("\\s+");
        ArrayList<ItemBean> itemBeans = itemService.search(keywords);
        ArrayList<ItemBean> results = new ArrayList<ItemBean>();
        int returnNum = 0;
        if(Integer.valueOf(end)<itemBeans.size())
            returnNum = Integer.valueOf(end);
        else
            returnNum = itemBeans.size();
        for(int i=Integer.valueOf(start);i<returnNum;i++){
            results.add(itemBeans.get(i));
        }
        return gson.toJson(results);
    }

    @ResponseBody
    @RequestMapping("/searchNum")
    public String searchNum(String keyword){
        String [] keywords = keyword.split("\\s+");
        return "{\"num\":"+itemService.searchCount(keywords)+"}";
    }




}
