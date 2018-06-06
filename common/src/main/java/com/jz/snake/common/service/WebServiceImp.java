package com.jz.snake.common.service;

import javax.jws.WebService;
import java.lang.annotation.Annotation;

/**
 * Created by jazzyshi on 2018/6/6.
 */
@WebService
public class WebServiceImp implements WebServiceInterface {
    @Override
    public String sayHello(String name) {
        System.out.println("成功了哦。。。");
        return "你好："+name;
    }
}
