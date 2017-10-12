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
public class TopMenu extends JFrameL
{
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    private JMenuBar MainMenu;
    private JMenu play, rules, quit;
    private JMenuItem openGame, quitGame, howTo;
    Image background;
    public static JTextArea ta;

    public TopMenu(String title)
    {
        super(title);
        setSize(WIDTH, HEIGHT);//How you set the Hieght and Width of JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);//Makes sure User can Exit when they press the X
        setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("StarWars.jpg"));
        add(background);
        background.setLayout(new FlowLayout());
        setVisible(true);
  
        MenuListener mListener = new MenuListener();//Constructs Menu Listener to get Users Actions on Menu
        
        play = new JMenu("Play");
        openGame = new JMenuItem("Open Game");
        openGame.addActionListener(mListener);
        play.add(openGame);
        rules = new JMenu("Rules");
        howTo = new JMenuItem("How To Play");
        howTo.addActionListener(mListener);
        rules.add(howTo);
        quit = new JMenu("Quit");
        quitGame = new JMenuItem("Exit Game");
        quitGame.addActionListener(mListener);
        quit.add(quitGame);
        
        MainMenu = new JMenuBar();
        MainMenu.add(play);
        MainMenu.add(rules);
        MainMenu.add(quit);
        setJMenuBar(MainMenu);
//         setPreferredSize (new Dimension(1920, 1080));
//        JLabel background=new JLabel(new ImageIcon("StarWars"));
//        add(background);
//        background.setLayout(new FlowLayout());
//        setVisible(true);
    }
    private class MenuListener implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent event)
        {
            String source = event.getActionCommand();
            if (source.equals("Open Game"))
            {
                Bets.StartGameBetOption();
            }      
            if (source.equals("How To Play"))
            {
               String  rules  = "In this game you just have to push the button once\n"
                              + "(If not betting then just see if u can match all three)\n"
                              + "and the reel will start spinning and will stop at\n" 
                              + "random times if you math two parts you will get (1.5)\n"
                              + "of your bet but if you match all three parts you will\n"
                              + "get double your bet, But beware if you dont match any\n" 
                              + "you'll lose of your bet.\n"
                              + "\ntMAY THE FORCE BE WITH YOU ";
                JOptionPane.showMessageDialog (null, rules);
            }
            if (source.equals("Exit Game"))
            {
                Sound.StopSound();
                Main.menuFrame.setVisible(false);
                Main.frame.setVisible(true);
            }
        }
    }
    
    
}
