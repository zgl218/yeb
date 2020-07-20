package com.yy.yeb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author YangYu
 * @Date 2020/07/17 16:19
 */
@SpringBootApplication
@MapperScan("com.yy.yeb.mapper")
public class YebApplication {
    public static void main(String[] args) {
        SpringApplication.run(YebApplication.class,args);
    }
}