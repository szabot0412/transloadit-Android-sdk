package hu.szabot.transloadit.parser;

import hu.szabot.transloadit.exceptions.NotParseableException;
import hu.szabot.transloadit.log.TransloaditLogger;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


/**The JSON implementation of the IRequsetParser interface*/
public class JSONRequestParser implements IRequestParser{
	
	/**The request parameters*/
	private Map<String, Object> request;

	/**
	 * Creates new JSONRequestParser and sets the request properties
	 * @param request The params key-value pairs
	 */
	public JSONRequestParser(Map<String, Object> request) 
	{
		this.request = request;
	}
	
	/**
	 * Creates new JSONRequestParser
	 */
	public JSONRequestParser() {}

	@Override
	public Map<String, Object> getRequest() {
		return request;
	}

	@Override
	public void setRequest(Map<String, Object> request) 
	{
		this.request=request;
	}

	@Override
	public String parse() throws NotParseableException 
	{
		try {
			
			return toJson(getRequest()).toString();
			
		} catch (Exception e) 
		{
			TransloaditLogger.logError(getClass(), e,"Not parseable map: %s", request);
			
			throw new NotParseableException();
		}
	}
	
	/**
	 * Parse the Map to hierarchical JSON
	 * @param params The Map to be parsed
	 * @return The created JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject toJson(Map<String,Object> params)
    {
    	JSONObject json=new JSONObject();
    	
    	for(String key: params.keySet())
    	{
    		if(params.get(key) instanceof Map)
    		{
    			try {
					json.put(key,toJson((Map<String,Object>)params.get(key)));
				} catch (JSONException e) 
				{}
    			
    			
    		}else
    		{
    			try {
					json.put(key, params.get(key));
				} catch (JSONException e) 
				{
				}
    		}
    	}
    	
    	
        return json;
    }

}
