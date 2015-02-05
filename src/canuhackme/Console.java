/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Andrzej
 */
public class Console implements KeyListener {
    public final String prompt = "-> ";
    private final Process proc;
    public final JTextArea text;
    Line line = null;
    
    public Console(Process proc){
        this.proc = proc;
        text = new JTextArea();
        text.setEditable(false);
        text.setBackground(Color.BLACK);
        text.setForeground(Color.GREEN);
        proc.frame.add(text);
        proc.frame.setTitle("Console");
    }
    
    public void showOnScreen(char s){
        if(s == 8){  try {
            //Backpace
            text.setText(text.getText(0, text.getText().length()-1));
            /*
            proc.text.moveCaretPosition(proc.text.getText().length()-1);
            proc.text.replaceSelection("");
            */
            } catch (BadLocationException ex) {
                Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else 
            text.append("" + s);
        //System.out.print(s);
    }
    public void showOnScreen(String s){
        text.append(s);
        //System.out.print(s);
    }
    
    public void read(){
        char temp;
        
        text.addKeyListener(this);
        
        showOnScreen("Loading console.. OK\n");
        showOnScreen(prompt);
        
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
        if(Character.hashCode(e.getKeyChar()) < 65535) {
            if(e.getKeyChar() == 8){
                if(line != null)
                    if(line.removeLast())
                        showOnScreen(e.getKeyChar());
            } else {
                showOnScreen(e.getKeyChar());

                if(e.getKeyChar() == '\n'){
                    if(line != null)
                        try {
                            proc.exeAction(line.args());
                    } catch (UnknownCommandException ex) {
                        showOnScreen("Unknown cmd " + ex.problem + " in\n");
                        showOnScreen(ex.problem.fullText()+ "\n");
                    }
                    line = null;
                    showOnScreen(prompt);
                }
                else{
                    if(line == null)
                        line = new Line();

                    line.addCh(e.getKeyChar());
                }
            }
        } else if(e.getKeyCode() == 38) {
            //gora

        } else if(e.getKeyCode() == 40){
            //dol
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
