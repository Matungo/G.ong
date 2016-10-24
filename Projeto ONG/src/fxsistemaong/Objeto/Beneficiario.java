/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxsistemaong.Objeto;

import java.util.Date;

/**
 *
 * @author Luciano
 */
public class Beneficiario {
    private int codigo;
    private Date dataCadastro;
    
    //Dados Pessoais
    private String nome,rg,nis,cpf,email,sexo;
    private Date dataNascimento;
    
    //Dados Complementais
    private String endereco,CEP,complemento,bairro,cidade;
    private int numero;
    
    //Demais informaçoes
    private String estadoCivil,telRes,telRec,telCel,profissao,qual,obs;
    private float rendaFamiliar;
    private int filhos,qtdeFilhos,idade,interesse;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTelRes() {
        return telRes;
    }

    public void setTelRes(String telRes) {
        this.telRes = telRes;
    }

    public String getTelRec() {
        return telRec;
    }

    public void setTelRec(String telRec) {
        this.telRec = telRec;
    }

    public String getTelCel() {
        return telCel;
    }

    public void setTelCel(String telCel) {
        this.telCel = telCel;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getQual() {
        return qual;
    }

    public void setQual(String qual) {
        this.qual = qual;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public float getRendaFamiliar() {
        return rendaFamiliar;
    }

    public void setRendaFamiliar(float rendaFamiliar) {
        this.rendaFamiliar = rendaFamiliar;
    }

    public int getFilhos() {
        return filhos;
    }

    public void setFilhos(int filhos) {
        this.filhos = filhos;
    }

    public int getQtdeFilhos() {
        return qtdeFilhos;
    }

    public void setQtdeFilhos(int qtdeFilhos) {
        this.qtdeFilhos = qtdeFilhos;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getInteresse() {
        return interesse;
    }

    public void setInteresse(int interesse) {
        this.interesse = interesse;
    }
}
