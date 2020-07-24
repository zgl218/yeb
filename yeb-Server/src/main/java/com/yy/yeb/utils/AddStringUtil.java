package com.yy.yeb.utils;

public class AddStringUtil {
    public static String addOne(String oneStr){
        //根据不是数字的字符拆分字符串
        String[] strs = oneStr.split("[^0-9]");
        //取出最后一组数字
        String numStr = strs[strs.length - 1];
        //如果最后一组没有数字(也就是不以数字结尾)，抛NumberFormatException异常
        if (numStr != null && numStr.length() > 0) {
            //取出字符串的长度
            int n = numStr.length();
            //将该数字加一
            int num = Integer.parseInt(numStr) + 1;
            String added = String.valueOf(num);
            n = Math.min(n, added.length());
            //拼接字符串
            return oneStr.subSequence(0, oneStr.length() - n) + added;
        } else {
            throw new NumberFormatException();


        }
    }
}