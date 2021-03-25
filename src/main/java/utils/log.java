package utils;

/*************************************** PURPOSE **********************************

 - This class implement the logging functionality. Capture the log  into application.log file

 */

import org.apache.commons.logging.Log;
import freemarker.log.Logger;

public class log {

    private static Logger Log = Logger.getLogger(Log.class.getName());

    //We can use it when starting tests
    public static void startLog (String testClassName){
        Log.info("Test case is Starting..." +testClassName);
    }

    //We can use it when ending tests
    public static void endLog (String testClassName){
        Log.info("Test is Ending..." + testClassName);
    }

    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
    }

    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }

    //Error Level Logs
    public static void error (String message) {
        Log.error(message);
    }

    //Fatal Level Logs
    public static void fatal (String message) {
        ((org.apache.commons.logging.Log) Log).fatal(message);
    }

    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }
}