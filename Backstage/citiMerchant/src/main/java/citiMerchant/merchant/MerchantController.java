package citiMerchant.merchant;

import citiMerchant.mapper.MerchantMapper;
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
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantMapper merchantMapper;

    private static String url = "http://www.byzhong.cn/image/merchant/";

    @RequestMapping("/editMerchant")
    public ModelAndView editMerchantInformation(HttpSession session){
        String merchantID = session.getAttribute("merchantID").toString();
        ModelAndView mv =new ModelAndView();
        mv.setViewName("table/editMerchant");
        mv.addObject("merchant",merchantMapper.selectByID(merchantID));
        return mv;
    }

    @RequestMapping("submitEditMerchantInformation")
    public ModelAndView submitEditMerchantInformation(String name, String description, String cardDescription, String address, String url2, String url3){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        String merchantID = session.getAttribute("merchantID").toString();
        Merchant merchantOdd = merchantMapper.selectByID(merchantID);
        ModelAndView mv =new ModelAndView();
        url2 = url+url2;
        url3 = url+url3;
        merchantOdd.setName(name);
        merchantOdd.setDescription(description);
        merchantOdd.setCardDescription(cardDescription);
        merchantOdd.setAddress(address);
        merchantOdd.setMerchantLogoURL(url2);
        merchantOdd.setCardLogoURL(url3);
        merchantMapper.updateMerchantName(merchantID, name);
        merchantMapper.updateMercahntAddress(merchantID,address);
        merchantMapper.updateMercahntDescription(merchantID,description);
        merchantMapper.updateMerchantCardDescription(merchantID,cardDescription);
        merchantMapper.updateMerchantLogo(url2,merchantID);
        merchantMapper.updateMerchantCardLogo(url3, merchantID);
        mv.addObject("merchant",merchantMapper.selectByID(merchantID));
        mv.setViewName("redirect:/starter");
        return mv;
    }

    @RequestMapping("/uploadFile1")
    @ResponseBody
    public Map<String, Object> uploadFile1(MultipartFile myfile1)
            throws IllegalStateException, IOException {
        // 原始名称
        String oldFileName = myfile1.getOriginalFilename(); // 获取上传文件的原名
//      System.out.println(oldFileName);
        // 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
        String saveFilePath = "/usr/share/tomcat7/image/merchant";
        //String saveFilePath = "E:\\picture";
        // 上传图片
        if (myfile1 != null && oldFileName != null && oldFileName.length() > 0) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session =request.getSession();
            String merchantID = session.getAttribute("merchantID").toString();            // 新的图片名称
            String newFileName = merchantID + oldFileName;
            // 新图片
            File newFile = new File(saveFilePath + "/" + newFileName);
            // 将内存中的数据写入磁盘
            myfile1.transferTo(newFile);
            // 将新图片名称返回到前端
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", "success");
            map.put("url", newFileName);
            return map;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", "fail");
            return map;
        }
    }

    @RequestMapping("/uploadFile2")
    @ResponseBody
    public Map<String, Object> uploadFile2(MultipartFile myfile2)
            throws IllegalStateException, IOException {
        // 原始名称
        String oldFileName = myfile2.getOriginalFilename(); // 获取上传文件的原名
//      System.out.println(oldFileName);
        // 存储图片的虚拟本地路径（这里需要配置tomcat的web模块路径，双击猫进行配置）
        String saveFilePath = "/usr/share/tomcat7/image/merchant";
        //String saveFilePath = "E:\\picture";
        // 上传图片
        if (myfile2 != null && oldFileName != null && oldFileName.length() > 0) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session =request.getSession();
            String merchantID = session.getAttribute("merchantID").toString();            // 新的图片名称
            String newFileName = merchantID + oldFileName;
            // 新图片
            File newFile = new File(saveFilePath + "/" + newFileName);
            // 将内存中的数据写入磁盘
            myfile2.transferTo(newFile);
            // 将新图片名称返回到前端
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", "success");
            map.put("url", newFileName);
            return map;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("error", "图片不合法");
            return map;
        }
    }
}
