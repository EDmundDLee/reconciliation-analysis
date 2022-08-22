package com.rongxin.demo.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author wyq
 * @version 1.0
 * @date 2020/6/16 13:32
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

  private static ApplicationContext context = null;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.context = applicationContext;
  }

  // 传入线程中
  public static <T> T getBean(String beanName) {
    return (T) context.getBean(beanName);
  }

  // 传入线程中
  public static <T> T getBeanByCLass(Class<T> requiredType) {
    return (T) context.getBean(requiredType);
  }

  // 国际化使用
  public static String getMessage(String key) {
    return context.getMessage(key, null, Locale.getDefault());
  }

  /// 获取当前环境
  public String getActiveProfile() {
    return context.getEnvironment().getActiveProfiles()[0];
  }

}
