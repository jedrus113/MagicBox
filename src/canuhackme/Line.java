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
    public String word;
    
    
    
    public Args args(){
        return new Args(word);
    }
            
    
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
            word = w;
        else
            word += w;
    }

    public void addCh(char c){
        if(word == null)
            word = "" + c;
        else
            word += c;
    }

    public boolean removeLast() {
        if(word == null || word.equals(""))
            return false;
        else{
            word = word.substring(0, word.length()-1);
            return true;
        }
    }
}