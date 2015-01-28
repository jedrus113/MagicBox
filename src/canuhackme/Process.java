/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme;

import javax.swing.JFrame;

/**
 *
 * @author Andrzej
 */
public class Process extends JFrame implements Runnable {

    private ProcessManager.ProcessList th = null;
    
    public void setT(ProcessManager.ProcessList th){
        if(this.th == null)
            this.th = th;
        else
            throw new IllegalArgumentException();
    }
    
    @Override
    public void run() {
        
    }
    
    public Process(String p){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if(th != null)
            th.remove();
    }
    
}
