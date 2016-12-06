package fxsistemaong.DAO;

import fxsistemaong.Objeto.AberturaOficina;
import fxsistemaong.Objeto.Beneficiario;
import fxsistemaong.Objeto.Funcionario;
import fxsistemaong.Objeto.InclusaoAlunoOficinaAberta;
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

    
    public boolean AdicionarOficinaAberta(AberturaOficina oficina){
        int salvo=0;
        try{
            conexao = banco.getConexao();
            String sql = "INSERT into ABERTURA_OFICINAS (COD_OFICINA, NOME, INSTRUTOR, DIA_SEMANA, HORARIOCOMECA, HORARIOTERMINA)"
                    + "value (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            
            statement.setInt(1, oficina.getCodOficina());
            statement.setString(2, oficina.getNome());
            statement.setString(3, oficina.getInstrutor());
            statement.setString(4, oficina.getDiaSemana());
            statement.setString(5 , oficina.getHorarioComeca());
            statement.setString(6 , oficina.getHorarioTermina());
            
            salvo = statement.executeUpdate();
            banco.fechar(conexao);
        }catch(SQLException | RuntimeException sql){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
        if(salvo == 1){
            return true;
        } else{
            return false;
        }
    }
    
    public List<AberturaOficina> Listar(){
        String sql= "SELECT * from ABERTURA_OFICINAS where COD_OFICINA > 0";
        List <AberturaOficina> retorno = new ArrayList<AberturaOficina>();
        try{
        conexao = banco.getConexao();

           PreparedStatement std = conexao.prepareStatement(sql);
           ResultSet rs = std.executeQuery();
           
           while(rs.next()){
               AberturaOficina oficina = new AberturaOficina();
               oficina.setCodOficina(rs.getInt("COD_OFICINA"));
               oficina.setNome(rs.getString("NOME"));
               oficina.setInstrutor(rs.getString("INSTRUTOR"));
               oficina.setDiaSemana(rs.getString("DIA_SEMANA"));
               oficina.setHorarioComeca(rs.getString("HORARIOCOMECA"));
               oficina.setHorarioTermina(rs.getString("HORARIOTERMINA"));
               
               retorno.add(oficina);
           }
           banco.fechar(conexao);
       }catch(SQLException ex){
                   Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                   }      
       return retorno;
    }
    
    public boolean ExcluirOficina(int CodOficinaAberta){
        int excluido = 0;
        try {
            conexao = banco.getConexao();
            String sql = "delete from ABERTURA_OFICINAS where COD_OFICINA = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setInt(1, CodOficinaAberta);
            
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
    
    public boolean AtualizarOficinaAberta(AberturaOficina oficina, int CodOficinaAberta){
         int atualizado = 0;
            try{
                   conexao = banco.getConexao();
                   String sql = "UPDATE ABERTURA_OFICINAS set NOME =?,INSTRUTOR=?, "
                    + "DIA_SEMANA=?, HORARIOCOMECA=?, HORARIOTERMINA=? WHERE COD_OFICINA=? ";
                PreparedStatement std;
                std = conexao.prepareStatement(sql);
            
                // Parametros para troca de dados entre o banco e os campos
                std.setString(1, oficina.getNome());
                std.setString(2, oficina.getInstrutor());
                std.setString(3, oficina.getDiaSemana());
                std.setString(4, oficina.getHorarioComeca());
                std.setString(5, oficina.getHorarioTermina());
                std.setInt(6, CodOficinaAberta);
                
                
                atualizado = std.executeUpdate();
                banco.fechar(conexao);
        }catch (SQLException | NullPointerException sql){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
        if (atualizado == 1){
            return true;
        }else{
            return false;
        }
    }
    
    public Beneficiario pesquisarBeneficiarioCPF(Beneficiario beneficiario) {
        try {
            conexao = banco.getConexao();
            String sql = "select * from BENEFICIARIOS where CPF = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setString(1, beneficiario.getCpf());
            ResultSet rs = std.executeQuery();
            while (rs.next()) {

                beneficiario.setCodigo(rs.getInt("ID_BENEFICIARIO"));
                beneficiario.setNome(rs.getString("NOME"));
                beneficiario.setDataNascimento(rs.getDate("DATA_NASC"));
                beneficiario.setTelRes(rs.getString("TELEFONE1"));

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
    
    public Beneficiario pesquisarBeneficiarioCOD(Beneficiario beneficiario) {
        try {
            conexao = banco.getConexao();
            String sql = "select * from BENEFICIARIOS where ID_BENEFICIARIO = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setInt(1, beneficiario.getCodigo());
            ResultSet rs = std.executeQuery();
            while (rs.next()) {
                beneficiario.setNome(rs.getString("NOME"));
                beneficiario.setDataNascimento(rs.getDate("DATA_NASC"));
                beneficiario.setCpf(rs.getString("CPF"));
                beneficiario.setTelRes(rs.getString("TELEFONE1"));

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
    
    
    public List<AberturaOficina> ListarOficinaAberta(){
        String sql= "SELECT * from ABERTURA_OFICINAS";
        List <AberturaOficina> retorno = new ArrayList<AberturaOficina>();
        try{
        conexao = banco.getConexao();

           PreparedStatement std = conexao.prepareStatement(sql);
           ResultSet rs = std.executeQuery();
           while(rs.next()){
               AberturaOficina addOficina = new AberturaOficina();

               addOficina.setNome(rs.getString("NOME"));
               
               retorno.add(addOficina);
          
           }
           banco.fechar(conexao);
       }catch(SQLException ex){
                   Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                   }
            
       return retorno;
    }
    
    public List<AberturaOficina> ListarInstrutorOficinaAberta(AberturaOficina oficina){
        String sql= "SELECT * from ABERTURA_OFICINAS where NOME = ?";
        
        
        List <AberturaOficina> retorno = new ArrayList<AberturaOficina>();
        try{
        conexao = banco.getConexao();
           PreparedStatement std = conexao.prepareStatement(sql);
           std.setString(1, oficina.getNome());
           ResultSet rs = std.executeQuery();
           while(rs.next()){
               AberturaOficina addFuncionario = new AberturaOficina();

               addFuncionario.setInstrutor(rs.getString("INSTRUTOR"));
               
               retorno.add(addFuncionario);
           }
           banco.fechar(conexao);
       }catch(SQLException ex){
                   Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                   }
            
       return retorno;
    }
    
    public List<AberturaOficina> ListarHorarioOficinaAberta(AberturaOficina oficina){
        String sql= "SELECT * from ABERTURA_OFICINAS where NOME = ? and INSTRUTOR = ?";
        
        
        List <AberturaOficina> retorno = new ArrayList<AberturaOficina>();
        try{
        conexao = banco.getConexao();
           PreparedStatement std = conexao.prepareStatement(sql);
           std.setString(1, oficina.getNome());
           std.setString(2, oficina.getInstrutor());
           ResultSet rs = std.executeQuery();
           while(rs.next()){
               AberturaOficina addFuncionario = new AberturaOficina();

               addFuncionario.setHorarioComeca(rs.getString("HORARIOCOMECA"));
               
               retorno.add(addFuncionario);
           }
           banco.fechar(conexao);
       }catch(SQLException ex){
                   Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                   }
            
       return retorno;
    }
    
    public boolean InserirAlunoOficina(InclusaoAlunoOficinaAberta oficina){
        int Salvo=0;
        try{
            conexao = banco.getConexao();
            String sql = "INSERT into INCLUSAO_ALUNO (OFICINA, INSTRUTOR, HORARIO, NOME_ALUNO, CPF, IDADE, TELEFONE) "
                    + "values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            
            statement.setString(1, oficina.getNomeOficina());
            statement.setString(2, oficina.getNomeInstrutor());
            statement.setString(3, oficina.getHorarioComeca());
            statement.setString(4, oficina.getNomeAluno());
            statement.setString(5, oficina.getCPFAluno());
            statement.setInt(6, oficina.getIdadeAluno());
            statement.setString(7, oficina.getTelefoneAluno());
            
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
    
    public List<InclusaoAlunoOficinaAberta> ListarListaChamadas(String oficinaSel){
        String sql= "SELECT * from INCLUSAO_ALUNO where OFICINA = ?";
        List <InclusaoAlunoOficinaAberta> retorno = new ArrayList<InclusaoAlunoOficinaAberta>();
        try{
        conexao = banco.getConexao();

           PreparedStatement std = conexao.prepareStatement(sql);
           std.setString(1, oficinaSel);
           ResultSet rs = std.executeQuery();
           
           while(rs.next()){
               InclusaoAlunoOficinaAberta oficina = new InclusaoAlunoOficinaAberta();
               oficina.setCod(rs.getInt("COD_CLASSE"));
               oficina.setNomeOficina(rs.getString("OFICINA"));
               oficina.setNomeInstrutor(rs.getString("INSTRUTOR"));
               oficina.setHorarioComeca(rs.getString("HORARIO"));
               oficina.setNomeAluno(rs.getString("NOME_ALUNO"));
               oficina.setCPFAluno(rs.getString("CPF"));
               oficina.setIdadeAluno(rs.getInt("IDADE"));
               oficina.setTelefoneAluno(rs.getString("TELEFONE"));
               
               
               retorno.add(oficina);
           }
           banco.fechar(conexao);
       }catch(SQLException ex){
                   Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                   }      
       return retorno;
    }
    
    public boolean ExcluirAlunoOficina(int CodAlunoOficina){
       int excluido = 0;
        try {
            conexao = banco.getConexao();
            String sql = "delete from INCLUSAO_ALUNO where COD_CLASSE = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setInt(1, CodAlunoOficina);
            
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
 
}
