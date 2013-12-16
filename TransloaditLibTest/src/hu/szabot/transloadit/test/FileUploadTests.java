package hu.szabot.transloadit.test;

import hu.szabot.transloadit.ITransloadit;
import hu.szabot.transloadit.Transloadit;
import hu.szabot.transloadit.TransloaditResponse;
import hu.szabot.transloadit.assembly.AssemblyBuilder;
import hu.szabot.transloadit.assembly.IAssemblyBuilder;
import hu.szabot.transloadit.assembly.IStep;
import hu.szabot.transloadit.assembly.Step;
import hu.szabot.transloadit.exceptions.FileNotOpenableException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.os.Environment;
import android.test.AndroidTestCase;

public class FileUploadTests extends AndroidTestCase
{
	
    @SuppressWarnings("rawtypes")
	public void testResizeImageExistingFile() throws FileNotOpenableException, IOException
    {
        ITransloadit transloadit = new Transloadit(Constants.API_KEY);
        IAssemblyBuilder assembly = new AssemblyBuilder();
        assembly.addFile(new File(Environment.getExternalStorageDirectory().getPath()+"/"+Constants.TEST_IMAGE_ON_SD_CARD));

        IStep step = new Step();
        step.setOption("robot", "/image/resize");
        step.setOption("width", 75);
        step.setOption("height", 75);
        step.setOption("resize_strategy", "pad");
        step.setOption("background", "#000000");

        assembly.addStep("thumb", step);

        TransloaditResponse response = transloadit.invokeAssembly(assembly);

        if(!Constants.SIGNATURE_AUTHENTICATION)
        {
	        assertTrue((((String)response.getData().get("ok")).equals("ASSEMBLY_COMPLETED") || ((String)response.getData().get("ok")).equals("ASSEMBLY_EXECUTING")) &&
	        			((List)(response.getData().get("uploads"))).size() > 0);
        
	    }else
	    {
	    	assertTrue(true);
	    }
    }

    
    @SuppressWarnings("rawtypes")
	public void testResizeImageNonExistingFile() throws IOException
    {
        ITransloadit transloadit = new Transloadit(Constants.API_KEY);
        IAssemblyBuilder assembly = new AssemblyBuilder();
        
        try {
			assembly.addFile(new File("non_existing"));
			
			assertFalse(true);
			
		} catch (FileNotOpenableException e) 
		{
			assertTrue(true);
		}

        IStep step = new Step();
        step.setOption("robot", "/image/resize");
        step.setOption("width", 75);
        step.setOption("height", 75);
        step.setOption("resize_strategy", "pad");
        step.setOption("background", "#000000");

        assembly.addStep("thumb", step);

        TransloaditResponse response = transloadit.invokeAssembly(assembly);

        if(!Constants.SIGNATURE_AUTHENTICATION)
        {
        	
	        assertTrue((((String)response.getData().get("ok")).equals("ASSEMBLY_COMPLETED") || ((String)response.getData().get("ok")).equals("ASSEMBLY_EXECUTING")) &&
	        			((List)(response.getData().get("uploads"))).size() == 0);
	        
	    }else
	    {
	    	assertTrue(true);
	    }
    }
}
