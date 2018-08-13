package citiMerchant.test;

import citiMerchant.vo.Test;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestCon {

    @Autowired
    private Gson gson;

    @RequestMapping("test")
    @ResponseBody
    public String test(Test test){
        return gson.toJson(test);
    }
}
