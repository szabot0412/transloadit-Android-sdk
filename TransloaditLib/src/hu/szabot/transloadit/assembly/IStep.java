package hu.szabot.transloadit.assembly;

import java.util.HashMap;

/// <summary>
/// Defines the major functionality of Transloadit step
/// </summary>
public interface IStep
{
    /**
     * Sets an option for a step in the assembly
     * @param key Name of the option
     * @param value Value of the option
     */
    void setOption(String key, Object value);

    /**Gets the current step as dictionary
     * 
     * @return Step Map
     */
    public HashMap<String, Object> getMap();
}

