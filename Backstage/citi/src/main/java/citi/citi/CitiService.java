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
import com.google.gson.*;
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
        refreshAccessToken = Authorize.getTokenByRF(refreshAccessToken);
        RefreshToken refreshToken = new RefreshToken(userID,refreshAccessToken,String.valueOf(System.currentTimeMillis()));
        tokenMapper.update(refreshToken);
    }

    public String getPhoneNum(String accessToken){
        String phoneNum = null;
        String phoneInformation = Customer.getCustomerPhone(accessToken);
        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(phoneInformation).getAsJsonObject();
        //再转JsonArray 加上数据头
        JsonArray jsonArray = jsonObject.getAsJsonArray("phones");

        ArrayList<Phone> phones = new ArrayList<>();

        //循环遍历
        for (JsonElement phone : jsonArray) {
            //通过反射 得到UserBean.class
            Phone phoneVO = gson.fromJson(phone, new TypeToken<Phone>() {}.getType());
            phones.add(phoneVO);
        }
        phoneNum = String.valueOf(phones.get(0).getPhoneNum());
        return phoneNum;
    }

    public String getCardNum(String accessToken){
        String creditCardNum=null;
        String cardsInformation = Card.getCardsInformation(accessToken);
        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(cardsInformation).getAsJsonObject();
        //再转JsonArray 加上数据头
        JsonArray jsonArray = jsonObject.getAsJsonArray("cardDetails");

        ArrayList<CardDetail> details = new ArrayList<>();

        //循环遍历
        for (JsonElement phone : jsonArray) {
            //通过反射 得到UserBean.class
            CardDetail cardDetail = gson.fromJson(phone, new TypeToken<CardDetail>() {}.getType());
            details.add(cardDetail);
        }
        creditCardNum = String.valueOf(details.get(0).getDisplayCardNumber());
        return creditCardNum;
    }



    public String getCardID(String accessToken){
        String creditCardNum=null;
        String cardsInformation = Card.getCardsInformation(accessToken);
        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(cardsInformation).getAsJsonObject();
        //再转JsonArray 加上数据头
        JsonArray jsonArray = jsonObject.getAsJsonArray("cardDetails");

        ArrayList<CardDetail> details = new ArrayList<>();

        //循环遍历
        for (JsonElement detail : jsonArray) {
            //通过反射 得到UserBean.class
            CardDetail cardDetail = gson.fromJson(detail, new TypeToken<CardDetail>() {}.getType());
            details.add(cardDetail);
        }
        creditCardNum = String.valueOf(details.get(0).getCardId());
        return creditCardNum;
    }

}
