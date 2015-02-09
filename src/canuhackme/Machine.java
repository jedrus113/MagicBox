/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme;


/**
 *  main class of this, evert new instance have ti ask this class about enything
 *  TODO: Shoud i set it static?
 * @author Andrzej
 */
public class Machine extends Stopable {
            
    public static final Machine mainF = new Machine();
    public final String mainFileName = "trust_me.hak";
    
    
    public void newProcess(Args cmds){
        ProcessManager.makeProcess(new Process(cmds));
    }
    
    public void run(){
        ProcessManager.makeProcess(new Process(new Args("cmd")));
        
    }
    
    @Override
    public synchronized void stop(boolean w){
        if(isStop)
            return;
        
        super.stop(w);
        
        //TODO: Saving work.. <thinking> or maybe better saving in real time?
        System.out.println("Koncze..");
        System.exit(0); //everything is over when this is over
    }
    
    public static void main(String... args){
        Machine.mainF.run();
    }
}
