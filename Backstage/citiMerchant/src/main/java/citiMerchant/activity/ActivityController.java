package citiMerchant.activity;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhong on 2018/8/21 16:27
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private Gson gson;

    @RequestMapping("/all")
    public String getActivities(){

        return null;
    }
}
