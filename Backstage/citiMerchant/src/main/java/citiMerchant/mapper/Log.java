package citiMerchant.mapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    static private String pathname = "./src/main/java/citiMerchant/mapper/log.txt";
    static private File log_file;
    static private BufferedWriter log_writer;

    static {
        log_file = new File(pathname);  //init log_file
    }

    /*
     * 用于日志
     */
    static public void log(String method, long time) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String current_time = df.format(new Date());// new Date()为获取当前系统时间
            log_writer = new BufferedWriter(new FileWriter(log_file));
            log_writer.write("\"" + method + "\" elapsed time: " +
                    (System.currentTimeMillis() - time) + "ms at" + current_time + "\n");
            log_writer.flush();
            log_writer.close();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("\nfail to log \"" + method + "\"\n");
        }
    }


}
