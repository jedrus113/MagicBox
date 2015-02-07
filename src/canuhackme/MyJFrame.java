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
public class MyJFrame extends JFrame {
    Stopable proc;
    
    public MyJFrame(Stopable proc){
        super();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 300);
        
        this.proc = proc;
    }
    
    @Override
    public void dispose(){
        super.dispose();
        proc.stop(Stopable.ALL);
    }
}
