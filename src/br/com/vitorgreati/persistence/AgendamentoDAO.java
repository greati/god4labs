/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.persistence;

import br.com.vitorgreati.model.Agendamento;
import br.com.vitorgreati.model.Equipamento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Vitor
 */
public class AgendamentoDAO implements GenericoDAO<Agendamento>{
    private Connection conexao;
    private UsuarioDAO usuarioDAO;
    private EquipamentoDAO equipamentoDAO;
    
    public AgendamentoDAO(){
        usuarioDAO = new UsuarioDAO();
        equipamentoDAO = new EquipamentoDAO();
    }

    @Override
    public void cadastrar(Agendamento agendamento) {
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "INSERT INTO usuario_reserva_equipamento VALUES (?,?,?,?,?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, agendamento.getDescricao());
            ps.setTimestamp(2, new java.sql.Timestamp(agendamento.getDataInicio().getTime()));
            ps.setTimestamp(3, new java.sql.Timestamp(agendamento.getDataFinal().getTime()));
            ps.setInt(4, agendamento.getUsuario().getId());
            ps.setInt(5, agendamento.getEquipamento().getId());
 
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void atualizar(Agendamento agendamento) {
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "UPDATE usuario_reserva_equipamento SET descricao=?,data_inicio=?,data_final=?,"
                    + "id_usuario=?,id_equipamento=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, agendamento.getDescricao());
            ps.setDate(2, Date.valueOf(agendamento.getDataInicio().toString()));
            ps.setDate(3, Date.valueOf(agendamento.getDataFinal().toString()));
            ps.setInt(4, agendamento.getUsuario().getId());
            ps.setInt(5, agendamento.getEquipamento().getId());
 
            ps.execute();            
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void excluir(Agendamento agendamento) {
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "DELETE FROM usuario_reserva_equipamento WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, agendamento.getId());
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public ArrayList<Agendamento> listar() {
        conexao = ConexaoSQLServer.getConnection();
        ArrayList<Agendamento> agendamentos = new ArrayList<Agendamento>();
        try {
            String sql = "SELECT * FROM usuario_reserva_equipamento";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                agendamentos.add(new Agendamento(rs.getInt("id"), rs.getString("descricao"), rs.getDate("data_inicio"), 
                        rs.getDate("data_final"), usuarioDAO.buscarPorId(rs.getInt("id_usuario")), equipamentoDAO.buscarPorId(rs.getInt("id_equipamento"))));
            }
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return agendamentos;
    }
    
    public ArrayList<Agendamento> listarPorEquipamento(Equipamento equipamento){
        conexao = ConexaoSQLServer.getConnection();
        
        ArrayList<Agendamento> agendamentos = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM usuario_reserva_equipamento WHERE id_equipamento = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1,equipamento.getId());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                agendamentos.add(new Agendamento(rs.getInt("id"), rs.getString("descricao"), rs.getTimestamp("data_inicio"), 
                        rs.getTimestamp("data_final"), usuarioDAO.buscarPorId(rs.getInt("id_usuario")), equipamentoDAO.buscarPorId(rs.getInt("id_equipamento"))));
            }
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return agendamentos;
    }
    

    @Override
    public Agendamento buscarPorId(int id) {
        conexao = ConexaoSQLServer.getConnection();
        Agendamento agendamento = null;
        try {
            String sql = "SELECT * FROM usuario_reserva_equipamento WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1,id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                agendamento = new Agendamento(rs.getInt("id"), rs.getString("descricao"), rs.getDate("data_inicio"), 
                        rs.getDate("data_final"), usuarioDAO.buscarPorId(rs.getInt("id_usuario")), equipamentoDAO.buscarPorId(rs.getInt("id_equipamento")));
            }
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return agendamento;
    }
    
    
}
