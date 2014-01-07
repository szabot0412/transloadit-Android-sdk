package hu.szabot.transloadit.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**Util functions for Transoadit*/
public class ShaUtils
{
	/**
	 * Generates and gets SHA1 string based on the passed parameters
	 * @param key Key of the hash
	 * @param str String to be secured
	 * @return The genarated hash in hex format
	 * @throws InvalidKeyException Thrown if the key is invalid
	 */
    public static String getSha1(String key, String str) throws InvalidKeyException {

    	byte[] bytes=null;
    	
    	try{
	    	
    		SecretKeySpec keySpec = new SecretKeySpec((key).getBytes("UTF-8"), "HmacSHA1");
	    	Mac mac = Mac.getInstance("HmacSHA1");
	    	mac.init(keySpec);
	    	
	    	bytes = mac.doFinal(str.getBytes("UTF-8"));
	    	
    	}catch (UnsupportedEncodingException e) 
    	{
		}catch (NoSuchAlgorithmException e) {
		}
    	
    	StringBuilder sb=new StringBuilder();
    	
    	for(byte b: bytes)
    	{
    		sb.append(String.format("%02x", b));
    	}
    	
    	return sb.toString();

    }
}
