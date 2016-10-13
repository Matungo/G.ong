/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxsistemaong.Objeto;

import java.util.Date;

/**
 * @author Luciano
 * 
 */
public class Dependentes {
    
    private int codigo;
    private Date dataCadastro;
    
    //Dados dos responsaveis
    //Mae   
    private String nomeMae,profissaoMae,rgMae,cpfMae;;
    private Date dataMae; 
    
    //Pai   
    private String nomePai,profissaoPai,rgPai,cpfPai;
    private Date dataPai; 
   
    //Dados da crianca
    private String nomeCrianca,corPele,nis,ubs,territorio,cpfCrianca,sexo;
    private Date dataCrianca;
    private float rendaFamiliar;
    
    //Dados complementais
    private String endereco,complemento,bairro,cidade,responsavel,grauParentesco,serie,motivoEstudo,nomeEscola,composicao,numero,cep,telRes,telRec,telCel,estuda,periodoEscolar;
    
    //Dados Clinicos
    private String outrasInfo,outrasAti,relFamilia,relEscola,qualAlergia,descricao,qualMedica,qualInternado,quando,internado,alergia,quandoConvulsao,convulsao,medicamento,saudeMental,outrasSaude,atividades;
    private int qualSaude;
    
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

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getProfissaoMae() {
        return profissaoMae;
    }

    public void setProfissaoMae(String profissaoMae) {
        this.profissaoMae = profissaoMae;
    }

    public Date getDataMae() {
        return dataMae;
    }

    public void setDataMae(Date dataMae) {
        this.dataMae = dataMae;
    }

    public String getRgMae() {
        return rgMae;
    }

    public void setRgMae(String rgMae) {
        this.rgMae = rgMae;
    }

    public String getCpfMae() {
        return cpfMae;
    }

    public void setCpfMae(String cpfMae) {
        this.cpfMae = cpfMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getProfissaoPai() {
        return profissaoPai;
    }

    public void setProfissaoPai(String profissaoPai) {
        this.profissaoPai = profissaoPai;
    }

    public Date getDataPai() {
        return dataPai;
    }

    public void setDataPai(Date dataPai) {
        this.dataPai = dataPai;
    }

    public String getRgPai() {
        return rgPai;
    }

    public void setRgPai(String rgPai) {
        this.rgPai = rgPai;
    }

    public String getCpfPai() {
        return cpfPai;
    }

    public void setCpfPai(String cpfPai) {
        this.cpfPai = cpfPai;
    }

    public String getNomeCrianca() {
        return nomeCrianca;
    }

    public void setNomeCrianca(String nomeCrianca) {
        this.nomeCrianca = nomeCrianca;
    }

    public String getCorPele() {
        return corPele;
    }

    public void setCorPele(String corPele) {
        this.corPele = corPele;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getUbs() {
        return ubs;
    }

    public void setUbs(String ubs) {
        this.ubs = ubs;
    }

    public String getTerritorio() {
        return territorio;
    }

    public void setTerritorio(String territorio) {
        this.territorio = territorio;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataCrianca() {
        return dataCrianca;
    }

    public void setDataCrianca(Date dataCrianca) {
        this.dataCrianca = dataCrianca;
    }

    public String getCpfCrianca() {
        return cpfCrianca;
    }

    public void setCpfCrianca(String cpfCrianca) {
        this.cpfCrianca = cpfCrianca;
    }

    public float getRendaFamiliar() {
        return rendaFamiliar;
    }

    public void setRendaFamiliar(float rendaFamiliar) {
        this.rendaFamiliar = rendaFamiliar;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getGrauParentesco() {
        return grauParentesco;
    }

    public void setGrauParentesco(String grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMotivoEstudo() {
        return motivoEstudo;
    }

    public void setMotivoEstudo(String motivoEstudo) {
        this.motivoEstudo = motivoEstudo;
    }

    public String getNomeEscola() {
        return nomeEscola;
    }

    public void setNomeEscola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
    }

    public String getComposicao() {
        return composicao;
    }

    public void setComposicao(String composicao) {
        this.composicao = composicao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getEstuda() {
        return estuda;
    }

    public void setEstuda(String estuda) {
        this.estuda = estuda;
    }

    public String getPeriodoEscolar() {
        return periodoEscolar;
    }

    public void setPeriodoEscolar(String periodoEscolar) {
        this.periodoEscolar = periodoEscolar;
    }

    public String getOutrasInfo() {
        return outrasInfo;
    }

    public void setOutrasInfo(String outrasInfo) {
        this.outrasInfo = outrasInfo;
    }

    public String getOutrasAti() {
        return outrasAti;
    }

    public void setOutrasAti(String outrasAti) {
        this.outrasAti = outrasAti;
    }

    public String getRelFamilia() {
        return relFamilia;
    }

    public void setRelFamilia(String relFamilia) {
        this.relFamilia = relFamilia;
    }

    public String getRelEscola() {
        return relEscola;
    }

    public void setRelEscola(String relEscola) {
        this.relEscola = relEscola;
    }

    public String getQualAlergia() {
        return qualAlergia;
    }

    public void setQualAlergia(String qualAlergia) {
        this.qualAlergia = qualAlergia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQualMedica() {
        return qualMedica;
    }

    public void setQualMedica(String qualMedica) {
        this.qualMedica = qualMedica;
    }

    public String getQualInternado() {
        return qualInternado;
    }

    public void setQualInternado(String qualInternado) {
        this.qualInternado = qualInternado;
    }

    public String getInternado() {
        return internado;
    }

    public void setInternado(String internado) {
        this.internado = internado;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public String getConvulsao() {
        return convulsao;
    }

    public void setConvulsao(String convulsao) {
        this.convulsao = convulsao;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getSaudeMental() {
        return saudeMental;
    }

    public void setSaudeMental(String saudeMental) {
        this.saudeMental = saudeMental;
    }

    public String getAtividades() {
        return atividades;
    }

    public void setAtividades(String atividades) {
        this.atividades = atividades;
    }

    public int getQualSaude() {
        return qualSaude;
    }

    public void setQualSaude(int qualSaude) {
        this.qualSaude = qualSaude;
    }

    public String getOutrasSaude() {
        return outrasSaude;
    }

    public void setOutrasSaude(String outrasSaude) {
        this.outrasSaude = outrasSaude;
    }

    public String getQuando() {
        return quando;
    }

    public void setQuando(String quando) {
        this.quando = quando;
    }

    public String getQuandoConvulsao() {
        return quandoConvulsao;
    }

    public void setQuandoConvulsao(String qunadoConvulsao) {
        this.quandoConvulsao = qunadoConvulsao;
    }
}
