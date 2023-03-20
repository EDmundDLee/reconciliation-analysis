package com.rongxin.web.util;


import com.rongxin.web.domain.UserCtx;

/**
 * 双token使用模式，也可根据实际情况进行使用
 * 当前请求可根据此方法，在 AuthenticationInterceptor 中对token内的值赋予CurrentUserUtil方便请求过程中获取对应的id等属性
 *
 * 使用方式，例如，在 AuthenticationInterceptor 方法中解析token后，CurrentUserUtil.setCurrentUser(UserCtx);
 *
 * 业务方法中即可通过CurrentUserUtil.getCurrentUser();即可获取token这次赋予的属性
 *
 */
public class CurrentUserUtil {
    private static ThreadLocal<UserCtx> threadLocal = new ThreadLocal<>();

    /**
     * 获取当前用户
     * @return
     */
    public static UserCtx getCurrentUser(){
        return threadLocal.get();
    }

    public static void setCurrentUser(UserCtx loginUserDto){
        threadLocal.set(loginUserDto);
    }

    public static String getLoginUserId() {
        return getCurrentUser().getId();
    }

    public static void remove(){
        threadLocal.remove();
    }
}
