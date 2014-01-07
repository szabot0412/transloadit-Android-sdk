package hu.szabot.transloadit.assembly;

import java.util.HashMap;

/// <summary>
/// Represents a Transloadit step
/// </summary>
public class Step implements IStep
{
    /**Stores the options of the current step*/
    protected HashMap<String, Object> options;

    /**Creates a new step*/
    public Step()
    {
        options = new HashMap<String, Object>();
    }

    public void setOption(String key, Object value)
    {
        options.put(key, value);
    }

    public HashMap<String, Object> getMap()
    {
        return options;
    }

}

