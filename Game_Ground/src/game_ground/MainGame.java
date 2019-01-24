/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_ground;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static void main(String[] args) throws IOException {
        isrunning=true;
        try {
            inputReader=new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        game=new GameLobby();
        do {
            ShowWellcome();
            ChooseGame();
            RunGame(); //continue updating until change game
            if(game==null){
                ShowThankMessage();
                break;}
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
    
    private static GameMachine CreateSelectedGame(String name){
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
        System.out.println("歡迎來到遊戲中心。請輸入要進行的遊戲名稱。");
        System.out.println("或輸入 list取得目前在系統中的所有遊戲清單。");
    }

    private static void ShowThankMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
