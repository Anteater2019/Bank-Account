/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
/**
 *
 * @author marco
 */
public class Bets 
{
    private static double betAmt;
    public static void StartGameBetOption()
   {
       Main.menuFrame.setVisible(false);
       String message = "Do you want to make a Bet?";
       String title = "Its REAL MONEY";
       String strBet;
       int bet = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
       if(bet == JOptionPane.YES_OPTION)
       {
           Main.frame.setVisible(false); 
           strBet = JOptionPane.showInputDialog ("Enter your Bet Amount: ");//Recieve Inout for Inital Balance as a String
           betAmt = Double.parseDouble(strBet);//Convert the string input into dou
           Main.StartGame(); 
       }
       else
       {
           Main.frame.setVisible(false);
           Main.StartGame();
       }      
   }
    public static void GetGameScore()
   {
           System.out.print(Main.score);
           if(Main.score == 1 || Main.score == 2)
           {
               System.out.println("W");
               Main.ProcessWinnings(Main.score, betAmt);
           }
           else
           {
                              System.out.println("L");
               Main.ProcessLosses(betAmt);
           }
   }
}
