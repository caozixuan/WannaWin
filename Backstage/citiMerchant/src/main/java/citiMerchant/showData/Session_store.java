package citiMerchant.showData;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class Session_store {

    private static Map<String, HttpSession> sessions = new HashMap<>();

    public static HttpSession getSession(String mercahntID) {
        return sessions.get(mercahntID);
    }

    public static void addSession(String merchantID, HttpSession session) {
        sessions.put(merchantID, session);
    }

}
