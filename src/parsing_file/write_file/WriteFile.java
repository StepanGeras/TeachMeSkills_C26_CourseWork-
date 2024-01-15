package parsing_file.write_file;

import logger.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import static util_information.constant.Constant.*;

public class WriteFile {

    public static void doWriteFile() {

        try {

            double allSum = SUM_CHECK + SUM_INVOICE + SUM_ORDER;

            Logger.executionLogger(new Date(),  "Write the total turnover of check in a file");
            Files.write(Paths.get(PATH_ALL_RESULT_FILE), ("Total turnover for all checks: " + SUM_CHECK + "\n").getBytes(), StandardOpenOption.APPEND);
            Logger.executionLogger(new Date(),  "Recording was successful");

            Logger.executionLogger(new Date(),  "Write the total turnover of invoice in a file");
            Files.write(Paths.get(PATH_ALL_RESULT_FILE), ("Total turnover for all order: " + SUM_INVOICE + "\n").getBytes(), StandardOpenOption.APPEND);
            Logger.executionLogger(new Date(),  "Recording was successful");

            Logger.executionLogger(new Date(),  "Write the total turnover of order in a file");
            Files.write(Paths.get(PATH_ALL_RESULT_FILE), ("Total turnover for all invoice: " + SUM_ORDER + "\n").getBytes(), StandardOpenOption.APPEND);
            Logger.executionLogger(new Date(),  "Recording was successful");

            Logger.executionLogger(new Date(),  "Write the total turnover");
            Files.write(Paths.get(PATH_ALL_RESULT_FILE), ("Total turnover for all result: " + allSum + "\n").getBytes(), StandardOpenOption.APPEND);
            Logger.executionLogger(new Date(),  "Recording was successful");

        } catch (IOException e) {
            Logger.errorLogger(new Date(), "Write error", e);
        }

    }

}
