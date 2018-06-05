package com.jz.snake.home.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.jz.snake.home.service.HomeService;
import com.jz.snake.important.shiro.InstallPermission;
import com.jz.snake.important.shiro.Result;
import com.jz.snake.important.shiro.bean.Permission;
import com.jz.snake.important.shiro.ext.anno.ThunderRequiresPermissions;
import com.jz.snake.important.shiro.modules.AbstractBaseModule;
import com.jz.snake.important.shiro.service.Pager;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

/**
 * Created by jazzyshi on 2018/6/5.
 */
@At("home")
public class HomeModule extends AbstractBaseModule {

    @Inject
    HomeService homeService;
    /**
     * 首页
     *
     * @return
     *
     * @author jzshi
     */
    @At
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("beetl:pages/permission/list.html")
    public Result homeTrend() {
        return Result.success().addData("homePage", homeService.getHomePage()).setTitle("态势感知");
    }
}
