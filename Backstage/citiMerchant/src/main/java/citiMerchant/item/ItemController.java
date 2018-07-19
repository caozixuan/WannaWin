package citiMerchant.item;

import citiMerchant.mapper.MerchantMapper;
import citiMerchant.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

/**
 * Created by zhong on 2018/7/11 19:51
 */
@Controller

public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private MerchantMapper merchantMapper;

    private static String url = "http://www.byzhong.cn/image/item/";

    @RequestMapping("/item/getItem")
    public ModelAndView getItemList() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String merchantID = session.getAttribute("merchantID").toString();
        ModelAndView mv = new ModelAndView();
        List<Item> items = itemService.getInfo(merchantID);
        mv.addObject("items", items);
        mv.addObject("merchant", merchantMapper.selectByID(merchantID));
        mv.setViewName("/item/showItem");
        return mv;
    }

    @RequestMapping("/item/addItem")
    public ModelAndView addItem(String merchantID) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("merchant", merchantMapper.selectByID(merchantID));
        mv.setViewName("item/addItem");
        return mv;
    }

    @RequestMapping("/item/editItem")
    public ModelAndView editItem(String itemID) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String merchantID = session.getAttribute("merchantID").toString();
        ModelAndView mv = new ModelAndView();
        Item item = itemService.getItem(itemID);
        mv.addObject("item", item);
        mv.addObject("merchant", merchantMapper.selectByID(merchantID));
        mv.setViewName("/item/editItem");
        return mv;
    }


    @RequestMapping("/item/submitEdit")
    public ModelAndView submitEdit(String url2, String itemID, String name, String description, String originalPrice, String points, String stock, String overdueTime, String myfile) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String merchantID = session.getAttribute("merchantID").toString();
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
    }

    @RequestMapping("/item/deleteItem")
    public ModelAndView deleteItem(String itemID) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String merchantID = session.getAttribute("merchantID").toString();
        itemService.deleteItem(itemID);
        ModelAndView mv = new ModelAndView();
        List<Item> items = itemService.getInfo(merchantID);
        mv.addObject("items", items);
        mv.addObject("merchant", merchantMapper.selectByID(merchantID));
        mv.setViewName("/item/showItem");
        return mv;
    }

    @RequestMapping("/item/addItemOperation")
    public ModelAndView addItemOperation(String url2, String name, String description, String originalPrice, String points, String stock, String overdueTime, String myfile) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String merchantID = session.getAttribute("merchantID").toString();
        myfile = url + url2;
        overdueTime = overdueTime + " 00:00:00";
        Item item = new Item(name, description, merchantID, myfile, Double.valueOf(originalPrice), Integer.valueOf(points), Timestamp.valueOf(overdueTime), Long.valueOf(stock));
        itemService.addItem(item);
        ModelAndView mv = new ModelAndView("redirect:/item/getItem");
        List<Item> items = itemService.getInfo(merchantID);
        mv.addObject("items", items);
        mv.addObject("merchant", merchantMapper.selectByID(merchantID));
        //mv.setViewName("/item/showItem");
        return mv;
    }

    @RequestMapping("/item/uploadFile")
    @ResponseBody
    public Map<String, Object> uploadFile(MultipartFile myfile)
            throws IllegalStateException, IOException {
        // 原始名称
        String oldFileName = myfile.getOriginalFilename(); // 获取上传文件的原名
//      System.out.println(oldFileName);
        // 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
        String saveFilePath = "/usr/share/tomcat7/image/item";
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
