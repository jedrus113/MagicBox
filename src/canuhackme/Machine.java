/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *  main class of this
 * @author Andrzej
 */
public class Machine {
            
    public static final Machine mainF = new Machine();
    public final String mainFileName = "trust_me.hak";
    
    ProcessManager pm = new ProcessManager();
    boolean gameOver = false;
    
    public void newProcess(){
        pm.makeProcess(new Process());
    }
    
    public void run(){
        pm.makeProcess(new Process("register"));
        
        while (!gameOver){
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Machine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // czeka na zamkniecie
        //TODO: Zapisz prace
    }
    
    public void gameOver(){
        gameOver = true;
    }
    
    public static void main(String... args){
        Machine.mainF.run();
    }
}
