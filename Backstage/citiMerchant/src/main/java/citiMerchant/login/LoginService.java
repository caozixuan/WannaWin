package citiMerchant.login;


import citiMerchant.mapper.DBhandler;
import citiMerchant.mapper.MerchantMapper;
import citiMerchant.vo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by zhong on 2018/7/11 19:52
 */
@Service
public class LoginService {

    @Autowired
    private DBhandler dBhandler;
    @Autowired
    private MerchantMapper merchantMapper;

    public ArrayList<Integer> getNums(String username, String password) {
        Merchant m = merchantMapper.loginMerchant(username,password);
        if(m==null)
            return null;
        else
            return dBhandler.getAmountByMerchantID(m.getMerchantID());
    }
}
