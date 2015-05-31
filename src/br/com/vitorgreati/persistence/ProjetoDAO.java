/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.persistence;

import br.com.vitorgreati.model.Projeto;
import br.com.vitorgreati.model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Vitor
 */
public class ProjetoDAO implements GenericoDAO<Projeto>{

    private Connection conexao;
    private UsuarioDAO usuarioDAO;
    
    public ProjetoDAO(){
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public ProjetoDAO(UsuarioDAO usuarioDAO){
        this.usuarioDAO = usuarioDAO;
    }
    
    
    @Override
    public void cadastrar(Projeto projeto) {
        conexao = ConexaoSQLServer.getConnection();
        ResultSet generatedKeys = null;
        
        try {
            String sql = "INSERT INTO projeto VALUES (?,?,?,?,?)";
            PreparedStatement ps = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, projeto.getNome());
            ps.setString(2,projeto.getDescricao());
            ps.setDate(3, new java.sql.Date(projeto.getDataInicio().getTime()));
            ps.setDate(4, new java.sql.Date(projeto.getDataFinal().getTime()));
            ps.setInt(5, projeto.getCadastradoPor().getId());
            
            limparUsuariosDoProjeto(projeto);
            
            ps.execute();
            
            generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()){
                projeto.setId(generatedKeys.getInt(1));
            }
            
            for(Usuario u : projeto.getUsuarios()){
                cadastrarUsuarioEmProjeto(projeto, u);
            }
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public void cadastrarUsuarioEmProjeto(Projeto projeto, Usuario usuario){
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "INSERT INTO usuario_projeto (id_usuario,id_projeto) VALUES (?,?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, usuario.getId());
            ps.setInt(2,projeto.getId());
            //ps.setDate(3, new java.sql.Date(projeto.getDataInicio().getTime()));
            //ps.setDate(4, new java.sql.Date(projeto.getDataFinal().getTime()));
            //ps.setInt(5, projeto.getCadastradoPor().getId());
            
            ps.execute();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }        
    }
    
    public void limparUsuariosDoProjeto(Projeto p){
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "DELETE FROM usuario_projeto WHERE id_projeto=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1,p.getId());
            
            ps.execute();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
    }

    
    @Override
    public void atualizar(Projeto projeto) {
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "UPDATE projeto SET nome=?,descricao=?,data_inicio=?,data_final=? WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, projeto.getNome());
            ps.setString(2,projeto.getDescricao());
            ps.setDate(3, new java.sql.Date(projeto.getDataInicio().getTime()));
            ps.setDate(4, new java.sql.Date(projeto.getDataFinal().getTime()));
            ps.setInt(5,projeto.getId());
            
            limparUsuariosDoProjeto(projeto);

            for(Usuario u : projeto.getUsuarios()){
                cadastrarUsuarioEmProjeto(projeto, u);
            }
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    
    @Override
    public void excluir(Projeto projeto) {
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "DELETE FROM projeto WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1,projeto.getId());
            
            limparUsuariosDoProjeto(projeto);
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    
    @Override
    public ArrayList<Projeto> listar() {
        conexao = ConexaoSQLServer.getConnection();
        
        ArrayList<Projeto> projetos = new ArrayList<Projeto>();
        
        try {
            String sql = "SELECT * FROM projeto";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Projeto projeto = new Projeto(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"), 
                        rs.getDate("data_inicio"), rs.getDate("data_final"), usuarioDAO.buscarPorId(rs.getInt("cadastrado_por")));
                projeto.setUsuarios(usuarioDAO.buscarUsuariosPorProjeto(projeto));
                projetos.add(projeto);
                
            }
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return projetos;
        
    }

    
    @Override
    public Projeto buscarPorId(int id) {
        conexao = ConexaoSQLServer.getConnection();
        
        Projeto projeto = null;
        
        try {
            String sql = "SELECT * FROM projeto WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                projeto = new Projeto(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"), 
                        rs.getDate("data_inicio"), rs.getDate("data_final"), usuarioDAO.buscarPorId(rs.getInt("cadastrado_por")));
                projeto.setUsuarios(usuarioDAO.buscarUsuariosPorProjeto(projeto));
            }
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return projeto;
    }
    
    public ArrayList<Projeto> buscarProjetosPorUsuario(Usuario usuario){
        conexao = ConexaoSQLServer.getConnection();
        ArrayList<Projeto> projetos = new ArrayList<>();
        try {
            System.out.println("0");
            String sql = "SELECT DISTINCT p.id, convert(varchar(max),p.nome) nome,convert(varchar(max),p.descricao) descricao,convert(varchar(max),p.data_inicio) data_inicio,convert(varchar(max),p.data_final) data_final,p.cadastrado_por"
                    + " FROM projeto AS p INNER JOIN usuario_projeto AS up ON up.id_projeto = p.id INNER JOIN usuario u"
                    + " ON up.id_usuario = ? AND up.id_usuario = u.id";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, usuario.getId());
            System.out.println("1");
            
            ResultSet rs = ps.executeQuery();
            System.out.println("2");
            while(rs.next()){
                Projeto projeto = new Projeto(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"), 
                        rs.getDate("data_inicio"), rs.getDate("data_final"), usuarioDAO.buscarPorId(rs.getInt("cadastrado_por")));
                System.out.println("3");
                //projeto.setUsuarios(usuarioDAO.buscarUsuariosPorProjeto(projeto));
                projetos.add(projeto);
                System.out.println("4");
            }
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return projetos;
    }    
    
    public ArrayList<Projeto> buscarOtimizado(String nome, String participante){
        conexao = ConexaoSQLServer.getConnection();
        
        ArrayList<Projeto> projetos = new ArrayList<>();
        
        try {
            String sql = "SELECT DISTINCT p.id FROM projeto p INNER JOIN usuario_projeto up ON"
                    + " up.id_projeto = p.id INNER JOIN usuario u ON up.id_usuario = u.id WHERE (u.nome like ? OR u.username like ?) AND p.nome like ?";
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1,"%"+participante+"%");
            ps.setString(2,"%"+participante+"%");
            ps.setString(3,"%"+nome+"%");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Projeto projeto = buscarPorId(rs.getInt("id"));
                projetos.add(projeto);
            }
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return projetos;
    }


    
}
