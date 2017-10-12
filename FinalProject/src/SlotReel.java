/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;



/**
 *
 * @author marco
 */
public class SlotReel extends JPanel
{
    private final static int WIDTH = 1200, HEIGHT = 1200;
    private int imageNum = 0;
    private final static int DELAY = 5, IMAGE_SIZE = 300,
                             y1 = 150, y2 = 450, y3 = 750;
    private static final String[] imageListT = {"DVT.JPEG",   "R2T.JPEG", "STT.JPEG", "YT.JPEG"};
    private static final String[] imageListM = {"DVM.JPEG", "R2M.JPEG", "STM.jpg", "YM.jpg"};
    private static final String[] imageListB = {"DVB.JPEG", "R2B.JPEG", "STB.jpg", "YB.jpg"};
    private static ImageIcon reelOneT, reelOneM, reelOneB, reelTwoT, reelTwoM, reelTwoB, 
                             reelThreeT, reelThreeM, reelThreeB, reelFourT, reelFourM, reelFourB,
                             playButton, exitButton;
    
    private static Random randDelayCount1, randDelayCount2, randDelayCount3;
    //private static boolean soundFlag = true;
    private Timer timerReel1, timerReel2, timerReel3;
    private static int moveXReel1, moveXReel2, moveXReel3,
                       xT1 = 0, xT2 = 300, xT3 = 600, xT4 = 900,
                       xM1 = 0, xM2 = 300, xM3 = 600, xM4 = 900,
                       xB1 = 0, xB2 = 300, xB3 = 600, xB4 = 900,
                       countReel1, countReel2, countReel3;
    private JRadioButton play , exit;
    Image img;
    public static String url = "file:D:/Computer Science 3/FinalProject/imperial.wav";
    public static boolean check1 = false, check2 = false, check3 = false;
                       
    public SlotReel()
    {
       

        
//        timerReel1 = new Timer(DELAY, new SlotReelListener());
//        timerReel2 = new Timer(DELAY, new SlotReel2Listener());
//        timerReel3 = new Timer(DELAY, new SlotReel3Listener());
        moveXReel1 = 10;
        moveXReel2 = 25;
        moveXReel3 = 50;
        img = Toolkit.getDefaultToolkit().createImage("LightSpeed.jpg");
        setPreferredSize (new Dimension(WIDTH, HEIGHT));
//        timerReel1.start();
        
//        timerReel3.start();
        randDelayCount1 = new Random();
        randDelayCount2 = new Random();
        randDelayCount3 = new Random();
        countReel1 = randDelayCount1.nextInt(1500 + 1) + 1500;
        countReel2 = randDelayCount2.nextInt(1500 + 1) + 1500;
        countReel3 = randDelayCount3.nextInt(1500 + 1) + 1500;
       

       

        reelOneT = new ImageIcon(imageListT[imageNum]);
        reelOneM = new ImageIcon(imageListM[imageNum]);
        reelOneB = new ImageIcon(imageListB[imageNum]);
//        do
//        {
//        imageNum = rand2.nextInt(3) + 0;
//        }while(rand2 == rand);
        imageNum = 1;
        reelTwoT = new ImageIcon(imageListT[imageNum]);
        reelTwoM = new ImageIcon(imageListM[imageNum]);
        reelTwoB = new ImageIcon(imageListB[imageNum]);
//        do
//        {
//        imageNum = rand3.nextInt(3) + 0;
//        }while(rand3 == rand || rand3 == rand2);
        imageNum= 2;
        reelThreeT = new ImageIcon(imageListT[imageNum]);
        reelThreeM = new ImageIcon(imageListM[imageNum]);
        reelThreeB = new ImageIcon(imageListB[imageNum]);
//        do
//        {
//        imageNum = rand4.nextInt(3) + 0;
//        }while(rand4 == rand || rand4 == rand2 || rand4 == rand3);
        imageNum = 3;
        reelFourT = new ImageIcon(imageListT[imageNum]);
        reelFourM = new ImageIcon(imageListM[imageNum]);
        reelFourB = new ImageIcon(imageListB[imageNum]);
        playButton = new ImageIcon("Push.jpg");
        exitButton = new ImageIcon("Exit.png");
        
        play = new JRadioButton(playButton);
        ButtonGroup bg = new ButtonGroup();
        bg.add(play);
        SlotReel1Listener listener1 = new SlotReel1Listener();
        play.addActionListener(listener1);
        SlotReel2Listener listener2 = new SlotReel2Listener();
        play.addActionListener(listener2);
        SlotReel3Listener listener3 = new SlotReel3Listener();
        play.addActionListener(listener3);
        SoundListener listener4 = new SoundListener();
        play.addActionListener(listener4);
        exit = new JRadioButton(exitButton);
        bg.add(exit);
        ExitGameListener exitListener = new ExitGameListener();
        exit.addActionListener(exitListener);
        add(play);
        add(exit);
        setPreferredSize (new Dimension(WIDTH, HEIGHT));
    }
    
