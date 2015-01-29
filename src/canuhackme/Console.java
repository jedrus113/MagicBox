/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Andrzej
 */
public class Console implements KeyListener {
    public final String prompt = "-> ";
    private final Process proc;
    Line line = null;
    
    public Console(Process proc){
        this.proc = proc;
    }
    
    public void showOnScreen(String s){
        proc.text.append(s);
        //System.out.print(s);
    }
    
    public void read(){
        char temp;
        
        proc.text.addKeyListener(this);
        
        showOnScreen("Loading console.. OK\n");
        showOnScreen(prompt);
        
    }
    
    int exeAction(Queue cmd) throws UnknownCommandException{
        switch((String)cmd.item){
            case "new":
                Machine.mainF.newProcess();
                break;
            default:
                throw new UnknownCommandException(cmd);
        }
        
        
        return 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(Character.hashCode(e.getKeyChar()) < 65535) {
            showOnScreen("" + e.getKeyChar());
            
            if(e.getKeyChar() == '\n'){
                if(line != null)
                    try {
                        exeAction(line.word.first());
                } catch (UnknownCommandException ex) {
                    showOnScreen("Unknown cmd " + ex.problem.item + " in\n");
                    showOnScreen(ex.problem.toString() + "\n");
                }
                line = null;
                showOnScreen(prompt);
            }
            else if(e.getKeyChar() == ' '){
                if(line != null && line.word.item.length() > 0)
                    line.nextWord();
            }
            else{
                if(line == null)
                    line = new Line();

                line.addCh(e.getKeyChar());
            }
        } else if(e.getKeyCode() == 38) {
            //gora

        } else if(e.getKeyCode() == 40){
            //dol
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
