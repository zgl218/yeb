package com.yy.yeb.utils;

import com.yy.yeb.exceptions.ParamsException;

/**
 * 非空校验
 * @author xingtong
 */
public class AssertUtil {
    public static void isTrue(Boolean flag,String msg){
        if (flag){
            throw new ParamsException(msg);
        }
    }
}