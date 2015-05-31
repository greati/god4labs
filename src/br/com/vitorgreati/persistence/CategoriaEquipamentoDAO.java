/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.persistence;

import br.com.vitorgreati.model.CategoriaEquipamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author Vitor
 */
public class CategoriaEquipamentoDAO implements GenericoDAO<CategoriaEquipamento>{
    
    private Connection conexao;

    @Override
    public void cadastrar(CategoriaEquipamento categoria) {
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "INSERT INTO categoria_equipamento VALUES(?,?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, categoria.getNome());
            ps.setString(2, categoria.getDescricao());
            
            ps.execute();
            
            ps.close();
            conexao.close();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void atualizar(CategoriaEquipamento categoria) {
        
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "UPDATE categoria_equipamento SET nome=?,descricao=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, categoria.getNome());
            ps.setString(2, categoria.getDescricao());
            
            ps.execute();
            
            ps.close();
            conexao.close();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
    }

    @Override
    public void excluir(CategoriaEquipamento categoria) {
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "DELETE FROM categoria_equipamento WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1,categoria.getId());
            
            limparEquipamentosDaCategoria(categoria);
            
            ps.execute();
            
            ps.close();
            conexao.close();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
    }
    
    public void limparEquipamentosDaCategoria(CategoriaEquipamento categoria){
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "UPDATE equipamento SET id_categoria = ? WHERE id_categoria = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setNull(1, Types.INTEGER);
            ps.setInt(2, categoria.getId());
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public ArrayList<CategoriaEquipamento> listar() {
        conexao = ConexaoSQLServer.getConnection();
        
        ArrayList<CategoriaEquipamento> categorias = new ArrayList<CategoriaEquipamento>();
        
        try {
            String sql = "SELECT * FROM categoria_equipamento";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                CategoriaEquipamento categoria = new CategoriaEquipamento(rs.getInt("id"),rs.getString("nome"),rs.getString("descricao"));
                categorias.add(categoria);
            }
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return categorias;
        
    }

    @Override
    public CategoriaEquipamento buscarPorId(int id) {
        conexao = ConexaoSQLServer.getConnection();
        
        CategoriaEquipamento categoria = null;
        
        try {
            String sql = "SELECT * FROM categoria_equipamento WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                categoria = new CategoriaEquipamento(rs.getInt("id"),rs.getString("nome"),rs.getString("descricao"));
            }
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return categoria;
    }
    
    public CategoriaEquipamento buscarPorNome(String nome) {
        conexao = ConexaoSQLServer.getConnection();
        
        CategoriaEquipamento categoria = null;
        
        try {
            String sql = "SELECT * FROM categoria_equipamento WHERE nome = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, nome);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                categoria = new CategoriaEquipamento(rs.getInt("id"),rs.getString("nome"),rs.getString("descricao"));
            }
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return categoria;
    }    
    
    
    
}
