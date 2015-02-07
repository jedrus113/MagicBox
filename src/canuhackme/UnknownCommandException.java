/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canuhackme;

import javax.swing.JOptionPane;


/**
 *
 * @author Andrzej
 */
public class UnknownCommandException extends Exception {
    Args problem;
    public UnknownCommandException(Args ex){
        problem = ex;
    }
    
    public String ProblemMessage(){
        return "Unknown command " + problem.get(0) + " in:\n" + problem;
    }
    
    public void showErrorMassage(){
        JOptionPane.showMessageDialog(null, ProblemMessage());
    }
}
