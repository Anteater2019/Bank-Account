/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;

/**
 *
 * @author marco
 */
public class Transaction implements Serializable
{
    private final int transNum;
    private final int transId;
    private final double transAmt;
    
    public Transaction(int number, int id, double amount)
    { 
       transNum = number;
       transId = id;
       transAmt = amount;
    }
    public int getTransNum()
    {
        return transNum;
    }
    public int getTransId()
    {
        return transId;
    }
    public double getTransAmt()
    {
        return transAmt;
    }
}
