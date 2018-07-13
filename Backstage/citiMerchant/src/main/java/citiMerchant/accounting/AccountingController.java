package citiMerchant.accounting;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhong on 2018/7/11 19:49
 */

public class AccountingController {

    private AccountingService accountingService;

    @RequestMapping("/accounting")
    @ResponseBody
    public String  getStat(){
        //return String.valueOf(accountingService.totalPoints);
        return "";
    }

}
