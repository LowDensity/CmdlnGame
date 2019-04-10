/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_ground;

import game_ground.message.Message;
import java.util.regex.Pattern;

/**
 * 圈圈叉叉遊戲，採用單線陣列
 * @author HAI
 */
public class TicToe implements GameMachine {
    private int GameId,GameState;
    private int[] tictoeboard;
    private static final String cordinatePattern="\\(\\d{1},\\d{1}\\)"; 
    private static final String numberPattern="\\d{1}"; 
    private static final String cordinateSplitPattern="[\\D]"; 
    public static final int GAME_FINISHED=-1;
    public static final int UNASSIGNED_BLOCK=0;
    public static final int PLAYER_TOKEN=1;
    public static final int RIVAL_TOKEN=2;
    
    public TicToe(){
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
            case 0: ProcessSetup();return "";
            case 1: return ProcessInput(arg);
            default:return "Bugs!!!!!";
        }

    }
    
    private String ProcessWinnerMessage(int winner){
        switch(winner){
            case PLAYER_TOKEN:return "遊戲結束，玩家獲勝";
            case RIVAL_TOKEN:return "遊戲結束，電腦獲勝";
            case GAME_FINISHED:return "遊戲結束，平手";
            default:return "未知結果，天殺的有BUG!!!!!!!!!!!!!!!!!!!!!!";
        }
    }
    
    private String ProcessInput(String arg){
        if(!arg.trim().matches(cordinatePattern)){
            return "輸入錯誤，請重新輸入";
        }
        String[] cordinate=arg.split("[\\D]");
        int x= Integer.parseInt(cordinate[1])-1;
        int y= Integer.parseInt(cordinate[2])-1;
        int inputIndex= x*3 +y;
        int currentNumber;
        try{
            currentNumber= tictoeboard[inputIndex];
        }catch(ArrayIndexOutOfBoundsException aioe){
            return "輸入的座標無效，請重新輸入。";
        }
        if (currentNumber!=UNASSIGNED_BLOCK){return "輸入的格子已被使用，請重新輸入。";}
        else{tictoeboard[inputIndex]=PLAYER_TOKEN;}
        int winner=GetWinner();
        if (winner!=UNASSIGNED_BLOCK){return ProcessWinnerMessage(winner);}
        boolean rivalMoved=false;
        while(true){
            
            int rivalPosition=(int)(Math.random()*9);
            System.out.println("rival moving "+ rivalPosition);
            if(tictoeboard[rivalPosition]==UNASSIGNED_BLOCK){
                tictoeboard[rivalPosition]=RIVAL_TOKEN;
                break;
            }
        }
        if (winner!=UNASSIGNED_BLOCK){
            ProcessSetup();
            return ProcessWinnerMessage(winner);
        }
        StringBuilder tictoeBoardStatus=new StringBuilder();
        for(int i =0; i< tictoeboard.length;i++){
            tictoeBoardStatus.append(" "+tictoeboard[i]+" , ");
            if((i+1) %3==0){tictoeBoardStatus.append("\n");}
        }
        return "您的回合，請輸入要設定的座標，格式為(x,y)，計算從1開始。\n 當下盤面為：\n"+tictoeBoardStatus.toString();
    }
    
    //判斷遊戲是否已經結束，回傳贏家的數字代號。
    //版面已被填滿但沒有勝利者時，回傳-1
    public int GetWinner(){
        int winner=UNASSIGNED_BLOCK;
        //三條橫線
        for(int i = 0 ; i<3 ; i++){
            int last_token=tictoeboard[i*3];
            for(int i2 =0 ;i2<3; i2++){
                if (tictoeboard[i*3+i2] != last_token ){
                    last_token=UNASSIGNED_BLOCK;
                    break;
                    }
                }
            if(last_token!=UNASSIGNED_BLOCK){
                winner=last_token;
                break;
            }
        }
 

        for(int y=0;y<3;y++){
            int current_token=tictoeboard[y];
            for(int y2=0;y2<3;y2++){
                if(tictoeboard[y+y2*3] != current_token){
                    current_token=UNASSIGNED_BLOCK;
                    break;
                }
            }
            if(current_token!=UNASSIGNED_BLOCK){
                winner=current_token;
            }
        }


        for(int u=0 ; u < 4; u+=2){
            int current_token_d=tictoeboard[u];
            for(int u2=0;u2<3;u2++){
                if(tictoeboard[u+u2*(4-u)] != current_token_d){
                    current_token_d=UNASSIGNED_BLOCK;
                    break;
                }
            }
            if(current_token_d!=UNASSIGNED_BLOCK){
                winner=current_token_d;
            }
        }
        return winner;
    }

    @Override
    public void ProcessSetup() {
        tictoeboard=new int[9];
        //填滿unassigned_block
        for(int i= 0; i <9;i++){tictoeboard[i]=UNASSIGNED_BLOCK;}
        GameId+=1;
        GameState=1;
        System.out.println("這是圈圈叉叉遊戲，請輸入您的座標(x,y)");
        
        
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


