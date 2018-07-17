package citi.user;

import citi.mapper.MSCardMapper;
import citi.mapper.MerchantMapper;
import citi.mapper.UserMapper;
import citi.vo.MSCard;
import citi.vo.Merchant;
import citi.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhong on 2018/7/11 19:23
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MSCardMapper msCardMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    public User getInfo(String userID){
        return userMapper.getInfoByUserID(userID);
    }

    public double getAvailablePoints(User user){
        if (user==null||user.getUserID()==null)return -1;
        List<MSCard> msCards=msCardMapper.select(user.getUserID());
        double availablePoints=0;
        for (MSCard msCard:msCards){
            Merchant merchant=merchantMapper.selectByID(msCard.getMerchantId());
            double proportion=merchant.getProportion();
            availablePoints+=proportion*msCard.getPoints();
        }
        return availablePoints;
    }
}
