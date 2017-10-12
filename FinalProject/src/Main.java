/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.text.NumberFormat; 
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.io.*;
import java.util.Collections;
import java.util.Vector;
/**
 *
 * @author marco
 */
public class Main
{
  static double serChrg=0.00;
  static MyAccount account;
  static Checks myChecks;
  static Deposits myDeposits;
  static ServiceCharge mySrvChrgs;
  static Gambling myGamblings;
  static NumberFormat fmt1 = NumberFormat.getCurrencyInstance();
  public static AcctOptionsFrame frame;
  static int transChoice;
  static final int checkID =1;
  static final int depositID =2;
  static final int srvChrgID = 3;
  static final int lossID = 4;
  static final int winningID = 5;        
  public static String fileName = "C:\\Users\\marco\\Documents\\Account.data";
  public static boolean saved = true;
  public static JTextArea tArea;
  public static Vector<MyAccount>manyAccounts;
  public static int index=-1;
  public static TopMenu menuFrame;
  public static JFrame gameFrame;
  public static int score;
  
  public static void main(String[] args)
  {      
      manyAccounts = new Vector<MyAccount>();
      frame = new AcctOptionsFrame ("Account Options");
      frame.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
      tArea = new JTextArea(10,50);
      tArea.setFont(new Font("Monospaced",Font.PLAIN, 12));
      frame.getContentPane().add (tArea);
      frame.pack();
      frame.setVisible(true); 
    }
  public static void transaction()
   { 
      double transAmt;
      transChoice = getTransChoice();
      serChrg = account.getTotalSerChrg();
      if(transChoice == 1)//Transaction is a Check
            {
                double chkchrg = 0.15;
                String chkNumStr;
                chkNumStr = JOptionPane.showInputDialog ("Enter the Check Number: ");
                int chkNum = Integer.parseInt(chkNumStr);
                transAmt = getTransAmount();// uses getTransAmount() method to get transaction code from user for a Check
                serChrg += chkchrg;//adds Service Charge of 15 cents for each Check Transaction made by user
                myChecks = new Checks(account.getTransCount(),checkID, transAmt,  chkNum);
                account.addTransaction(myChecks);
                mySrvChrgs = new ServiceCharge(account.getTransCount(),srvChrgID, chkchrg, myChecks.getTransNum());
                account.addTransaction(mySrvChrgs);
                processCheck(transAmt, transChoice);//Sends Transaction Amount and Transaction Code to processCheck method 
                manyAccounts.equals(account);
            }
      else if(transChoice == 2)//Transaction is a Deposit
      {
          double depositchrg = 0.10;
          String cashAmount = "", checkAmount = "";
          double getCash, getCheck;
          JTextField Cash = new JTextField("");
          JTextField Checks = new JTextField("");
          JPanel panel = new JPanel(new GridLayout(0,1));
          panel.add(new JLabel("Cash"));
          panel.add(Cash);
          panel.add(new JLabel("Checks"));
          panel.add(Checks);
          Cash.addAncestorListener(new SetFocus());
          int result = JOptionPane.showConfirmDialog(null, panel, "Deposit",
          JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
          if (result == JOptionPane.OK_OPTION)
          {
              cashAmount = Cash.getText();
              checkAmount= Checks.getText();
          }
          else 
          {
              System.out.println("Cancelled");
              System.exit(0);
          }
          getCash = Double.parseDouble(cashAmount);
          getCheck=Double.parseDouble(checkAmount);
          transAmt = getCash + getCheck;
          serChrg += depositchrg;//adds Service Charge of 10 cents for each Deposit Transaction made by user
          myDeposits = new Deposits(account.getTransCount(),depositID, getCash , getCheck, transAmt);
          account.addTransaction(myDeposits);//adds the new transaction object to the transaction arraylist
          mySrvChrgs = new ServiceCharge(account.getTransCount(),srvChrgID, depositchrg, myDeposits.getTransNum());
          account.addTransaction(mySrvChrgs);
          processDeposit(transAmt,transChoice,getCash,getCheck);//Sends Transaction Amount and Transaction Code to processCheck method
          manyAccounts.equals(account);
      }
    }
   public static int getTransChoice()//method used for getting the Transaction Code from user
   {
       String strTransCode = "", message = "";//String to accept string input from user for Transaction Code
       strTransCode = JOptionPane.showInputDialog("Enter Transaction Code ('1' for checks; '2' for deposits; '0' to end this session): ");//Ask and accepts the users Transaction Choice and initialize the String
       transChoice = Integer.parseInt(strTransCode);//converts the string to int and intitializes the int for Transaction Choice
       if(transChoice == 0)
       {
           message += "Transaction: End" + "\n"
              +  "Current Balance: " + fmt1.format(account.getBalance()) + "\n"
              +  "Total Service Charge: " + fmt1.format(account.getTotalSerChrg()) + "\n"
              +  "Final Balance: " + fmt1.format(account.getBalance()- account.getTotalSerChrg());//Message user will see once Transaction code = 0
      JOptionPane.showMessageDialog(null,message);//Command for Showing Output
       }
       return transChoice;//Returns the choice back
   }
   public static double getTransAmount()//method used for getting Transaction Ammount From user
   {
       String strTransAmt;//String to Accept string input from user for Tranaction Amount
       double transAmount;//Double that will accept the conversion from the string input
       strTransAmt=JOptionPane.showInputDialog("Enter Transaction Amount: ");//ask and accpets the users Transaction AMount and initializes the string
       transAmount=Double.parseDouble(strTransAmt);//converts the string to a double and initializes the double for Transaction Amount
       return transAmount;//Returns the Amount Back
   }
   public static void processCheck(double transAmt, int transChoice)//Method for Processing each Check Transaction then Displaying a message about each Transaction
   {
       String message = "";//string variable for a message
       account.processAccountBalance(transAmt, transChoice);//method that uses the choice to decide if the transAmt will either be added or subtracted from the balance
       message += account.getName() + "'s account-" + "\n"
               + "Transaction: Check #" + myChecks.getCheckNumber() +  " in amount of "+fmt1.format(transAmt)+ "\n"
               +  "Current Balance: "+fmt1.format(account.getBalance())+ "\n"
               +  "Service Charge: Check --- charge $0.15" + "\n";//Message displayed for each Check Transaction
       if(account.getBalance() < 500 && account.getBelow500Counter() == 0)
       {
            double below500chrg = 5.00;
            serChrg +=below500chrg;
//            serviceChargeKeeper(5.00);
            account.setBelow500Counter(1);
            message += "Service Charge: Below $500.00 --- charge $5.00" + "\n";
            mySrvChrgs = new ServiceCharge(account.getTransCount(),srvChrgID, below500chrg, myChecks.getTransNum());
            account.addTransaction(mySrvChrgs);
       }//If Statement that will ass this message if Account Balance below 500 and also charge them 5 bucks(added to serChrg) but only once
       if(account.getBalance() < 50)
       {
            message += "Warning: Balance below $50.00" + "\n";
       }//If statement that will add this message if Acount Balance below 50
       if (account.getBalance() < 0)
       {   
            double below0chrg = 10.00;
            serChrg += below0chrg;
            message += "Service Charge: Below $0.00 --- charge $10.00" + "\n";
            mySrvChrgs = new ServiceCharge(account.getTransCount(),srvChrgID, below0chrg, myChecks.getTransNum());
            account.addTransaction(mySrvChrgs);
       }//If Statement that will charge them 10 dollars(added to serChrg) and add a message about the charge
       account.processTotalServiceChrg(serChrg);//This method will send the Service charges that just happened to the main one that keeps track of the total Service Charges
       message += "Total Service Charge: " + fmt1.format(account.getTotalSerChrg()) + "\n";// adds total Service Charge to message and gets Total Service Charges by the method
       JOptionPane.showMessageDialog(null,message);//Displays the Message
       frame.setVisible(true);
   }
    public static void processDeposit(double transAmt, int transChoice, double cashAmt, double chkAmt)//Method for Processing each Deposit Transaction then Displaying a message about each Transaction
   {
       String message="";//string variable for a message
       account.processAccountBalance(transAmt, transChoice);//method that uses the choice to decide if the transAmt will either be added or subtracted from the balance
       message += "Transaction: Deposit in amount of " + fmt1.format(transAmt)+ "\n";
       message += "Current Balance: "+ fmt1.format(account.getBalance()) + "\n";
       message += "Service Charge: Deposit --- charge $0.10" + "\n";//message displayed for each Deposit Transaction
       account.processTotalServiceChrg(serChrg);//This method will send the Service Charges to method in MyAccount
       message += "Total Service Charge: " + fmt1.format(account.getTotalSerChrg()) + "\n";//adds total service charge to message
       JOptionPane.showMessageDialog(null,message);//displays message
       frame.setVisible(true);
   }
   public static void listAllTrans()
   {
         frame.setVisible(false);
         account = manyAccounts.elementAt(index);
         String message = "";
         message+="\nList All Transactions: "
                 + "\nName: " + account.getName();
         message+="\nID      Type            Amount\n";
         for (int i = 0; i <account.getTransCount(); i++) 
         {
             String Type="";
             if(account.getTrans(i).getTransId() == checkID)
             {
                 Type = "Check ";
             }
             else if(account.getTrans(i).getTransId()== depositID)
                 Type="Deposit";
             else if(account.getTrans(i).getTransId()== lossID)
                 Type="Losses";
             else if(account.getTrans(i).getTransId() == winningID)
                 Type="Winnings";
             else
                 Type="Svc. Chrg.";
                 
             message +=String.format("%-2d      %-11s    %7s\n",account.getTrans(i).getTransNum(),Type,
                       fmt1.format(account.getTrans(i).getTransAmt()));
             
         }
         System.out.println(message);
         tArea.setText(message); 
         frame.setVisible(true);
    }
   public static void listAllChecks()
     {
         frame.setVisible(false);
         
         String message = "";
         account = (MyAccount) manyAccounts.elementAt(index);
         message+="Listing all Checks for " + account.getName() + "\n\n";
         message+="ID    Check        Amount\n";
         for ( int i=0; i < account.getTransCount();i++) 
         {
             if(account.getTrans(i).getTransId()== checkID)
             {
               Checks c =(Checks)account.getTrans(i);
               message +=String.format("%-2d    %-5d        %7s\n",account.getTrans(i).getTransNum(),c.getCheckNumber(),
                       fmt1.format(account.getTrans(i).getTransAmt()));
             } 
         }
         tArea.setText(message);
         frame.setVisible(true);
     }
   public static void listAllDeposits()
     {
         frame.setVisible(false);
         String message = "";
         account = (MyAccount) manyAccounts.elementAt(index);
         message+="Listing all Deposits for " + account.getName() + "\n\n";
         message+="ID       Type      Checks      Cash          Amount\n";
         for ( int i=0; i < account.getTransCount();i++) 
         {
             String Type = "";
             if(account.getTrans(i).getTransId()== depositID)
             {
                Type = "Deposit";
                Deposits d =(Deposits)account.getTrans(i);
                message +=String.format("%-2d       %-7s   %7s   %8s       %7s\n",account.getTrans(i).getTransNum(),Type,fmt1.format(d.getChkAmt()), fmt1.format(d.getCashAmt()),
                         fmt1.format( account.getTrans(index).getTransAmt()));
             }
             
         }
         tArea.setText(message);
         frame.setVisible(true);
     }
   public static void ListAllServiceCharges()
   {
         frame.setVisible(false);
       String message = "";
       account = (MyAccount) manyAccounts.elementAt(index);
       
       message += "Listing all Service Charges for " + account.getName() + "\n\n";
       message += "Trans      Associated\n"
               +  "Number     TransNumber         Amount\n";
       for ( int i = 0; i < account.getTransCount();i++)
       {
           if(account.getTrans(i).getTransId()== srvChrgID)
           {
               ServiceCharge sc = (ServiceCharge)account.getTrans(i);
               message +=String.format("%-2d         %5d              %7s\n",account.getTrans(i).getTransNum(),sc.getAssociatedTransNum(),
                       fmt1.format(account.getTrans(i).getTransAmt()));
           }
       }
       tArea.setText(message);
       frame.setVisible(true);
   }
   public static void readFile()
   {
        int confirm;  
        if (!saved)
        {
           String  message = "The data in the application is not saved.\n"+
               "would you like to save it before reading the new file in?";
           confirm = JOptionPane.showConfirmDialog (null, message);
           if (confirm == JOptionPane.YES_OPTION)
           {
              chooseFile(2);
           }
        }
        chooseFile(1);
        try
	{
            String name = "";
            double initBal = 0;
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            account = new MyAccount(name,initBal);
            System.out.println("Reading File.....");
            manyAccounts = (Vector<MyAccount>)in.readObject();        
            in.close();
            System.out.println("Done Reading File");
            saved = false;
            index = 0;
        }				
        catch(ClassNotFoundException | IOException e )
        {         
            System.out.println(e);
        }
    }	
   public static void writeToFile()
   {
        chooseFile(2);
        try
        {
            FileOutputStream fos = new FileOutputStream(fileName);
            try (ObjectOutputStream out = new ObjectOutputStream(fos)) 
            {
                out.writeObject(manyAccounts);
                out.close();
                saved = true;
            }  
        }
        catch (IOException e) 
                 { 
                     System.out.println(e);
                 }
   }
     public static void chooseFile(int ioOption) 
   {  
      int status, confirm;       
                
      String  message = "Would you like to use the current default file: \n" + fileName;
      confirm = JOptionPane.showConfirmDialog (null, message);
      if (confirm == JOptionPane.YES_OPTION)
          return;
      JFileChooser chooser = new JFileChooser();
      if (ioOption == 1)
          status = chooser.showOpenDialog (null);
      else
          status = chooser.showSaveDialog (null);
      if (status == JFileChooser.APPROVE_OPTION)
      {
          File file = chooser.getSelectedFile();
          fileName = file.getPath();
      }
   }
   public static void findAccount()
   {  
        String name, message = "";
        name = JOptionPane.showInputDialog ("Enter the Account's name: ");
        Collections.sort(manyAccounts);
        int i = Collections.binarySearch(manyAccounts, new Account(name,0));
        if (i >=0)
        {
            index = i;
            account = manyAccounts.elementAt(i);
            message = "Found account for " + name; 
        }
        if(i <= 0)
         {
            message = "The Account for " + name + " was not found";
         }
        tArea.setText(message);         
   }  
   public static void newAccount()
   {
       String strInitialBal, name; //String to Recieve Initial Balance and the Name of Account 
       double initBal, transAmt; //doubles to accept the tranfer of data type from the string input  
       name = JOptionPane.showInputDialog ("Enter the Account Name: ");
       strInitialBal = JOptionPane.showInputDialog ("Enter your Initial Balance: ");//Recieve Inout for Inital Balance as a String
       initBal = Double.parseDouble(strInitialBal);//Convert the string input into double and Initialize initBal with that double 
       account = new MyAccount(name,initBal);//send Initial Balance to My Account Class
       manyAccounts.addElement(account);
       index++;
       saved = false;
   }
   public static void listAllAccounts()
   {
       String message = "";
       Collections.sort(manyAccounts);
       message = "\nList Of Names Of All Accounts: \n"
               + "Number      Name                     Balance\n";
       for(int i=0; i != manyAccounts.size();i++)
       {
           account = manyAccounts.elementAt(i);
           message += String.format(" %1d          %-16s     %9s",i,
                   account.getName(),account.getBalance())
                   + "\n";
       }
       tArea.setText(message);
   }
   public static void listAllGambling()
   {
         frame.setVisible(false);
         account = manyAccounts.elementAt(index);
         String message = "";
         message+="\nList All Gambling Transactions: "
                 + "\nName: " + account.getName();
         message+="\nID         Type                Amount\n";
         for (int i = 0; i <account.getTransCount(); i++) 
         {
             String type = "";
             if(account.getTrans(i).getTransId()== lossID)
             {
                type = "Losses";
                message +=String.format("%-2d         %-4s              %7s\n",account.getTrans(i).getTransNum(),type,
                          fmt1.format(account.getTrans(i).getTransAmt()));
             }
             if(account.getTrans(i).getTransId()== winningID)
             {
                type= "Winnings";
                message +=String.format("%-2d      %-11s    %7s\n",account.getTrans(i).getTransNum(),type,
                          fmt1.format(account.getTrans(i).getTransAmt()));
             }
         }
         System.out.println(message);
         tArea.setText(message); 
         frame.setVisible(true);
   }
   public static void MyGame()
   {
        frame.setVisible(false);
        menuFrame = new TopMenu ("Star Wars Slots"); 
        menuFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        menuFrame.getContentPane();
        menuFrame.pack();
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
   }
   public static void StartGame() 
    {
        menuFrame.setVisible(false);
        Sound.StopSound();
        gameFrame = new JFrame("May The Force Be With You");
        gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        gameFrame.getContentPane().add (new SlotReel());
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);   
    }
   public static void ProcessLosses(double betAmt)
   {
       double gameLosses = betAmt;
       transChoice = 1;
       myGamblings = new Gambling(account.getTransCount(),lossID, gameLosses);
       account.addTransaction(myGamblings);
       account.processAccountBalance(gameLosses, transChoice);
       manyAccounts.equals(account);   
   }
   public static void ProcessWinnings(int score, double betAmt)
   {
       double gameWinnings = 0;
       transChoice = 2;
       if(score == 1)
       {
           gameWinnings = (betAmt * 1.5);
       }
       if(score == 2)
       {
           gameWinnings = (betAmt * 2);
       }
       myGamblings = new Gambling(account.getTransCount(),winningID, gameWinnings);
       account.addTransaction(myGamblings);
       account.processAccountBalance(gameWinnings, transChoice);
       manyAccounts.equals(account);
   }
   private static class SetFocus implements AncestorListener 
   {
     @Override
     public void ancestorAdded(AncestorEvent e)
     {
        JComponent component = e.getComponent();
        component.requestFocusInWindow();
     }
     @Override
     public void ancestorMoved(AncestorEvent e) 
     {
     }
     @Override
     public void ancestorRemoved(AncestorEvent e) 
     { 
     }  
   }
}