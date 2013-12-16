package hu.szabot.transloadit.executor;

import hu.szabot.transloadit.ApiData;
import hu.szabot.transloadit.exceptions.NotParseableException;
import hu.szabot.transloadit.log.TransloaditLogger;
import hu.szabot.transloadit.parser.IRequestParser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**Stores information which can be sent via an HTTP request. This object contains the parsed data.*/
public class ParsedApiData {
	
	
	/**The parsed fields (custom and params)*/
	private Map<String,String> fields;
	
	/**File objects of the files which will be sent*/
	private Map<String,File> files;
	
	/**Creates a new empty ParsedApiData object.*/
	public ParsedApiData() 
	{
		fields = new HashMap<String, String>();
		files =  new HashMap<String, File>();
	}

	/** 
	 * Creates a new ParsedApiData object with the given parser.
	 * @param data The raw api data.
	 * @param parser The parser to create from the data object
	 */
	public ParsedApiData(ApiData data,IRequestParser parser) 
	{
		this();
		
		if(data!=null)
		{
			files.putAll(data.getFiles());
			fields.putAll(data.getFields());
			
			parser.setRequest(data.getParams());
			
			try 
			{
				
				fields.put("params", parser.parse());
			
			} catch (NotParseableException e) 
			{
				TransloaditLogger.logError(getClass(), e);
			}
		}
	}
	
	/**Gets the data fields of a request (key - value pairs)*/
	public Map<String, String> getFields() {
		return fields;
	}
	
	/**Gets the File objects of the files which will be sent*/
	public Map<String, File> getFiles() {
		return files;
	}
	
}
