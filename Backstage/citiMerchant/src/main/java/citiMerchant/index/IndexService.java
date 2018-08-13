package citiMerchant.index;

import citiMerchant.mapper.MerchantMapper;
import citiMerchant.vo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    @Autowired
    private MerchantMapper merchantMapper;

    public Merchant getMerchant(String merchantID){
        return merchantMapper.selectByID(merchantID);
    }
}
