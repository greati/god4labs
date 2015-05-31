/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.persistence;

import br.com.vitorgreati.exceptions.UsernameExistenteException;
import br.com.vitorgreati.model.Autoridade;
import br.com.vitorgreati.model.Projeto;
import br.com.vitorgreati.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Vitor
 */
public class UsuarioDAO {//implements {//GenericoDAO<Usuario>{

    private  Connection conexao;
    private  PreparedStatement ps;
    private ProjetoDAO projetoDAO;
    
    public UsuarioDAO(ProjetoDAO projetoDAO){
        this.projetoDAO = projetoDAO;
    }
    
    public UsuarioDAO(){
        this.projetoDAO = new ProjetoDAO(this);
    }
    
    public Usuario logar(String username, String senha){
        conexao = ConexaoSQLServer.getConnection();
        
        Usuario usuario = null;
        
        try {
            String sql = "SELECT * FROM usuario WHERE username=? AND senha=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1,username);
            ps.setString(2,senha);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                System.out.println("rs");
                usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("username"), 
                        rs.getString("senha"), rs.getString("descricao"), rs.getDate("data_cadastro"), rs.getDate("data_nascimento"),
                        rs.getString("telefoneFixo"),rs.getString("celular"),rs.getString("rua"),rs.getInt("numeroCasa"),rs.getString("bairro"),
                        rs.getString("cidade"),rs.getString("estado"),rs.getString("cep"),buscarPorId(rs.getInt("cadastrado_por")));
                usuario.setAutoridades(buscarAutoridadesPorUsuario(usuario));
                usuario.setProjetos(projetoDAO.buscarProjetosPorUsuario(usuario));
            }
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return usuario;
    }
    
    
    //@Override
    public void cadastrar(Usuario usuario) throws UsernameExistenteException{
        
        if(usernameExistente(usuario.getUsername())) throw new UsernameExistenteException();
        
        conexao = ConexaoSQLServer.getConnection();
        ResultSet generatedKeys = null;
        try{
            String sql = "INSERT INTO usuario VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement ps = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getUsername());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getDescricao());
            ps.setDate(6, new java.sql.Date(usuario.getDataCadastro().getTime()));
            ps.setDate(7,  new java.sql.Date(usuario.getDataNascimento().getTime()));
            ps.setString(8, usuario.getTelefoneFixo());
            ps.setString(9, usuario.getCelular());
            ps.setString(10, usuario.getRua());
            ps.setInt(11, usuario.getNumeroCasa());
            ps.setString(12, usuario.getBairro());
            ps.setString(13, usuario.getCidade());
            ps.setString(14, usuario.getEstado());
            ps.setString(15, usuario.getCep());
            ps.setInt(16, usuario.getCadastradoPor().getId());
            
            ps.executeUpdate();
            
            generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()){
                usuario.setId(generatedKeys.getInt(1));
            }
            
            for(Autoridade autoridade : usuario.getAutoridades()){
                System.out.println(autoridade.getNome());
                definirAutoridadeEmUsuario(usuario, autoridade);
            }
            
            ps.close();
            conexao.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        
    }
    
    public ArrayList<Usuario> buscarUsuariosPorProjeto(Projeto projeto){
        conexao = ConexaoSQLServer.getConnection();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            String sql = "SELECT u.id FROM usuario AS u INNER JOIN usuario_projeto AS up ON up.id_usuario = u.id INNER JOIN projeto p"
                    + " ON up.id_projeto = ? AND up.id_projeto = p.id";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, projeto.getId());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                usuarios.add(buscarPorId(rs.getInt("id")));
            }
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return usuarios;
    }
    
    
    //@Override
    public void atualizar(Usuario usuario) throws UsernameExistenteException{
        
        //if(usernameExistente(usuario.getUsername())) throw new UsernameExistenteException();
        
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "UPDATE usuario SET nome=?,email=?,username=?,senha=?,descricao=?,data_cadastro=?,data_nascimento=?,"
                    + "telefoneFixo=?,celular=?,rua=?,numeroCasa=?,bairro=?,estado=?,cep=?,cidade=? WHERE usuario.id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getUsername());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getDescricao());
            ps.setDate(6, new java.sql.Date(usuario.getDataCadastro().getTime()));
            ps.setDate(7,  new java.sql.Date(usuario.getDataNascimento().getTime()));
            ps.setString(8, usuario.getTelefoneFixo());
            ps.setString(9, usuario.getCelular());
            ps.setString(10, usuario.getRua());
            ps.setInt(11, usuario.getNumeroCasa());
            ps.setString(12, usuario.getBairro());
            ps.setString(13, usuario.getEstado());
            ps.setString(14, usuario.getCep());
            ps.setString(15, usuario.getCidade());
            ps.setInt(16, usuario.getId());
            
            ps.execute();
            
            limparAutoridadesUsuario(usuario);
           
            for(Autoridade autoridade : usuario.getAutoridades()){
                System.out.println(autoridade.getNome());
                definirAutoridadeEmUsuario(usuario, autoridade);
            }
            
            ps.close();
            conexao.close();            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    
    //@Override
    public void excluir(Usuario usuario) {
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "DELETE FROM usuario WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setLong(1, usuario.getId());
            
            limparCadastradoPorEquipamento(usuario);
            limparCadastradoPorProjeto(usuario);
            limparCadastradoPorUsuarioProjeto(usuario);
            limparCadastradoPorUsuario(usuario);
            limparAutoridadesUsuario(usuario);
            limparReservasUsuario(usuario);
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    
    //@Override
    public ArrayList<Usuario> listar() {
        conexao = ConexaoSQLServer.getConnection();

        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        try {
            String sql = "SELECT * FROM usuario";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("username"), 
                        rs.getString("senha"), rs.getString("descricao"), rs.getDate("data_cadastro"), rs.getDate("data_nascimento"),
                        rs.getString("telefoneFixo"),rs.getString("celular"),rs.getString("rua"),rs.getInt("numeroCasa"),rs.getString("bairro"),
                        rs.getString("cidade"),rs.getString("estado"),rs.getString("cep"),buscarPorId(rs.getInt("cadastrado_por")));
                ArrayList<Autoridade> autoridades = buscarAutoridadesPorUsuario(usuario);
                ArrayList<Projeto> projetos = projetoDAO.buscarProjetosPorUsuario(usuario);
                usuario.setAutoridades(autoridades);
                usuario.setProjetos(projetos);
                usuarios.add(usuario);
            }
            
            rs.close();
            ps.close();
            conexao.close();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return usuarios;
    }

    
    //@Override
    public  Usuario buscarPorId(int id) {
        conexao = ConexaoSQLServer.getConnection();

        try {
            String sql = "SELECT * FROM usuario WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1,id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("username"), 
                        rs.getString("senha"), rs.getString("descricao"), rs.getDate("data_cadastro"), rs.getDate("data_nascimento"),
                        rs.getString("telefoneFixo"),rs.getString("celular"),rs.getString("rua"),rs.getInt("numeroCasa"),rs.getString("bairro"),
                        rs.getString("cidade"),rs.getString("estado"),rs.getString("cep"),buscarPorId(rs.getInt("cadastrado_por")));
                ArrayList<Autoridade> autoridades = buscarAutoridadesPorUsuario(usuario);
                //ArrayList<Projeto> projetos = projetoDAO.buscarProjetosPorUsuario(usuario);
                usuario.setAutoridades(autoridades);
                //usuario.setProjetos(projetos);
                return usuario;
            }
            
            rs.close();
            ps.close();
            conexao.close();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }        
        return null;
    }
    
    public ArrayList<Usuario> buscarOtimizado(String nome, String username, String estado, ArrayList<String> auts){
        conexao = ConexaoSQLServer.getConnection();
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        try {
            //String sql = "SELECT u.* FROM usuario u, autoridade a, usuario_autoridade ua";
            
            /*SELECT DISTINCT u.id, convert(varchar(max),u.nome), convert(varchar(max),u.email),convert(varchar(max),u.username),convert(varchar(max),u.senha),convert(varchar(max),u.descricao), convert(varchar(max),u.data_cadastro), convert(varchar(max),u.data_nascimento), 
convert(varchar(max),u.telefoneFixo), convert(varchar(max),u.celular), convert(varchar(max),u.rua), convert(varchar(max),u.numeroCasa), convert(varchar(max),u.bairro), convert(varchar(max),u.cidade), convert(varchar(max),u.estado), convert(varchar(max),u.cep), convert(varchar(max),u.cadastrado_por)
FROM autoridade AS a LEFT JOIN usuario_autoridade AS ua ON ua.id_autoridade = a.id,
usuario AS u LEFT JOIN usuario_autoridade ON id_usuario = u.id*/
            
//            String sql = "SELECT DISTINCT u.id, convert(varchar(max),u.nome) nome, convert(varchar(max),u.email) email, convert(varchar(max),u.username) username, convert(varchar(max),u.senha) senha, convert(varchar(max),u.descricao) descricao, u.data_cadastro, u.data_nascimento,"
//+"convert(varchar(max),u.telefoneFixo) telefoneFixo, convert(varchar(max),u.celular) celular, convert(varchar(max),u.rua) rua, convert(varchar(max),u.numeroCasa) numeroCasa, convert(varchar(max),u.bairro) bairro, convert(varchar(max),u.cidade) cidade, convert(varchar(max),u.estado) estado, convert(varchar(max),u.cep) cep, convert(varchar(max),u.cadastrado_por) cadastrado_por"
//+" FROM autoridade AS a INNER JOIN usuario_autoridade AS ua ON ua.id_autoridade = a.id,"
//+"usuario AS u INNER JOIN usuario_autoridade ON id_usuario = u.id";
            
            String sql = "SELECT DISTINCT u.id, convert(varchar(max),u.nome) nome, convert(varchar(max),u.email) email, convert(varchar(max),u.username) username, convert(varchar(max),u.senha) senha, convert(varchar(max),u.descricao) descricao, u.data_cadastro, u.data_nascimento,"
+"convert(varchar(max),u.telefoneFixo) telefoneFixo, convert(varchar(max),u.celular) celular, convert(varchar(max),u.rua) rua, convert(varchar(max),u.numeroCasa) numeroCasa, convert(varchar(max),u.bairro) bairro, convert(varchar(max),u.cidade) cidade, convert(varchar(max),u.estado) estado, convert(varchar(max),u.cep) cep, convert(varchar(max),u.cadastrado_por) cadastrado_por"
+" FROM autoridade AS a INNER JOIN usuario_autoridade AS ua ON ua.id_autoridade = a.id"
+" RIGHT JOIN usuario AS u ON id_usuario = u.id";
            if(!nome.trim().equals("") || !username.trim().equals("") || !estado.trim().equals("") || !auts.isEmpty()){
                //String where = " WHERE ua.id_usuario = u.id AND ua.id_autoridade = a.id AND ";
                String where = " WHERE ";
                if(!nome.trim().equals("")){
                    where+="u.nome like '%"+nome+"%' AND ";
                }
                if(!username.trim().equals("")){
                    where+="u.username like '%"+username+"%' AND ";
                }
                if(!estado.trim().equals("")){
                    where+="u.estado like '%"+estado+"%' AND ";
                }
                if(auts != null && !auts.isEmpty()){
                   where+="(";
                    for(String aut : auts){
                        //where+="(ua.id_usuario = u.id AND ua.id_autoridade = a.id AND a.nome='"+aut+"') OR ";
                        where+="(a.nome='"+aut+"') OR ";
                    }
                    if(where.endsWith("AND ")) where = where.substring(0,where.length()-4);
                    if(where.endsWith("OR ")) where = where.substring(0,where.length()-3);
                    where+=")";
                }

                if(where.endsWith("AND ")) where = where.substring(0,where.length()-4);
                if(where.endsWith("OR ")) where = where.substring(0,where.length()-3);

                sql+=where;
            }
            System.out.println(sql);
            
            PreparedStatement ps = conexao.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("username"), 
                        rs.getString("senha"), rs.getString("descricao"), rs.getDate("data_cadastro"), rs.getDate("data_nascimento"),
                        rs.getString("telefoneFixo"),rs.getString("celular"),rs.getString("rua"),rs.getInt("numeroCasa"),rs.getString("bairro"),
                        rs.getString("cidade"),rs.getString("estado"),rs.getString("cep"),buscarPorId(rs.getInt("cadastrado_por")));
                ArrayList<Autoridade> autoridades = buscarAutoridadesPorUsuario(usuario);
                usuario.setAutoridades(autoridades);
                usuarios.add(usuario);
            }            
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return usuarios;
    }
    
    public void definirAutoridadeEmUsuario(Usuario usuario, Autoridade autoridade){
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "INSERT INTO usuario_autoridade VALUES(?,?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, usuario.getId());
            ps.setInt(2, autoridade.getId());
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
    }
    
    public ArrayList<Autoridade> buscarAutoridadesPorUsuario(Usuario usuario){
        conexao = ConexaoSQLServer.getConnection();
        ArrayList<Autoridade> autoridades = new ArrayList<Autoridade>();
        try {
            String sql = "SELECT a.id, a.nome FROM autoridade a, usuario u, usuario_autoridade ua"
                    + " WHERE ua.id_usuario = ? AND ua.id_autoridade = a.id";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, usuario.getId());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                autoridades.add(new Autoridade(rs.getInt("id"),rs.getString("nome")));
            }
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return autoridades;
    }
    
    public void limparAutoridadesUsuario(Usuario usuario){
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "DELETE FROM usuario_autoridade WHERE id_usuario = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, usuario.getId());
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public void limparReservasUsuario(Usuario usuario){
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "DELETE FROM usuario_reserva_equipamento WHERE id_usuario = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, usuario.getId());
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public void limparCadastradoPorUsuario(Usuario usuario){
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "UPDATE usuario SET cadastrado_por = ? WHERE cadastrado_por = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, 1);
            ps.setInt(2, usuario.getId());
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public void limparCadastradoPorEquipamento(Usuario usuario){
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "UPDATE equipamento SET cadastrado_por = ? WHERE cadastrado_por = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, 1);
            ps.setInt(2, usuario.getId());
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public void limparCadastradoPorProjeto(Usuario usuario){
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "UPDATE projeto SET cadastrado_por = ? WHERE cadastrado_por = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, 1);
            ps.setInt(2, usuario.getId());
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }    
    
    public void limparCadastradoPorUsuarioProjeto(Usuario usuario){
        conexao = ConexaoSQLServer.getConnection();
        try {
            String sql = "UPDATE usuario_projeto SET cadastrado_por = ? WHERE cadastrado_por = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, 1);
            ps.setInt(2, usuario.getId());
            
            ps.execute();
            
            ps.close();
            conexao.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }    
    
    public boolean usernameExistente(String username){
        conexao = ConexaoSQLServer.getConnection();
        boolean existe = false;
        try {
            String sql = "SELECT id FROM usuario WHERE username=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1,username);
            
            ResultSet rs = ps.executeQuery();
            
            existe = rs.next();
            
            ps.close();
            conexao.close();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return existe;
    }
    
    
    
}