    @Override
    public void paintComponent(Graphics page)
    {
//        countReel1 --;
//        countReel2 --;
//        countReel3 --;
        super.paintComponent (page);
        page.drawImage(img, 0, 0, null);
        reelOneT.paintIcon (this, page, xT1, y1);
        reelOneM.paintIcon (this, page, xM1, y2);
        reelOneB.paintIcon (this, page, xB1, y3);
        reelTwoT.paintIcon (this, page, xT2, y1);
        reelTwoM.paintIcon (this, page, xM2, y2);
        reelTwoB.paintIcon (this, page, xB2, y3);
        reelThreeT.paintIcon (this, page, xT3, y1);
        reelThreeM.paintIcon (this, page, xM3, y2);
        reelThreeB.paintIcon (this, page, xB3, y3);
        reelFourT.paintIcon (this, page, xT4, y1);
        reelFourM.paintIcon (this, page, xM4, y2);
        reelFourB.paintIcon (this, page, xB4, y3);
        countReel1 --;
        countReel2 --;
        countReel3 --;
    }  

    private class ExitGameListener implements ActionListener 
    {
        public ExitGameListener(){}
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Object source = e.getSource();
            if (source == exit)
            {
                Main.gameFrame.dispose();
                Sound.StopSound();
                Main.menuFrame.setVisible(true);
            }
        }
        
    }
    private class SlotReel1Listener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Object source = e.getSource();
            if (source == play)
            {
                remove(play);
                timerReel1 = new Timer(DELAY, new Slot1Listener());
                timerReel1.start();
            }
        }
    }
    private class Slot1Listener implements ActionListener
    {
        public Slot1Listener(){}
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            xT1 += moveXReel1;
            xT2 += moveXReel1;
            xT3 += moveXReel1;
            xT4 += moveXReel1;
            if (xT1 >= WIDTH)
            { 
                xT1 = 0;
            }
            if (xT2 >= WIDTH)
            {
                xT2 = 0;
            }
            if (xT3 >= WIDTH)
            {
                xT3 = 0;
            }
            if (xT4 >= WIDTH)
            {
                xT4 = 0;
            }
            repaint();
            if (countReel1 <= 0) 
            {
                if (xT1 == 0 || xT1 == 300|| xT1 == 600 || xT1 == 900)
                {
                   timerReel1.stop();
                   check1 = true;
                   FinishedGameChecker();
                }
            }         
        }
    }
    private class SlotReel2Listener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            Object source = e.getSource();
            if(source == play)
            {
                timerReel2 = new Timer(DELAY, new Slot2Listener());
                timerReel2.start();
            }
        }
    }
    private class Slot2Listener implements ActionListener 
    {
        public Slot2Listener(){}
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            xM1 += moveXReel2;
            xM2 += moveXReel2;
            xM3 += moveXReel2;
            xM4 += moveXReel2;
            if (xM1 >= WIDTH)
            { 
                xM1 = 0;
            }
            if (xM2 >= WIDTH)
            {
                xM2 = 0;
            }
            if (xM3 >= WIDTH)
            {
                xM3 = 0;
            }
            if (xM4 >= WIDTH)
            {
                xM4 = 0;
            }
            repaint();
            if (countReel2 <= 0) 
                {
                    if (xM1 == 0 || xM1 == 300|| xM1 == 600 || xM1 == 900)
                    {
                       timerReel2.stop();
                       check2 = true;
                       FinishedGameChecker();
                    }
                }    
            }
    }
    private class SlotReel3Listener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            Object source = e.getSource();
            if (source == play)
            {
                timerReel3 = new Timer(DELAY, new Slot3Listener());
                timerReel3.start();
            }
        }
    }
    private class Slot3Listener implements ActionListener
    {
        public Slot3Listener(){}
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            xB1 += moveXReel3;
            xB2 += moveXReel3;
            xB3 += moveXReel3;
            xB4 += moveXReel3;
            if (xB1 >= WIDTH)
            { 
                xB1 = 0;
            }
            if (xB2 >= WIDTH)
            {
                xB2 = 0;
            }
            if (xB3 >= WIDTH)
            {
                xB3 = 0;
            }
            if (xB4 >= WIDTH)
            {
                xB4 = 0;
            }
            repaint();
            if (countReel3 <= 0) 
                {
                    if (xB1 == 0 || xB1 == 300|| xB1 == 600 || xB1 == 900)
                    {
                       timerReel3.stop();
                       check3 = true;
                       FinishedGameChecker();
                    }
                }        
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
    public static void FinishedGameChecker()
    {
        if(check1 && check2 && check3)
       {
           ScoreKeeper();
           Bets.GetGameScore();
       }
    }
    public static void ScoreKeeper()
    {
       if(check1 && check2 && check3)
       {
        if(xT1 == xM1 || xM1 == xB1 ||xT1 == xB1)
        {
            Main.score = 1;
            if(xT1 == xM1 && xM1 == xB1)
            {
                Main.score = 2;
            }
        }
        else
        {
            Main.score = 0;
        }
       }
    }
}