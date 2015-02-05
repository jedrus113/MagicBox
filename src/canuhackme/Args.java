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
public class Args {
        private String args[];
        private int p;
        
        public Args(String cmd){
            String magic[] = cmd.split(" ");
            int size = 0;
            
            for (String magic1 : magic) {
                if (!magic1.equals("")) {
                    size++;
                }
            }
            
            args = new String[size];
            p=0;
            
            for (String magic1 : magic) {
                if (!magic1.equals("")) {
                    args[p++] = magic1; 
                }
            }
            p=0;
        }
        
        public String get(){
            return get(p);
        }
        
        public String get(int i){
            if(i < 0)
                throw new IllegalArgumentException();
            else if(i >= args.length)
                return null;
            else
                return args[i];
        }
        
        public int size(){
            return args.length;
        }
        
        public String next(){
            if(p < args.length-1)
                return args[++p];
            else
                return null;
        }
        
        @Override
        public String toString(){
            return args[p];
        }
        
        public String fullText(){
            String r = "";
            
            for(String arg : args)
                r += arg + " ";
            return r;
        }
        
        public Args getArgsOnly(){
            return getArgsFrom(1);
        }
        
        public Args getArgsFrom(int s){
            if(s < 0)
                throw new IllegalArgumentException();
            String arg = "";
            
            for(; s < args.length; s++)
                arg += args[s] + " ";
            
            return new Args(arg);
        }
        
    }