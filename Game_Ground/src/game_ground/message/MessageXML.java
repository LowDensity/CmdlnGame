/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_ground.message;

import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.*;
import org.jdom2.input.*;
/**
 *
 * @author HAI
 * 取得XML格式的Message檔案
 */
public class MessageXML extends Message{
    private HashMap<String,String> messageLibrary;
    public MessageXML(String messageLibraryPath) throws JDOMException, IOException{
           messageLibrary=loadMessageLibraryFile(messageLibraryPath);
           Setup_Messages(messageLibrary);
    }
    
    @Override
    void Setup_Messages(HashMap<String, String> messageLibraryPath) {
        
    }

    @Override
    HashMap<String, String> loadMessageLibraryFile(String libraryFilePath) throws IOException {
        HashMap<String,String> messagelib=new HashMap<String,String>();
        Document messageFile;
        try {
            messageFile = new SAXBuilder().build(new File(libraryFilePath));
        } catch (JDOMException ex) {
            Logger.getLogger(MessageXML.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException();
        }
           for (Element ele :  messageFile.getRootElement().getChildren("Message")){
               System.out.println(ele.getAttribute("name").getValue()+"  |  "+ele.getText());
               messagelib.put(ele.getAttribute("name").getValue(), ele.getText());
           }
           return messagelib;
    }
    
    
    
}

