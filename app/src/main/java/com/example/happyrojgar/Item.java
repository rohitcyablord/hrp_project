package com.example.happyrojgar;

public class Item {

    String transAmt, trans_UserName, trans_Date;

    public Item(String transAmount,String usrName, String transac_dt)
    {
        this.transAmt = transAmount;
        this.trans_UserName = usrName;
        this.trans_Date = transac_dt;
    }

    public String getTransactionAmount()
    {
        return transAmt;
    }

    public String getUserName()
    {
        return trans_UserName;
    }

    public String getTransactionDate(){
        return trans_Date;
    }
}