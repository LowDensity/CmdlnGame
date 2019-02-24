/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_ground;

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
        if(arg.trim().matches(cordinatePattern)){
            return "輸入錯誤，請重新輸入";
        }
        String[] cordinate=arg.trim().split("[\\D]");
        int x= Integer.parseInt(cordinate[0])-1;
        int y= Integer.parseInt(cordinate[1])-1;
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
            int rivalPosition=(int)Math.random()*9;
            if(tictoeboard[rivalPosition]==UNASSIGNED_BLOCK){
                tictoeboard[rivalPosition]=RIVAL_TOKEN;
                break;
            }
        }
        if (winner!=UNASSIGNED_BLOCK){return ProcessWinnerMessage(winner);}
        return "您的回合，請輸入要設定的座標，格式為(x,y)，計算從1開始。";
    }
    
    //判斷遊戲是否已經結束，回傳贏家的數字代號。
    //版面已被填滿但沒有勝利者時，回傳-1
    public int GetWinner(){
        int winner=UNASSIGNED_BLOCK;
        //處理橫排
        for(int x=0;x <3 ; x++){
            int current_winner=tictoeboard[x*3];
            for(int x2=0;x2<3;x2++){
                int index=x*3 + x2;
                if (current_winner!=tictoeboard[index]){
                    break;
                }else{current_winner=tictoeboard[index];}
            }
            if (current_winner!=UNASSIGNED_BLOCK){return current_winner;}
        }
        //處理縱列
        
        for(int y=0;y <3 ; y++){
            int current_winner=tictoeboard[y];
            for(int y2=0;y2<3;y2++){
                int index=y+y2*3;
                if (current_winner!=tictoeboard[index]){
                    break;
                }else{current_winner=tictoeboard[index];}
            }
            if (current_winner!=UNASSIGNED_BLOCK){return current_winner;}
        }
        //處理斜線
        
        for(int c=0 ; c<=2; c++){
            int current_winner=tictoeboard[c];
            for(int c2 = 0 ;c2<3;c2*=2){
                int index=(2+c)*c;
                if (tictoeboard[index]!=current_winner){break;}
            }
            if (current_winner!=UNASSIGNED_BLOCK){return current_winner;}
        }
        
        for(int i=0 ;i<tictoeboard.length;i++){
            if(tictoeboard[i]!=UNASSIGNED_BLOCK){return UNASSIGNED_BLOCK;}
        }
        return GAME_FINISHED;
    }
    
    private String ProcessWinnerMessage(int winner){
        switch(winner){
            case PLAYER_TOKEN:return "遊戲結束，玩家獲勝";
            case RIVAL_TOKEN:return "遊戲結束，電腦獲勝";
            case GAME_FINISHED:return "遊戲結束，平手";
            default:return "未知結果，天殺的有BUG!!!!!!!!!!!!!!!!!!!!!!";
        }
    }

    @Override
    public void ProcessSetup() {
        tictoeboard=new int[9];
    }
    
}


