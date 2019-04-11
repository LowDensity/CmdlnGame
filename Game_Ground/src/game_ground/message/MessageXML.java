/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_ground.message;

import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import org.jdom2.*;
import org.jdom2.input.*;
/**
 *
 * @author HAI
 * 取得XML格式的Message檔案
 */
public class MessageXML extends Message{

    public MessageXML(String messageLibraryPath) throws JDOMException, IOException{
           Document messageFile;
           messageFile=new SAXBuilder().build(new File(messageLibraryPath));

    }
    
    @Override
    void Setup_Messages(HashMap<String, String> messageLibraryPath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    HashMap<String, String> loadMessageLibraryFile(String libraryFilePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

