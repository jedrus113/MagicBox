/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicbox.programs.consols;

import magicbox.Args;
import magicbox.Stopable;
import magicbox.UnknownCommandException;
import magicbox.programs.Console;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Andrzej
 */
public class Snake extends ConsoleProgram implements ActionListener {
    Timer timer = new Timer(1000, this);
    char gameField[][];
    
    public Snake(Console con, Args arg){
        super(con, "Snake");
        
        try {
            con.exeAction(new Args("cls"));
        } catch (UnknownCommandException ex) {
            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        set();
    }
    public Snake(Stopable s, Args arg){
        super(s, "Snake");
        set();
    }
    
    private void set(){
        timer.start();
        
    }
    
    @Override
    public void stop(boolean w){
        if(isStop)
            return;
        
        timer.stop();
        super.stop(w);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        console.showOnScreen(name+"\n");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println((int)e.getKeyCode());
        
        if(e.getKeyCode() == 38) {
            //gora

        } else if(e.getKeyCode() == 40){
            //dol
            
        } else if(e.getKeyCode() == 37){
            //lewo
            
        }else if(e.getKeyCode() == 39){
            //prawo
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}
