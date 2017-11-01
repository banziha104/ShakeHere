package com.veryworks.iyeongjun.shakehere.domain;

/**
 * Created by iyeongjun on 2017. 10. 31..
 */

public class Fields
{
    private String id;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+"]";
    }
}
