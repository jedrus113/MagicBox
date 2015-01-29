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
public class Interpreter {
    
    static int exeAction(Words cmd) throws UnknownCommandException{
        switch(cmd.word){
            case "exit":
                
                break;
            default:
                throw new UnknownCommandException(cmd);
        }
        
        
        return 0;
    }
    
}
