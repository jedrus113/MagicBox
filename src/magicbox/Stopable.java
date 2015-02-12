/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicbox;

/**
 *
 * @author Andrzej
 */
public abstract class Stopable {
    public static final boolean FROM = true, ALL = false;
    protected boolean isStop = false;
    protected Stopable up = null, down = null;
    
    public synchronized void stop(boolean w){
        if(isStop)
            return;
        
        isStop = true;
        if(up != null)
            up.stop(w);
        if(down != null && w == ALL)
            down.stop(w);
        
    }
}
