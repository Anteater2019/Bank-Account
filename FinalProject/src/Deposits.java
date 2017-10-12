/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marco
 */
public class Deposits extends Transaction
{
    private double cashAmt, chkAmt;

    public Deposits(int number, int id, double cashAmount, double chkAmount, double totalAmt) 
    {
        super(number, id, totalAmt);
        cashAmt = cashAmount;
        chkAmt = chkAmount;
    }
    public double getCashAmt()
    {
        return cashAmt;
    }
    public double getChkAmt()
    {
        return chkAmt;
    }
    public void setCashAmt(double cashAmount)
    {
        cashAmt = cashAmount;
    }
    public void setChkAmt(double chkAmount)
    {
        chkAmt = chkAmount;
    }
}
