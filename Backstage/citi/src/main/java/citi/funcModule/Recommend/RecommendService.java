package citi.funcModule.Recommend;

import citi.persist.mapper.ItemMapper;
import citi.persist.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public boolean initPref(ArrayList<String> prefList){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String userID = (String)session.getAttribute("userID");
        boolean flag = true;
        for(int i=0;i<prefList.size();i++){
            /**
             * 接口一：PrefMapper-----addPref()
             * 参数一：userID,参数二：一个偏好（字符串形式）
             */
            if(prefMapper.addPref(userID,prefList.get(i))!=1){
                flag = false;
                break;
            }
        }
        return flag;
    }
}
