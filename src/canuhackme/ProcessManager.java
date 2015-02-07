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
public class ProcessManager {
    public class ProcessList{
        public ProcessList pNext, pPrev;
        public final Thread t;

        public ProcessList add(Process p){
            return pNext = new ProcessList(p, this, pNext);
        }

        public void remove(){
            if(pNext != null)
                pNext.pPrev = pPrev;
            if(pPrev != null)
                pPrev.pNext = pNext;
            
            if(pPrev == null && pNext == null)
                Machine.mainF.stop(Stopable.ALL);
            
        }

        public ProcessList(Process p){
            pNext = null;
            pPrev = null;

            t = new Thread(p);
            p.setT(this);
        }
        public ProcessList(Process p, ProcessList pPrev, ProcessList pNext){
            this.pNext = pNext;
            this.pPrev = pPrev;
            t = new Thread(p);
            p.setT(this);
        }
    }

    //nie wszytsko jest publiczne. ;)
    private ProcessList p;

    public ProcessManager(){
        p = null;
    }

    synchronized public void makeProcess(Process n){
        if(p == null)
            p = new ProcessList(n);
        else
            p = p.add(n);

        p.t.start();
    }
    
}