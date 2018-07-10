package citi.merchant;

import citi.vo.Merchant;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    @ResponseBody
    @RequestMapping("/getInfos")
    public String getMerchantInfos(int start,int n){
        List<Merchant> merchants= merchantSerivce.getMerchants(start,n);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0;i<merchants.size();i++){
            sb.append(merchants.get(i).toString());
            sb.append(",");
        }

        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 获取该ID的商家信息
     * @param MerchantID
     * @return {"merchantID":"xxxx","merchantName":"xxxx","description":"xxxx","logoURL":"xxxx"}
     */
    @ResponseBody
    @RequestMapping("/{MerchantID}")
    public String getMerchant(@PathVariable String MerchantID){
        Merchant m = merchantSerivce.getMerchant(MerchantID);
        if(m==null)
            return "{}";
        else
            return m.toString();
    }
}
