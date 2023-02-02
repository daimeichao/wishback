package com.jiading.common.util;

import org.apache.commons.lang.StringUtils;

public class PhoneUtil {
    static final String MOBILE_RULE = "^1[3-9]\\d{9}$";

    public static boolean isLegalMobileNumber(String phone) {
        if (StringUtils.isEmpty(phone) || phone.length() != 11) {
            return false;
        }
        return phone.matches(MOBILE_RULE);
    }
}
