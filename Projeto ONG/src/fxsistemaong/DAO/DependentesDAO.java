/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxsistemaong.DAO;

import java.sql.Connection;
;
import fxsistemaong.Objeto.Dependentes;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author Luciano
 */


public class DependentesDAO {

    private Banco banco = new Banco("root", "", "localhost", "g_ong", 3306);
    Connection conexao;
    
    /**
     * Metodo que pesquisa todos os campos do formulario de cadastro de
     * dependentes de acordo com o codigo,recebe o codigo e devolve o objeto da classe
     * Dependentes.Possibilidade de ter erro com sql por erro de
     * conexao e de operacoes dml do sql
     * 
     * @param dependentes O usuario irá pesquisar pelo codigo nos dados do dependentes
     * @return Dependentes
     */
    public Dependentes pesquisarDependentes(Dependentes dependentes) {
        
        try {
            conexao = banco.getConexao();
            String sql = "select * from DEPENDENTES where ID_DEPENDENTES = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setInt(1, dependentes.getCodigo());
            ResultSet rs = std.executeQuery();
            while (rs.next()) {
                dependentes.setDataCadastro(rs.getDate("DATA_CADASTRO"));
                dependentes.setNomeMae(rs.getString("NOME_MAE"));
                dependentes.setDataMae(rs.getDate("DATA_NASC_MAE"));
                dependentes.setProfissaoMae(rs.getString("PROFISSAO_MAE"));
                dependentes.setRgMae(rs.getString("RG_MAE"));
                dependentes.setCpfMae(rs.getString("CPF_MAE"));
                dependentes.setNomePai(rs.getString("NOME_PAI"));
                dependentes.setDataPai(rs.getDate("DATA_NASC_PAI"));
                dependentes.setProfissaoPai(rs.getString("PROFISSAO_PAI"));
                dependentes.setRgPai(rs.getString("RG_PAI"));
                dependentes.setCpfPai(rs.getString("CPF_PAI"));
                dependentes.setNomeCrianca(rs.getString("NOME"));
                dependentes.setDataCrianca(rs.getDate("DATA_NASC"));
                dependentes.setRendaFamiliar(rs.getFloat("RENDA_FAMILIAR"));
                dependentes.setNis(rs.getString("NIS"));
                dependentes.setCpfCrianca(rs.getString("CPF"));
                dependentes.setCorPele(rs.getString("COR"));
                dependentes.setUbs(rs.getString("UBS"));
                dependentes.setTerritorio(rs.getString("TERRITORIO"));
                dependentes.setSexo(rs.getString("SEXO"));
                dependentes.setEndereco(rs.getString("ENDERECO"));
                dependentes.setNumero(rs.getString("NUMERO"));
                dependentes.setCep(rs.getString("CEP"));
                dependentes.setComplemento(rs.getString("COMPLEMENTO"));
                dependentes.setBairro(rs.getString("BAIRRO"));
                dependentes.setCidade(rs.getString("CIDADE"));
                dependentes.setResponsavel(rs.getString("RESPONSAVEL"));
                dependentes.setGrauParentesco(rs.getString("PARENTESCO"));
                dependentes.setTelCel(rs.getString("TELEFONE1"));
                dependentes.setTelRec(rs.getString("TELEFONE2"));
                dependentes.setTelRes(rs.getString("TELEFONE3"));
                dependentes.setEstuda(rs.getString("ESTUDA"));
                dependentes.setSerie(rs.getString("SERIE"));//se o codigo funcionar vou converter as string em char ,é para depoiss
                dependentes.setMotivoEstudo(rs.getString("PORQUE"));
                dependentes.setNomeEscola(rs.getString("ESCOLA"));
                dependentes.setPeriodoEscolar(rs.getString("PERIODO"));
                dependentes.setComposicao(rs.getString("COMPOSICAO"));
                dependentes.setSaudeMental(rs.getString("SAUDE_MENTAL"));
                dependentes.setQualSaude(rs.getInt("QUAL_SAUDE"));
                dependentes.setOutrasSaude(rs.getString("OUTROS"));
                dependentes.setInternado(rs.getString("INTERNADO"));
                dependentes.setQuando(rs.getString("QUANDO"));
                dependentes.setQualInternado(rs.getString("QUAL_INTERNADO"));
                dependentes.setMedicamento(rs.getString("MEDICAMENTO"));
                dependentes.setQualMedica(rs.getString("QUAL_MEDICA"));
                dependentes.setConvulsao(rs.getString("COMVULSOES"));
                dependentes.setQuandoConvulsao(rs.getString("QUANDO_COMVUL"));
                dependentes.setDescricao(rs.getString("DESCREVA"));
                dependentes.setAlergia(rs.getString("ALERGIA"));
                dependentes.setQualAlergia(rs.getString("QUAL_ALERGIA"));
                dependentes.setRelFamilia(rs.getString("REL_FAMILIA"));
                dependentes.setRelEscola(rs.getString("REL_ESCOLA"));
                dependentes.setAtividades(rs.getString("ATIVIDADES"));
                dependentes.setOutrasAti(rs.getString("OUTRAS_ATIV"));
                dependentes.setOutrasInfo(rs.getString("OUTRAS_INFO"));

            }
            banco.fechar(conexao);
        } catch (SQLException | RuntimeException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();

        }
        return dependentes;
    }

