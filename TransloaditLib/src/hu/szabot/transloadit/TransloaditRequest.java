package hu.szabot.transloadit;

import hu.szabot.transloadit.executor.DefaultHttpExecutor;
import hu.szabot.transloadit.executor.ParsedApiData;
import hu.szabot.transloadit.log.TransloaditLogger;
import hu.szabot.transloadit.parser.IRequestParser;
import hu.szabot.transloadit.parser.JSONRequestParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**Uses Transloadit services based on the current configuration*/
public class TransloaditRequest implements IApiRequest<TransloaditResponse>
{
    /**Default absolute URI path of assembly requests*/
    public static final String ASSEMBLY_ROOT = "/assemblies";

    /**Default absolute URI path to request bored instance*/
    public static final String BORED_INSTANCE_PATH = "/instances/bored";

    /**Default host of Transloadit services*/
    public static final String DEFAULT_HOST = "api2.transloadit.com";

    /**Default protocol of Transloadit services*/
    public static final String DEFAULT_SCHEME = "http";

    /**The request URI*/
    private URI uri;
    
    /**The request ApiData*/
    private ApiData data;
    
    /**The request method*/
    private RequestMethod method;
    
    /**Creates a new TransloaditRequest object. sets the Host to the default one, also sets the path to AssemblyRoot*/
    public TransloaditRequest()
    {
    	try {
			uri=new URI(DEFAULT_SCHEME,DEFAULT_HOST,ASSEMBLY_ROOT,null);
		} catch (URISyntaxException e) {
			
			TransloaditLogger.logError(getClass(), e,"Default URI parameter syntax invalid");
		}
    }

	@Override
	public TransloaditResponse execute() throws IOException 
	{
		DefaultHttpExecutor executor=new DefaultHttpExecutor();
		
		executor.setUri(uri);
		
		executor.setMethod(getMethod());
		
		return new TransloaditResponse(executor.execute(new ParsedApiData(getData(),getParser())));
	}

	@Override
	public ApiData getData() 
	{
		return data;
	}

	@Override
	public RequestMethod getMethod() 
	{
		return method;
	}

	@Override
	public URI getURI() {
		return uri;
	}

	@Override
	public void setPath(String path) throws URISyntaxException
	{
		uri=new URI(uri.getScheme(),uri.getHost(),path,null);
	}
	
	@Override
	public void setHost(String host) throws URISyntaxException
	{
		uri=new URI(uri.getScheme(),host,uri.getPath(),null);
	}
	
	@Override
	public void setMethod(RequestMethod method) 
	{
		this.method=method;
	}
	
	@Override
	public IRequestParser getParser()
	{
		return new JSONRequestParser();
	}
	
	@Override
	public void setData(ApiData data) 
	{
		this.data=data;
	}
    
}
