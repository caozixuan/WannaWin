package citi.mscard;

import citi.mybatismapper.MSCardMapper;
import citi.mybatismapper.MerchantMapper;
import citi.vo.MSCard;
import citi.vo.MSCardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 接口设计：刘钟博
 * 代码填充：曹子轩
 */

@Service
public class MSCardService {

    /**
     * 获取该用户下积分最多的n张卡信息
     * @param userId
     * @param n
     * @return 卡列表
     */

    @Autowired
    private MSCardMapper msCardMapper;
    @Autowired
    private MerchantMapper merchantMapper;

    public List<MSCard> getInfo(String userId, int n){
        List<MSCard> allCards =msCardMapper.select(userId);
        if(allCards==null)
            return null;
        Collections.sort(allCards,new SortByPoints());
        ArrayList<MSCard> returnCards = new ArrayList<MSCard>();
        for(int i=0;i<n;i++){
            returnCards.add(allCards.get(i));
        }

        return returnCards;
    }

    class SortByPoints implements Comparator{
        public int compare(Object o1, Object o2){
            MSCard c1 = (MSCard) o1;
            MSCard c2 = (MSCard) o2;
            if(c1.getPoints()<c2.getPoints()){
                return 1;
            }
            return 0;
        }
    }
    /**
     * 由cardID获取卡片
     * @param CardID
     * @return
     */
    public MSCard getMSCardInfo(String CardID){
        return msCardMapper.selectCard(CardID).toMSCard();
    }

    /**
     * 获取该商户下的卡类型
     * @param merchantID
     * @return
     */
    public List<MSCardType> getTypes(String merchantID){
        List<MSCardType> types = merchantMapper.selectTypes(merchantID);
        return types;
    }

    /**
     * 添加会员卡
     * @param msCard
     * @return
     */
    public boolean addMSCard(MSCard msCard){
        // TODO:请求相关商家接口，做验证

        int flag = msCardMapper.insert(msCard);
        if(flag>0)
            return true;
        return false;
    }
}
