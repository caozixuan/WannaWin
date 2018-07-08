package citi.citi;

import citi.vo.CitiCard;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/citi")
@Controller
public class CitiController {

    @Autowired
    private CitiService citiService;

    @Autowired
    private Gson gson;

    /**
     * 绑定花旗卡
     * @param citiCard
     * @return 成功：{"isBinding":true}，失败：{"isBinding":false}
     */
    @RequestMapping("/bindCard")
    public String bindCard(CitiCard citiCard){

        return "";
    }

    /**
     * @param citiCard 将被解绑的卡
     * @return 成功：{"unBinding":true}，失败：{"unBinding":false}
     */
    @RequestMapping("/unbind")
    public String unBind(CitiCard citiCard){


        return null;
    }




}
