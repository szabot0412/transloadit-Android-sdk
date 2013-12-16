package hu.szabot.transloadit.parser;

import hu.szabot.transloadit.exceptions.NotParseableException;

import java.util.Map;

/**The params filed parser interface*/
public interface IRequestParser {

	/**Sets the request objects to be parsed*/
	public void setRequest(Map<String, Object> request);
	
	/**Gets the request parser*/
	public Map<String, Object> getRequest();
	
	/**
	 * Parse the request
	 * @return The parsed String
	 * @throws NotParseableException Thrown when the request not parseable.
	 */
	public String parse() throws NotParseableException;
}
