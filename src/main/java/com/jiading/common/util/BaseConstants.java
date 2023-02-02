package com.jiading.common.util;


public class BaseConstants {

    /**
     * 响应数据体
     */
    public static final String DATA = "data";

    /**
     * 时间开始标记
     */
    public static final String TIMESTART = "start";

    /**
     * 时间结束标记
     */
    public static final String TIMEEND = "end";

    /**
     * 当前页码
     */
    public static final String PAGE = "currPage";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "pageSize";
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "sidx";
    /**
     * 排序方式
     */
    public static final String ORDER = "orderBy";
    /**
     * group
     */
    public static final String GROUP = "groupBy";
    /**
     * 升序
     */
    public static final String ASC = "asc";
    /**
     * serialVersionUID
     */
    public static final String SERIAL = "serialVersionUID";
    /**
     * 校区id
     */
    public static final String CAMPUS_ID = "campusId";
    /**
     * 机构id
     */
    public static final String ORG_ID = "orgId";

    /**
     * 业务响应码
     */
    public static final String RESULT_CODE = "resultCode";

    /**
     * 业务异常信息
     */
    public static final String MESSAGE = "message";


    /**
     * 分页
     */
    public static final String PAGE_INFO = "pageInfo";

    /**
     * 验证码
     */
    public static final String CAPTCHA_CODE = "captchaCode";


    /**
     * 时间相等标记
     */
    public static final String EQUALS = "equals";


    public enum TimeMode {

        MINUTES(1), HOURS(2), DAY(3), WEEK(4);
        private final int value;

        TimeMode(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
