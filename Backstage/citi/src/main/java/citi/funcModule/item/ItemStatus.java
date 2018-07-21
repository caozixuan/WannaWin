package citi.funcModule.item;

import citi.support.status.Status;

/**
 * Created by zhong on 2018/7/18 11:27
 */
public class ItemStatus {
    public final static Status<Boolean,String> SUCCESS;
    public final static Status<Boolean,String> INVALID;
    public final static Status<Boolean,String> EMPTY;
    public final static Status<Boolean,String> OTHER;
    static {
        SUCCESS=new Status<>(true,"true");
        INVALID=new Status<>(false,"不存在此商品");
        EMPTY=new Status<>(false,"库存不足");
        OTHER=new Status<>(false,"未知错误");
    }
}
