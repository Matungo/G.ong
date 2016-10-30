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
public class SaidaProduto {
    private int CodigoSaida, Quantidade;
    private String nome;
    private String Categoria;
    private Date DataSaida;
    private float ValorSaida;

    public int getCodigoSaida() {
        return CodigoSaida;
    }

    public void setCodigoSaida(int CodigoSaida) {
        this.CodigoSaida = CodigoSaida;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

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

    public Date getDataSaida() {
        return DataSaida;
    }

    public void setDataSaida(Date DataSaida) {
        this.DataSaida = DataSaida;
    }

    public float getValorSaida() {
        return ValorSaida;
    }

    public void setValorSaida(float ValorSaida) {
        this.ValorSaida = ValorSaida;
    }
    
    
}
