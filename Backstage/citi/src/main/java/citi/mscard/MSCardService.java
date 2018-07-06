package citi.mscard;

import citi.vo.MSCard;
import citi.vo.MSCardType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MSCardService {

    /**
     * 获取该用户下积分最多的n张卡信息
     * @param userId
     * @param n
     * @return 卡列表
     */
    public List<MSCard> getInfo(String userId, String n){

        return null;
    }

    /**
     * 由cardID获取卡片
     * @param CardID
     * @return
     */
    public MSCard getMSCardInfo(String CardID){

        return null;
    }

    /**
     * 获取该商户下的卡类型
     * @param merchantID
     * @return
     */
    public List<MSCardType> getTypes(String merchantID){

        return null;
    }

    /**
     * 添加会员卡
     * @param msCard
     * @return
     */
    public boolean addMSCard(MSCard msCard){

        return false;
    }



}
