package com.veryworks.iyeongjun.shakehere.domain;

/**
 * Created by iyeongjun on 2017. 11. 1..
 */
public class Header
{
    private String resultMsg;

    private String resultCode;

    public String getResultMsg ()
    {
        return resultMsg;
    }

    public void setResultMsg (String resultMsg)
    {
        this.resultMsg = resultMsg;
    }

    public String getResultCode ()
    {
        return resultCode;
    }

    public void setResultCode (String resultCode)
    {
        this.resultCode = resultCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [resultMsg = "+resultMsg+", resultCode = "+resultCode+"]";
    }
}


