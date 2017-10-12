/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author marco
 */
public class MyAccount extends Account 
{
   private double totalSerChrg = 0.00;// keep track of balance and the accumalating service charges and also sets it to 0 
   private final ArrayList<Transaction> transactions;
   private int transCounter = 0;
   private int accountCounter = 0;
   private int below500Counter=0; 
   
   public MyAccount(String name, double initBal)
   {
       super(name, initBal);
       this.transactions = new ArrayList<>();
       accountCounter++;
   }
   public double getBelow500Counter()
   {
       return below500Counter;
   }
   public void setBelow500Counter(int counter)
   {
       below500Counter = counter;
   }
   public double getTotalSerChrg()
   {
       return totalSerChrg;//Returns Total Service Charges on the Account when user calls method
   }
   public void processAccountBalance(double transAmt, int transChoice)//add or subtract from balance
   {
       if(transChoice == 1)//check
       {
           this.balance -= transAmt;//subtracts because its a check
       }
       else if(transChoice == 2)//deposit
       {
           this.balance += transAmt;//adds because a deposit
       }
   }
   public void processTotalServiceChrg(double serchrg)//brings all the service charges to this method from main
    {
        totalSerChrg = serchrg;  //This is the Total Serivce Charge for all transactions
    }   
   public void addTransaction(Transaction transactionKeeper)
   {
       this.transactions.add(transactionKeeper);
       transCounter++;
   }
   
   public int getTransCount()
   {
       return transCounter;
   } 
   public Transaction getTrans(int index)
    {
        return this.transactions.get(index);
    }
   public int getAccountCount()
   {
       return accountCounter;
   }
}

//    @Override
//    public int compareTo(Object o) 
//    {    
////        MyAccount a = (MyAccount)o;
////        String name = (a.getName());
////        System.out.println(name);
////        System.out.println(nameOfPersonOfAcct);
//        return this.nameOfPersonOfAcct.compareTo(((MyAccount)o).getName());
////        System.out.println(nameOfPersonOfAcct.compareTo(((Account)o).nameOfPersonOfAcct));
////        return nameOfPersonOfAcct.compareTo(((Account)o).nameOfPersonOfAcct);
//    }
//}

//    public static Comparator<MyAccount> AcctNameComparator = new Comparator<MyAccount>()
//    {
//        @Override
//        public int compare(MyAccount n1, MyAccount n2) 
//        {    
//	   String AccountName1 = n1.getName().toUpperCase();
//	   String AccountName2 = n2.getName().toUpperCase();
//
//	   //ascending order
//	   return AccountName1.compareTo(AccountName2);
//
//	   //descending order
//	   //return StudentName2.compareTo(StudentName1);
//        }
//    };   

//    @Override
//    public int compareTo(MyAccount o) 
//    {
//        return (this.nameOfPersonOfAcct.equals(o.getName())) ? 0: -1;
//    }
