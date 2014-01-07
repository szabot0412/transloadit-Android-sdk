package hu.szabot.transloadit.assembly.exceptions;

/**Thrown when the specified field name is invalid*/
@SuppressWarnings("serial")
public class InvalidFieldKeyException extends Exception
{
    public InvalidFieldKeyException(String key)
    {
    	super(String.format("Invalid key was tried to be set: %s: ", key));
    }
}

