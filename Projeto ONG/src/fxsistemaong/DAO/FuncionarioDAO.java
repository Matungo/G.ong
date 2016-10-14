/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxsistemaong.DAO;


import fxsistemaong.DAO.Banco;
import fxsistemaong.Objeto.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 *
 * @author giovanni.sanches
 */
public class FuncionarioDAO {

    private Funcionario func;
    private Banco banco;
    private Connection conexao;
    private PreparedStatement pst = null;
    private ResultSet rs;
    private Statement st;
    private String sql=null;

    public boolean SalvarFuncionario(Funcionario func) throws SQLException {//throws SQLException 
        banco =    new Banco("root","123456", "localhost","G_ONG", 3306);
        int salvado = 0;
            try {
                conexao = banco.getConexao();
                String sql = "insert into FUNCIONARIO(NOME, RG, CPF, EMAIL, ENDERECO, NUMERO, BAIRRO, CIDADE, CEP,"
                        + "TELEFONE1, TELEFONE2, TELEFONE3, SEXO, COMPLEMENTO, DISPONIBILIDADE_DIA, DISPONIBILIDADE_SAB)"
                        + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement std;
                std = conexao.prepareStatement(sql);
                std.setString(1, func.getNome());
                std.setString(2, func.getRg());
                std.setString(3, func.getCpf());
                std.setString(4, func.getEmail());
                std.setString(5, func.getEndereco());
                std.setString(6, func.getNumero());
                std.setString(7, func.getBairro());
                std.setString(8, func.getCidade());
                std.setString(9, func.getCep());
                std.setString(10, func.getFone1());
                std.setString(11, func.getFone2());
                std.setString(12, func.getFone3());
                std.setString(13, func.getSexo());
                std.setString(14, func.getComplemento());
                std.setString(15, func.getDisp_dia());
                std.setString(16, func.getDisp_sab());
                salvado = std.executeUpdate();
                banco.fechar(conexao);
            }catch (SQLException | RuntimeException sql) {
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

    public boolean excluirFuncionario(Funcionario func) {
        banco =    new Banco("root","123456", "localhost","G_ONG", 3306);
        int excluido = 0;
        try {
            conexao = banco.getConexao();
            String sql = "delete from FUNCIONARIO WHERE ID_COLABORADOR =" + func.getCod();
            System.out.println(sql);
            PreparedStatement std;
            std = conexao.prepareStatement(sql);            
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

    public Funcionario pesquisarFuncionario(Funcionario func) {
        banco = new Banco("root","123456", "localhost","G_ONG", 3306);
        try {
            conexao = banco.getConexao();
            String sql = "select * from funcionario where ID_COLABORADOR =" + func.getCod();
            PreparedStatement std;         
            std = conexao.prepareStatement(sql);
            ResultSet rs = std.executeQuery();
            while (rs.next()) {
                func.setNome(rs.getString("NOME"));
                func.setRg(rs.getString("RG"));
                func.setCpf(rs.getString("CPF"));
                func.setEmail(rs.getString("EMAIL"));
                func.setEndereco(rs.getString("ENDERECO"));
                func.setNumero(rs.getString("NUMERO"));
                func.setBairro(rs.getString("BAIRRO"));
                func.setCidade(rs.getString("CIDADE"));
                func.setCep(rs.getString("CEP"));
                func.setFone1(rs.getString("TELEFONE1"));
                func.setFone2(rs.getString("TELEFONE2"));
                func.setFone3(rs.getString("TELEFONE3"));
                func.setSexo(rs.getString("SEXO"));
                func.setComplemento(rs.getString("COMPLEMENTO"));
            }
            banco.fechar(conexao);
        } catch (SQLException | RuntimeException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
        return func;
    }

    public Funcionario atualizarFuncionario(Funcionario func) throws SQLException {//throws SQLException  {
        banco =    new Banco("root","123456", "localhost","G_ONG", 3306);
        try {
            conexao = banco.getConexao();
            String sql = "select * from funcionario where ID_COLABORADOR =" + func.getCod();
            PreparedStatement std;         
            std = conexao.prepareStatement(sql);
            ResultSet rs = std.executeQuery();
            while (rs.next()) {
                func.setNome(rs.getString("NOME"));
                func.setRg(rs.getString("RG"));
                func.setCpf(rs.getString("CPF"));
                func.setEmail(rs.getString("EMAIL"));
                func.setEndereco(rs.getString("ENDERECO"));
                func.setNumero(rs.getString("NUMERO"));
                func.setBairro(rs.getString("BAIRRO"));
                func.setCidade(rs.getString("CIDADE"));
                func.setCep(rs.getString("CEP"));
                func.setFone1(rs.getString("TELEFONE1"));
                func.setFone2(rs.getString("TELEFONE2"));
                func.setFone3(rs.getString("TELEFONE3"));
                func.setSexo(rs.getString("SEXO"));
                func.setComplemento(rs.getString("COMPLEMENTO"));
            }
            String sqlu = "update funcionario where ID_COLABORADOR =" + func.getCod()
                    + "set NOME =" + func.getNome() + "RG =" + func.getRg();
            PreparedStatement stdu;         
            
            stdu = conexao.prepareStatement(sqlu);
            ResultSet rsu = stdu.executeQuery();
            banco.fechar(conexao);
        } catch (SQLException | RuntimeException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
        return func;
    }
}