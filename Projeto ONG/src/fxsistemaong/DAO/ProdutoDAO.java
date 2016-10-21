package fxsistemaong.DAO;

import fxsistemaong.Objeto.EntradaProduto;
import fxsistemaong.Objeto.Funcionario;
import fxsistemaong.Objeto.Produto;
import fxsistemaong.Objeto.SaidaProduto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Thiago Lopes
 */
public class ProdutoDAO {
    //parâmetros para conexão com o banco
    Banco banco = new Banco("root","","127.0.0.1","G_ONG",3306);
    Connection conexao;
    
    
    //Metodo para conexao ao banco e realizar a pesquisa do produto
    public Produto pesquisarProduto (Produto produto){
        //banco = new Banco ("root","","127.0.0.1","G_ONG",3306);
        try{
            conexao = banco.getConexao();
            String sql="Select * from PRODUTO where NOME = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            
            std.setString(1, produto.getNome());
            ResultSet rs = std.executeQuery();
            while(rs.next()){
                produto.setCod(rs.getInt("COD"));
                produto.setNome(rs.getString("NOME"));
                produto.setCategoria(rs.getString("CATEGORIA"));
        }
            banco.fechar(conexao);
        }catch (SQLException | RuntimeException sql){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
        return produto;
    }
    
    /**
     *
     * @return
     */
    public List<EntradaProduto> Listar(){
        String sql= "SELECT * from ENTRADA_PRODUTO where QTD > 0";
        List <EntradaProduto> retorno = new ArrayList<EntradaProduto>();
        try{
        conexao = banco.getConexao();
        //String sql= "SELECT * from ENTRADA_PRODUTO where DATA_ENTRADA= CURDATE()";
                //String sql= "SELECT * from ENTRADA_PRODUTO where QTD > 0";

           PreparedStatement std = conexao.prepareStatement(sql);
           ResultSet rs = std.executeQuery();
           while(rs.next()){
               EntradaProduto AddProduto = new EntradaProduto();

               AddProduto.setCodigoEntrada(rs.getInt("COD_ENTRADA"));
               AddProduto.setTipoEntrada(rs.getString("TIPO_ENTRADA"));
               AddProduto.setNome(rs.getString("NOME"));
               AddProduto.setCategoria(rs.getString("CATEGORIA"));
               AddProduto.setQuantidade(rs.getInt("QTD"));
               AddProduto.setDataEntrada(rs.getDate("DATA_ENTRADA"));
               AddProduto.setValorEntrada(rs.getFloat("VALOR_ENTRADA"));
               
               retorno.add(AddProduto);
               //banco.fechar(conexao);
           }
           banco.fechar(conexao);
       }catch(SQLException ex){
                   Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                   }
            
       return retorno;
    }
    
    
    
    public boolean SalvarProduto(Produto produto){
        int Salvo=0;
        try{
            conexao = banco.getConexao();
            String sql = "INSERT into PRODUTO (NOME, CATEGORIA) values (?,?)";
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getCategoria());
            
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
    
    //metodo para colocar os dados de entrada de produto na tabela no banco
    public boolean AdicionarEntradaProduto(Produto produto, EntradaProduto entraProduto){
        int salvo=0;
        try{
            conexao = banco.getConexao();
            String sql = "INSERT into ENTRADA_PRODUTO (TIPO_ENTRADA, NOME, CATEGORIA, QTD, DATA_ENTRADA, VALOR_ENTRADA)"
                    + "value (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            
            statement.setString(1, entraProduto.getTipoEntrada());
            statement.setString(2, produto.getNome());
            statement.setString(3, produto.getCategoria());
            statement.setInt(4, entraProduto.getQuantidade());
            statement.setDate(5,new Date(entraProduto.getDataEntrada().getTime()));
            statement.setFloat(6, entraProduto.getValorEntrada());
            
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
    
        //public boolean AdicionarSaidaProduto(SaidaProduto saidaProduto){
            
        //}

    
   
    public boolean AtualizarProduto(EntradaProduto produto){
         int atualizado = 0;
            try{
                   conexao = banco.getConexao();
                   String sql = "UPDATE ENTRADA_PRODUTO set TIPO_ENTRADA=?, NOME =?,CATEGORIA=?, "
                    + "QTD=?, DATA_ENTRADA=?, VALOR_ENTRADA=? WHERE COD_ENTRADA=? ";
                PreparedStatement std;
                std = conexao.prepareStatement(sql);
            
                // Parametros para troca de dados entre o banco e os campos
                std.setString(1, produto.getTipoEntrada());
                std.setString(2, produto.getNome());
                std.setString(3, produto.getCategoria());
                std.setInt(4, produto.getQuantidade());
                std.setDate(5, new Date(produto.getDataEntrada().getTime()));
                std.setFloat(6, produto.getValorEntrada());
                std.setInt(7, produto.getCodigoEntrada());
                
                
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
    
    public boolean ExcluirProduto (EntradaProduto produto){
        int excluido = 0;
        try {
            conexao = banco.getConexao();
            String sql = "delete from ENTRADA_PRODUTO where COD_ENTRADA = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setInt(1, produto.getCodigoEntrada());
            
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