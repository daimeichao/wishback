package com.jiading.common.exception;

/**
 * 业务逻辑异常码
 */
public enum VtronLogicExceptionCode {

    INTERNAL_SERVER_ERROR(999999, "服务器内部错误"),
    SUCCESS_CODE(2000, "操作成功"),
    FAILURE_CODE(5000, "操作失败"),
    EXCEl_IMPORT(4005, "excel表格导入数据格式有误"),
    ROLE_PERMISSIONS_BEYOND(4006, "角色权限越界"),
    NAME_ALREADY_EXISTS(4007, "名称已存在"),
    ROLE_MODE_ERROR(4008, "角色类型错误"),
    FILE_UPLOAD(4009, "文件上传失败"),
    CAMPUS_UNDEFINED(4010, "缺少参数校区id"),
    EXCEl_CHECK(4011, "excel表格数据校验冲突"),
    ORIGIN_PASSWORD_ERROR(4012, "原密码错误"),
    REQ_PARAM_MISSING(4013, "请求参数缺失"),
    REQ_PARAM_ILLEGAL(4014, "请求参数不合法"),
    NAME_UNCHANGEABLE(4015, "名称不可修改"),
    WALLAH_PHONE_EXISTS(4016, "负责人手机号已存在"),
    DIC_KEY_EXISTS(4017, "字典类型关键字已存在"),
    DIC_NAME_EXISTS(4018, "字典类型名称已存在"),
    DIC_VALUE_EXISTS(4019, "字典类型值已存在"),
    LOGIN_ID_EXISTS(4020, "登录账号已存在"),
    UN_DELETABLE_IN_USE(4021, "正在使用中，不可删除"),
    DIC_NAME_REPEATED(4022, "字典类型名称有重复"),
    DIC_VALUE_REPEATED(4023, "字典类型值有重复"),
    CLUE_STUDENT_EXIST(4024, "手机号码和学员名字相同的线索学员已存在"),
    SMS_CODE(4025, "短信发送失败"),
    SMS_CONFIRM(4026, "验证码错误"),
    PASSWORD_CONFIRM(4027, "密码不一致"),
    PHONE_NUMBER(4028, "手机号码已存在"),
    ACCOUNT_ERROR(4029, "账号不存在"),
    PASSWORD_ERROR(4030, "密码错误"),
    ACCOUNT_LOCK(4031, "账号已被锁定,请联系管理员"),
    CRON_EXPRESSION_INVALID(4032, "cron表达式无效"),
    SMS_FREQUENT_SEND(4033, "验证码不能频繁发送"),
    PASSWORD_NULL(4034, "密码为空"),
    TALKCLOUD_ERRCODE(4035, "拓课云错误"),
    SIGNUP_EXISTS(4036, "已报过名"),
    COLUMN_CONTENT_DATA_OVERLENGTH(4037, "栏目内容数据过长"),
    CREATCLASS_FEIBO_COURSE_ERROR(4038, "创建双师班级，课程包数据有误"),
    CREATCLASS_FEIBO_COURSE_EXPIRED(4039, "创建双师班级，课程包已过期"),
    CAMPUS_INSUFFICIENT_BALANCE(4040, "余额不足"),
    CLASS_NOT_EXIST(4041, "班级不存在"),
    COURSEPACKAGE_NOT_EXIST(4042, "课程包不存在"),
    COURSE_NOT_EXIST(4043, "课程不存在"),
    CAMPUS_NOT_EXIST(4044, "校区不存在"),
    ORDER_NOT_EXIST(4045, "订单不存在"),
    FEIBO_SAVESYLLABUS_EMPTY(4046, "约名师返回数据为空"),
    FEIBO_SAVESYLLABUS_FULL(4047, "班级可约名师课节已满"),
    FEIBO_SAVESYLLABUS_BEYOND(4048, "超出班级可约名师课节数"),
    FEIBO_COURSE_EXPIRED(4049, "课程包已过期"),
    FEIBO_COURSE_NOTALLOW_CANCLE(4050, "已约名师开课前2小时内不允许取消"),
    BOS_UPLOAD_FILE_ABNORMAL(4051, "BOS资源文件处理异常"),
    FEIBO_SAVESYLLABUS_EXPIRED(4052, "课节上课时间过了可约时间"),
    SYLLABUS_NOT_EXIST(4053, "课节不存在"),
    TALCLOUDROOM_NOT_EXIST(4054, "网络教室不存在,请输入正确的教室号"),
    FAILED_PARTIAL(4055, "操作部分失败"),
    ALREADY_BUY_MEALS(4056, "月份已经购买过同样的餐费"),
    NOT_ENOUGH_MEALSBALANCE(4057, "没有足够的餐费余额抵扣"),
    COURSELIBRARY_NOT_EXIST(4058, "课程库不存在"),
    COURSELIBRARYSYLLABUS_NOT_EXIST(4059, "课程库课节不存在"),
    FEIBOLESSON_NOT_EXIST(4060, "飞博课节不存在"),
    CLASS_COMPLETE(4061, "班级已结课"),
    MEALS_NOT_EXIST(4062, "餐项不存在"),
    STUDENT_MEALS_RECORD_NOT_EXIST(4063, "用餐记录不存在"),
    STUDENT_MEALS_RECORD_ISSETTLEMENT(4064, "用餐记录已结算，不可更改"),
    TALCLOUDROOM_ERROR(4065, "请输入正确的教室号!"),
    CAMPUS_OVERDUE(4066, "校区已过期"),
  	SUBSCRIPTION_MEMBER_COUNT(4067, "会员名额已满"),
    SUBSCRIPTION_TRIAL_COUNT(4068, "试听名额已满"),
	SUBSCRIPTION_LOCK(4069, "连续请假被锁"),
    DEVICE_AUTH_ERROR(4100, "设备授权失败"),
    USBKEY_AUTH_ERROR(4101, "设备ID已经被其他老师占用"),
    EVA_ORG_NO_CONFIG(4102, "机构没有配置闯关关卡"),
    YEAR_ALREADY_EXISTS(4103, "学年已存在"),
    MSG_NOT_EXISTS(4104, "消息不存在"),
    MSG_NOTALLOW_UPDATE(4105, "消息已发布，不可修改"),
    MSG_IS_PUBLISH(4106, "消息已发布"),
    MSG_HASNOT_RECEIVER(4107, "请选择接收方"),
    NO_RIGHT(4108, "没有该接口权限");
    VtronLogicExceptionCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private Integer code;

    private String msg;


}
