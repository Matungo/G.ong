/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxsistemaong.DAO;

import fxsistemaong.Objeto.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    
    public FuncionarioDAO(Banco banco) {
        this.banco = banco;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean inserir(Funcionario func) throws SQLException {
        System.out.println("  TESTE  !!!");
        /*  sql = "SELECT * FROM FUNCIONARIO WHERE NOME = '"
            + obj.getNome()+ "'";
        conexao = banco.getConexao();
        st = conexao.createStatement(); //cria o statement
        rs = st.executeQuery(sql); //executa o select e joga dentro do resultset
        
        if(rs.next()) {
            banco.fechar(conexao);
        } else {
            sql = "INSERT INTO FUNCIONARIO(DATA_INICIO, " + 
                    "NOME, " +  
                    "RG, " + 
                    "CPF, " +
                    "EMAIL," +
                    "SEXO," +
                    "ENDERECO," +
                    "NUMERO," +
                    "CEP," +
                    "COMPLEMENTO," +
                    "BAIRRO," +
                    "CIDADE," +
                    "TELEFONE1," +
                    "TELEFONE2," +
                    "TELEFONE3," +
                    "PERMISSAO," +
                    "APTIDOES," +
                    "DISPONIBILIDADE_DIA," +
                    "DISPONIBILIDADE_HORA," +
                    "DISPONIBILIDADE_SAB) VALUES ('" +
                    obj.getDatacadastro()+ "', '" +
                    obj.getNome()+ "', '" +
                    obj.getRg()+ "', '" + 
                    obj.getCpf()+ "', '" + 
                    obj.getEmail()+ "','" +
                    obj.getSexo()+ "','" +
                    obj.getEndereço()+ "','" +
                    obj.getNumero()+ "','" +
                    obj.getCep()+ "','" +
                    obj.getComplemento()+ "','" +
                    obj.getBairro()+ "','" +
                    obj.getCidade()+ "','" +
                    obj.getFone1()+ "','" +
                    obj.getFone2()+ "','" +
                    obj.getFone3()+ "','" +
                    obj.getAptidoes()+ "','" +
                    obj.getDisp_dia()+ "','" +
                    obj.getDisp_hora()+ "','" +
                    obj.getDisp_sab() + "')";
            
            st.execute(sql); //executa o insert
            
            banco.fechar(conexao);
            return true;
        } */
        return false;
    }
}

