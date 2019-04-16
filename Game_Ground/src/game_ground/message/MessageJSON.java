/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_ground.message;

import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author HAI
 */
public class MessageJSON extends Message{
    HashMap<String,String> messageLibrary;
    
    public MessageJSON(String libraryFilePath) throws IOException{
           messageLibrary=loadMessageLibraryFile(libraryFilePath);
           Setup_Messages(messageLibrary);
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
