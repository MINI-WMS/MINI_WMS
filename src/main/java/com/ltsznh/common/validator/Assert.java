package com.ltsznh.common.validator;

import com.ltsznh.common.exception.FamilyException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new FamilyException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new FamilyException(message);
        }
    }
}
