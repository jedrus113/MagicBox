/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicbox.programs;

import magicbox.programs.consols.Snake;
import magicbox.Args;
import magicbox.Line;
import magicbox.Machine;
import magicbox.MyJFrame;
import magicbox.Stopable;
import magicbox.UnknownCommandException;
import magicbox.programs.consols.ConsoleProgram;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Andrzej
 */
public class Console extends Stopable implements KeyListener {
    public final String prompt = "-> ";
    
    private final JFrame frame;
    private final JTextArea text;
    private KeyListener kl = null;
    
    Line line = null;
    
    /*
        ATTENCION!
        this setting console for consoleProgram
    */
    public Console(ConsoleProgram prog){
        down = prog;
        
        frame = new MyJFrame(this);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle(prog.name);
        frame.addKeyListener(prog);
        
        text = new JTextArea();
        text.setEditable(false);
        text.setRows(20);
        text.setColumns(40);
        //text.setAutoscrolls(true);
        text.setBackground(Color.BLACK);
        text.setForeground(Color.GREEN);
        text.addKeyListener(prog);
        
        frame.add(text);
        frame.pack();
        
    }
    
    /*
        ATTENCION!
        This is starting real console, like with new process.
    */
    public Console(Stopable proc, Args args){
        down = proc;
        
        frame = new MyJFrame(this);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.addKeyListener(this);
        
        text = new JTextArea();
        text.addKeyListener(this);
        text.setEditable(false);
        frame.add(text);
        
        showOnScreen("Loading console.. OK\n\n");
        
        resetConsole();
        
        try {
            if(args != null && !args.end()){
                showOnScreen(args.toStringLeft());
                exeAction(args);
                showOnScreen("\n" + prompt);
            }
        } catch (UnknownCommandException ex) {
            showOnScreen(ex.ProblemMessage() + "\n");
        }
        
    }
    
    public void resetConsole(){
        frame.setTitle("Console");
        frame.setResizable(false);
        text.setRows(20);
        text.setColumns(40);
        text.setAutoscrolls(true);
        text.setBackground(Color.BLACK);
        text.setForeground(Color.GREEN);
        
        showOnScreen( prompt );
        frame.pack();
        
        line = null;
        kl = null;
        
        if(up != null)
            up.stop(FROM);
        up = null;
    }
    
    public void giveKeyListener(KeyListener kl){
        this.kl = kl;
    }
    
    protected boolean amIKeyListener(){
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
        //TODO: trying to know whats on screen in real-time
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
                        showOnScreen(ex.ProblemMessage() + "\n");
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
            if(up != null)
                up.stop(FROM);
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
    
    @Override
    public synchronized void stop(boolean w){
        if(isStop)
            return;
        
        super.stop(w);
        frame.dispose();
    }
    
    public int exeAction(Args arg) throws UnknownCommandException{
        if(arg == null || arg.end())
            return 1;
        
        switch(arg.get().toLowerCase()){
            case "new":
                if(arg.end())
                    Machine.mainF.newProcess(new Args("cmd"));
                else
                    Machine.mainF.newProcess(arg);
                break;
            case "exit":
                stop(ALL);
                break;
            case "snake":
                up = new Snake(this, arg);
                break;
            case "cls":
                text.setText("");
                break;
            case "title":
                if(arg.end())
                    frame.setTitle("Console");
                else
                    frame.setTitle(arg.getArgsOnly().toString());
                break;
            case "shutdown":
                Machine.mainF.stop(ALL);
                break;
            case "wait":
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "getRows":
                return text.getRows();
                //break;
            case "getColumns":
                return text.getColumns();
                //break;
            default:
                throw new UnknownCommandException(arg);
        }
        return 0;
    }
    
}
