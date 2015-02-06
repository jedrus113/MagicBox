/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme;

import java.awt.FlowLayout;

/**
 *
 * @author Andrzej
 */
public class Process implements Runnable {
    private ProcessManager.ProcessList th = null;
    public MyJFrame frame = null;
    
    public void setT(ProcessManager.ProcessList th){
        if(this.th == null)
            this.th = th;
        else
            throw new IllegalArgumentException();
    }
    
    @Override
    public void run() {
        frame.setVisible(true);
        new Console(this);
    }
    
    public void remove(){
        if(frame.isVisible())
            frame.dispose();
        th.remove();
    }
    
    public Process(String p, Args cmd){
        frame = new MyJFrame(p, this);
        frame.setLayout(new FlowLayout());
    }
    
    
}
