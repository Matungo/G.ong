
package fxsistemaong.DAO;

import fxsistemaong.Objeto.Consulta;
import fxsistemaong.Objeto.Dependentes;
import java.sql.Connection;
import java.sql.Date;
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
 * @author Macbook
 */
public class ConsultaDAO {
   
    private Banco banco = new Banco("root", "", "localhost", "g_ong", 3306);
    Connection conexao;
    
    
    //METODO PARA BUSCAR AS INFORMAÇÕES DOS DEPENDENTES ATRAVES DO NUMERO DO CPF
    public Dependentes PesquisarCriançaCPF(Dependentes dependente) {
        try {
            conexao = banco.getConexao();
            String sql = "select * from DEPENDENTES where CPF = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setString(1, dependente.getCpfCrianca());
            ResultSet rs = std.executeQuery();
            while (rs.next()) {
                
                dependente.setCodigo(rs.getInt("ID_DEPENDENTES"));
                dependente.setNomeCrianca(rs.getString("NOME"));
                dependente.setDataCrianca(rs.getDate("DATA_NASC"));
                dependente.setCpfCrianca(rs.getString("CPF"));
            }
            banco.fechar(conexao);
        } catch (SQLException | RuntimeException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();

        }
        return dependente;
    }
    
    //METODO PARA BUSCAR AS INFORMAÇÕES DOS DEPENDENTES ATRAVES DO NUMERO DO CODIGO DE CADASTRO
    public Dependentes PesquisarCriançaCOD(Dependentes dependente) {
        try {
            conexao = banco.getConexao();
            String sql = "select * from DEPENDENTES where ID_DEPENDENTES = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setInt(1, dependente.getCodigo());
            ResultSet rs = std.executeQuery();
            while (rs.next()) {
                
                dependente.setCodigo(rs.getInt("ID_DEPENDENTES"));
                dependente.setNomeCrianca(rs.getString("NOME"));
                dependente.setDataCrianca(rs.getDate("DATA_NASC"));
                dependente.setCpfCrianca(rs.getString("CPF"));
            }
            banco.fechar(conexao);
        } catch (SQLException | RuntimeException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();

        }
        return dependente;
    }
    
    //CRIAR UMA LISTA DE CONSULTAS PARA ADICIONA-LAS NA TABLE VIEW DA TELA DE CONSULTAS
    public List<Consulta> Listar(){
        String sql= "SELECT * from CONSULTAS where COD_CONSULTA > 0";
        List <Consulta> retorno = new ArrayList<Consulta>();
        try{
        conexao = banco.getConexao();

           PreparedStatement std = conexao.prepareStatement(sql);
           ResultSet rs = std.executeQuery();
           
           while(rs.next()){
               Consulta consulta = new Consulta();
               consulta.setCodigoConsulta(rs.getInt("COD_CONSULTA"));
               consulta.setTipoConsulta(rs.getString("TIPO_CONSULTA"));
               consulta.setCodigoPaciente(rs.getInt("COD_PACIENTE"));
               consulta.setCpfPaciente(rs.getString("CPF_PACIENTE"));
               consulta.setNomePaciente(rs.getString("NOME_PACIENTE"));
               consulta.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
               consulta.setNumeroConsultas(rs.getInt("N_CONSULTAS"));
               
               retorno.add(consulta);
           }
           banco.fechar(conexao);
       }catch(SQLException ex){
                   Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                   }      
       return retorno;
    }
    
    //METODO PARA ADICIONAR UMA CONSULTA MARCADA
    public boolean AdicionarConsultaMarcada(Consulta consulta){
        int salvo=0;
        try{
            conexao = banco.getConexao();
            String sql = "INSERT into CONSULTAS (TIPO_CONSULTA, COD_PACIENTE, CPF_PACIENTE, NOME_PACIENTE, DATA_NASCIMENTO, N_CONSULTAS)"
                    + "value (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement;
            statement = conexao.prepareStatement(sql);
            
            statement.setString(1, consulta.getTipoConsulta());
            statement.setInt(2, consulta.getCodigoPaciente());
            statement.setString(3, consulta.getCpfPaciente());
            statement.setString(4, consulta.getNomePaciente());
            statement.setDate(5 ,new Date(consulta.getDataNascimento().getTime()));
            statement.setInt(6 , consulta.getNumeroConsultas());
            
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
        public boolean ExcluirConsulta(int nConsulta){
        int excluido = 0;
        try {
            conexao = banco.getConexao();
            String sql = "delete from CONSULTAS where COD_CONSULTA = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setInt(1, nConsulta);
            
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
