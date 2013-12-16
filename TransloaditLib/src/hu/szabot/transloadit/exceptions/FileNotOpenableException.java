package hu.szabot.transloadit.exceptions;

import java.io.File;

/**Thrown when a File not openable or not readable*/
@SuppressWarnings("serial")
public class FileNotOpenableException extends Exception
{
    public FileNotOpenableException(File file)
    {
    	super(String.format("File: %s not exist or directory or not readable", file.getAbsoluteFile()));
    }

}

