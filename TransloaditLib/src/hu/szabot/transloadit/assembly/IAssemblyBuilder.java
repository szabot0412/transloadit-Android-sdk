package hu.szabot.transloadit.assembly;

import hu.szabot.transloadit.ApiData;
import hu.szabot.transloadit.assembly.exceptions.AlreadyDefinedKeyException;
import hu.szabot.transloadit.assembly.exceptions.InvalidFieldKeyException;
import hu.szabot.transloadit.exceptions.FileNotOpenableException;

import java.io.File;
import java.util.Date;

/**
*Builds up an assembly which will be tried to be created on Transloadit
**/
public interface IAssemblyBuilder
{
    /**Adds file to the current assembly
     * 
     * @param file File to upload
     * @throws FileNotOpenableException
     */
	public void addFile(File file) throws FileNotOpenableException;

    /**
     * Adds file to the current assembly with specific key
     * @param key Key of the file to be uploaded
     * @param file File to upload
     * @throws InvalidFieldKeyException Thrown when an invalid (reserved) field key is tried to be used
     * @throws FileNotOpenableException File not exist or not openable.
     */
    public void addFile(String key, File file) throws InvalidFieldKeyException, FileNotOpenableException;

    /**Adds step to the current assembly
     * 
     * @param name Name of the step
     * @param step Step to be added
     */
    public void addStep(String name, IStep step);

    /**Checks whether the assembly has notify URL*/
    public boolean hasNotifyUrl();

    /**Checks whether the assembly has template ID
     * 
     * @return Existing of set template ID
     */
    public boolean hasTemplateID();

    /**Sets the expiration datetime of the assembly (as UTC date)
     * 
     * @param dateTime Expiration datetime
     */
    public void setAuthExpires(Date dateTime);

    /**
     * Do not call directly! 
     * <p>
     * Transloadit class will override the value. Use Transloadit constructor parameter.
     * <p>
     * Sets the authentication key for the assembly
     * @param key API key of the user
     */
    public void setAuthKey(String key);

    /**Sets the maximum size of the assembly
     * 
     * @param maxSize Maximum size (in bytes)
     */
    public  void setAuthMaxSize(int maxSize);

    /**Sets a custom field in the current assembly
     * 
     * @param key Key of the field
     * @param value Value of the field
     * @throws InvalidFieldKeyException Thrown when an invalid (reserved) field key is tried to be used
     * @throws AlreadyDefinedKeyException Thrown when an already defined key (in files or in fields) is tried to be used
     */
    public void setField(String key, String value) throws InvalidFieldKeyException, AlreadyDefinedKeyException;

    /**
     * Sets the notification URL of the assembly, which will be requested after assembly is completed
     * @param notifyURL Notification URL (e.g.: 'http://my.domain.me/application')
     */
    public void setNotifyURL(String notifyURL);

    /**Sets the used template ID of the assmebly (you can create multiple Transloadit templates under your account,please use its unique ID here)
     * 
     * @param templateID Template ID of the assmebly
     */
    public void setTemplateID(String templateID);

    /**Converts the builder to ApiData and gets the object, which will be the base of the sent Transloadit request
     * 
     * @return Data to be sent to Transloadit backend
     */
    public ApiData toApiData();

    /**Checks whether the assembly has steps*/
    public boolean hasSteps();
}

