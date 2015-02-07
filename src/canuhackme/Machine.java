/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme;


/**
 *  main class of this
 * @author Andrzej
 */
public class Machine extends Stopable {
            
    public static final Machine mainF = new Machine();
    public final String mainFileName = "trust_me.hak";
    
    ProcessManager pm = new ProcessManager();
    
    
    public void newProcess(Args cmds){
        pm.makeProcess(new Process(cmds));
    }
    
    public void run(){
        pm.makeProcess(new Process(new Args("cmd")));
        
    }
    
    @Override
    public synchronized void stop(boolean w){
        if(isStop)
            return;
        
        super.stop(w);
        
        //TODO: Zapisz prace
        System.out.println("Koncze..");
        System.exit(0); //nie ma chuja, jak to się konczy to wszystko sie konczy
    }
    
    public static void main(String... args){
        Machine.mainF.run();
    }
}
