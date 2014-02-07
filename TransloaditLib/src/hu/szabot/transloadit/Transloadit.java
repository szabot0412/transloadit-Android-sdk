package hu.szabot.transloadit;

import hu.szabot.transloadit.IApiRequest.RequestMethod;
import hu.szabot.transloadit.assembly.IAssemblyBuilder;
import hu.szabot.transloadit.assembly.exceptions.AlreadyDefinedKeyException;
import hu.szabot.transloadit.assembly.exceptions.InvalidFieldKeyException;
import hu.szabot.transloadit.exceptions.NotParseableException;
import hu.szabot.transloadit.log.TransloaditLogger;
import hu.szabot.transloadit.parser.IRequestParser;
import hu.szabot.transloadit.utils.ShaUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**Handles whole data transfer procedures between Your Application and Transloadit*/
public class Transloadit implements ITransloadit
{
	
    /** The API Key of the current Transloadit user. This key is used for each Transloadit request*/
    private String key;

    /** The API Secret code of the current Transloadit user. This code is used for each request if signature authentication is enabled */
    private String secret;
    
    
    public void useSignature(String secret)
    {
    	this.secret=secret;
    }
    
    /**
     * Creates a new Transloadit object with the given key
     * @param key The API key
     */
    public Transloadit(String key)
    {
        this.key=key;
    }

    public TransloaditResponse deleteAssembly(String assemblyID)
    {
    	
        TransloaditRequest request = request();
        
        try {
			request.setPath("/" +assemblyID);
		} catch (URISyntaxException e2) 
		{
			TransloaditLogger.logError(getClass(), e2);
		}
        
        TransloaditResponse response;
		
        try {
			
			response = request.execute();
			
			if (response.isSuccess())
	        {
	        	try{
		            URL uri = new URL((String)response.getData().get("assembly_url"));
		            
		            TransloaditRequest deleteRequest = request();
		            deleteRequest.setMethod(RequestMethod.DELETE);
		            deleteRequest.setHost(uri.getHost());
		            deleteRequest.setPath(uri.getPath());
		            TransloaditResponse deleteResponse = request.execute();
		        	
		            return deleteResponse;
		            
	            }catch (Exception e) 
	            {
	            	TransloaditLogger.logError(getClass(), e);
					return null;
				}
	        }
			
		} catch (IOException e1) 
		{
			TransloaditLogger.logError(getClass(), e1);
		}

        

        return null;
    }

    private String getBoredInstance()
    {
        TransloaditRequest request = request();
        
    	request.setMethod(RequestMethod.GET);
        
        try {
			request.setPath(TransloaditRequest.BORED_INSTANCE_PATH);
		} catch (URISyntaxException e) 
		{
			TransloaditLogger.logError(getClass(), e,"Default bored instance path not working");
		}

        TransloaditResponse boredInstance;
		
        try {
			boredInstance = (TransloaditResponse)requestAndExecute(request);
		
			 if (boredInstance.isSuccess())
		        {
		           
					return ((String)boredInstance.getData().get("api2_host"));
					
		        }else
		        {
		        	TransloaditLogger.logError(getClass(),"Bored instance host is invalid using default path %s",(String)boredInstance.getData().get("api2_host"));
					
					return TransloaditRequest.DEFAULT_HOST;
		        }
			 
		} catch (IOException e) 
		{
			e.printStackTrace();
			
			TransloaditLogger.logError(getClass(),e,"Bored instance host is invalid using default path");
			
			return TransloaditRequest.DEFAULT_HOST;
		}

       
    }
    
    
    
    public TransloaditResponse invokeAssembly(IAssemblyBuilder assembly) throws IOException
    {
    	
        TransloaditRequest request = request();
        
        assembly.setAuthKey(key);

        try {
        	
			request.setHost(getBoredInstance());
			
		} catch (URISyntaxException e2) 
		{
			try {
				
				request.setHost(TransloaditRequest.DEFAULT_HOST);
				
			} catch (URISyntaxException e) 
			{
				TransloaditLogger.logError(getClass(), e,"Default host invalid");
			}
			
		}

        request.setMethod(RequestMethod.POST);
        
        try {
			request.setPath(TransloaditRequest.ASSEMBLY_ROOT);
		} catch (URISyntaxException e1) 
		{
			TransloaditLogger.logError(getClass(), e1,"Default path invalid");
		}

        if(secret!=null)
        {
        	IRequestParser parser=request.getParser();
        	
        	parser.setRequest(assembly.toApiData().getParams());
        	
			try {
				
				String signatureValue = getSignature(parser.parse());
	        	
	        	assembly.setField("signature", signatureValue);
	        	
			} catch (NotParseableException e) 
			{
				TransloaditLogger.logError(getClass(), e);
			
			} catch (InvalidFieldKeyException e) 
			{}
			catch (AlreadyDefinedKeyException e) 
			{}
        
        	
        }
        
        request.setData(assembly.toApiData());

		return request.execute();

    }
    
    public TransloaditRequest request()
    {
        return new TransloaditRequest();
    }

    public TransloaditResponse requestAndExecute(TransloaditRequest request) throws IOException
    {
        if (request == null)
        {
            request = request();
        }
        return (TransloaditResponse)request.execute();
    }

    /**
     * Generates and gets the signature of the current request (based on the API Secret code and "params" field)
     * @param str Specified "params" field of the current Transloadit request
     * @return SHA1 signature for the current request
     */
    private String getSignature(String str)
    {
        if (secret == null || secret.length() < 1)
        {
            return null;
        }

		try {
			return ShaUtils.getSha1(secret, str);
		} catch (Exception e) 
		{
			TransloaditLogger.logError(getClass(), e);
			
			return null;
		} 
    }

}

