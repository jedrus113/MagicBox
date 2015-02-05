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
public class Process implements Runnable {
    private ProcessManager.ProcessList th = null;
    public final MyJFrame frame;
    
    public void setT(ProcessManager.ProcessList th){
        if(this.th == null)
            this.th = th;
        else
            throw new IllegalArgumentException();
    }
    
    @Override
    public void run() {
        frame.setVisible(true);
        new Console(this).read();
    }
    
    public void remove(){
        if(frame.isVisible())
            frame.dispose();
        th.remove();
    }
    
    public Process(String p, Args cmd){
        frame = new MyJFrame(p, this);
    }
    
    int exeAction(Args arg) throws UnknownCommandException{
        
        switch(arg.get()){
            case "new":
                Machine.mainF.newProcess(arg.getArgsOnly().fullText(), null);
                break;
            case "exit":
                remove();
                break;
            default:
                throw new UnknownCommandException(arg);
        }
        
        
        return 0;
    }
    
    
}
