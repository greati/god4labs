/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Vitor
 */
public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String username;
    private String senha;
    private String descricao;
    private Date dataNascimento;
    private Date dataCadastro;
    private String telefoneFixo;
    private String celular;
    private String rua;
    private int numeroCasa;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private Usuario cadastradoPor;
    private ArrayList<Autoridade> autoridades;
    private ArrayList<Projeto> projetos;

    public ArrayList<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(ArrayList<Projeto> projetos) {
        this.projetos = projetos;
    }

    public Usuario(int id, String nome, String email, String username, String senha, String descricao, Date dataNascimento, Date dataCadastro, String telefoneFixo, String celular, String rua, 
            int numeroCasa, String bairro, String cidade, String estado, String cep, Usuario cadastradoPor) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.senha = senha;
        this.descricao = descricao;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.telefoneFixo = telefoneFixo;
        this.celular = celular;
        this.rua = rua;
        this.numeroCasa = numeroCasa;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.cadastradoPor = cadastradoPor;
    }
    
    public Usuario(String nome, String email, String username, String senha, String descricao, Date dataNascimento, Date dataCadastro, String telefoneFixo, String celular, String rua, 
            int numeroCasa, String bairro, String cidade, String estado, String cep, Usuario cadastradoPor) {
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.senha = senha;
        this.descricao = descricao;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.telefoneFixo = telefoneFixo;
        this.celular = celular;
        this.rua = rua;
        this.numeroCasa = numeroCasa;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.cadastradoPor = cadastradoPor;
    }
    
    public ArrayList<Autoridade> getAutoridades() {
        return autoridades;
    }

    public void setAutoridades(ArrayList<Autoridade> autoridades) {
        this.autoridades = autoridades;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Usuario getCadastradoPor() {
        return cadastradoPor;
    }

    public void setCadastradoPor(Usuario cadastradoPor) {
        this.cadastradoPor = cadastradoPor;
    }

    /**
     * @return the telefoneFixo
     */
    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    /**
     * @param telefoneFixo the telefoneFixo to set
     */
    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * @param rua the rua to set
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * @return the numeroCasa
     */
    public int getNumeroCasa() {
        return numeroCasa;
    }

    /**
     * @param numeroCasa the numeroCasa to set
     */
    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }
}
