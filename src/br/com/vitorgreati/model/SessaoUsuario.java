/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vitor
 */
public class SessaoUsuario {
    
    private static Map<String,Object> atributos = new HashMap<>();
    
    private SessaoUsuario(){}
    
    public static Usuario getUsuario(){
        return (Usuario) atributos.get("usuario");
    }
    
    public static void setUsuario(Usuario usuario){
        atributos.put("usuario", usuario);
    }
    
    public static void addAtributo(String key, Object objeto){
        atributos.put(key, objeto);
    }
}
