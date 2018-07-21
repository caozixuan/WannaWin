package citi.funcModule.merchant;

import citi.persist.mapper.MerchantMapper;
import citi.vo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 接口设计：刘钟博
 * 代码填充：彭璇
 */
@Service
public class MerchantSerivce {

    @Autowired
    private MerchantMapper merchantMapper;

    /**
     * 获取商家列表
     * @param start 开始节点
     * @param length 长度
     * @return 商家列表
     */
    public List<Merchant> getMerchants(int start,int length){
        List<Merchant> merchants = merchantMapper.select(start,length);
        return merchants;
    }

    /**
     * 通过id获取商家
     * @param merchantID 商家ID
     * @return 商家
     */
    public Merchant getMerchant(String merchantID){
        return merchantMapper.selectByID(merchantID);
    }

    public int getNum(){
        return merchantMapper.getMerchantAmount();
    }
}
