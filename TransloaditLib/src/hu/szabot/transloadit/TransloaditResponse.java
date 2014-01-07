package hu.szabot.transloadit;

import hu.szabot.transloadit.exceptions.NotParseableException;
import hu.szabot.transloadit.parser.IResponseParser;
import hu.szabot.transloadit.parser.JSONResponseParser;

import java.util.HashMap;

/**Handles the whole functionality of Transloadit response*/
public class TransloaditResponse implements IApiResponse
{
	/**Request success flag*/
	private boolean success=false;
	
	/**The response string*/
	private String responseString;
	
	/**The parsed data*/
	private HashMap<String, Object> data;
	
    /**Creates a new TransloaditResponse object with response string string
     * @param responseString The response string
     */
    public TransloaditResponse(String responseString)
    {
    	this(responseString,new JSONResponseParser());
    	
    }
    
    /**
     * Creates a new TransloaditResponse object with response string string and parser
     * @param responseString The response string
     * @param parser The response parser
     */
    public TransloaditResponse(String responseString,IResponseParser parser)
    {
    	this.responseString=responseString;
    	
    	parser.setResponse(responseString);
    	
        try {
			
        	data=parser.parse();
        	
	        if (data.containsKey("ok"))
	        {
	            success = true;
	        }
	        
		} catch (NotParseableException e) 
		{
		}
    	
    }

	@Override
	public String getResponseString() 
	{
		return responseString;
	}

	@Override
	public HashMap<String, Object> getData() 
	{
		return data;
	}

	@Override
	public boolean isSuccess() {
		return success;
	}

}
