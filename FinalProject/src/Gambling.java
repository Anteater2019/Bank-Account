/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
public class Gambling extends Transaction 
{
    private double Winnings;
    public Gambling(int number, int id, double amount)
    {
        super(number, id, amount);
        this.Winnings = amount;
        
    }
    public double GetWinnings()
    {
        return Winnings;
    }
    public void SetWinnings(double amount)
    {
        Winnings = amount;
    }
}
