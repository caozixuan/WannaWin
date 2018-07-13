package citiMerchant.login;


import citiMerchant.mapper.DBHandler;
import citiMerchant.mapper.MerchantMapper;
import citiMerchant.vo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by zhong on 2018/7/11 19:52
 * @author 彭璇
 */
@Service
public class LoginService {

    @Autowired
    private DBHandler dBhandler;
    @Autowired
    private MerchantMapper merchantMapper;

    public ArrayList<Integer> getNums(String merchantID, String password) {
        Merchant m = merchantMapper.loginMerchant(merchantID,password);
        if(m==null)
            return null;
        else
            return dBhandler.getAmountByMerchantID(m.getMerchantID());
    }


}
