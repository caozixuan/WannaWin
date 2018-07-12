package citiMerchant.login;

import citiMerchant.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by zhong on 2018/7/11 19:52
 */
@Service
public class LoginService {

    @Autowired
    private LoginDao loginDao;

    public ArrayList<Integer> getNums(String username, String password) {
        if(!loginDao.verify(username,password))
            return null;
        else
            return loginDao.getNums(username,password);
    }


}
