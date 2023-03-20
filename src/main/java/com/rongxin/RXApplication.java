package com.rongxin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import java.net.InetAddress;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

/**
 * 启动程序
 * 
 * @author rx
 */
@Slf4j
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class})
public class RXApplication
{
    public static void main(String[] args)
    {
        try {
            ConfigurableApplicationContext application =
                    SpringApplication.run(RXApplication.class, args);
            Environment env = application.getEnvironment();
            String ip = InetAddress.getLocalHost().getHostAddress();
            String port = env.getProperty("server.port");
            String path = getString(env.getProperty("server.servlet.context-path"));
            log.info("\n--------------------------------------------------------------------------\n\t"
                    + "管理系统 is running! Access URLs:\n\t"
                    + "Local: \t\t\thttp://localhost:" + port + path + "/doc.html\n\t"
                    + "External: \t\thttp://" + ip + ":" + port + path + "/doc.html\n\t"
                    + "Swagger文档: \thttp://" + ip + ":" + port + path + "/doc.html\n"
                    + "--------------------------------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getString(String s) {
        return (getString(s, ""));
    }

    public static String getString(String s, String defval) {
        if (isEmpty(s)) {
            return (defval);
        }
        return (s.trim());
    }
}
