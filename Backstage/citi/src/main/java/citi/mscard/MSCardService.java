package citi.mscard;

import citi.mapper.MSCardMapper;
import citi.mapper.MerchantMapper;
import citi.vo.MSCard;
import citi.vo.Merchant;
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




    @Autowired
    private MSCardMapper msCardMapper;

    @Autowired
    private MerchantMapper merchantMapper;
    /**
     * 获取该用户下积分最多的n张卡信息
     * @param userId 用户id
     * @param n n张卡
     * @return 卡列表
     */
    public List<MSCard> getInfo(String userId, int n){
        List<MSCard> allCards =msCardMapper.select(userId);
        if(allCards==null)
            return null;
        Collections.sort(allCards,new SortByPoints());

        ArrayList<MSCard> returnCards = new ArrayList<>();
        for(int i=0;i<n&&i<allCards.size();i++){
            MSCard msCard=allCards.get(i);
            Merchant merchant=merchantMapper.selectByID(msCard.getMerchantId());
            msCard.setLogoURL(merchant.getMerchantLogoURL());
            msCard.setMerchantName(merchant.getName());
            msCard.setProportion(merchant.getProportion());
            returnCards.add(msCard);
        }

        return returnCards;
    }

    public int getCardNum(String userID){
        int num = msCardMapper.select(userID).size();
        return num;
    }
    class SortByPoints implements Comparator{
        public int compare(Object o1, Object o2){
            MSCard c1 = (MSCard) o1;
            MSCard c2 = (MSCard) o2;
            /*if(c1.getPoints()*c1.getProportion()<c2.getPoints()*c2.getProportion()){
                return 1;
            }*/
            return 0;
        }
    }
    /**
     * 由userID和merchantID获取卡片
     * @param userID, @param merchantID
     * @return 卡信息
     */
    public MSCard getMSCardInfo(String userID, String merchantID){
        return msCardMapper.getBy_userID_AND_merchantID(userID,merchantID);
    }


    /**
     * 添加会员卡
     * @param msCard
     * @return
     */
    public boolean addMSCard(MSCard msCard){
        // TODO:请求相关商家接口，做验证
        boolean isNoBlank = MSCard.checkAttribute(msCard);
        if(!isNoBlank)
            return false;
        int flag = msCardMapper.insert(msCard);
        if(flag>0)
            return true;
        return false;
    }

    public ArrayList<BriefCard> changeToBriefCards(List<MSCard> cards){
        ArrayList<BriefCard> briefCards = new ArrayList<BriefCard>();
        for(int i=0;i<cards.size();i++){
            MSCard card = cards.get(i);
            BriefCard briefCard = new BriefCard(card.getMerchantId(),card.getLogoURL(),card.getMerchantName(),card.getPoints(),card.getProportion());
            briefCards.add(briefCard);
        }
        return briefCards;
    }
}
