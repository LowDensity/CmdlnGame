/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_ground;

import game_ground.message.Message;
import game_ground.message.MessageJSON;
import game_ground.message.MessageXML;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.JDOMException;

/**
 *
 * @author Harry
 */
public class MainGame {

    /**
     * @param args the command line arguments
     */
    private static boolean isrunning=false;
    private static BufferedReader inputReader;
    private static GameMachine game;
    private static Message mainGameMessage;
    public static void main(String[] args) throws IOException {
        isrunning=true;
        try {
            mainGameMessage=new MessageXML("MessageLibs/MainGame.xml");
            inputReader=new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        game=new GameLobby();
            do {
                try{
                    ShowWellcome();
                    ChooseGame();
                    RunGame(); //continue updating until change game
                    if(game==null){
                        ShowThankMessage();
                        break;}
                }
                catch(NoGameFoundException ngfe){
                    System.out.println(mainGameMessage.get_message("GameNonExist"));
                }
            }while(game!=null);    

     
        

    }
    private static void ChooseGame(){
        System.out.print(">");
        try {
            String[] cmds =inputReader.readLine().split(" ");
            game=CreateSelectedGame(cmds[0]);
            game.ProcessSetup();
            //
        } catch (IOException ex) {
            Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    private static GameMachine CreateSelectedGame(String name) throws IOException{
        switch(name){
            case "GN":return new GuessingNum();
            case "PCS":return new PapSciStone();
            case "TIC":return new TicToe();

            default: throw new NoGameFoundException();
        }
    }
    
    
    
    
    private static void RunGame(){
        while(game.isrunning()){
            try {
                System.out.println(game.ProcessArguments(inputReader.readLine()));
            } catch (IOException ex) {
                Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    
    public static void EndCurrentGame(){
        isrunning=false;
    }
    
    
    public static void ShowWellcome(){
        System.out.println(mainGameMessage.get_message("Wellcome"));
    }

    private static void ShowThankMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
