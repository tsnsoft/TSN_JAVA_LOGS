package java_logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import org.apache.log4j.Logger;


public class MY_JAVA_CLASS {

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tF %1$tT [%4$-7s] %5$s %n");
    }
    
//    static {
//        InputStream stream = MY_JAVA_CLASS.class.getClassLoader().
//                getResourceAsStream("logging.properties");
//        try {
//            LogManager.getLogManager().readConfiguration(stream);
//            java.util.logging.Logger log = Logger.getLogger(MY_JAVA_CLASS.class.getName());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
    static java.util.logging.Logger log = java.util.logging.Logger.getLogger(MY_JAVA_CLASS.class.getName());

    static Logger log4j = Logger.getLogger(MY_JAVA_CLASS.class);

    public static void main(String[] args) throws IOException {
        // Технология логирования log4j
        log4j.info("Start log4j");
        log4j.info("Hi Logger info!");
        log4j.warn("Warrning!");
        log4j.error("Error!");
        log4j.fatal("Fatal error!");
        log4j.info("End log4j");
        org.apache.log4j.LogManager.shutdown();

        // Технология логирования java.util.logging
        FileHandler fileHandler = new FileHandler("logging.log", true);
        fileHandler.setFormatter(new SimpleFormatter());
        log.addHandler(fileHandler);

        log.setLevel(Level.ALL);
        log.info("Start");
        log.log(Level.INFO, "Запись лога с уровнем INFO (информационная)");
        log.log(Level.WARNING, "Запись лога с уровнем WARNING (Предупреждение)");
        log.log(Level.SEVERE, "Запись лога с уровнем SEVERE (серъёзная ошибка)");
        log.info("Some message");

        try {
            new Exception("ERR!");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "Exception: ", ex);
        }

        log.log(Level.WARNING, "Some!");
        log.info("End");
    }

}
