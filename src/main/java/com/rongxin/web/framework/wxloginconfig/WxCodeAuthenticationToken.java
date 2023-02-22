package com.rongxin.web.framework.wxloginconfig;



import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 将openid封装成authentication
 */

public class WxCodeAuthenticationToken extends AbstractAuthenticationToken
{
    /**
     * 在 UsernamePasswordAuthenticationToken 中该字段代表登录的用户名，
     * 在这里就代表登录的微信openid
     */
    private final Object principal;

    /**
     * 构建一个没有鉴权的 CodeAuthenticationToken
     */
    public WxCodeAuthenticationToken(Object principal)
    {
        super(null);
        this.principal = principal;
        setAuthenticated(false);
    }

    /**
     * 构建拥有鉴权的 SmsCodeAuthenticationToken，小程序中不需要
     */
    public WxCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        // must use super, as we override
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException
    {
        if (isAuthenticated)
        {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

}
