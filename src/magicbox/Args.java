/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicbox;

/**
 * nieda sie utworyc instancji klasy arg bez argumentow
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
            
            if(size == 0)
                throw new IllegalArgumentException();
            
            args = new String[size];
            p=0;
            
            for (String magic1 : magic) {
                if (!magic1.equals("")) {
                    args[p++] = magic1; 
                }
            }
            p=0;
        }
        
        public boolean end(){
            return p >= size();
        }
        
        public String get(){
            return get(p++);
        }
        
        public String peek(){
            return get(p);
        }
        public String peekLast(){
            return get(p-1);
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
            String r = "";
            
            for(String arg : args)
                r += arg + " ";
            return r;
        }
        
        public String toStringLeft(){
            String r = "";
            
            for(int i=p; i<args.length; i++)
                r += args[i] + " ";
            return r;
        }
        
        //TODO: zastanowić się nad innym przesyłem, żeby zawzse był dostęp do pełnej ścieszki
        public Args getArgsOnly(){
            return getArgsFrom(p);
        }
        
        public Args getArgsFrom(int s){
            if(s < 0)
                throw new IllegalArgumentException();
            String arg = "";
            
            for(; s < args.length; s++)
                arg += args[s] + " ";
            try{
                Args r = new Args(arg);
                return r;
            } catch (IllegalArgumentException ex){
                return null;
            }
            
        }
        
    }