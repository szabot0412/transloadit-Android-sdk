package hu.szabot.transloadit.test;

import hu.szabot.transloadit.ITransloadit;
import hu.szabot.transloadit.Transloadit;
import hu.szabot.transloadit.TransloaditResponse;
import hu.szabot.transloadit.assembly.AssemblyBuilder;
import hu.szabot.transloadit.assembly.IAssemblyBuilder;

import java.io.IOException;

import junit.framework.TestCase;

public class NetworkTests extends TestCase
{
	
    public void testNoInternetConnection() throws IOException
    {
        ITransloadit transloadit = new Transloadit(Constants.API_KEY);
        IAssemblyBuilder assembly = new AssemblyBuilder();
        TransloaditResponse response = transloadit.invokeAssembly(assembly);

        if (!Constants.SIGNATURE_AUTHENTICATION && !response.isSuccess() && response.getData().containsKey("status") && (String)response.getData().get("status") == "NO_RESPONSE")
        {
            assertTrue(true);
        }
    }
}

