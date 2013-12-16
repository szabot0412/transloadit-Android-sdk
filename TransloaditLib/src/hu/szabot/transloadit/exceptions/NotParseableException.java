package hu.szabot.transloadit.exceptions;

/**Thrown when the data not parseable with the given parser*/
@SuppressWarnings("serial")
public class NotParseableException extends Exception {

	public NotParseableException() 
	{
		super("Data not parseable with the given parser.");
	}
}
