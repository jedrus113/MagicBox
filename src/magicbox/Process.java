 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicbox;

import magicbox.programs.Console;
import magicbox.programs.consols.Snake;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrzej
 */
public class Process extends Stopable implements Runnable {
    private ProcessManager th = null;
    protected final Args arg;
    
    public void setT(ProcessManager th){
        if(this.th == null)
            this.th = th;
        else
            throw new IllegalArgumentException();
    }
    
    
    @Override
    public void run(){
        switch(arg.get().toLowerCase()){
            case "cmd":
                up = new Console(this, arg);
                break;
            case "snake":
                up = new Snake(this, arg);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Unknown program.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    @Override
    public void stop(boolean w){
        if(!isStop){
            super.stop(w);
            th.remove();
        }
    }
    
    public Process(Args cmd){
        arg = cmd;
    }
    
    
}
