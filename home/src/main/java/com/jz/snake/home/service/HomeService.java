package com.jz.snake.home.service;

import com.jz.snake.home.bean.HomeData;
import com.jz.snake.important.shiro.service.BaseService;
import com.jz.snake.important.utils.query.AtomCndTool;
import com.jz.snake.important.utils.query.AtomQuery;

import java.util.concurrent.locks.Condition;

/**
 * Created by jazzyshi on 2018/6/5.
 */
public class HomeService extends BaseService<HomeData>{

    public HomeData homeTrend(AtomQuery atomQuery){
        return fetch(AtomCndTool.makeCnd(HomeData.class,atomQuery));
    }
}
