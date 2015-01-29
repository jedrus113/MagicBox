/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Andrzej
 */
public class Process implements Runnable {

    private ProcessManager.ProcessList th = null;
    private final JFrame frame;
    
    public void setT(ProcessManager.ProcessList th){
        if(this.th == null)
            this.th = th;
        else
            throw new IllegalArgumentException();
    }
    
    public void showOnScreen(String s){
        System.out.print(s);
    }
    
    @Override
    public void run() {
        Words line = null;
        char temp;
        while (frame.isVisible()){
            try {
                if(System.in.available() > 0){
                    temp = (char)System.in.read();
                    if(temp == '\n'){
                        if(line != null)
                            try {
                                Interpreter.exeAction(line.first());
                        } catch (UnknownCommandException ex) {
                            showOnScreen("Unknown cmd " + ex.problem.word + " in\n");
                            showOnScreen(ex.problem.toString() + "\n");
                        }
                        line = null;
                    }
                    else if(temp == ' '){
                        if(line != null)
                            line = line.add();
                    }
                    else{
                        if(line == null)
                            line = new Words();
                        
                        line.word += temp;
                        frame.setTitle(line.word);
                    }
                        
                } else
                    sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Machine.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        th.remove();
    }
    
    public Process(String p){
        frame = new JFrame("Kpptko testowe");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300);
    }
    
    
}
