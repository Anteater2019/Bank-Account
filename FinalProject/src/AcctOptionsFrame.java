/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author marco
 */

public class AcctOptionsFrame extends JFrameL
{
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;
    private JMenu file, account, trans, game;
    private JMenuItem readFile, writeFile, addNewAcct, listAcctTrans, listChks, listBet,
            listDeps, listSrvChrgs, listAllAcctNames, findAcct, addTrans, gamble;
    private JMenuBar mainMenu;
    public static String url = "file:D:/Computer Science 3/FinalProject/starwars.wav";
    public AcctOptionsFrame(String title) 
    {
        super(title);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        file = new JMenu("File"); 
        readFile = new JMenuItem("Read File");
        MenuListener mListener = new MenuListener(); 
        readFile.addActionListener(mListener);
        file.add(readFile);
        writeFile = new JMenuItem("Write To File");
        writeFile.addActionListener(mListener);
        file.add(writeFile);
        account = new JMenu("Acount");
        addNewAcct = new JMenuItem("Add New Account");
        addNewAcct.addActionListener(mListener);
        account.add(addNewAcct);
        listAcctTrans = new JMenuItem ("List Accounts Transactions");
        listAcctTrans.addActionListener(mListener);
        account.add(listAcctTrans);
        listChks = new JMenuItem("List All Checks");
        listChks.addActionListener(mListener);
        account.add(listChks);
        listDeps = new JMenuItem("List All Deposits");
        listDeps.addActionListener(mListener);
        account.add(listDeps);
        listSrvChrgs = new JMenuItem("List All Service Charges");
        listSrvChrgs.addActionListener(mListener);
        account.add(listSrvChrgs);
        listBet = new JMenuItem("List All Winnings/Losses");
        listBet.addActionListener(mListener);
        account.add(listBet);
        listAllAcctNames = new JMenuItem("List All Account Names");
        listAllAcctNames.addActionListener(mListener);
        account.add(listAllAcctNames);
        findAcct = new JMenuItem("Find An Account");
        findAcct.addActionListener(mListener);
        account.add(findAcct);
        trans = new JMenu("Transactions");
        addTrans = new JMenuItem("Add Transactions");
        addTrans.addActionListener(mListener);
        trans.add(addTrans);
        game = new JMenu("Gamble");
        gamble = new JMenuItem("Play Game");
        gamble.addActionListener(mListener);
        SoundListener listener = new SoundListener();
        gamble.addActionListener(listener);
        game.add(gamble);
        mainMenu = new JMenuBar();
        mainMenu.add(file);
        mainMenu.add(account);
        mainMenu.add(trans);
        mainMenu.add(game);
        setJMenuBar(mainMenu);
    }

    private class MenuListener implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent event)
        {
            String source = event.getActionCommand();
            if (source.equals("Read File"))
            {
               Main.readFile();
            }
            else if (source.equals("Write To File"))
            {
                Main.writeToFile();
            }
            else if (source.equals("Add New Account"))
            {
                Main.newAccount();
            }
            else if (source.equals("List Accounts Transactions"))
            {
                Main.listAllTrans();
            }        
            else if (source.equals("List All Checks"))
            {
                Main.listAllChecks();
            }
            else if (source.equals("List All Deposits"))
            {
                Main.listAllDeposits();
            }
            else if (source.equals("List All Service Charges"))
            {
                Main.ListAllServiceCharges();
            }
            else if(source.equals("Find An Account"))
            {
                Main.findAccount();
            }
            else if(source.equals("Add Transactions"))
            {
                Main.transaction();
            }
            else if(source.equals("Play Game"))
            {
                Main.MyGame();
            }
            else if(source.equals("List All Winnings/Losses"))
            {
                Main.listAllGambling();
            }
            else
                Main.listAllAccounts();
        }
    }
     private class SoundListener implements ActionListener 
     {
        public SoundListener() 
        {
        }
        @Override
        public void actionPerformed(ActionEvent e) 
        {
             try
        {
              Sound.sound(url);
        }catch (InterruptedException ie) 
        {
        System.out.println(ie);
        }
        }
    }
}
