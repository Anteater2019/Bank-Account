/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author marco
 */
public class JFrameL extends JFrame 
{
    public JFrameL(String title) 
    {
        super(title);
        FrameListener listener = new FrameListener();
        addWindowListener(listener);
    }
    private class FrameListener extends WindowAdapter
     {

    @Override
    public void windowClosing(WindowEvent e) 
    {
        int confirm;
        if (!Main.saved)
        {
            String message="The data in the application is not saved.\n"+ "Would you like to save it before exiting the program?";
            confirm=JOptionPane.showConfirmDialog(null,message);
            if(confirm==JOptionPane.YES_OPTION)
             Main.writeToFile();
        }
       System.out.println("WindowListener method called: windowClosed.");
       Main.frame.setVisible(false);
       System.exit(0);
     }
    }
}



