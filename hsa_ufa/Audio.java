package hsa_ufa;

/*Program: ICS Culminating 2022 - Geometry Dash
 * 
 * Created by: Peter Meijer
 *
 * Improved by: Miller Downey
 *       
 * For: Donald Corea
 * 
 * Class: Audio Player
 * 
 * This class is the audio player class. It handles the playing, looping, and stopping of audio clips
 * used for music and sound effects. An object of this class is created with a wav file string name inputed
 * the audio is then loaded and the instance methods can be called on the object to update the audio.
 */

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio
{
   private Clip c;
   
   //constructor to create audioplayer based on file name
   public Audio(String fileName) 
   {
      try
      {
         File file = new File(fileName);
         if (file.exists())
         {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            c = AudioSystem.getClip();
            c.open(sound);
                     }
      } 
      catch (Exception e){
         System.err.println("Exception Occurred");
         e.printStackTrace();
      }  
   }
   
   //used for pause() and resume() 
   int pauseFrame = 0;
   
   //method play to play the audio
   public void play()
   {
      c.setFramePosition(0);
      c.start();
   }
   
   //method to set looping of audio to forever
   public void loop()
   {
      c.loop(Clip.LOOP_CONTINUOUSLY);
   }
   
   //method to stop the playing of the clip
   public void stop()
   {
      c.stop();
   }
   
   
   //following methods added by Miller. D
   //method to get the frame location currently then stop
   public void pause()
   {
      pauseFrame = c.getFramePosition();
      c.stop();
   }
   //method to resume from the frame location from the pause method
   public void resume()
   {
      c.setFramePosition(pauseFrame);
      c.start();
   }
}