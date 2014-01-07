package hu.szabot.transloadit.assembly.exceptions;

/**Thrown when a field key is already defined*/
@SuppressWarnings("serial")
public class AlreadyDefinedKeyException extends Exception
{
    public AlreadyDefinedKeyException(String key, String location)
    {
    	super(String.format("Key is already defined: %s, in %s", key, location));
    }

}

