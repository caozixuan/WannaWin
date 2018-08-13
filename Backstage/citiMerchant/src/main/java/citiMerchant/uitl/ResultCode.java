package citiMerchant.uitl;

/**
 * Create Time: 2018年04月25日 13:25
 *
 * @author fin
 */
public final class ResultCode {



    private ResultCode() {
    }

    public static final int SUCCESS = 200;
    public static final int NORMAL_ERROR = 498;


    /**
     * 权限错误 100000 - 101000
     */
    public static final int PARAMETER_ERROR = 100001;
    public static final int NOMAL_EXCEPTION = 100002;
    public static final int NO_USER_AUTH = 100003;
    public static final int UNKOWN_USER = 100005;


    /**
     * 后台错误 150000 - 200000
     */

    public static final int INSERT_COURSE_FAIL = 150000;
    public static final int UPDATE_COURSE_FAIL = 150001;
    public static final int INSERT_COURSE_PERIODS_FAIL = 150002;
    public static final int UPDATE_COURSE_PERIODS_FAIL = 150003;
    public static final int INSERT_PROGRESS_FAIL = 150004;
    public static final int INSERT_PROGRESS_STUDY_FAIL = 150005;
    public static final int INSERT_QUESTION_FAIL = 150006;
    public static final int INSERT_COUPON_FAIL = 150007;
    public static final int DELETE_COUPON_FAIL = 150008;

    /**
     * *****************************
     * 公众号错误区域 200000 - 300000**
     * ******************************
     */

    /**
     * 公众登录错误 200000 - 200100
     */
    public static final int NO_USER_INFO = 200001;          // 未找到用户信息
    public static final int USER_UNABLED = 200002;          // 用户冻结不可用
    public static final int REGIST_ERROR = 200003;          // 注册用户失败
    public static final int LOGIN_FAIL = 200004;            // 登录失败
    public static final int UPDATE_USER_FAIL = 200005;      // 更新用户信息失败

    /**
     * 用户业务错误 200100 - 200500
     */
    public static final int DUMPLICATED_CHOICE_REQUEST = 200100;   // 重复提交选课请求
    public static final int CHOICE_ERROR = 200101;                  // 选课失败
    public static final int PERIODS_NOT_START = 200102;             // 课期尚未开始
    public static final int END_PERIODS = 200103;                   // 课期已结束
    public static final int PROGRESS_NOT_FOUND = 200104;            // 今日课程未找到
    public static final int STUDY_NOT_FOUND = 200105;               // 用户没有找到学习进度



    /**
     * 小程序api错误区域 600000 - 700000
     */

    /**
     * 用户数据查询错误 600000 - 600100
     */
    public static final Integer USER_LIST_NOT_FOUND = 600000;
    public static final int NO_CHOOSE_MESSAGE= 600001;          // 没有选课信息

    /**
     * 课程数据查询错误 602000-602100
     */
    public static final int COURSE_NOT_FOUND = 602000;

    /**
     * 课程期数据查询错误 601000-601100
     */
    public static final int COURSE_PERIODS_NOT_FOUND = 601000;
    public static final int PERIOD_NOT_FOUND = 601001;

    /**
     * 题库查询错误 601500-601600
     */
    public static final int QUESTION_NOT_FOUND = 601500;

    public static final int NO_LEVEL_ID = 600010;               //没有levelId
    public static final int NO_VALID_LEVEL = 600011;            //没有找到对应的level



}
