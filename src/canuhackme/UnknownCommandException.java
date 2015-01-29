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
public class UnknownCommandException extends Exception {
    Words problem;
    public UnknownCommandException(Words ex){
        problem = ex;
    }
}
