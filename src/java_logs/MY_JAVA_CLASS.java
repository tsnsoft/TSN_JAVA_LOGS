package java_logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class MY_JAVA_CLASS {

    static {
        // Установка формата вывода для java.util.logging.SimpleFormatter
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tF %1$tT [%4$-7s] %3$s - %5$s %n");

    }

    // Объявление логировцика типа java.util.logging.Logger 
    static java.util.logging.Logger log = java.util.logging.Logger.getLogger(MY_JAVA_CLASS.class.getName());

    // Объявление логировцика типа org.apache.log4j.Logger
    static org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(MY_JAVA_CLASS.class);

    public static void main(String[] args) throws IOException {
        // Инициализация логировцика типа java.util.logging.Logger 
        // с файлом не более 100 КБ и не более 3 файлов-логов с дозаписью логов
        Handler fileHandler = new FileHandler("logging.log", 100 *  1024, 3, true);
        fileHandler.setFormatter(new SimpleFormatter());
        log.addHandler(fileHandler);

        // Технология логирования java.util.logging
        log.setLevel(Level.ALL);
        log.info("Start");
        log.log(Level.INFO, "Запись лога с уровнем INFO (информационная)");
        log.log(Level.WARNING, "Запись лога с уровнем WARNING (Предупреждение)");
        log.log(Level.SEVERE, "Запись лога с уровнем SEVERE (серъёзная ошибка)");
        log.info("Some message");

        try {
            throw new Exception("ERR!");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "My Exception: {0}", ex.getMessage());
        }

        log.info("End");
        // -----------------------------------------

        // Технология логирования log4j
        log4j.info("Start log4j");
        log4j.info("Hi Logger info!");
        log4j.warn("Warrning!");
        log4j.error("Error!");
        log4j.fatal("Fatal error!");
        log4j.info("End log4j");
        org.apache.log4j.LogManager.shutdown();
    }

}
