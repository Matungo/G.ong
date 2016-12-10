/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxsistemaong.DAO;

import fxsistemaong.Objeto.Beneficiario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author Luciano
 */
public class BeneficiarioDAO {

    private Banco banco = new Banco("root", "", "localhost", "g_ong", 3306);
    Connection conexao;

    public Beneficiario pesquisarBeneficiario(Beneficiario beneficiario) {
        try {
            conexao = banco.getConexao();
            String sql = "select * from BENEFICIARIOS where ID_BENEFICIARIO = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setInt(1, beneficiario.getCodigo());
            ResultSet rs = std.executeQuery();
            while (rs.next()) {

                beneficiario.setDataCadastro(rs.getDate("DATA_INICIO"));
                beneficiario.setNome(rs.getString("NOME"));
                beneficiario.setDataNascimento(rs.getDate("DATA_NASC"));
                beneficiario.setRg(rs.getString("RG"));
                beneficiario.setNis(rs.getString("NIS"));
                beneficiario.setCpf(rs.getString("CPF"));
                beneficiario.setEmail(rs.getString("EMAIL"));
                beneficiario.setSexo(rs.getString("SEXO"));
                beneficiario.setEndereco(rs.getString("ENDERECO"));
                beneficiario.setNumero(Integer.parseInt(rs.getString("NUMERO")));
                beneficiario.setCEP(rs.getString("CEP"));
                beneficiario.setComplemento(rs.getString("COMPLEMENTO"));
                beneficiario.setBairro(rs.getString("BAIRRO"));
                beneficiario.setCidade(rs.getString("CIDADE"));
                beneficiario.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
                beneficiario.setFilhos(rs.getInt("FILOS"));
                beneficiario.setQtdeFilhos(rs.getInt("QTD_FILHOS"));
                beneficiario.setIdade(Integer.parseInt(rs.getString("IDADES")));
                beneficiario.setTelRes(rs.getString("TELEFONE1"));
                beneficiario.setTelRec(rs.getString("TELEFONE2"));
                beneficiario.setTelCel(rs.getString("TELEFONE3"));
                beneficiario.setProfissao(rs.getString("PROFISSAO"));
                beneficiario.setRendaFamiliar(rs.getFloat("RENDA_FAMILIAR"));
                beneficiario.setInteresse(rs.getInt("INTERESSE"));
                beneficiario.setQual(rs.getString("OUTROS"));
                beneficiario.setObs(rs.getString("OBS"));

            }
            banco.fechar(conexao);
        } catch (SQLException | RuntimeException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();

        }
        return beneficiario;
    }

