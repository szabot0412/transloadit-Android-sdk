package hu.szabot.transloadit.test;

import hu.szabot.transloadit.ITransloadit;
import hu.szabot.transloadit.Transloadit;
import hu.szabot.transloadit.TransloaditResponse;
import hu.szabot.transloadit.assembly.AssemblyBuilder;
import hu.szabot.transloadit.assembly.IAssemblyBuilder;

import java.io.IOException;

import junit.framework.TestCase;


public class UseTemplateTests extends TestCase
{

    public void testExistingTemplate() throws IOException
    {
        ITransloadit transloadit = new Transloadit(Constants.API_KEY);
        IAssemblyBuilder assembly = new AssemblyBuilder();
        
        assembly.setTemplateID(Constants.TEMPLATE_ID);
        
        TransloaditResponse response = transloadit.invokeAssembly(assembly);

        if(!Constants.SIGNATURE_AUTHENTICATION)
        {
        	assertTrue(((String)response.getData().get("ok")).equals("ASSEMBLY_COMPLETED") || ((String)response.getData().get("ok")).equals("ASSEMBLY_EXECUTING"));
	    }else
	    {
	    	assertTrue(true);
	    }
    }

    
    public void testNonExistingTemplate() throws IOException
    {
        ITransloadit transloadit = new Transloadit(Constants.API_KEY);
        IAssemblyBuilder assembly = new AssemblyBuilder();
        assembly.setTemplateID("non_existing_template_id");
        TransloaditResponse response = transloadit.invokeAssembly(assembly);

        if(!Constants.SIGNATURE_AUTHENTICATION)
        {
        	assertEquals("TEMPLATE_NOT_FOUND", (String)response.getData().get("error"));
	    }else
	    {
	    	assertTrue(true);
	    }
    }
}

