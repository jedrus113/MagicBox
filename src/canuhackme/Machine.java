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
    
    
    public void newProcess(String name, Args cmds){
        if(name == null)
            name = "new";
        pm.makeProcess(new Process(name, cmds));
    }
    
    public void run(){
        pm.makeProcess(new Process("register", null));
        
        while (!gameOver){
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Machine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // czeka na zamkniecie
        //TODO: Zapisz prace
        System.out.println("Koncze..");
        System.exit(0); //nie ma chuja, jak to się konczy to wszystko sie konczy
    }
    
    public void gameOver(){
        gameOver = true;
    }
    
    public static void main(String... args){
        Machine.mainF.run();
    }
}
