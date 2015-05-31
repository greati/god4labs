/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.persistence;

import br.com.vitorgreati.model.CategoriaEquipamento;
import br.com.vitorgreati.model.Equipamento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Vitor
 */
public class EquipamentoDAO implements GenericoDAO<Equipamento>{

    private Connection conexao;
    private UsuarioDAO usuarioDAO;
    private CategoriaEquipamentoDAO categoriaDAO;
    
    public EquipamentoDAO(){
        usuarioDAO = new UsuarioDAO();
        categoriaDAO = new CategoriaEquipamentoDAO();
    }
    
    @Override
    public void cadastrar(Equipamento equipamento) {
        conexao = ConexaoSQLServer.getConnection();
        ResultSet generatedKeys = null;
        try {
            String sql = "INSERT INTO equipamento VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, equipamento.getNome());
            ps.setString(2, equipamento.getFabricante());
            ps.setString(3, equipamento.getDescricao());
            ps.setString(4, equipamento.getInstrucoes());
            ps.setString(5, equipamento.getPrecaucoes());
            ps.setDate(6, new java.sql.Date(equipamento.getDataCadastro().getTime()));
            ps.setInt(7, equipamento.getQuantidade());
            ps.setInt(8, equipamento.getCadastradoPor().getId());
            ps.setInt(9, equipamento.getCategoria().getId());
            ps.setString(10, equipamento.getTombamento());
            
            ps.execute();
            
            generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()){
                equipamento.setId(generatedKeys.getInt(1));
            }
            
            ps.close();
            conexao.close();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }        
    }
    
    private String dateToString(java.util.Date data){
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return format.format(data);
    }

    @Override
    public void atualizar(Equipamento equipamento) {
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "UPDATE equipamento SET nome=?,fabricante=?,descricao=?,instrucoes=?,precaucoes=?,"
                    + "data_cadastro=?,quantidade=?,id_categoria=?,tombamento=? WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, equipamento.getNome());
            ps.setString(2, equipamento.getFabricante());
            ps.setString(3, equipamento.getDescricao());
            ps.setString(4, equipamento.getInstrucoes());
            ps.setString(5, equipamento.getPrecaucoes());
            ps.setDate(6, new java.sql.Date(equipamento.getDataCadastro().getTime()));
            ps.setInt(7, equipamento.getQuantidade());
            ps.setInt(8,equipamento.getCategoria().getId());
            ps.setString(9, equipamento.getTombamento());
            ps.setInt(10, equipamento.getId());
            
            ps.execute();
            
            ps.close();
            conexao.close();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
               
    }

    @Override
    public void excluir(Equipamento equipamento) {
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "DELETE FROM equipamento WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            limparReservas(equipamento);
            
            ps.setInt(1,equipamento.getId());
            
            ps.execute();
            
            ps.close();
            conexao.close();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public ArrayList<Equipamento> listar() {
        conexao = ConexaoSQLServer.getConnection();

        ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();

        try {
            String sql = "SELECT * FROM equipamento";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Equipamento equipamento = new Equipamento(rs.getInt("id"), rs.getString("nome"), rs.getString("fabricante"), rs.getString("descricao"), 
                        rs.getString("instrucoes"), rs.getString("precaucoes"), rs.getDate("data_cadastro"), rs.getInt("quantidade"), 
                        usuarioDAO.buscarPorId(rs.getInt("cadastrado_por")),categoriaDAO.buscarPorId(rs.getInt("id_categoria")),rs.getString("tombamento"));
                equipamentos.add(equipamento);
            }
            
            rs.close();
            ps.close();
            conexao.close();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return equipamentos;
    }

    @Override
    public Equipamento buscarPorId(int id) {
        conexao = ConexaoSQLServer.getConnection();

        Equipamento equipamento = null;
        
        try {
            String sql = "SELECT * FROM equipamento WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1,id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                 equipamento = new Equipamento(rs.getInt("id"), rs.getString("nome"), rs.getString("fabricante"), rs.getString("descricao"), 
                        rs.getString("instrucoes"), rs.getString("precaucoes"), rs.getDate("data_cadastro"), rs.getInt("quantidade"), 
                        usuarioDAO.buscarPorId(rs.getInt("cadastrado_por")), categoriaDAO.buscarPorId(rs.getInt("id_categoria")),rs.getString("tombamento"));
            }
            
            rs.close();
            ps.close();
            conexao.close();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return equipamento;
        
    }
    
    public ArrayList<Equipamento> buscarOtimizado(String nome, String fabricante, java.util.Date dataCadastro, ArrayList<CategoriaEquipamento> categorias) {
        conexao = ConexaoSQLServer.getConnection();

        ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
        
        try {
            
            String sql = "SELECT DISTINCT e.id, c.nome FROM equipamento e, categoria_equipamento c";
            String where = " WHERE ";
            
            if(!nome.trim().equals("") || !fabricante.trim().equals("") || dataCadastro!=null || categorias!=null){

                if(!nome.trim().equals("")){
                    where += "e.nome like '%"+nome+"%' AND ";
                }
                if(!fabricante.trim().equals("")){
                    where+="e.fabricante like '%"+fabricante+"%' AND ";
                }
                if(dataCadastro != null){
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    where += "e.data_cadastro > ('"+format.format(dataCadastro)+"') AND ";
                }

                if(categorias != null){
                    where+="(";
                    if(categorias.size()==1){
                        where += "e.id_categoria = "+categorias.get(0).getId();
                        where+=" AND e.id_categoria = c.id";
                        where+=")";
                    }else{
                        for(int i=0; i<categorias.size(); i++){
                            if(i!=0) where+=" OR (";
                            where+="e.id_categoria = "+categorias.get(i).getId();
                            where+=" AND e.id_categoria = c.id";
                            where+=")";
                        }
                    }

                }
                //se termina com AND, remover esse AND
                if(where.endsWith("AND ")) where = where.substring(0,where.length()-4);
                where+=" AND e.id_categoria = c.id";    
            }else{
                where+="e.id_categoria = c.id";
            }

            sql+=where;
            
            System.out.println(sql);
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Equipamento equipamento = buscarPorId(rs.getInt("id"));
//                Equipamento equipamento = new Equipamento(rs.getInt("id"), rs.getString("nome"), rs.getString("fabricante"), rs.getString("descricao"), 
//                        rs.getString("instrucoes"), rs.getString("precaucoes"), rs.getDate("data_cadastro"), rs.getInt("quantidade"), 
//                        usuarioDAO.buscarPorId(rs.getInt("cadastrado_por")),categoriaDAO.buscarPorId(rs.getInt("id_categoria")),rs.getString("tombamento"));
                equipamentos.add(equipamento);
            }
            
            rs.close();
            ps.close();
            conexao.close();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return equipamentos;
        
    }    
    
    public void limparReservas(Equipamento e){
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "DELETE FROM usuario_reserva_equipamento WHERE id_equipamento = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, e.getId());
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
}
