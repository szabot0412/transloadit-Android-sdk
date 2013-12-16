package hu.szabot.transloadit.executor;

import hu.szabot.transloadit.IApiRequest.RequestMethod;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.ClientProtocolException;

public interface IRequestExecutor {

	/** Sets the request URI
	 * 
	 * @param uri Destination URI
	 */
	public void setUri(URI uri);
	
	/** Sets the request method
	 * 
	 * @param method The method
	 */
	public void setMethod(RequestMethod method);
	
	
	/**Executes the current requests and gets the result
	 * 
	 * @param data The parsed ApiData
	 * @return The server response
	 * @throws ClientProtocolException Signals an error in the HTTP protocol.
	 * @throws IOException Thrown when I/O error occurred in the communication.
	 */
	public String execute(ParsedApiData data) throws ClientProtocolException, IOException;
	
}
