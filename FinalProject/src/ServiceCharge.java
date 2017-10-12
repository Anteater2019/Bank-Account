/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marco
 */
public class ServiceCharge extends Transaction 
{
    private int associatedTransNumber;

    public ServiceCharge(int number, int id, double amount, int transNumber) //transNumber is the transaction number of what got charged say its a check
    {
        super(number, id, amount);
        associatedTransNumber = transNumber;
    }
    public int getAssociatedTransNum()
    {
        return associatedTransNumber;
    }
    public void setAssociatedTransNum(int TransNum)
    {
        associatedTransNumber = TransNum;
    }
    
}
