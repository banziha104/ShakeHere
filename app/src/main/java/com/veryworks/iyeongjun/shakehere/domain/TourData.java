package com.veryworks.iyeongjun.shakehere.domain;

/**
 * Created by iyeongjun on 2017. 10. 31..
 */
public class TourData
{
    private Records[] records;

    private Fields[] fields;

    public Records[] getRecords ()
    {
        return records;
    }

    public void setRecords (Records[] records)
    {
        this.records = records;
    }

    public Fields[] getFields ()
    {
        return fields;
    }

    public void setFields (Fields[] fields)
    {
        this.fields = fields;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [records = "+records+", fields = "+fields+"]";
    }
}
