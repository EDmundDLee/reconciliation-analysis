package com.rongxin.web.framework.wxloginconfig;

import com.rongxin.web.framework.web.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

import javax.annotation.Resource;

@Configuration
public class WxCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Resource
    private WxCodeAuthenticationProvider wxCodeAuthenticationProvider;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        wxCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
        http.authenticationProvider(wxCodeAuthenticationProvider);
    }
}
