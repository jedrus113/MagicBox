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
class ProcessManager {
    
    private ProcessManager pNext, pPrev;
    private final Thread t;
    private static ProcessManager last = null;

    static synchronized void makeProcess(Process p){
        if(last == null)
            last = new ProcessManager(p);
        else
            last = last.add(p);
        
        last.t.start();
    }
    
    private ProcessManager add(Process p){
        return pNext = new ProcessManager(p, this, pNext);
    }

    public void remove(){   // remove by process ok, adding only wia Machine.java
        if(pNext != null)
            pNext.pPrev = pPrev;
        if(pPrev != null){
            pPrev.pNext = pNext;
            if(pNext == null)   //if there is no next, then i just remove tha last (from end i mean) process
                last = pPrev;   //new last
        }

        if(pPrev == null && pNext == null)
            Machine.mainF.stop(Stopable.ALL);

    }

    private ProcessManager(Process p){
        pNext = null;
        pPrev = null;

        t = new Thread(p);
        p.setT(this);
    }
    private ProcessManager(Process p, ProcessManager pPrev, ProcessManager pNext){
        this.pNext = pNext;
        this.pPrev = pPrev;
        t = new Thread(p);
        p.setT(this);
    }
    
}