    public boolean salvarBeneficiarios(Beneficiario beneficiario) {
        int salvado = 0;
        try {

            conexao = banco.getConexao();
            String sql = "insert into BENEFICIARIOS (DATA_INICIO,NOME,DATA_NASC,RG,NIS,CPF,EMAIL, "
                    + "SEXO,ENDERECO,NUMERO,CEP,COMPLEMENTO,BAIRRO,CIDADE,ESTADO_CIVIL,FILOS, "
                    + "QTD_FILHOS,IDADES,TELEFONE1,TELEFONE2,TELEFONE3,PROFISSAO,RENDA_FAMILIAR, "
                    + "INTERESSE,OUTROS,OBS) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                   
            PreparedStatement std;
            std = conexao.prepareStatement(sql);

            std.setDate(1, new Date(beneficiario.getDataCadastro().getTime()));;
            std.setString(2, beneficiario.getNome());
            std.setDate(3, new Date(beneficiario.getDataNascimento().getTime()));; 
            std.setString(4, beneficiario.getRg());
            std.setString(5, beneficiario.getNome());
            std.setString(6, beneficiario.getCpf());
            std.setString(7, beneficiario.getEmail());
            std.setString(8, beneficiario.getSexo());
            std.setString(9, beneficiario.getEndereco());
            std.setInt(10, beneficiario.getNumero());
            std.setString(11, beneficiario.getCEP());
            std.setString(12, beneficiario.getComplemento());
            std.setString(13, beneficiario.getBairro());
            std.setString(14, beneficiario.getCidade());
            std.setString(15, beneficiario.getEstadoCivil());
            std.setInt(16, beneficiario.getFilhos());
            std.setInt(17, beneficiario.getQtdeFilhos());
            std.setInt(18, beneficiario.getIdade());
            std.setString(19, beneficiario.getTelRes());
            std.setString(20, beneficiario.getTelRec());
            std.setString(21, beneficiario.getTelCel());
            std.setString(22, beneficiario.getProfissao());
            std.setFloat(23, beneficiario.getRendaFamiliar());
            std.setInt(24, beneficiario.getInteresse());
            std.setString(25, beneficiario.getQual());
            std.setString(26, beneficiario.getObs());

            salvado = std.executeUpdate();

            banco.fechar(conexao);
        } catch (SQLException | RuntimeException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
        if (salvado == 1) {
            return true;
        } else {
            return false;
        }
    }
    public boolean atualizarBeneficiario(Beneficiario beneficiario){
        int atualizado =  0;
        try {                   
            conexao = banco.getConexao();
            String sql = "update BENEFICIARIOS set NOME=?,DATA_NASC=?,RG=?,NIS=?,CPF=?,EMAIL=?, "
                    + "SEXO=?,ENDERECO=?,NUMERO=?,CEP=?,COMPLEMENTO=?,BAIRRO=?,CIDADE=?,ESTADO_CIVIL=?, "
                    + "FILOS=?,QTD_FILHOS=?,IDADES=?,TELEFONE1=?,TELEFONE2=?,TELEFONE3=?,PROFISSAO=?, "
                    + "RENDA_FAMILIAR=?,INTERESSE=?,OUTROS=?,OBS=? where ID_BENEFICIARIO=?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            //
            std.setString(1, beneficiario.getNome());
            std.setDate(2, new Date(beneficiario.getDataNascimento().getTime()));; 
            std.setString(3, beneficiario.getRg());
            std.setString(4, beneficiario.getNome());
            std.setString(5, beneficiario.getCpf());
            std.setString(6, beneficiario.getEmail());
            std.setString(7, beneficiario.getSexo());
            std.setString(8, beneficiario.getEndereco());
            std.setInt(9, beneficiario.getNumero());
            std.setString(10, beneficiario.getCEP());
            std.setString(11, beneficiario.getComplemento());
            std.setString(12, beneficiario.getBairro());
            std.setString(13, beneficiario.getCidade());
            std.setString(14, beneficiario.getEstadoCivil());
            std.setInt(15, beneficiario.getFilhos());
            std.setInt(16, beneficiario.getQtdeFilhos());
            std.setInt(17, beneficiario.getIdade());
            std.setString(18, beneficiario.getTelRes());
            std.setString(19, beneficiario.getTelRec());
            std.setString(20, beneficiario.getTelCel());
            std.setString(21, beneficiario.getProfissao());
            std.setFloat(22, beneficiario.getRendaFamiliar());
            std.setInt(23, beneficiario.getInteresse());
            std.setString(24, beneficiario.getQual());
            std.setString(25, beneficiario.getObs());
            std.setInt(26, beneficiario.getCodigo());
            
            atualizado = std.executeUpdate();

            banco.fechar(conexao);
        } catch (SQLException | RuntimeException sql) {
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
     public boolean excluirDependentes(Beneficiario beneficiario) {
        int excluido = 0;
        try {
            conexao = banco.getConexao();
            String sql = "delete from BENEFICIARIOS where ID_BENEFICIARIO = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setInt(1, beneficiario.getCodigo());
            
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
    public Beneficiario consultarBeneficiario(Beneficiario beneficiario) {
        try {
            conexao = banco.getConexao();
            String sql = "select * from BENEFICIARIOS where CPF = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setString(1, beneficiario.getCpf());
            ResultSet rs = std.executeQuery();
            while (rs.next()) {

                beneficiario.setNome(rs.getString("NOME"));
                beneficiario.setDataNascimento(rs.getDate("DATA_NASC"));
                beneficiario.setRg(rs.getString("RG"));
                beneficiario.setNis(rs.getString("NIS"));
                beneficiario.setCpf(rs.getString("CPF"));
                beneficiario.setEmail(rs.getString("EMAIL"));
                beneficiario.setSexo(rs.getString("SEXO"));

            }
            banco.fechar(conexao);
        } catch (SQLException | RuntimeException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();

        }
        return beneficiario;
    }
}
