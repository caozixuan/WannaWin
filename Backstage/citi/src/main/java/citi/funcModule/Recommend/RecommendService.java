package citi.funcModule.Recommend;

import citi.persist.mapper.ItemMapper;
import citi.persist.mapper.UserMapper;
import citi.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class RecommendService {
    //需要访问到用户的喜好、用户的访问记录、商品的基本信息
    @Autowired
    private PrefMapper prefMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private ItemMapper itemMapper;

    /**
     * 初始化用户的偏好列表
     * @param prefList
     * @return true/false
     */
    public boolean initPref(String userID,ArrayList<String> prefList){
        boolean flag = true;
        for(int i=0;i<prefList.size();i++){
            /**
             * 接口一：PrefMapper-----addPref()
             * 参数一：userID,参数二：一个偏好（字符串形式）
             * 返回受影响的行数
             */
            if(prefMapper.addPref(userID,prefList.get(i))!=1){
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 添加用户浏览记录
     * @param userID
     * @param itemID
     * @param time
     * @return true/false
     */
    public boolean addRecord(String userID, String itemID, Timestamp time){
        /**
         * 接口一：RecordMapper-----addRecord()
         * 参数一：userID,参数二：itemID,参数三：该浏览记录的时间戳
         * 返回受影响的行数
         */
      if(recordMapper.addRecord(userID,itemID,time)!=1)
          return false;
      return true;
    }


    /**
     * 返回用户的推荐商品列表
     * @param userID
     * @return ArrayList<Item>
     */
    public ArrayList<Item> getRecommendedItems(String userID){

    }
}
