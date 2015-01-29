/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Andrzej
 */
public class Process implements Runnable {
    static public int instance = 0;
    private ProcessManager.ProcessList th = null;
    public final JFrame frame;
    public final JTextArea text;
    
    public void setT(ProcessManager.ProcessList th){
        if(this.th == null)
            this.th = th;
        else
            throw new IllegalArgumentException();
    }
    
    @Override
    public void run() {
        text.setEditable(false);
        text.setBackground(Color.BLACK);
        text.setForeground(Color.GREEN);
        frame.add(text);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300);
        
        new Console(this).read();
        
        th.remove();
        instance--;
    }
    
    public Process(){
        instance++;
        frame = new JFrame();
        text = new JTextArea();
    }
    public Process(String p){
        instance++;
        frame = new JFrame(p);
        text = new JTextArea();
    }
    
    
}
