/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme;

import canuhackme.programs.Snake;
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
    public final Process proc;
    public final JTextArea text;
    private KeyListener kl = null;
    private Stopable actTask = null;
    Line line = null;
    
    public Console(Process proc){
        this.proc = proc;
        
        text = new JTextArea();
        text.addKeyListener(this);
        text.setEditable(false);
        proc.frame.add(text);
        showOnScreen("Loading console.. OK\n");
        resetConsole();
    }
    
    public void resetConsole(){
        proc.frame.setTitle("Console");
        proc.frame.setResizable(false);
        text.setRows(20);
        text.setColumns(40);
        text.setAutoscrolls(true);
        text.setBackground(Color.BLACK);
        text.setForeground(Color.GREEN);
        
        showOnScreen("\n" + prompt);
        proc.frame.pack();
        
        line = null;
        actTask = null;
        kl = null;
    }
    
    public void giveKeyListener(KeyListener kl){
        this.kl = kl;
    }
    
    private boolean amIKeyListener(){
        return (kl == null);
    }
    
    public void showOnScreen(char s){
        if(s == 8){  try {
            //Backpace
            text.setText(text.getText(0, text.getText().length()-1));
            } catch (BadLocationException ex) {
                Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
            showOnScreen("" + s);
        //System.out.print(s);
    }
    public void showOnScreen(String s){
        text.append(s);
        //System.out.print(s);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 27)
            return;
        
        if(kl != null){
            kl.keyTyped(e);
            return;
        }
            
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
                            exeAction(line.args());
                    } catch (UnknownCommandException ex) {
                        showOnScreen("Unknown cmd " + ex.problem + " in\n");
                        showOnScreen(ex.problem.fullText()+ "\n");
                    }
                    line = null;
                    if(amIKeyListener())
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
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 27){    //escape
            if(actTask != null)
                actTask.stop();
            resetConsole();
            return;
        }
        else if(kl != null){
            kl.keyPressed(e);
            return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(kl != null){
            kl.keyReleased(e);
            return;
        }
    }
    
    public int exeAction(Args arg) throws UnknownCommandException{
        if(arg == null)
            return 1;
        switch(arg.get().toLowerCase()){
            case "new":
                Machine.mainF.newProcess(arg.getArgsOnly().fullText(), null);
                break;
            case "exit":
                proc.remove();
                break;
            case "snake":
                actTask = new Snake(this);
                break;
            case "cls":
                text.setText("");
                break;
            case "title":
                proc.frame.setTitle(arg.getArgsOnly().fullText());
                break;
            case "wait":
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                throw new UnknownCommandException(arg);
        }
        
        return 0;
    }
    
}
