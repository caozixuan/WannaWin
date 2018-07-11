package citi.pay;
import citi.mybatismapper.OrderMapper;
import citi.vo.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhong
 */
@RequestMapping("/pay")
@Controller
public class PayController {
    @Autowired
    private Gson gson;

    @RequestMapping("/pay")
    public void pay(String userId){

    }

}
