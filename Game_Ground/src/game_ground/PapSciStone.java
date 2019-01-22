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
    final int STONE=0;
    final int Paper=1;
    final int Scissor=2;


    
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
            case 0:return "aaa";
            case 1:return "bbb";
            case 2:return "ccc";

        }
        return "";
    }
    
    
    private void SetupGame(){
        GameState=1;
        
    }
    
}
