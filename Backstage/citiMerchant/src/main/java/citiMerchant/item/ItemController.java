package citiMerchant.item;

import citiMerchant.mapper.MerchantMapper;
import citiMerchant.uitl.JsonResult;
import citiMerchant.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhong on 2018/7/11 19:51
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private MerchantMapper merchantMapper;

    private static String url = "http://www.byzhong.cn/image/item/";

    @RequestMapping("/getItem")
    public ModelAndView getItemList(HttpSession session) {
        String merchantID=session.getAttribute("merchantID").toString();
        ModelAndView mv = new ModelAndView();
        List<Item> items = itemService.getInfo(merchantID);
        mv.addObject("items", items);
        mv.addObject("merchant", merchantMapper.selectByID(merchantID));
        mv.setViewName("table/itemTable");
        return mv;
    }

    @RequestMapping("/addItem")
    public ModelAndView addItem() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("item",new Item());
        mv.setViewName("table/editItem");
        return mv;
    }

    @RequestMapping("/editItem")
    public ModelAndView editItem(String itemID) {
        ModelAndView mv = new ModelAndView();
        Item item = itemService.getItem(itemID);
        mv.addObject("item", item);
        mv.setViewName("table/editItem");
        return mv;
    }

    @RequestMapping("/submitEdit")
    @ResponseBody
    public String submitEdit(String itemID, String name, String description, double originalPrice, int points, long stock, String overdueTime,String itemType ,String logoURL,HttpSession session){
        ModelAndView mv = new ModelAndView();
        String merchantID = (String)session.getAttribute("merchantID");
        overdueTime = overdueTime + " 00:00:00";
        Item item=new Item(itemID,name,description,merchantID,logoURL,originalPrice,points,Timestamp.valueOf(overdueTime),stock,itemType);
        if (item.getItemID().equals("")){
            item.setItemID(UUID.randomUUID().toString());
            itemService.addItem(item);
        }else {
            itemService.updateItem(item);
        }
        return JsonResult.SUCCESS;
    }


    /*@RequestMapping("/item/submitEdit")
    public ModelAndView submitEdit(String url2, String itemID, String name, String description, String originalPrice, String points, String stock, String overdueTime, String myfile,HttpSession session) {
        String merchantID=session.getAttribute("merchantID").toString();
        ModelAndView mv = new ModelAndView();
        myfile = url + merchantID + url2;
        overdueTime = overdueTime + " 00:00:00";
        Item item = new Item(itemID, name, description, merchantID, myfile, Double.valueOf(originalPrice), Integer.valueOf(points), Timestamp.valueOf(overdueTime), Long.valueOf(stock));
        itemService.updateItem(item);
        List<Item> items = itemService.getInfo(merchantID);
        mv.addObject("items", items);
        mv.addObject("merchant", merchantMapper.selectByID(merchantID));
        mv.setViewName("item/showItem");
        return mv;
    }*/

    @RequestMapping("/deleteItem")
    public ModelAndView deleteItem(String itemID,HttpSession session) {
        String merchantID=session.getAttribute("merchantID").toString();
        itemService.deleteItem(itemID);
        ModelAndView mv = new ModelAndView();
        List<Item> items = itemService.getInfo(merchantID);
        mv.addObject("items", items);
        mv.addObject("merchant", merchantMapper.selectByID(merchantID));
        mv.setViewName("/table/itemTable");
        return mv;
    }

   /* @RequestMapping(value = {"/item/addItemOperation"})
    public ModelAndView addItemOperation(String url2, String name, String description, String originalPrice, String points, String stock, String overdueTime, String myfile,HttpSession session) {
        String merchantID=session.getAttribute("merchantID").toString();
        url2 = url + url2;
        overdueTime = overdueTime + " 00:00:00";
        Item item = new Item(name, description, merchantID, url2, Double.valueOf(originalPrice), Integer.valueOf(points), Timestamp.valueOf(overdueTime), Long.valueOf(stock));
        itemService.addItem(item);
        ModelAndView mv = new ModelAndView("redirect:/item/getItem");
        List<Item> items = itemService.getInfo(merchantID);
        mv.addObject("items", items);
        mv.addObject("merchant", merchantMapper.selectByID(merchantID));
        //mv.setViewName("/item/showItem");
        return mv;
    }*/

    /*@RequestMapping("/uploadFile")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile myfile)
            throws IllegalStateException, IOException {
        // 原始名称
        String oldFileName = myfile.getOriginalFilename(); // 获取上传文件的原名
//      System.out.println(oldFileName);
        // 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
        String saveFilePath = "/usr/share/tomcat7/image/item";
        //String saveFilePath = "E:\\image";
        // 上传图片
        if (myfile != null && oldFileName != null && oldFileName.length() > 0) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            String merchantID = session.getAttribute("merchantID").toString();            // 新的图片名称
            String newFileName = merchantID + oldFileName;
            // 新图片
            File newFile = new File(saveFilePath + "/" + newFileName);
            // 将内存中的数据写入磁盘
            myfile.transferTo(newFile);
            // 将新图片名称返回到前端
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", "success");
            map.put("url", url + newFileName);
            return map;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", "error");
            return map;
        }
    }*/


    @RequestMapping("/{itemID}")
    @ResponseBody
    public Item getItem(@PathVariable String itemID){
        return itemService.getItem(itemID);
    }
}
