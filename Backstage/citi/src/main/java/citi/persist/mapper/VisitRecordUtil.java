package citi.persist.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;


@Component
public class VisitRecordUtil {

    public static VisitRecordMapper visitRecordMapper;

    //<userID, <itemID, visit_times>>
    private static ConcurrentHashMap<String, HashMap<String, Integer>> users_visit_info = new ConcurrentHashMap<>();

    //<userID> //用户级缓存，暂时用 LRU.
    private static ConcurrentLinkedQueue<String> LRUCache = new ConcurrentLinkedQueue<>();
    private static final Integer capacity = 50;

    @Autowired
    public void setVisitRecordMapper(VisitRecordMapper visitRecordMapper) {
        VisitRecordUtil.visitRecordMapper = visitRecordMapper;
    }


    public static Integer getVisitTimesBy_userID_AND_itemID(final String userID, final String itemID) {

        LRUCache.remove(userID);
        LRUCache.add(userID);
        if (LRUCache.size() > capacity) {
            users_visit_info.remove(LRUCache.peek());
            LRUCache.remove(LRUCache.peek());
        }

        HashMap<String, Integer> visits_times = users_visit_info.get(userID);
        if (null == visits_times) {
            List<String> items = visitRecordMapper.getItemIDByUserID(userID, "010101010101");
            //<item, times>
            HashMap<String, Integer> visit_times = new HashMap<>();
            for (String item : items) {
                Integer times = visit_times.get(item);
                if (times == null) visit_times.put(item, 1);
                else visit_times.put(item, times + 1);
            }
            users_visit_info.put(userID, visit_times);
            return visit_times.get(itemID);
        } else {
            return visits_times.get(itemID);
        }
    }


}