package com.jz.snake.common.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by jazzyshi on 2018/6/6.
 */
@WebService
public interface WebServiceInterface {
    @WebMethod
    String sayHello(String name);
}
