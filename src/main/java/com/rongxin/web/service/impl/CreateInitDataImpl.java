package com.rongxin.web.service.impl;


import com.rongxin.web.service.ICreateInitData;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 参数配置 服务层实现
 * 
 * @author rx
 */
@Service
public class CreateInitDataImpl implements ICreateInitData
{

    private static final Logger log = LoggerFactory.getLogger(CreateInitDataImpl.class);

    @Value("${spring.datasource.druid.master.url}")
    private String dbUrl;

    @Value("${spring.datasource.driverClassName}")
    private String className;

    @Value("${spring.datasource.druid.master.username}")
    private String dbUsername;

    @Value("${spring.datasource.druid.master.password}")
    private String dbPassword;

    @Value("${spring.initdatabase}")
    private boolean createDataBase;

    @Value("${spring.profiles.active}")
    private String proenv;

    /**
     * 项目启动时，初始化数据库
     */
    @PostConstruct
    public void init()
    {
        loadingSqlForCreate();
    }

    /**
     * 加载参数缓存数据
     */
    public  void loadingSqlForCreate()
    {
        if( proenv.equals("local") && createDataBase ){
            try {
                Class.forName(className);
                Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                ScriptRunner runner = new ScriptRunner(conn);
                runner.setAutoCommit(true);

                String fileName = String.format("src/main/resources/sql/%s.sql", "init");
                File file = new File(fileName);
                log.info(String.format("【%s】准备初始化数据库脚本", "init"));

                try {
                    if (file.getName().endsWith(".sql")) {
                        log.info(String.format("【%s】初始化数据库开始,请等待几分钟。。。预计3分30秒后完成", "init"));

                        runner.setFullLineDelimiter(false);
                        runner.setDelimiter(";");//语句结束符号设置
                        runner.setLogWriter(null);//日志数据输出，这样就不会输出过程
                        runner.setSendFullScript(false);
                        runner.setAutoCommit(true);
                        runner.setStopOnError(true);
                        runner.runScript(new InputStreamReader(new FileInputStream(fileName), "utf8"));
                        log.info(String.format("【%s】初始化数据库完成", "init"));
                    }else{
                        log.info(String.format("【%s】准备初始化数据库脚本失败", "init"));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }
        }

    }

}
