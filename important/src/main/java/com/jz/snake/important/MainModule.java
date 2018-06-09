package com.jz.snake.important;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.jz.snake.important.shiro.DES;
import com.jz.snake.important.shiro.Result;
import com.jz.snake.important.shiro.ShiroDemo;
import com.jz.snake.important.shiro.bean.User;
import org.beetl.ext.nutz.BeetlViewMaker;
import org.nutz.integration.shiro.ShiroSessionProvider;
import org.nutz.integration.shiro.annotation.NutzRequiresPermissions;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.ChainBy;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SessionBy;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.annotation.Views;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * @author jzshi
 * @email jz_shi@163.com
 */
@Ok("json")
@Fail("http:500")
@Modules(scanPackage = true, packages = "com.jz.snake")
@IocBy(type = ComboIocProvider.class, args = {
        "*js", "ioc/",
        "*anno", "com.jz.snake",
        "*tx",
        "*jedis", // 加载jedis
        "*async"})
@SetupBy(MainSetup.class) // 启动
@Views({BeetlViewMaker.class}) // beetl
@SessionBy(ShiroSessionProvider.class)
@Encoding(input = "UTF-8", output = "UTF-8")
@ChainBy(type = MainChainMaker.class, args = {}) // 自定义shiro注解处理器
public class MainModule {

    @At("/")
    @Ok("re:beetl:pages/login.html")
    public String home(HttpServletRequest request, @Attr(ShiroDemo.SessionKeys.USER_KEY) User user) {
        if (user != null) {
            return ">>:/user/list";
        }
        String cookie = _getCookie("jzsnake");
        NutMap data = NutMap.NEW();
        if (!Strings.isBlank(cookie)) {
            data = Lang.map(DES.decrypt(cookie));
        }
        request.setAttribute("loginInfo", data);
        return null;
    }

    /**
     * 获取缓存数据
     * @param name
     * @return
     */
    protected String _getCookie(String name) {
        Cookie[] cookies = Mvcs.getActionContext().getRequest().getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Strings.equals(cookie.getName(), name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
