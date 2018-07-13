package citiMerchant.item;

import citiMerchant.vo.Item;
import citiMerchant.vo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

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
        mv.setViewName("item/showItem");
        return mv;
    }

    @RequestMapping("addItem")
    public ModelAndView addItem(String merchantID){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("item/addItem");
        return mv;
    }

    @RequestMapping("editItem")
    public ModelAndView editItem(String itemID){
        ModelAndView mv =new ModelAndView();
        Item item = itemService.getItem(itemID);
        mv.addObject("item",item);
        mv.setViewName("editItem");
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

    @RequestMapping("addItemOperation")
    public ModelAndView addItemOperation(String name, String description, String originalPrice, String points, String stock, String overdueTime, String myfile){

        String merchantID = "123";
        overdueTime = overdueTime +" 00:00:00";
        Item item = new Item(name, description, merchantID, myfile, Double.valueOf(originalPrice),Integer.valueOf(points), Timestamp.valueOf(overdueTime), Integer.valueOf(stock));
        itemService.addItem(item);
        ModelAndView mv =new ModelAndView();
        List<Item> items = itemService.getInfo(merchantID);
        mv.addObject("items",items);
        mv.setViewName("showItem");
        return mv;
    }

    @RequestMapping("uploadFile")
    @ResponseBody
    public Map<String, Object> uploadFile(MultipartFile myfile)
            throws IllegalStateException, IOException {
        System.out.println("hahahahahah");
        // 原始名称
        String oldFileName = myfile.getOriginalFilename(); // 获取上传文件的原名
//      System.out.println(oldFileName);
        // 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
        String saveFilePath = "E://picture";
        // 上传图片
        if (myfile != null && oldFileName != null && oldFileName.length() > 0) {
            // 新的图片名称
            String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
            // 新图片
            File newFile = new File(saveFilePath + "\\" + newFileName);
            // 将内存中的数据写入磁盘
            myfile.transferTo(newFile);
            // 将新图片名称返回到前端
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", "成功啦");
            map.put("url", newFileName);
            return map;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("error", "图片不合法");
            return map;
        }
    }

}
