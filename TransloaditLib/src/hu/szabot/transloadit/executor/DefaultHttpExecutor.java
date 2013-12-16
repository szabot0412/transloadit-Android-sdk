package hu.szabot.transloadit.executor;

import hu.szabot.transloadit.IApiRequest.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

public class DefaultHttpExecutor implements IRequestExecutor{

	/**Destination URI*/
	private URI uri;
	
	/**Request method*/
	private RequestMethod method=RequestMethod.GET;
	
	@Override
	public void setUri(URI uri) 
	{
		this.uri=uri;
	}
	
	@Override
	public void setMethod(RequestMethod method) 
	{
		this.method=method;
	}

	@Override
	public String execute(ParsedApiData data) throws ClientProtocolException, IOException 
	{
		HttpClient client = new DefaultHttpClient();
        
        HttpRequestBase request=getRequest(method, uri);
        
        if(method==RequestMethod.POST ||method==RequestMethod.PUT)
        {
        	HttpEntityEnclosingRequestBase requestWithEntity=(HttpEntityEnclosingRequestBase)request;
        	
        	MultipartEntityBuilder entityBuilder=MultipartEntityBuilder.create();
    		
    		entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
    		
    		for(String key : data.getFields().keySet())
    		{
    			entityBuilder.addTextBody(key, data.getFields().get(key));
    		}
    		
    		for(String key : data.getFiles().keySet())
    		{
    			entityBuilder.addPart(key, new FileBody(data.getFiles().get(key)));
    		}
    		
    		HttpEntity entity=entityBuilder.build();
    		
    		requestWithEntity.setEntity(entity);
        }
        
        HttpResponse response = client.execute(request);
        
        return getContent(response);
        
    }

	/** Export the response string from response object
	 * 
	 * @param response Response object
	 * @return The response string
	 * @throws IOException Thrown when I/O error occurred in the communication.
	 */
	public static String getContent(HttpResponse response) throws IOException 
	{
	    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    String body = "";
	    String content = "";
	
	    while ((body = rd.readLine()) != null) 
	    {
	        content += body + "\n";
	    }
	    return content.trim();
	}
	
	
	/**
	 * Creates the request object from method and URI
	 * @param method The request method
	 * @param uri The request URI
	 * @return The created request object
	 */
	private HttpRequestBase getRequest(RequestMethod method,URI uri)
    {
    	switch (method) 
    	{
			case GET:
			{
				return new HttpGet(uri);
			}
			case POST:
			{	
				return new HttpPost(uri);
			}
		    case PUT:
		    {	
				return new HttpPut(uri);
		    }
		    case DELETE:
		    {	
				return new HttpDelete(uri);
			}
    	}
    	
    	return null;
    
    }
}
