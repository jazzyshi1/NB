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
import com.jz.snake.important.utils.query.AtomQuery;
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
     * 态势分析
     *
     * @return
     *
     * @author jzshi
     */
    @At
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("beetl:pages/home/trend.html")
    @ThunderRequiresPermissions(InstallPermission.HOME_TREND)
    public Result trend(AtomQuery atomQuery) {
        return Result.success().addData("homePage", "aa").addData("homeP","bb").setTitle("态势感知");
    }
    /**
     * 实时监测
     *
     * @return
     *
     * @author jzshi
     */
    @At
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("beetl:pages/home/monitor.html")
    @ThunderRequiresPermissions(InstallPermission.HOME_MONITOR)
    public Result monitor(AtomQuery atomQuery) {
        return Result.success().addData("homePage", "aa").addData("homeP","bb").setTitle("实时监测");
    }
}
