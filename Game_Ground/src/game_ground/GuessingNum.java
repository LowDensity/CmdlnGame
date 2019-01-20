/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_ground;

import java.util.HashMap;

/**
 *
 * @author Harry
 */
public class GuessingNum implements GameMachine {
    private HashMap GameMessages=null;
    private int TargetNumber,CurrentUpperBound,CurrentBottomBound;
    private int GameId,GameState;//遊戲當下的進行階段。0:初始化、1:進行中、2:已結束
    private boolean GameSet;
    public GuessingNum(){
        GameMessages=SetupMessages();
        GameId=0;
        GameState=0;
        GameSet=false;
    }
    
    public HashMap SetupMessages(){
        HashMap Messages=new HashMap();
        Messages.put("Wellcome", "你好");
        Messages.put("Instruction", "這是猜數字遊戲，\n系統會隨機產生一個介於1~100的數字。請隨意輸入一個100到0的數字，\n系統會逐漸縮小範圍直到您輸入的數字等於系統隨機產生的數字為止。");
        Messages.put("InputMessage", "您輸入的數字為");
        Messages.put("Congrats", "恭喜您猜中了，您總共花了 <total_count/> 次才猜中。");
        return Messages;
    }
    
    @Override
    public String ProcessArguments(String arg) {
        switch(GameState){
            case 0:
                   ProcessSetup();
                   GameState=1;
                   return PrintChoiceMessage();
            case 1:
                int guess;
                try{
                    guess=Integer.parseInt(arg);
                }catch(NumberFormatException nfe){
                    return "輸入的數字格式錯誤，請重新輸入。";
                }
                
                if(match(guess)){GameState=2;return "恭喜猜中";}
                if(CurrentBottomBound>guess || CurrentUpperBound<guess){return "輸入的數字超出範圍，請重新輸入。";}
                return UpdatedBounds(guess);
            case 2:return ProcessEndGameRequest(arg);
            default :return "NoooooooooMatching Case";
        }
    }
    
    private boolean match(int number){
        return number==TargetNumber;
    }
    
    private String ProcessEndGameRequest(String arg){
        return "ProcessEndGameRequest";
    }
    
    
    private String UpdatedBounds(int number){
        if(number<TargetNumber){
            CurrentBottomBound=number;
        }else{
            CurrentUpperBound=number;
        }
            
    return "您沒猜中，目前的範圍為：  "+ CurrentBottomBound +"  到  "+ CurrentUpperBound;
    }
    
    private void ProcessSetup(){
        TargetNumber=(int)(Math.random()*100 +1);
        CurrentUpperBound=100;
        CurrentBottomBound=1;
        GameId+=1;
    }
    
    private String PrintChoiceMessage(){
        return "遊戲開始，目前的範圍是 "+ CurrentUpperBound + " 到 " + CurrentBottomBound+ "   請輸入您的猜測";  
    }
    
    private void PrintGameMessage(String key){
        System.out.print("S");
    }

    @Override
    public boolean isrunning() {
        return true;
    }
    
}
