/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.exceptions;

/**
 *
 * @author Vitor
 */
public class UsernameExistenteException extends Exception{
    public UsernameExistenteException(){
        super("Username existente.");
    }
}
