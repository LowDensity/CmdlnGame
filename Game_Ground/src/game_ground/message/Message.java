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
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;


/**r
 *
 * @author Harry
 * 用來讀取文件設定的Map，提供大量常數供使用
 * 
 */
public abstract class Message {
   private final String WellcomeMessage;
   private final String YouWinMessage;
   private final String YoutLoseMessage;
   private final String YourTurnMessage;
   private final String HelpMessage;
   
    
    public Message(HashMap<String,String> messageLibrary){
       WellcomeMessage=messageLibrary.get("Wellcom");
       YouWinMessage=messageLibrary.get("Wellcom");
       YoutLoseMessage=messageLibrary.get("Wellcom");
       YourTurnMessage=messageLibrary.get("Wellcom");
       HelpMessage=messageLibrary.get("Wellcom");
    }
    
}


