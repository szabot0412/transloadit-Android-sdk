package hu.szabot.transloadit;

import hu.szabot.transloadit.parser.IRequestParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**General interface for accessing REST API service*/
public interface IApiRequest<T extends IApiResponse>
{
	/**The type of request*/
	public enum RequestMethod
    {

        /**Used for GET requests*/
        GET,

        /** Used for POST requests*/
        POST,

        /**Used for PUT requests*/
        PUT,

        /**Used for DELETE requests*/
        DELETE,

    }
	
    /**Gets data to be posted*/
    public ApiData getData();

    /**Gets the method of the current request*/
    public RequestMethod getMethod();
    
    /**Sets the method of the current request*/
    public void setMethod(RequestMethod method);

    /**Gets the URI of the current request*/
    public URI getURI();
    
    /**
     *  Sets the absolute path of the current request
     * @param path The path string
     * @throws URISyntaxException Thrown if the URI is not valid with the given path
     */
    public void setPath(String path) throws URISyntaxException;

    /**
     * Sets the host of the current request
     * @param path The host string
     * @throws URISyntaxException Thrown if the URI is not valid with the given host
     */
    public void setHost(String path) throws URISyntaxException;
    
    /**Sets data to be posted*/
    public void setData(ApiData data);
    
    /**Gets the Request parser object*/
    public IRequestParser getParser();
	
    
    /**
     * Executes the current requests and gets the result
     * @return The response object
     * @throws IOException Thrown when I/O error occurred in the communication.
     */
    public T execute() throws IOException;
}

