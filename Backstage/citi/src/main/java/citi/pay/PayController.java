package citi.pay;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhong
 * @date 2018-7-11
 */
@RequestMapping("/pay")
@Controller
public class PayController {
    @Autowired
    private Gson gson;

    @RequestMapping("/pay")
    public void pay(String userId,String merchantId,float totelPrice){


    }

}
