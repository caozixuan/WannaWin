package citi.citi;

import citi.API.Authorize;
import citi.API.Card;
import citi.API.Customer;
import citi.mybatismapper.CitiMapper;
import citi.mybatismapper.TokenMapper;
import citi.vo.CardDetail;
import citi.vo.CitiCard;
import citi.vo.Phone;
import citi.vo.RefreshToken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CitiService {

    /**
     * 绑定操作
     * @param citiCard
     * @return 判断绑定是否成功
     */
    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private CitiMapper citiMapper;

    @Autowired
    private Gson gson;

    public boolean binding(CitiCard citiCard){
        int flag = citiMapper.insert(citiCard);
        if(flag>0)
            return true;
        return false;
    }

    public void saveRefreshToken(String accessInformation, String userID){
        String refreshAccessToken = Authorize.getRefreshToken(accessInformation);
        RefreshToken refreshToken = new RefreshToken(userID,refreshAccessToken,String.valueOf(System.currentTimeMillis()));
        tokenMapper.insert(refreshToken);
    }

    public String getPhoneNum(String accessToken){
        String phoneNum = null;
        String phoneInformation = Customer.getCustomerPhone(accessToken);
        ArrayList<Phone> phones = gson.fromJson(phoneInformation, new TypeToken<ArrayList<Phone>>() {
        }.getType());
        if(phones.size()==1){
            phoneNum = String.valueOf(phones.get(0).getPhoneNum());
        }
        return phoneNum;
    }

    public String getCardNum(String accessToken){
        String creditCardNum=null;
        String cardsInformation = Card.getCardsInformation(accessToken);
        ArrayList<CardDetail> cardDetails = gson.fromJson(cardsInformation, new TypeToken<ArrayList<CardDetail>>() {
        }.getType());
        if(cardDetails.size()==1){
            creditCardNum = cardDetails.get(0).getDisplayCardNumber();
        }
        return creditCardNum;
    }

    public String getCardID(String accessToken){
        String creditCardID=null;
        String cardsInformation = Card.getCardsInformation(accessToken);
        ArrayList<CardDetail> cardDetails = gson.fromJson(cardsInformation, new TypeToken<ArrayList<CardDetail>>() {
        }.getType());
        if(cardDetails.size()==1){
            creditCardID = cardDetails.get(0).getCardId();
        }
        return creditCardID;
    }

}
