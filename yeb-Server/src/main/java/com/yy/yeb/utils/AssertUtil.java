package com.yy.yeb.utils;

import com.yy.yeb.expecitions.ParamsException;

public class AssertUtil {

    public  static void isTrue(Boolean flag,String msg){
        if(flag){
            throw  new ParamsException(msg);
        }
    }

}
