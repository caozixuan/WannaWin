package citi.merchant;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private Gson gson;
    @Autowired
    private MerchantSerivce merchantSerivce;

    /**
     * 从start开始的n个商家，不足n即返回实际数量
     * @param start
     * @param n
     * @return [{"merchantID":"xxxx","merchantName":"xxxx","description":"xxxx","logoURL":"xxxx"},{}...]
     */
    @RequestMapping("/getInfos")
    public String getMerchantInfos(int start,int n){

        return null;
    }

    /**
     * 获取该ID的商家信息
     * @param MerchantID
     * @return {"merchantID":"xxxx","merchantName":"xxxx","description":"xxxx","logoURL":"xxxx"}
     */
    @RequestMapping("/{MerchantID}")
    public String getMerchant(@PathVariable String MerchantID){

        return null;
    }
}
