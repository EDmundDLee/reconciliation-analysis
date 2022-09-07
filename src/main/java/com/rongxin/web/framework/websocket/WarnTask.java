package com.rongxin.web.framework.websocket;

import com.alibaba.fastjson.JSONObject;
import com.rongxin.framework.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 测试wensocket测试
 *
 */
//@Component("warntask")
public class WarnTask
{
  @Autowired
  private WebSocketServer webSocketServer;
  int i = 0;

  public void warnTask() throws IOException {
    System.out.println("i==   "+i);
    String message = JSONObject.toJSONString("ttt"+i);
    //前端发送消息
    webSocketServer.sendInfo(message, "admin");
    i++;
  }

}