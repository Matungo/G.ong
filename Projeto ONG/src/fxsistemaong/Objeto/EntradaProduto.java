/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxsistemaong.Objeto;

import java.util.Date;

/**
 *
 * @author Macbook
 */
public class EntradaProduto {
    private int CodigoEntrada, Quantidade;
    private String nome;
    private String Categoria;
    private String TipoEntrada;
    private Date DataEntrada;
    private float ValorEntrada;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public Date getDataEntrada() {
        return DataEntrada;
    }

    public void setDataEntrada(Date DataEntrada) {
        this.DataEntrada = DataEntrada;
    }

    public String getTipoEntrada() {
        return TipoEntrada;
    }

    public void setTipoEntrada(String TipoEntrada) {
        this.TipoEntrada = TipoEntrada;
    }

    public int getCodigoEntrada() {
        return CodigoEntrada;
    }

    public void setCodigoEntrada(int CodigoEntrada) {
        this.CodigoEntrada = CodigoEntrada;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public float getValorEntrada() {
        return ValorEntrada;
    }

    public void setValorEntrada(float ValorEntrada) {
        this.ValorEntrada = ValorEntrada;
    }
    
    
    
}
