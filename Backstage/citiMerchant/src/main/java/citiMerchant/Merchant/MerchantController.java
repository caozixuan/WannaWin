package citiMerchant.Merchant;

import citiMerchant.item.ItemService;
import citiMerchant.mapper.MerchantMapper;
import citiMerchant.vo.Item;
import citiMerchant.vo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantMapper merchantMapper;

    @RequestMapping("editMerchantInformation")
    public ModelAndView editMerchantInformation(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        String merchantID = session.getAttribute("merchantID").toString();
        ModelAndView mv =new ModelAndView();
        mv.setViewName("merchantInformation/editInformation");
        Merchant merchant = merchantMapper.selectByID(merchantID);
        mv.addObject(merchant);
        return mv;
    }

    @RequestMapping("submitEditMerchantInformation")
    public ModelAndView submitEditMerchantInformation(String name, String description, String cardDescription, String address, String merchantLogoURL, String cardLogoURL){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        String merchantID = session.getAttribute("merchantID").toString();
        Merchant merchantOdd = merchantMapper.selectByID(merchantID);
        ModelAndView mv =new ModelAndView();
        merchantLogoURL = "localhost:8080/picture/"+merchantID+merchantLogoURL;
        merchantOdd.setName(name);
        merchantOdd.setDescription(description);
        merchantOdd.setCardDescription(cardDescription);
        merchantOdd.setAddress(address);
        merchantOdd.setMerchantLogoURL(merchantLogoURL);
        merchantOdd.setCardLogoURL(cardLogoURL);
        //TODO:这里缺数据库方法
        merchantMapper.addMerchant(merchantOdd);
        mv.setViewName("redirect:starter.jsp");
        return mv;
    }
}
