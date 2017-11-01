package com.veryworks.iyeongjun.shakehere.domain;

/**
 * Created by iyeongjun on 2017. 11. 1..
 */
public class Items
{
    private Item[] item;

    public Item[] getItem ()
    {
        return item;
    }

    public void setItem (Item[] item)
    {
        this.item = item;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [item = "+item+"]";
    }
}
