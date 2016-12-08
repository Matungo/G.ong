
package fxsistemaong.DAO;

import fxsistemaong.Objeto.Funcionario;
import fxsistemaong.Objeto.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author Macbook
 */
public class LoginDAO {
    Funcionario func = new Funcionario();
    Banco banco = new Banco("root","","127.0.0.1","G_ONG",3306);
    Connection conexao;
    
    public boolean ValidarLogin(Login login){
        int OK = 0;
        try {
            conexao = banco.getConexao();
            String sql = "select * from FUNCIONARIO where LOGIN = ? AND SENHA = ?";
            PreparedStatement std;
            std = conexao.prepareStatement(sql);
            std.setString(1, login.getUsuario());
            std.setString(2, login.getSenha());
            
            ResultSet rs = std.executeQuery();
            while (rs.next()) {

                login.setUsuario(rs.getString("LOGIN"));
                login.setUsuario(rs.getString("SENHA"));
                login.setPermissao(rs.getString("PERMISSAO"));
                
                OK++;
            }
            banco.fechar(conexao);
        } catch (SQLException | RuntimeException sql) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(sql.getMessage());
            alert.showAndWait();
        }
    if(OK != 0){
            return true;
        } else{
            return false;
        }

    }
}
