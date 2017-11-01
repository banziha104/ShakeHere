package com.veryworks.iyeongjun.shakehere.domain;

/**
 * Created by iyeongjun on 2017. 10. 31..
 */

public class TourData
{
    private Response response;

    public Response getResponse ()
    {
        return response;
    }

    public void setResponse (Response response)
    {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+"]";
    }
}