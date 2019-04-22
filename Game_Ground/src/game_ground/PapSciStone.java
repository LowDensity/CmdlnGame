/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_ground;

import game_ground.message.Message;
import game_ground.message.MessageJSON;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Harry
 */
public class PapSciStone implements GameMachine { 
    private int GameId,GameState;//遊戲當下的進行階段。0:初始化、1:進行中、2:已結束
    private int PlayerAction,RivalAction;
    //數字對應到的拳種
    private final int[] RivalActions=new int[]{3,2,1};
    private final int[] PlayerActions=new int[]{1,2,3};
    private final String[] ActionNames=new String[]{"stone","scissor","paper"};
    private Random RivalSrc;
    final int Scissor=0;
    final int Paper=1;
    final int STONE=2;
    private String[] phrases;
    private Message messageConfig;
    
    public PapSciStone() throws IOException{
        GameId=0;
        GameState=0;
        RivalSrc=new Random();
        phrases=new String[2];
        messageConfig=new MessageJSON("MessageLibs/PapSciStone.json");
        
    }
    
    

    @Override
    public boolean isrunning() {
        return true;
    }

    @Override
    public String ProcessArguments(String arg) {
        switch(GameState){
            case 0:return "";
            case 1:return ProcessInput(arg);
            case 2: return "@2222";

        }
        return "";
    }
    
    private String ProcessInput(String input){
                /*
            final int STONE=1;
    final int Paper=2;
    final int Scissor=3;

        */

                try{
                    PlayerAction=Integer.parseInt(input);
                    PlayerAction-=1;
                    ProcessSetup();
                    int Result=PlayerActions[PlayerAction]+ RivalActions[RivalAction];
                    phrases[0]=messageConfig.get_message(ActionNames[PlayerAction]);
                    phrases[1]=messageConfig.get_message(ActionNames[RivalAction]);

                        if(Result==4){
                            return messageConfig.get_message("draw",phrases);/*此局平手，您的動作為:"+ ActionNames[PlayerAction] + "對手的動作為："+ ActionNames[RivalAction]*/}
                        else if(Result>4){
                            if(PlayerAction<RivalAction){return messageConfig.get_message("draw",phrases);/*"您的勝利，您的動作為:"+ ActionNames[PlayerAction]  + "對手的動作為："+ ActionNames[RivalAction];*/}
                            else{return messageConfig.get_message("playerlose",phrases);/*"您輸了，您的動作為:"+ ActionNames[PlayerAction]  + "對手的動作為："+ ActionNames[RivalAction];*/}
                        }else{
                            if(Result==2){return messageConfig.get_message("playerlose",phrases);/*"您輸了，您的動作為:"+ ActionNames[PlayerAction]  + "對手的動作為："+ ActionNames[RivalAction]+"--結果為:"+Result;*/}
                            else{return messageConfig.get_message("playerwin",phrases);/*"您的勝利，您的動作為:"+ ActionNames[PlayerAction]  + "對手的動作為："+ ActionNames[RivalAction];*/}}
                        
                }catch(NumberFormatException nfe){
                    return messageConfig.get_message("inputformatincorrect");//"輸入的數字格式錯誤，請重新輸入。";
                }catch(ArrayIndexOutOfBoundsException AIE){
                    return messageConfig.get_message("inputinvalid");//"請輸入有效的數字。";
                }
    }
    

    @Override
    public void ProcessSetup() {
        GameId+=1;
        GameState=1;
        RivalAction=RivalSrc.nextInt(3);
        System.out.println(messageConfig.get_message("introduction"));
    }

    @Override
    public Message LoadMessageConfigs(String configuration) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message getMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