    /**
     * Metodo que salva todos os campos do formulario de cadastro de dependentes
     * no banco(menos a chave primaria de busca),recebe o objeto da classe
     * Dependentes e devolve valor booleano para tratamento.Possibilidade de ter erro com sql por erro de
     * conexao e de operacoes dml do sql.Possibilidade de ter erro com sql por erro de
     * conexao e de operacoes dml do sql
     *
     * @param dependentes Quando o usuario enviar os dados do formulario para o
     * banco sera por este paramento
     * @return boolean
     */
public boolean salvarDependentes(Dependentes dependentes)  {
        int salvado = 0;
        try {
            
            conexao = banco.getConexao();
            String sql = "insert into DEPENDENTES (DATA_CADASTRO,NOME_MAE,DATA_NASC_MAE,PROFISSAO_MAE, "
                    + "RG_MAE,CPF_MAE,NOME_PAI,DATA_NASC_PAI,PROFISSAO_PAI,RG_PAI, "
                    + "CPF_PAI,NOME,DATA_NASC,RENDA_FAMILIAR,NIS,CPF,COR,UBS,TERRITORIO, "
                    + "SEXO,ENDERECO,NUMERO,CEP,COMPLEMENTO,BAIRRO,CIDADE,RESPONSAVEL,PARENTESCO, "
                    + "TELEFONE1,TELEFONE2,TELEFONE3,ESTUDA,SERIE,PORQUE,ESCOLA,PERIODO,COMPOSICAO, "
                    + "SAUDE_MENTAL,QUAL_SAUDE,OUTROS,INTERNADO,QUANDO,QUAL_INTERNADO,MEDICAMENTO, "
                    + "QUAL_MEDICA,COMVULSOES,QUANDO_COMVUL,DESCREVA,ALERGIA,QUAL_ALERGIA,REL_FAMILIA, "
                    + "REL_ESCOLA,ATIVIDADES,OUTRAS_ATIV,OUTRAS_INFO) values (?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
           
            //
            std.setDate(1, new Date(dependentes.getDataCadastro().getTime()));  //transformar date java em date sql
            std.setString(2, dependentes.getNomeMae());
            std.setDate(3, new Date(dependentes.getDataMae().getTime())); 
            std.setString(4, dependentes.getProfissaoMae());
            std.setString(5, dependentes.getRgMae());
            std.setString(6, dependentes.getCpfMae());
            std.setString(7, dependentes.getNomePai());
            std.setDate(8, new Date(dependentes.getDataPai().getTime()));  
            std.setString(9, dependentes.getProfissaoPai());
            std.setString(10, dependentes.getRgPai());
            std.setString(11, dependentes.getCpfPai());
            std.setString(12, dependentes.getNomeCrianca());
            std.setDate(13, new Date(dependentes.getDataCrianca().getTime())); 
            std.setFloat(14, dependentes.getRendaFamiliar());
            std.setString(15, dependentes.getNis());
            std.setString(16, dependentes.getCpfCrianca());
            std.setString(17, dependentes.getCorPele());
            std.setString(18, dependentes.getUbs());
            std.setString(19, dependentes.getTerritorio());
            std.setString(20, dependentes.getSexo());
            std.setString(21, dependentes.getEndereco());
            std.setString(22, dependentes.getNumero());
            std.setString(23, dependentes.getCep());
            std.setString(24, dependentes.getComplemento());
            std.setString(25, dependentes.getBairro());
            std.setString(26, dependentes.getCidade());
            std.setString(27, dependentes.getResponsavel());
            std.setString(28, dependentes.getGrauParentesco());
            std.setString(29, dependentes.getTelCel());
            std.setString(30, dependentes.getTelRec());
            std.setString(31, dependentes.getTelRes());
            std.setString(32, dependentes.getEstuda());
            std.setString(33, dependentes.getSerie());
            std.setString(34, dependentes.getMotivoEstudo());
            std.setString(35, dependentes.getNomeEscola());
            std.setString(36, dependentes.getPeriodoEscolar());
            std.setString(37, dependentes.getComposicao());
            std.setString(38, dependentes.getSaudeMental());
            std.setInt(39, dependentes.getQualSaude());
            std.setString(40, dependentes.getOutrasSaude());
            std.setString(41, dependentes.getInternado());
            std.setString(42, dependentes.getQuando());
            std.setString(43, dependentes.getQualInternado());
            std.setString(44, dependentes.getMedicamento());
            std.setString(45, dependentes.getQualMedica());
            std.setString(46, dependentes.getConvulsao());
            std.setString(47, dependentes.getQuandoConvulsao());
            std.setString(48, dependentes.getDescricao());
            std.setString(49, dependentes.getAlergia());
            std.setString(50, dependentes.getQualAlergia());
            std.setString(51, dependentes.getRelFamilia());
            std.setString(52, dependentes.getRelEscola());
            std.setString(53, dependentes.getAtividades());
            std.setString(54, dependentes.getOutrasAti());
            std.setString(55, dependentes.getOutrasInfo());

            salvado = std.executeUpdate();
           
            banco.fechar(conexao);
        } catch (SQLException | RuntimeException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
       if(salvado == 1){
          return true;
       } 
       else{
           return false;
       }
    }

    /**
     * Metodo que atualiza todos os campos do formulario de cadastro de
     * dependentes no banco segundo o codigo do dependente,recebe o objeto da
     * classe Dependentes e devolve valor boolean para tratamento.Possibilidade de ter erro com sql por erro de
     * conexao e de operacoes dml do sql.Possibilidade de ter erro com sql por erro de
     * conexao e de operacoes dml do sql
     *
     * @param dependentes Quando o usuario enviar os dados do formulario para o
     * banco sera por este paramento
     * @return  boolean
     */
    public boolean atualizarDependentes(Dependentes dependentes) {
{        int atualizado =  0;
        try {
            conexao = banco.getConexao();
            String sql = "update DEPENDENTES set NOME_MAE=?,DATA_NASC_MAE=?,PROFISSAO_MAE=?, "
                    + "RG_MAE=?,CPF_MAE=?,NOME_PAI=?,DATA_NASC_PAI=?,PROFISSAO_PAI=?,RG_PAI=?, "
                    + "CPF_PAI=?,NOME=?,DATA_NASC=?,RENDA_FAMILIAR=?,NIS=?,CPF=?,COR=?,UBS=?,TERRITORIO=?, "
                    + "SEXO=?,ENDERECO=?,NUMERO=?,CEP=?,COMPLEMENTO=?,BAIRRO=?,CIDADE=?,RESPONSAVEL=?,PARENTESCO=?, "
                    + "TELEFONE1=?,TELEFONE2=?,TELEFONE3=?,ESTUDA=?,SERIE=?,PORQUE=?,ESCOLA=?,PERIODO=?,COMPOSICAO=?, "
                    + "SAUDE_MENTAL=?,QUAL_SAUDE=?,OUTROS=?,INTERNADO=?,QUANDO=?,QUAL_INTERNADO=?,MEDICAMENTO=?, "
                    + "QUAL_MEDICA=?,COMVULSOES=?,QUANDO_COMVUL=?,DESCREVA=?,ALERGIA=?,QUAL_ALERGIA=?,REL_FAMILIA=?, "
                    + "REL_ESCOLA=?,ATIVIDADES=?,OUTRAS_ATIV=?,OUTRAS_INFO=? where ID_DEPENDENTES=?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            
            std.setString(1, dependentes.getNomeMae());
            std.setDate(2, new Date(dependentes.getDataMae().getTime()));  //transformar date java em date sql
            std.setString(3, dependentes.getProfissaoMae());
            std.setString(4, dependentes.getRgMae());
            std.setString(5, dependentes.getCpfMae());
            std.setString(6, dependentes.getNomePai());
            std.setDate(7, new Date(dependentes.getDataPai().getTime())); 
            std.setString(8, dependentes.getProfissaoPai());
            std.setString(9, dependentes.getRgPai());
            std.setString(10, dependentes.getCpfPai());
            std.setString(11, dependentes.getNomeCrianca());
            std.setDate(12, new Date(dependentes.getDataCrianca().getTime())); 
            std.setFloat(13, dependentes.getRendaFamiliar());
            std.setString(14, dependentes.getNis());
            std.setString(15, dependentes.getCpfCrianca());
            std.setString(16, dependentes.getCorPele());
            std.setString(17, dependentes.getUbs());
            std.setString(18, dependentes.getTerritorio());
            std.setString(19, dependentes.getSexo());
            std.setString(20, dependentes.getEndereco());
            std.setString(21, dependentes.getNumero());
            std.setString(22, dependentes.getCep());
            std.setString(23, dependentes.getComplemento());
            std.setString(24, dependentes.getBairro());
            std.setString(25, dependentes.getCidade());
            std.setString(26, dependentes.getResponsavel());
            std.setString(27, dependentes.getGrauParentesco());
            std.setString(28, dependentes.getTelCel());
            std.setString(29, dependentes.getTelRec());
            std.setString(30, dependentes.getTelRes());
            std.setString(31, dependentes.getEstuda());
            std.setString(32, dependentes.getSerie());
            std.setString(33, dependentes.getMotivoEstudo());
            std.setString(34, dependentes.getNomeEscola());
            std.setString(35, dependentes.getPeriodoEscolar());
            std.setString(36, dependentes.getComposicao());
            std.setString(37, dependentes.getSaudeMental());
            std.setInt(38, dependentes.getQualSaude());
            std.setString(39, dependentes.getOutrasSaude());
            std.setString(40, dependentes.getInternado());
            std.setString(41, dependentes.getQuando());
            System.out.println(dependentes.getQuando());
            std.setString(42, dependentes.getQualInternado());
            std.setString(43, dependentes.getMedicamento());
            std.setString(44, dependentes.getQualMedica());
            std.setString(45, dependentes.getConvulsao());
            std.setString(46, dependentes.getQuandoConvulsao());
            std.setString(47, dependentes.getDescricao());
            std.setString(48, dependentes.getAlergia());
            std.setString(49, dependentes.getQualAlergia());
            std.setString(50, dependentes.getRelFamilia());
            std.setString(51, dependentes.getRelEscola());
            std.setString(52, dependentes.getAtividades());
            std.setString(53, dependentes.getOutrasAti());
            std.setString(54, dependentes.getOutrasInfo());
            std.setInt(55, dependentes.getCodigo());
            
            atualizado = std.executeUpdate();
            banco.fechar(conexao);

        } catch (SQLException | NullPointerException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
        if (atualizado == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que excluir todos os campos do formulario de cadastro de
     * dependentes no banco segundo o codigo do dependente,recebe o objeto da
     * classe Dependentes e devolve valor boolean para tratamento.Possibilidade de ter erro com sql por erro de
     * conexao e de operacoes dml do sql
     *
     * @exception SQLException Possibilidade de ter erro com sql por erro de
     * conexao e de operacoes dml do sql
     * @param dependentes Quando o usuario enviar os dados do formulario para o
     * banco sera por este paramento
     * @return boolean  
     */
    }
    public boolean excluirDependentes(Dependentes dependentes) {
        int excluido = 0;
        try {
            conexao = banco.getConexao();
            String sql = "delete from DEPENDENTES where ID_DEPENDENTES = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setInt(1, dependentes.getCodigo());
            
            excluido = std.executeUpdate();
            banco.fechar(conexao);
        } catch (SQLException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
        if (excluido == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que consulta somente os campos dos dados da mae no formulario de
     * cadastro de dependentes de acordo com o nome correspondente,recebe e
     * devolve o objeto da classe Dependentes.Possibilidade de ter erro com sql por erro de
     * conexao e de operacoes dml do sql.
     *
     * @param dependentes  Quando o usuario pesquisar pelo objeto dependentes sera pela cpf da mae
     * @return Dependentes
     */
    public Dependentes consultarMae(Dependentes dependentes)  {
        try {
            conexao = banco.getConexao();

            String sql = "select * from DEPENDENTES where CPF_MAE = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);

            std.setString(1, dependentes.getCpfMae());
            ResultSet rs = std.executeQuery();
            while (rs.next()) {
                dependentes.setNomeMae(rs.getString("NOME_MAE"));
                dependentes.setDataMae(rs.getDate("DATA_NASC_MAE"));
                dependentes.setProfissaoMae(rs.getString("PROFISSAO_MAE"));
                dependentes.setRgMae(rs.getString("RG_MAE"));
                dependentes.setCpfMae(rs.getString("CPF_MAE"));
            }
            banco.fechar(conexao);
        } catch (SQLException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();

        }

        return dependentes;
    }

    /**
     * Metodo que consulta somente os campos dos dados do pai no formulario de
     * cadastro de dependentes de acordo com o nome correspondente,recebe e
     * devolve o objeto da classe.Possibilidade de ter erro com sql por erro de
     * conexao e de operacoes dml do sql.
     *
     * @param dependentes  Quando o usuario pesquisar pelo objeto dependentes sera pela cpf do pai
     * @return Dependentes
     */
    public Dependentes consultarPai(Dependentes dependentes)  {
        try {
            conexao = banco.getConexao();

            String sql = "select * from DEPENDENTES where CPF_PAI = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);

            std.setString(1, dependentes.getCpfPai());
            ResultSet rs = std.executeQuery();
            while (rs.next()) {
                dependentes.setNomePai(rs.getString("NOME_PAI"));
                dependentes.setDataPai(rs.getDate("DATA_NASC_PAI"));
                dependentes.setProfissaoPai(rs.getString("PROFISSAO_PAI"));
                dependentes.setRgPai(rs.getString("RG_PAI"));
                dependentes.setCpfPai(rs.getString("CPF_PAI"));
            }
            banco.fechar(conexao);
        } catch (SQLException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();

        }
        return dependentes;
    }

    /**
     * Metodo que consulta somente os campos dos dados da crianca no formulario
     * de cadastro de dependentes de acordo com o nome correspondente,recebe e
     * devolve o objeto da classe.Possibilidade de ter erro com sql por erro de
     * conexao e de operacoes dml do sql.
     *
     * @param dependentes Quando o usuario pesquisar pelo objeto dependentes sera pela cpf da crianca
     * @return Dependentes
     */
    public Dependentes consultarCrianca(Dependentes dependentes) {
        try {
            conexao = banco.getConexao();

            String sql = "select * from DEPENDENTES where CPF = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setString(1, dependentes.getCpfCrianca());
            ResultSet rs = std.executeQuery();
            while (rs.next()) {
                dependentes.setNomeCrianca(rs.getString("NOME"));
                dependentes.setDataCrianca(rs.getDate("DATA_NASC"));
                dependentes.setRendaFamiliar(rs.getFloat("RENDA_FAMILIAR"));
                dependentes.setNis(rs.getString("NIS"));
                dependentes.setCorPele(rs.getString("COR"));
                dependentes.setUbs(rs.getString("UBS"));
                dependentes.setTerritorio(rs.getString("TERRITORIO"));
                dependentes.setSexo(rs.getString("SEXO"));
            }
            banco.fechar(conexao);
        } catch (SQLException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();

        }
        return dependentes;
    }
}
