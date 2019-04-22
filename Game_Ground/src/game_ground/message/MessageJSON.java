/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_ground.message;

import java.io.IOException;
import java.util.HashMap;
import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author HAI
 */
public class MessageJSON extends Message{
    HashMap<String,String> messageLibrary;
    
    public MessageJSON(String libraryFilePath) throws IOException{
           messageLibrary=loadMessageLibraryFile(libraryFilePath);
    }
    

    @Override
    void Setup_Messages(HashMap<String, String> messageLibrary) {
        for (String str : messageLibrary.keySet()){
            System.out.println("key = "+str+"   val="+messageLibrary.get(str));
        }
        
    }

    @Override
    HashMap<String, String> loadMessageLibraryFile(String libraryFilePath) throws IOException {
        BufferedReader bfr;
        bfr = new BufferedReader(new FileReader(new File(libraryFilePath)));
        String jsonString="";
        String buffer=bfr.readLine();
        while(buffer!=null){
            jsonString+=buffer;
            buffer=bfr.readLine();
        }
        //System.out.println(jsonString);
        bfr.close();
        return (HashMap<String,String>)JsonReader.jsonToJava(jsonString);
    }
    
    @Override
    HashMap<String, String> getMessageLib() {
        return messageLibrary;
    }
    
}
