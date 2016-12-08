package fxsistemaong.DAO;

import fxsistemaong.Objeto.Beneficiario;
import fxsistemaong.Objeto.Emprestimo;
import fxsistemaong.Objeto.Livro;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo
 */
public class EmprestimoDAO {
    
    Banco banco = new Banco("root", "123456", "localhost", "g_ong", 3306);
    Connection conexao;
    
    public Beneficiario buscarBeneficiario(Beneficiario beneficiario){
        
        try{
            conexao = banco.getConexao();
            String sql = "select NOME, CPF from BENEFICIARIOS where ID_BENEFICIARIO = ?";
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            statement.setDouble(1, beneficiario.getCodigo());
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                beneficiario.setNome(resultSet.getString("NOME"));
                beneficiario.setCpf(resultSet.getString("CPF"));
            }
            
        }catch(SQLException | RuntimeException sql){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
            
        return beneficiario;
    }
    
    public Livro buscarLivro(Livro livro){
        
        try{
            conexao = banco.getConexao();
            String sql = "select TITULO_LIVRO, PUBLICACAO, EDITORA_LIVRO, CATEGORIA, QTD, FORMATO "
                    + "from LIVRO where ISBN = ?";
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            statement.setLong(1, livro.getIsbn());
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                livro.setTitulo(resultSet.getString("TITULO_LIVRO"));
                java.util.Date utilData = resultSet.getTimestamp("PUBLICACAO"); //data sql para data java
                livro.setPublicao(utilData);
                livro.setEditora(resultSet.getString("EDITORA_LIVRO"));
                livro.setCategoria(resultSet.getString("CATEGORIA"));
                livro.setQtd(resultSet.getInt("QTD"));
                livro.setFormato(resultSet.getString("FORMATO"));
                
            }
            
        }catch(SQLException | RuntimeException sql){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
            
        return livro;
    }
    
    public boolean registrarEmprestimo(Emprestimo emprestimo){
        
        int salvo = 0;
        
        try{
            conexao = banco.getConexao();
            
            String sql = "insert into EMPRESTIMO (ID_BENEFICIARIO, ISBN, TITULO_LIVRO, "
                    + "DATA_RETIRADA, DATA_DEVOLUCAO) values (?,?,?,?,?)";
            
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            
            statement.setInt(1, emprestimo.getIdBeneficiario());
            statement.setLong(2, emprestimo.getIsbn());
            statement.setString(3, emprestimo.getTitulo());
            //gravar datas
            Date sqlDate = new Date(emprestimo.getDataRetirada().getTime()); //Conversão de util.Date para sql.Date 
            statement.setDate(4, sqlDate);
            Date sqlDate2 = new Date(emprestimo.getDataDevolucao().getTime()); //Conversão de util.Date para sql.Date 
            statement.setDate(5, sqlDate2);
                        
            salvo = statement.executeUpdate();
            
            banco.fechar(conexao);
            
        }catch (SQLException | RuntimeException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
        if(salvo == 1){
          return true;
        } 
        else{
           return false;
        }
        
    }
    
    public ArrayList<Emprestimo> buscarEmprestimo(Beneficiario beneficiario){
        
        ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
        //Emprestimo emprestimo = new Emprestimo(); 
        
        Emprestimo[] emps;
        emps = new Emprestimo[20];
        for(int i = 0; i < 20; i++){
            emps[i] = new Emprestimo();
        }
        
        int i = 0;
        
        try{
            conexao = banco.getConexao();
            String sql = "select ISBN, TITULO_LIVRO, DATA_RETIRADA, DATA_DEVOLUCAO, COD_EMPRESTIMO, ID_BENEFICIARIO"
                    + " from EMPRESTIMO where ID_BENEFICIARIO = ?";
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            statement.setLong(1, beneficiario.getCodigo());
            ResultSet resultSet = statement.executeQuery();
                                    
            while(resultSet.next()){
                emps[i].setIsbn(resultSet.getLong("ISBN"));
                emps[i].setTitulo(resultSet.getString("TITULO_LIVRO"));
                
                java.util.Date utilData1 = resultSet.getTimestamp("DATA_RETIRADA"); //data sql para data java
                emps[i].setDataRetirada(utilData1);
                
                java.util.Date utilData2 = resultSet.getTimestamp("DATA_DEVOLUCAO"); //data sql para data java
                emps[i].setDataDevolucao(utilData2);
                
                emps[i].setCodEmprestimo(resultSet.getInt("COD_EMPRESTIMO"));
                emps[i].setIdBeneficiario(resultSet.getInt("ID_BENEFICIARIO"));
                              
                emprestimos.add(i, emps[i]);
                i++;
            }
            
        }catch(SQLException | RuntimeException sql){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
            
        return emprestimos;
        //return emprestimo;
        
    }
    
    public boolean finalizarEmprestimo(Emprestimo emprestimo){
        int excluido = 0;
        try{
            conexao = banco.getConexao();
            String sql = "delete from EMPRESTIMO where COD_EMPRESTIMO = ?";
            PreparedStatement statement;   
            statement = conexao.prepareStatement(sql);
            statement.setDouble(1, emprestimo.getCodEmprestimo());

            excluido = statement.executeUpdate();
            banco.fechar(conexao);
            
        }catch(SQLException sql){
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
    
    public boolean validarDisponibilidade(Emprestimo emprestimo){
        
        int booleana=0;
        int qtd = 0;
        
        try{
            conexao = banco.getConexao();
            String sql = "select QTD from LIVRO where ISBN = ?";
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            statement.setLong(1, emprestimo.getIsbn());
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                qtd = (resultSet.getInt("QTD"));
                if(qtd > 0){
                    booleana = 1;
                }
                else
                    booleana = 0;                
            }
            else
                booleana = 0;
            
        }catch(SQLException | RuntimeException sql){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
            
        if(booleana==1){
            return true;}
        else  
            return false;
    }
    
    public boolean debitarLivro(Emprestimo emprestimo){
        
        int alterado = 0;
        
        try{
            conexao = banco.getConexao();
            String sql = "update LIVRO set QTD=QTD-1 where ISBN=?";
            
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            
            statement.setLong(1, emprestimo.getIsbn());
                                    
            alterado = statement.executeUpdate();
            banco.fechar(conexao);
            
        }catch(SQLException | RuntimeException sql){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
        
        if (alterado == 1){
            return true;
        }else{
            return false;
        }
        
    }
    
    public boolean creditarLivro(Emprestimo emprestimo){
        
        int alterado = 0;
        
        try{
            conexao = banco.getConexao();
            String sql = "update LIVRO set QTD=QTD+1 where ISBN=?";
            
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            
            statement.setLong(1, emprestimo.getIsbn());
                                    
            alterado = statement.executeUpdate();
            banco.fechar(conexao);
            
        }catch(SQLException | RuntimeException sql){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
        
        if (alterado == 1){
            return true;
        }else{
            return false;
        }
        
    }
    
}
