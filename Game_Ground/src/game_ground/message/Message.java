/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_ground.message;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Harry
 * 用來讀取文件設定的Map，提供大量常數供使用
 * 
 */
public abstract class Message {
   static int WellcomeMessage=0;
   static int WellcomeMessage1=0;
   static int WellcomeMessage2=0;
   static int WellcomeMessage3=0;
   private HashMap messages;
    
    public Message(String configurationPath){
        messages=new HashMap();
        BufferedReader configFile;
        FileReader fr;
       try {
           fr=new FileReader(configurationPath);
           configFile=new BufferedReader(fr);
           
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        
    }
    
}

