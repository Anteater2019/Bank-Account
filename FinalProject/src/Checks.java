/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marco
 */
public class Checks extends Transaction
{
    private int chkNum;

    public Checks(int number, int id, double amount, int checkNumber) //int number is transaction count 
    {
        super(number, id, amount);
        this.chkNum = checkNumber;
    }
     public int getCheckNumber() 
     {
        return chkNum;
     }
     public void setCheckNumber(int checkNumber)
     {
         this.chkNum = checkNumber;
     }

    
}
