package hu.szabot.transloadit.test;

import hu.szabot.transloadit.ITransloadit;
import hu.szabot.transloadit.Transloadit;
import hu.szabot.transloadit.TransloaditResponse;
import hu.szabot.transloadit.assembly.AssemblyBuilder;
import hu.szabot.transloadit.assembly.IAssemblyBuilder;
import hu.szabot.transloadit.assembly.Step;
import hu.szabot.transloadit.assembly.exceptions.AlreadyDefinedKeyException;
import hu.szabot.transloadit.assembly.exceptions.InvalidFieldKeyException;

import java.io.IOException;
import java.util.Map;

import junit.framework.TestCase;

public class FormDataTests extends TestCase
{
	
    @SuppressWarnings("unchecked")
	public void testInvokeAssemblyWithFormData() throws InvalidFieldKeyException, AlreadyDefinedKeyException, IOException
    {
        ITransloadit transloadit = new Transloadit(Constants.API_KEY);
        IAssemblyBuilder assembly = new AssemblyBuilder();
        assembly.setField("test", "200");
        
        Step step=new Step();
		
		step.setOption("robot", "/http/import");
		step.setOption("url", "http://static4.wikia.nocookie.net/__cb20120716045812/deadliestfiction/images/2/24/Cthulhu-rlyeh-rising.jpg");
		
		assembly.addStep("test", step);
        
        TransloaditResponse response = transloadit.invokeAssembly(assembly);

        if(!Constants.SIGNATURE_AUTHENTICATION)
        {
        assertTrue((((String)response.getData().get("ok")).equals("ASSEMBLY_COMPLETED") || ((String)response.getData().get("ok")).equals("ASSEMBLY_EXECUTING")) &&
        			((Map<String, Object>)(response.getData().get("fields"))).size() > 0);
        }else
        {
        	assertTrue(true);
        }
    }

    @SuppressWarnings("unchecked")
	public void testInvokeAssemblyWithoutFormData() throws IOException
    {
        ITransloadit transloadit = new Transloadit(Constants.API_KEY);
        IAssemblyBuilder assembly = new AssemblyBuilder();
        
        Step step=new Step();
		
		step.setOption("robot", "/http/import");
		step.setOption("url", "http://static4.wikia.nocookie.net/__cb20120716045812/deadliestfiction/images/2/24/Cthulhu-rlyeh-rising.jpg");
		
		assembly.addStep("test", step);
        
        TransloaditResponse response = transloadit.invokeAssembly(assembly);

        if(!Constants.SIGNATURE_AUTHENTICATION)
        {
	        assertTrue((((String)response.getData().get("ok")).equals("ASSEMBLY_COMPLETED") || ((String)response.getData().get("ok")).equals("ASSEMBLY_EXECUTING")) &&
	        			((Map<String, Object>)(response.getData().get("fields"))).size() == 0);
	    }else
	    {
	    	assertTrue(true);
	    }
    }
}

