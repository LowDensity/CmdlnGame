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
public interface GameMachine {
    public abstract boolean isrunning();
    public abstract String ProcessArguments(String arg);
    public abstract void ProcessSetup();
    
}
