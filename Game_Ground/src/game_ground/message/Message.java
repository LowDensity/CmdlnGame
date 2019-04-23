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
import java.util.logging.Level;
import java.util.logging.Logger;


/**r
 *
 * @author Harry
 * 用來讀取文件設定的Map，提供大量常數供使用
 * 
 */
public abstract class Message {
    abstract void Setup_Messages(HashMap<String,String> messageLibraryPath);
    abstract HashMap<String,String> loadMessageLibraryFile(String libraryFilePath) throws IOException;
    abstract HashMap<String,String> getMessageLib();
    StringBuilder builder=new StringBuilder(1024);
    //直接取得指定的Message名稱，Key不存在或內容物含有需要付寫的內容而為提供資訊時時丟出錯誤

   
    public String get_message(String key){
        return getMessageLib().get(key);
    }
    
    
    public String get_message(String key,String onlyParam){
        return getMessageLib().get(key)
                .replaceFirst("\\{\\d\\}", onlyParam)
                .replaceAll("\\{\\d\\}", "")
                ;
    }
    
    
    
    //取得指定的Key名稱，
    public String get_message(String key,String[] variables){
        String[] MessagSections=getMessageLib().get(key).split("\\{\\d\\}");
        builder.setLength(0);
        for(int i=0;i<MessagSections.length;i++){
            if(i<variables.length){
                builder.append(MessagSections[i]).append(variables[i]) ;
            }else{
                builder.append(MessagSections[i]);
            }
            
        }
        
        return builder.toString();
    }
    
}


