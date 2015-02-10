/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme.programs.consols;

import canuhackme.Args;
import canuhackme.Stopable;
import canuhackme.UnknownCommandException;
import canuhackme.programs.Console;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrzej
 */
public abstract class ConsoleProgram extends Stopable implements KeyListener{
    protected Console console;
    public final String name;
    
    public ConsoleProgram(Console con, String newName){
        down = console = con;
        name = newName;
        
        
        try {
            con.exeAction(new Args("title " + name));
        } catch (UnknownCommandException ex) {
            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        con.giveKeyListener(this);
    }
    
    public ConsoleProgram(Stopable proces, String newName){
        name = newName;
        down = proces;
        up = console = new Console(this);
    }

}
