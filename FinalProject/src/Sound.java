/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.applet.*;
import java.net.*;

/**
 *
 * @author marco
 */
public class Sound 
{
    static AudioClip ac;
    public static void sound(String Url) throws InterruptedException
    {
        try
           {
                ac = Applet.newAudioClip(new URL(Url));
                ac.loop();
           }catch (MalformedURLException murle) 
           {
                System.out.println(murle);
           }
    }
    public static void StopSound()
    {
        ac.stop();
    }
}
