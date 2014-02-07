package hu.szabot.transloadit;

import java.io.IOException;
import java.net.URISyntaxException;

import hu.szabot.transloadit.assembly.IAssemblyBuilder;

/**Interface for accessing Transloadit services*/
public interface ITransloadit
{
	
	/**
	 * Tries to delete an assembly by the specified assembly ID.
	 * @param assemblyID ID of the assembly which will be tried to be deleted
	 * @return Represents the whole result of the request. 
	 */
    TransloaditResponse deleteAssembly(String assemblyID);

    /**
     * Tries to create the specified assembly on Transloadit. Bored instance is requested at first. If there is one, then it will
     * be used to proceed the request, which can be completed on a bored instance.
     * @param assembly Specified assembly which will be tried to be created.
     * @return Represents the whole result of the request. 
     * @throws URISyntaxException
     * @throws IOException
     */
    TransloaditResponse invokeAssembly(IAssemblyBuilder assembly) throws IOException;

    /**
     * Creates and gets a new TransloaditRequest instance with default attributes
     * @return TransloaditRequest instance with default attributes
     */
    TransloaditRequest request();

    
    /**
     * Creates then tries to proceed the specified or a default request, then executes this and gets the response of it.
     * @param request Optional request which will be tried to be executed
     * @return Represents the whole result of the request. 
     * @throws URISyntaxException Thrown if the URI not valid.
     * @throws IOException Thrown when I/O error occurred in the communication.
     */
    TransloaditResponse requestAndExecute(TransloaditRequest request) throws IOException;

    
    /**
     * Sets API Secret code of the current Transloadit user. This code is used for each request if signature authentication is enabled 
     * @param secret The secret key, or NULL to not use signature.
     */
    public void useSignature(String secret);
 
}
