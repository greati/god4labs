/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.persistence;

import java.util.ArrayList;

/**
 *
 * @author Vitor
 */
public interface GenericoDAO <T> {
    
    public void cadastrar(T object);
    
    public void atualizar(T object);
    
    public void excluir(T object);
    
    public ArrayList<T> listar();
    
    public T buscarPorId(int id);
    
}
