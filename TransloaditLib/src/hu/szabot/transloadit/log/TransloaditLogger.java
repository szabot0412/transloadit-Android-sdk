package hu.szabot.transloadit.log;

import android.util.Log;

/**Transloadit logger*/
public class TransloaditLogger
{
	/**
	 * Logs information during application processes 
	 * @param type Type of the class, where the log is proceed
	 * @param message Parameterized info message
	 * @param parameters Parameters for the passed info message
	 */
    public static void logInfo(Class<?> type, String message, Object... parameters)
    {
        Log.i(type.getName(), String.format(message, parameters));
    }

    /**
     * Logs errors during application processes 
     * @param type Type of the class, where the log is proceed
     * @param exception Exception, which is the reason of the error
     * @param message Parameterized error message
     * @param parameters Parameters for the passed error message
     */
    public static void logError(Class<?> type, Exception exception, String message, Object... parameters)
    {
    	Log.e(type.getName(), String.format(message, parameters)+" - "+exception.getMessage());
    	exception.printStackTrace();
    }


    /**
     * Logs errors during application processes 
     * @param type Type of the class, where the log is proceed
     * @param message Parameterized error message
     * @param parameters Parameters for the passed error message
     */
    public static void logError(Class<?> type, String message, Object... parameters)
    {
    	Log.e(type.getName(), String.format(message, parameters));
    }


    /**
     * Logs errors during application processes 
     * @param type Type of the class, where the log is proceed
     * @param exception Exception, which is the reason of the error
     */
    public static void logError(Class<?> type, Exception exception)
    {
    	Log.e(type.getName(),exception.getMessage());
    	exception.printStackTrace();
    }

}
