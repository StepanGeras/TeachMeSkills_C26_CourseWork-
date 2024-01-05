package logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public static String PATH_EXECUTION_LOG = "/Users/stepan_gerasimovich/Desktop/Курсач/TeachMeSkills_C26_CourseWork/logger/Execution Log.txt";
    public static String PATH_ERROR_LOG = "/Users/stepan_gerasimovich/Desktop/Курсач/TeachMeSkills_C26_CourseWork/logger/Error_Log.txt";

    public static void executionLogger (Date date, String message) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            Files.write(Paths.get(PATH_EXECUTION_LOG), (df.format(date) + " " + message + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ignored) {

        }
    }

    public static void errorLogger (Date date, String message, Exception errorMessage) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(df.format(date)).append(" ").append(message).append("\n");

            StackTraceElement[] stackTraceElements = errorMessage.getStackTrace();

            for (StackTraceElement element: stackTraceElements) {
                stringBuilder.append(element.toString()).append("\n");
            }

            Files.write(Paths.get(PATH_ERROR_LOG), stringBuilder.toString().getBytes(), StandardOpenOption.APPEND);

        } catch (IOException ignored) {

        }
    }

}
