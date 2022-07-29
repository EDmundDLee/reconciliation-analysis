package com.rongxin;

 import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author rx
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RXApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RXApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____      ____       \n" +
                " |  _ _   \\      \\   \\   /   /    \n" +
                " | ( ' )  |       \\  _. /   /      \n" +
                " |(_ o _) /         _( ' ) _ .'    \n" +
                " | (_,_).'         _(_ o _)' '     \n" +
                " |  |\\ \\             _(_,_)' '    \n" +
                " |  | \\ \\        /  /'    \\  \\  \n" +
                " |  |  \\ \\      /  /'      \\  \\ \n" +
                " ''-'   `'-'      ''-'         `'-'      ");
    }
}
