
package fxsistemaong.DAO;

import fxsistemaong.Objeto.Livro;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javafx.scene.control.Alert;

/**
 *
 * @author Danilo
 */
public class LivroDAO {
    //parâmetros para conexão com o banco
    Banco banco = new Banco("root", "", "localhost", "G_ONG", 3306);
    Connection conexao;
    
    //método que grava  no banco as informações sobre um novo livro
    public boolean cadastrarDAO(Livro livro){
        int salvo = 0;
        
        try{
            conexao = banco.getConexao();
            
            String sql = "insert into LIVRO (TITULO_LIVRO, SUBTITULO, AUTOR_LIVRO, "
                    + "AUTOR_LIVRO2, EDITORA_LIVRO, QTD, N_PAG, RESUMO, "
                    + "SUMARIO, FORMATO, CATEGORIA, ISBN, PUBLICACAO) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            
            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getSubtitulo());
            statement.setString(3, livro.getAutor1());
            statement.setString(4, livro.getAutor2());
            statement.setString(5, livro.getEditora());
            statement.setInt(6, livro.getQtd());
            statement.setInt(7, livro.getNumPags());
            statement.setString(8, livro.getResumo());
            statement.setString(9, livro.getSumario());
            statement.setString(10, livro.getFormato());
            statement.setString(11, livro.getCategoria());
            statement.setLong(12, livro.getIsbn());
            Date sqlDate = new Date(livro.getPublicao().getTime()); //Conversão de util.Date para sql.Date 
            statement.setDate(13, sqlDate);
            
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
    
    //método que exclui um registro do banco, de acordo com o ISBN
    public boolean excluirDAO(Livro livro){
        int excluido = 0;
        try{
            conexao = banco.getConexao();
            String sql = "delete from LIVRO where ISBN = ?";
            PreparedStatement statement;   
            statement = conexao.prepareStatement(sql);
            statement.setDouble(1, livro.getIsbn());

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
    
    //método que efetua pesquisa pelo ISBN
    public Livro pesquisarLivroDAO(Livro livro) throws ParseException{
        try{
            conexao = banco.getConexao();
            String sql = "select * from LIVRO where ISBN = ?";
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            statement.setDouble(1, livro.getIsbn());
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                livro.setIsbn(resultSet.getLong("ISBN"));
                livro.setTitulo(resultSet.getString("TITULO_LIVRO"));
                livro.setSubtitulo(resultSet.getString("SUBTITULO"));
                livro.setAutor1(resultSet.getString("AUTOR_LIVRO"));
                livro.setAutor2(resultSet.getString("AUTOR_LIVRO2"));
                livro.setEditora(resultSet.getString("EDITORA_LIVRO"));
                livro.setQtd(resultSet.getInt("QTD"));
                livro.setNumPags(resultSet.getInt("N_PAG"));
                livro.setFormato(resultSet.getString("FORMATO"));
                livro.setCategoria(resultSet.getString("CATEGORIA"));
                livro.setResumo(resultSet.getString("RESUMO"));
                livro.setSumario(resultSet.getString("SUMARIO"));
                
                //*********resgatar data sql.Date e converter para util.Date                                                                   
                java.util.Date utilData = resultSet.getTimestamp("PUBLICACAO");
                livro.setPublicao(utilData);
               
            }
            
        }catch(SQLException | RuntimeException sql){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
        return livro;
    }
    
    //método que altera as informações do registro relacionado ao ISBN informado
    public boolean alterarDAO(Livro livro){
        int alterado = 0;
        
        try{
            conexao = banco.getConexao();
            String sql = "update livro set TITULO_LIVRO=?, SUBTITULO=?,  AUTOR_LIVRO=?,"
                    + " AUTOR_LIVRO2=?, EDITORA_LIVRO=?, QTD=?, N_PAG=?, FORMATO=?,"
                    + " CATEGORIA=?, RESUMO=?, SUMARIO=?, PUBLICACAO=? where ISBN=?";
            
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            
            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getSubtitulo());
            statement.setString(3, livro.getAutor1());
            statement.setString(4, livro.getAutor2());
            statement.setString(5, livro.getEditora());
            statement.setInt(6, livro.getQtd());
            statement.setInt(7, livro.getNumPags());
            statement.setString(8, livro.getFormato());
            statement.setString(9, livro.getCategoria());
            statement.setString(10, livro.getResumo());
            statement.setString(11, livro.getSumario());
            Date sqlDate = new Date(livro.getPublicao().getTime()); //Conversão de util.Date para sql.Date 
            statement.setDate(12, sqlDate);
            statement.setLong(13, livro.getIsbn());
            
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
