package citi.dao;

import citi.vo.MSCardType;
import citi.vo.Merchant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantMapper {

    List<Merchant> select(int start, int length);

    Merchant select(String merchantID);

    // 曹子轩：返回商户对应的卡类型，这个属于数据库的操作吧？
    // 返回商家所有有的卡的类型
    List<MSCardType> selectTypes(String merchantID);
}
