package hu.szabot.transloadit;

import java.util.HashMap;

/**Interface for API response*/
public interface IApiResponse
{
    /**Gets the response string*/
    String getResponseString();

    /**Gets data tree parsed from the sent response string*/
    HashMap<String, Object> getData();

    /**Gets success information about the sent request*/
    boolean isSuccess();
}
