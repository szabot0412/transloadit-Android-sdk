package hu.szabot.transloadit.parser;

import hu.szabot.transloadit.exceptions.NotParseableException;

import java.util.HashMap;

public interface IResponseParser {

	/**Sets the response String*/
	public void setResponse(String response);
	
	/**Gets the response String*/
	public String getResponse();
	
	/**
	 * Parse the request
	 * @return The parsed Map
	 * @throws NotParseableException Thrown when the request not parseable.
	 */
	public HashMap<String, Object> parse() throws NotParseableException;
	
}
