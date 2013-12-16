package hu.szabot.transloadit.parser;

import hu.szabot.transloadit.exceptions.NotParseableException;
import hu.szabot.transloadit.log.TransloaditLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**The JSON implementation of the IResponseParser interface*/
public class JSONResponseParser implements IResponseParser{

	/**The response String*/
	private String response;

	/**
	 * Creates a JSONResponseParser with the given string
	 * @param response The response string
	 */
	public JSONResponseParser(String response) 
	{
		this.response = response;
	}
	
	/**
	 * Creates a JSONResponseParser
	 */
	public JSONResponseParser() {}

	@Override
	public String getResponse() {
		return response;
	}

	@Override
	public void setResponse(String response) 
	{
		this.response=response;
	}

	@Override
	public HashMap<String, Object> parse() throws NotParseableException 
	{
		JSONObject json;
		
		try {
			
			json = new JSONObject(response);
			return toMap(json);
			
		} catch (JSONException e) 
		{
			TransloaditLogger.logError(getClass(), e,"Not parseable string: %s", response);
			
			throw new NotParseableException();
		}
	}
	
	/**
	 * Converts the JSONObject to Map
	 * @param object The JSON object
	 * @return the parsed Map
	 * @throws JSONException Thrown when the JSON not valid
	 */
	public HashMap<String, Object> toMap(JSONObject object) throws JSONException {
    	HashMap<String, Object> map = new HashMap<String, Object>();
        @SuppressWarnings("unchecked")
		Iterator<String> keys = object.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            map.put(key, fromJson(object.get(key)));
        }
        return map;
    }
 
	/**
	 * Converts the JSONArray to List
	 * @param array The JSONArray
	 * @return the parsed List
	 * @throws JSONException Thrown when the JSON not valid
	 */
    private List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            list.add(fromJson(array.get(i)));
        }
        return list;
    }
 
    /**
     * Parse the Object 
     * @param json The object to pars
     * @return The parsed Object
     * @throws JSONException Thrown when the JSON not valid
     */
    private Object fromJson(Object json) throws JSONException {
        if (json == JSONObject.NULL) {
            return null;
        } else if (json instanceof JSONObject) {
            return toMap((JSONObject) json);
        } else if (json instanceof JSONArray) {
            return toList((JSONArray) json);
        } else {
            return json;
        }
    }

}
