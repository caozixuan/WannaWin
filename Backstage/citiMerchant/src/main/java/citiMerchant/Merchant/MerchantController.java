package citiMerchant.Merchant;

import citiMerchant.item.ItemService;
import citiMerchant.mapper.MerchantMapper;
import citiMerchant.vo.Item;
import citiMerchant.vo.Merchant;
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

@Controller
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantMapper merchantMapper;

    private static String url = "http://www.byzhong.cn/image/merchant/";

    @RequestMapping("editMerchantInformation")
    public ModelAndView editMerchantInformation(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        String merchantID = session.getAttribute("merchantID").toString();
        ModelAndView mv =new ModelAndView();
        mv.setViewName("merchantInformation/editInformation");
        Merchant merchant = merchantMapper.selectByID(merchantID);
        mv.addObject(merchant);
        mv.addObject("merchant",merchantMapper.selectByID(merchantID));
        return mv;
    }

    @RequestMapping("submitEditMerchantInformation")
    public ModelAndView submitEditMerchantInformation(String name, String description, String cardDescription, String address, String myfile1, String myfile2){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        String merchantID = session.getAttribute("merchantID").toString();
        Merchant merchantOdd = merchantMapper.selectByID(merchantID);
        ModelAndView mv =new ModelAndView();
        myfile1 = url+merchantID+myfile1;
        myfile2 = url+merchantID+myfile2;
        merchantOdd.setName(name);
        merchantOdd.setDescription(description);
        merchantOdd.setCardDescription(cardDescription);
        merchantOdd.setAddress(address);
        merchantOdd.setMerchantLogoURL(myfile1);
        merchantOdd.setCardLogoURL(myfile2);
        merchantMapper.updateMerchantName(merchantID, name);
        merchantMapper.updateMercahntAddress(merchantID,address);
        merchantMapper.updateMercahntDescription(merchantID,description);
        merchantMapper.updateMerchantCardDescription(merchantID,cardDescription);
        merchantMapper.updateMerchantLogo(myfile1,merchantID);
        merchantMapper.updateMerchantCardLogo(myfile2, merchantID);
        mv.addObject("merchant",merchantMapper.selectByID(merchantID));
        mv.setViewName("redirect:/starter");
        return mv;
    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public Map<String, Object> uploadFile(MultipartFile myfile)
            throws IllegalStateException, IOException {
        // 原始名称
        String oldFileName = myfile.getOriginalFilename(); // 获取上传文件的原名
//      System.out.println(oldFileName);
        // 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
        String saveFilePath = "/usr/share/tomcat7/image/merchant";
        // 上传图片
        if (myfile != null && oldFileName != null && oldFileName.length() > 0) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session =request.getSession();
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
