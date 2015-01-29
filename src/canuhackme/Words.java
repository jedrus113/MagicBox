/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme;

/**
 *
 * @author Andrzej
 */
public class Words {
    public Words next, prev;
    String word;

    public Words first(){
        Words t = this;
        
        while(t.prev != null)
            t = t.prev;
        
        return t;
    }
    
    @Override
    public String toString(){
        Words t = this;
        String ret = t.word;
        
        while(t.next != null){
            t = t.next;
            ret += " " + t.word;
        }
        
        return ret;
    }

    public Words add(String p){
        return next = new Words(p, this, next);
    }
    public Words add(){
        return next = new Words("", this, next);
    }

    public void Words(){
        if(next != null)
            next.prev = prev;
        if(prev != null)
            prev.next = next;

        if(prev == null && next == null)
            Machine.mainF.gameOver();
    }

    public Words(){
        next = null;
        prev = null;

        word = "";
    }
    public Words(String p){
        next = null;
        prev = null;

        word = p;
    }
    public Words(String p, Words prev, Words next){
        this.next = next;
        this.prev = prev;
        word = p;
    }

    
    
    
}
