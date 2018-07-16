package citiMerchant.Merchant;

import citiMerchant.item.ItemService;
import citiMerchant.mapper.MerchantMapper;
import citiMerchant.vo.Item;
import citiMerchant.vo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
@Controller
@RequestMapping("/merchant")
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
    public ModelAndView submitEditMerchantInformation(String name, String description, String cardDescription, String address, String url2, String url3){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        String merchantID = session.getAttribute("merchantID").toString();
        Merchant merchantOdd = merchantMapper.selectByID(merchantID);
        ModelAndView mv =new ModelAndView();
        url2 = "localhost:8080/picture/"+merchantID+url2;
        url3 = "localhost:8080/picture/"+merchantID+url3;
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
        merchantMapper.updateMerchantLogo(merchantID,url2);
        merchantMapper.updateActivityTheme(merchantID,url3);
        mv.setViewName("redirect:/starter");
        return mv;
    }
}
