package citi.dao;

import citi.vo.Merchant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantMapper {

    List<Merchant> select(int start, int length);

    Merchant select(String merchantID);
}
