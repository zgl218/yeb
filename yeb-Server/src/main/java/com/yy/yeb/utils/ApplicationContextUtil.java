package com.yy.yeb.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全局上下文工具类配置
 *
 * @author xingtong
 */
@Slf4j
public class ApplicationContextUtil {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext ac) throws BeansException{
        applicationContext = ac;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }


    public static <T>List<T> getBeanByType(Class<T> clazz){
        List<T> list = new ArrayList<T>();

        /*获取接口的所有实例名*/
        String[] beanNames = applicationContext.getBeanNamesForType(clazz);

        log.debug("getBeanByType beanNames : " + beanNames == null ? "" : Arrays.toString(beanNames));

        if (beanNames == null || beanNames.length == 0){
            return list;
        }

        T t = null;
        for (String beanName : beanNames){
            t = (T)applicationContext.getBean(beanName);
            list.add(t);
        }

        return list;
    }
}
