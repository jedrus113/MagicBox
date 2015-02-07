/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme;

import canuhackme.programs.Console;

/**
 *
 * @author Andrzej
 */
public class Process extends Stopable implements Runnable {
    private ProcessManager.ProcessList th = null;
    protected final Args arg;
    
    public void setT(ProcessManager.ProcessList th){
        if(this.th == null)
            this.th = th;
        else
            throw new IllegalArgumentException();
    }
    
    @Override
    public void run() {
        if(arg == null)
            new UnknownCommandException(arg).showErrorMassage();
        
        switch(arg.get(0)){
            case "cmd":
                up = new Console(this, arg.getArgsOnly());
                break;
            default:
                new UnknownCommandException(arg).showErrorMassage();
                stop(ALL);
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
        if(cmd == null)
            arg = new Args("cmd");
        else
            arg = cmd;
    }
    
    
}
