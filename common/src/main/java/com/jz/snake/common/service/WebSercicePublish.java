package com.jz.snake.common.service;

import javax.xml.ws.Endpoint;

/**
 * Created by jazzyshi on 2018/6/6.
 */
public class WebSercicePublish {
    public static void main(String[] args){
        String address = "http://localhost:8888/HbaseService";
        Endpoint.publish(address,new WebServiceImp());
        System.out.println("发布WebService成功");
    }
}
