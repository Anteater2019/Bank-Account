/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
/**
 *
 * @author marco
 */
public class Account implements Serializable, Comparable <Account>
{
    protected String nameOfPersonOfAcct;
    protected double balance;
    
    public Account()
    {
        nameOfPersonOfAcct = " ";
        balance = 0.00;
    }
    public Account(String name, double amt)
    {
        this.nameOfPersonOfAcct = name;
        this.balance = amt;
    }
    public double getBalance()
    {
        return this.balance;
    }
    public String getName()
    {
        return  this.nameOfPersonOfAcct;
    }
    public void setName(String name) 
    {
        this.nameOfPersonOfAcct = name;
    }
    public void setBalance(double NewAmt)
    {
          this.balance = NewAmt;
    }
    @Override
    public int compareTo(Account t) 
    {
        int lastCmp=nameOfPersonOfAcct.compareTo(t.nameOfPersonOfAcct);
        return(lastCmp !=0 ? lastCmp : nameOfPersonOfAcct.compareTo(t.nameOfPersonOfAcct));
    }

//    public int compareTo(MyAccount ) 
//    {
//	   String StudentName1 = name1.getName().toUpperCase();
//	   String StudentName2 = name2.getName().toUpperCase();
//	   return StudentName1.compareTo(StudentName2);
//    }

//    public String compareTo(MyAccount compareName) {
//        String comparename=((MyAccount)compareName).getName();
//        /* For Ascending order*/
//        return this.studentage-compareage;
//
//        /* For Descending order do like this */
//        //return compareage-this.studentage;
//    }

         

//    @Override
//    public int compareTo(Object o) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    public int compareTo(MyAccount name) 
//    {
//        //return this.nameOfPersonOfAcct.compareTo(name.getName());
//       if(this.nameOfPersonOfAcct.equals(name.nameOfPersonOfAcct))
//        {
//            return 0;
//        }
//        else 
//            return this.nameOfPersonOfAcct > name.nameOfPersonOfAcct ?1:-1;
//    }
//         MyAccount account = (MyAccount) name1;
//         return account.compareTo(name1.toString());
//    }

//    @Override
//    public int compareTo(Account o) 
//    {
//        return nameOfPersonOfAcct.compareTo(((Account)o).nameOfPersonOfAcct);
////        int comparison = nameOfPersonOfAcct.compareTo(o.nameOfPersonOfAcct);
////        return (comparison!=0 ? comparison : nameOfPersonOfAcct.compareTo(nameOfPersonOfAcct));
//    }
}