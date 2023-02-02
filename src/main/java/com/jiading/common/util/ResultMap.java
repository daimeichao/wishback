package com.jiading.common.util;

import com.jiading.common.exception.VtronLogicExceptionCode;
import com.jiading.common.pojo.PageInfo;
import org.apache.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回数据
 */
public class ResultMap extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 成功，无详细信息
     * 成功默认带data，result="success"
     */
    public ResultMap() {
        put("errorCode", 0);
        put("status", "success");
        put("result", "操作成功");
    }

    /**
     * 失败，未知异常
     *
     * @return
     */
    public static ResultMap error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    /**
     * @param msg
     * @return 含错误信息的失败，错误信息传入
     */
    public static ResultMap error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    /**
     * @param code 错误码
     * @param msg  错误信息
     * @return
     */
    public static ResultMap error(int code, String msg) {
        ResultMap r = new ResultMap();
        r.put("errorCode", code);
        r.put("status", "false");
        r.put("result", msg);
        return r;
    }

    /**
     * @param msg 正确，含详细信息
     * @return
     */
    public static ResultMap ok(String msg) {
        ResultMap r = new ResultMap();
        r.put("result", msg);
        return r;
    }

    /**
     * @param map 正确，传入map，会以json格式返回
     * @return
     */
    public static ResultMap ok(Map<String, Object> map) {
        ResultMap r = new ResultMap();
        r.putAll(map);
        return r;
    }

    /**
     * 在data 赋值返回相应对象
     *
     * @param
     * @return
     */
    public ResultMap setData(Object o) {
        if (o instanceof List) {
            this.put("list", o);
        } else {
            setDataOther(o);
        }
        return this;
    }

    /**
     * 在data 赋值返回相应对象，同时保留业务逻辑码与信息
     * @param o
     * @return
     */
    public ResultMap setDataOther(Object o) {
        if (o instanceof Map) {
            ((Map) o).put(Constant.RESULT_CODE, VtronLogicExceptionCode.SUCCESS_CODE.getCode());
            ((Map) o).put(Constant.MESSAGE, VtronLogicExceptionCode.SUCCESS_CODE.getMsg());
            super.put("data", o);

        } else {
            Field[] fields = o.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    this.put(field.getName(), field.get(o));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    /**
     * 在data 赋值返回相应对象
     *
     * @param
     * @return
     */
    public ResultMap setResponseData(Object o) {
        if (o instanceof List) {

            this.put("list", o);

        }
        else if (o instanceof PageInfo) {

            this.put("pageInfo", o);

        }
        else {

            this.put("info", o);
        }
        return this;
    }


    /**
     * 默认
     *
     * @return
     */
    public static ResultMap ok() {
        ResultMap r = new ResultMap();
        r.put(Constant.RESULT_CODE, VtronLogicExceptionCode.SUCCESS_CODE.getCode());
        r.put(Constant.MESSAGE, VtronLogicExceptionCode.SUCCESS_CODE.getMsg());
        return r;
    }

    /**
     * 默认
     *
     * @return
     */
    public static ResultMap ok(VtronLogicExceptionCode vtronLogicExceptionCode) {
        ResultMap r = new ResultMap();
        r.put(Constant.RESULT_CODE, vtronLogicExceptionCode.getCode());
        r.put(Constant.MESSAGE, vtronLogicExceptionCode.getMsg());
        return r;
    }

    /**
     * 有参数返回时，需要在所有数据外包装一层data节点，方便前端统一处理
     *
     * @param key
     * @param value
     * @return
     */
    public ResultMap put(String key, Object value) {

        if ("status".equalsIgnoreCase(key) || "errorCode".equalsIgnoreCase(key)) {

            super.put(key, value);

        } else {

            HashMap<String, Object> dataObject = new HashMap<>();
            if (super.containsKey(Constant.DATA)) {
                dataObject = (HashMap<String, Object>) super.get("data");
            }

            if ("page".equalsIgnoreCase(key)) {

                handlePage((PageInfo) value, dataObject);

            } else if (Constant.PAGE_INFO.equals(key)) {

                handlePageInfo((PageInfo) value, dataObject);

            } else {

                dataObject.put(key, value);
            }

            super.put(Constant.DATA, dataObject);

        }

        return this;
    }

    /**
     * 分页特殊处理
     */
    public void handlePageInfo(PageInfo pageInfo, HashMap<String, Object> dataMap) {
        if (pageInfo == null) {
            return;
        }
        Field[] fileds = pageInfo.getClass().getDeclaredFields();
        for (Field field : fileds) {
            field.setAccessible(true);
            try {
                dataMap.put(field.getName(), field.get(pageInfo));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对page进行特殊处理
     */
    public void handlePage(PageInfo page, HashMap<String, Object> obj) {
        List<?> pageObjectList = page.getList();
        Integer totalCount = page.getTotalCount();
        obj.put("list", pageObjectList);
        obj.put("totalCount", totalCount);
        obj.put("pageSize", page.getPageSize());
        obj.put("totalPage", page.getTotalPage());
        obj.put("currPage", page.getCurrPage());
        if(page.getObject()!=null) {
            obj.put("object", page.getObject());
        }
    }
}
