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
public class Line {
    private Line next, prev;
    public Queue<String> word;
    
    public Line(Line p, Line n){
        next = n;
        prev = p;
        word = null;
    }
    public Line(){
        next = null;
        prev = null;
        word = null;
    }
    
    public Line nextLine(){
        return next = new Line(this, next);
    }
    
    public void add(String w){
        if(word == null)
            word = new Queue<String>(w);
        else
            word = word.add(w);
    }

    public void addCh(char c){
        if(word == null)
            word = new Queue<String>("" + c);
        else
            word.item += c;
    }
    
    public void nextWord(){
        add("");
    }
}