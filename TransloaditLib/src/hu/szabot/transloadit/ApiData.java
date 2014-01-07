package hu.szabot.transloadit;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**Stores information which can be sent. This object not parsed.*/
public class ApiData
{
    /**Data fields of a request (key - value pairs)*/
    public Map<String, String> fields;
    
    /**Params field of a request (key - value pairs)*/
    public Map<String, Object> params;

    /**File objects of the files which will be sent*/
    public Map<String, File> files;

    /**Creates new ApiData object. sets Fields and Files to empty collections*/
    public ApiData()
    {
        fields=new HashMap<String, String>();
        files=new HashMap<String, File>();
        params=new HashMap<String, Object>();
    }

    /**Gets the data fields of a request (key - value pairs)*/
	public Map<String, String> getFields() {
		return fields;
	}

	/**Gets the File objects of the files which will be sent*/
	public Map<String, File> getFiles() {
		return files;
	}

	/**Add a custom field property (key - value pairs)*/
	public void addField(String key, String value) 
	{
		fields.put(key, value);
	}
	
	/**Add a File object of the files which will be sent*/
	public void addFile(String key,File file) 
	{
		files.put(key, file);
	}
    
	/**Add a params field property (key - value pairs)*/
	public void addParam(String key,Object value) 
	{
		params.put(key, value);
	}
	
	/**Sets data fields of a request (key - value pairs)*/
	public void setFields(HashMap<String, String> fields) {
		this.fields = fields;
	}
	
	/**Sets the File objects of the files which will be sent*/
	public void setFiles(HashMap<String, File> files) {
		this.files = files;
	}
	
	/**Gets params field of a request (key - value pairs)*/
	public Map<String, Object> getParams() {
		return params;
	}
	
	/**Sets params field of a request (key - value pairs)*/
	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}
}

