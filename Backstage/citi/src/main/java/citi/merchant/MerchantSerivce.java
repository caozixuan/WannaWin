package citi.merchant;

import citi.mybatismapper.MerchantMapper;
import citi.vo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantSerivce {

    @Autowired
    private MerchantMapper merchantMapper;

    public List<Merchant> getMerchants(int start,int length){

        return null;
    }

    public Merchant getMerchant(String merchantID){

        return null;
    }

}
