package citi.merchant;

import citi.vo.Merchant;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 接口设计：刘钟博
 * 代码填充：彭璇
 *bug修复：刘钟博
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private Gson gson;
    @Autowired
    private MerchantSerivce merchantSerivce;

    /**
     * 从start开始的n个商家，不足n即返回实际数量
     * @url /merchant/getInfos
     * @param start 开始数
     * @param n 请求长度
     * @return [{"merchantID":"1","name":"1","description":"520d9479","address":"7ea18369","logoURL":"http://xx.png"}]
     */
    @ResponseBody
    @RequestMapping("/getInfos")
    public String getMerchantInfos(int start,int n){
        List<Merchant> merchants= merchantSerivce.getMerchants(start,n);
        return gson.toJson(merchants);
    }

    /**
     * 获取该ID的商家信息
     * @url /merchant/{MerchantID}
     * @param MerchantID 商户ID
     * @return {"merchantID":"00001","name":"apple","description":"to eat","address":"WHU","logoURL":"http://.png"}
     */
    @ResponseBody
    @RequestMapping("/{MerchantID}")
    public String getMerchant(@PathVariable String MerchantID){
        Merchant m = merchantSerivce.getMerchant(MerchantID);
        if(m==null)
            return "{}";
        else
            return gson.toJson(m);
    }

    /*
     * 获取商户数量
     * @url /merchant/getNum
     * @param 无
     * @return {num: 42}
     */
    @ResponseBody
    @RequestMapping("/getNum")
    public String getNum(){
        int num = merchantSerivce.getNum();
        return "{\"num\": "+num+"}";
    }


}
