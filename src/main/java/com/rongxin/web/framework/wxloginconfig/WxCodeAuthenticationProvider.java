package com.rongxin.web.framework.wxloginconfig;



import com.rongxin.web.framework.web.service.impl.UserDetailsByAuthIdServiceImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class WxCodeAuthenticationProvider implements AuthenticationProvider
{
    private UserDetailsByAuthIdServiceImpl userDetailsByAuthIdService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        //将前端传递的authentication转换为WxCodeAuthenticationToken
        WxCodeAuthenticationToken wxCodeAuthenticationToken = (WxCodeAuthenticationToken) authentication;
        //通过authentication获取openid
        String openid = (String) wxCodeAuthenticationToken.getPrincipal();

        //获取数据库的信息,如果openid获得的用户为空，WxUserDetailsServiceImpl直接报错
        UserDetails userDetails = userDetailsByAuthIdService.loadUserByUsername(openid);

        // 走到这里鉴权成功，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        WxCodeAuthenticationToken authenticationResult = new WxCodeAuthenticationToken(userDetails,userDetails.getAuthorities());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        // 判断 authentication 是不是 WxCodeAuthenticationToken 的子类或子接口,是的话就使用该provider
        return WxCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsByAuthIdService;
    }

    public void setUserDetailsService(UserDetailsByAuthIdServiceImpl userDetailsService) {
        this.userDetailsByAuthIdService = userDetailsService;
    }
 }
