package fxsistemaong.DAO;

import fxsistemaong.Objeto.Funcionario;
import fxsistemaong.Objeto.Oficina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Thiago Lopes
 */
public class OficinasDAO {
    //parâmetros para conexão com o banco
    Banco banco = new Banco("root","","127.0.0.1","G_ONG",3306);
    Connection conexao;
    
    public boolean InserirNovaOficina(Oficina oficina){
        int Salvo=0;
        try{
            conexao = banco.getConexao();
            String sql = "INSERT into OFICINAS (NOME) values (?)";
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            
            statement.setString(1, oficina.getNomeOficina());
            
            Salvo = statement.executeUpdate();
            banco.fechar(conexao);
        }catch(SQLException | RuntimeException sql){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();            
        }
        if(Salvo == 1){
            return true;
        } else{
            return false;
        }
    }
 
    
    public List<Oficina> ListarOficina(){
        String sql= "SELECT * from OFICINAS";
        List <Oficina> retorno = new ArrayList<Oficina>();
        try{
        conexao = banco.getConexao();

           PreparedStatement std = conexao.prepareStatement(sql);
           ResultSet rs = std.executeQuery();
           while(rs.next()){
               Oficina addOficina = new Oficina();

               addOficina.setNomeOficina(rs.getString("NOME"));
               
               retorno.add(addOficina);
               //banco.fechar(conexao);
           }
           banco.fechar(conexao);
       }catch(SQLException ex){
                   Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                   }
            
       return retorno;
    }
    
    public List<Funcionario> ListarInstrutor(){
        String sql= "SELECT * from FUNCIONARIO";
        List <Funcionario> retorno = new ArrayList<Funcionario>();
        try{
        conexao = banco.getConexao();

           PreparedStatement std = conexao.prepareStatement(sql);
           ResultSet rs = std.executeQuery();
           while(rs.next()){
               Funcionario addFuncionario = new Funcionario();

               addFuncionario.setNome(rs.getString("NOME"));
               
               retorno.add(addFuncionario);
           }
           banco.fechar(conexao);
       }catch(SQLException ex){
                   Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                   }
            
       return retorno;
    }
    
    public List<Funcionario> ListarHorario(){
        //nome.getNome();
        String sql= "SELECT * from FUNCIONARIO";
        List <Funcionario> retorno = new ArrayList<Funcionario>();
        try{
        conexao = banco.getConexao();

           PreparedStatement std = conexao.prepareStatement(sql);
           ResultSet rs = std.executeQuery();
           while(rs.next()){
               Funcionario addFuncionario = new Funcionario();

               addFuncionario.setDisp_dia(rs.getString("DISPONIBILIDADE_DIA"));
               //addFuncionario.setDisp_sab(rs.getString("DISPONIBILIDADE_SABADO"));
               
               retorno.add(addFuncionario);
           }
           banco.fechar(conexao);
       }catch(SQLException ex){
                   Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                   }
            
       return retorno;
    }
    
    public void ExcluirOficina(){
        
    }
    
    public void InserirNovaAberturaOficina(){
        
    }
    
    public void BuscarOficinasAbertas(){
        
    }
    
    public void ModificarOficinaAberta(){
        
    }
    
    public void ExcluirOficinaAberta(){
        
    }
    
    public void InserirAlunoOficina(){
        
    }
    
    public void ExcluirAlunoOficina(){
        
    }
 
}
