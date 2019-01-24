/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_ground;

/**
 *
 * @author Harry
 */
public class PapSciStone implements GameMachine { 
    private int GameId,GameState;//遊戲當下的進行階段。0:初始化、1:進行中、2:已結束
    private int PlayerAction,RivalAction;
    //數字對應到的拳種
    final int STONE=1;
    final int Paper=2;
    final int Scissor=3;


    
    public PapSciStone(){
        GameId=0;
        GameState=0;
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
                    int Result=PlayerAction % RivalAction; 
                    ProcessSetup();
                        if(Result==0 ||Result==PlayerAction){
                            return "此局平手，您的動作為:"+ PlayerAction + "對手的動作為："+ RivalAction+"--結果為:"+Result;
                        }else if(Result>PlayerAction){
                            return "您輸了，您的動作為:"+ PlayerAction + "對手的動作為："+ RivalAction+"--結果為:"+Result;
                        }else if(Result<PlayerAction){
                            return "您的勝利，您的動作為:"+ PlayerAction + "對手的動作為："+ RivalAction+"--結果為:"+Result;
                        }else{
                               return "未知的輸入，請重新輸入";
                                }
 
                }catch(NumberFormatException nfe){
                    return "輸入的數字格式錯誤，請重新輸入。";
                }
    }
    

    @Override
    public void ProcessSetup() {
        GameId+=1;
        GameState=1;
        RivalAction=(int)(2 * Math.random())+1;
        System.out.println("這是剪刀石頭布遊戲 ，輸入 1代表石頭，2代表剪刀，3代表布。電腦會隨機選擇一種拳，依照剪刀>布>石頭>剪刀的規則進行。");
    }
    
}
