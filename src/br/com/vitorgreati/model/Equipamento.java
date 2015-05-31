/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vitorgreati.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Vitor
 */
public class Equipamento {

    private int id;
    private String nome;
    private String fabricante;
    private String descricao;
    private String instrucoes;
    private String precaucoes;
    private Date dataCadastro;
    private int quantidade;
    private Usuario cadastradoPor;
    private CategoriaEquipamento categoria;
    private String tombamento;

    public Equipamento(int id){
        this.id = id;
    }
    
    public Equipamento(int id, String nome, String fabricante, String descricao, String instrucoes, String precaucoes, 
            Date dataCadastro, int quantidade, Usuario cadastradoPor, CategoriaEquipamento categoria, String tombamento) {
        this.id = id;
        this.nome = nome;
        this.fabricante = fabricante;
        this.descricao = descricao;
        this.instrucoes = instrucoes;
        this.precaucoes = precaucoes;
        this.dataCadastro = dataCadastro;
        this.quantidade = quantidade;
        this.cadastradoPor = cadastradoPor;
        this.categoria = categoria;
        this.tombamento = tombamento;
    }
    
    public Equipamento(String nome, String fabricante, String descricao, String instrucoes, String precaucoes, 
            Date dataCadastro, int quantidade, Usuario cadastradoPor, CategoriaEquipamento categoria, String tombamento) {
        this.id = id;
        this.nome = nome;
        this.fabricante = fabricante;
        this.descricao = descricao;
        this.instrucoes = instrucoes;
        this.precaucoes = precaucoes;
        this.dataCadastro = dataCadastro;
        this.quantidade = quantidade;
        this.cadastradoPor = cadastradoPor;
        this.categoria = categoria;
        this.tombamento = tombamento;
    }    
    
    public CategoriaEquipamento getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEquipamento categoria) {
        this.categoria = categoria;
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

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }

    public String getPrecaucoes() {
        return precaucoes;
    }

    public void setPrecaucoes(String precaucoes) {
        this.precaucoes = precaucoes;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Usuario getCadastradoPor() {
        return cadastradoPor;
    }

    public void setCadastradoPor(Usuario cadastradoPor) {
        this.cadastradoPor = cadastradoPor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.fabricante);
        hash = 29 * hash + Objects.hashCode(this.descricao);
        hash = 29 * hash + Objects.hashCode(this.instrucoes);
        hash = 29 * hash + Objects.hashCode(this.precaucoes);
        hash = 29 * hash + Objects.hashCode(this.dataCadastro);
        hash = 29 * hash + this.quantidade;
        hash = 29 * hash + Objects.hashCode(this.cadastradoPor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipamento other = (Equipamento) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.fabricante, other.fabricante)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.instrucoes, other.instrucoes)) {
            return false;
        }
        if (!Objects.equals(this.precaucoes, other.precaucoes)) {
            return false;
        }
        if (!Objects.equals(this.dataCadastro, other.dataCadastro)) {
            return false;
        }
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (!Objects.equals(this.cadastradoPor, other.cadastradoPor)) {
            return false;
        }
        return true;
    }

    public String getTombamento() {
        return tombamento;
    }

    public void setTombamento(String tombamento) {
        this.tombamento = tombamento;
    }
}
