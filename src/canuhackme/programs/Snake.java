/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme.programs;

import canuhackme.Args;
import canuhackme.Stopable;
import canuhackme.UnknownCommandException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Andrzej
 */
public class Snake extends Stopable implements ActionListener, KeyListener {
    Timer timer;
    Console con;
    char gameField[][];
    
    public Snake(Console con){
        down = this.con = con;
        con.giveKeyListener(this);
        try {
            con.exeAction(new Args("cls"));
            con.exeAction(new Args("title Snake"));
        } catch (UnknownCommandException ex) {
            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            con.showOnScreen('o');
            Thread.sleep(1000);
            con.showOnScreen('k');
        } catch (InterruptedException ex) {
            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        timer = new Timer(1000, this);
        timer.start();
        this.con.frame.setResizable(false);
        
        gameField = new char[con.text.getColumns()][con.text.getRows()];
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
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println((int)e.getKeyCode());
        
        if(e.getKeyCode() == 38) {
            //gora

        } else if(e.getKeyCode() == 40){
            //dol
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}
