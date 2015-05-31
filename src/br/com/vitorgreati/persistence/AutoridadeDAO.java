/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.persistence;

import br.com.vitorgreati.model.Autoridade;
import br.com.vitorgreati.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Vitor
 */
public class AutoridadeDAO implements GenericoDAO<Autoridade>{

    private Connection conexao;
    
    @Override
    public void cadastrar(Autoridade autoridade) {
        conexao = ConexaoSQLServer.getConnection();
        try{
            String sql = "INSERT INTO autoridade VALUES (?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, autoridade.getNome());
            
            ps.execute();
            
            ps.close();
            conexao.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }

    @Override
    public void atualizar(Autoridade autoridade) {
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "UPDATE autoridade SET nome=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, autoridade.getNome());
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void excluir(Autoridade autoridade) {
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "DELETE FROM autoridade WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, autoridade.getId());
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public ArrayList<Autoridade> listar() {
        
        ArrayList<Autoridade> autoridades = new ArrayList<Autoridade>();
        
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "SELECT * FROM autoridade";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                autoridades.add(new Autoridade(rs.getInt("id"), rs.getString("nome")));
            }
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return autoridades;
    }

    @Override
    public Autoridade buscarPorId(int id) {
        conexao = ConexaoSQLServer.getConnection();
        
        Autoridade autoridade = null;
        
        try {
            String sql = "SELECT * FROM autoridade WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                autoridade = new Autoridade(rs.getInt("id"), rs.getString("nome"));
            }
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return autoridade;
    }
    
    public Autoridade buscarPorNome(String nome) {
        conexao = ConexaoSQLServer.getConnection();
        
        Autoridade autoridade = null;
        
        try {
            String sql = "SELECT * FROM autoridade WHERE nome=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, nome);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                autoridade = new Autoridade(rs.getInt("id"), rs.getString("nome"));
            }
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return autoridade;
    }
    
}
