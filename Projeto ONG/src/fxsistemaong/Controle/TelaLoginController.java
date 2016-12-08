package fxsistemaong.Controle;

import fxsistemaong.DAO.LoginDAO;
import fxsistemaong.Objeto.Login;
import fxsistemaong.Objeto.TelaPrincipal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



public class TelaLoginController implements Initializable {
    @FXML
    private TextField  TxtFieldUsuario;
    @FXML
    private PasswordField TxtFieldSenha;
    @FXML
    private Button BtnLogar;
    @FXML
    private Button BtnNovo;
    @FXML
    private Button BtnSair;
    @FXML
    private ImageView ImageLogo;
    @FXML
    private ImageView ImageNome;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            TxtFieldUsuario.clear();
            TxtFieldUsuario.setDisable(false);
            TxtFieldSenha.clear();
            TxtFieldSenha.setDisable(false);
            BtnLogar.setDisable(false);
            BtnNovo.setDisable(true);
            BtnSair.setDisable(false);
    }
    
    
    public void HandleCadastrarLogin(ActionEvent evento) throws IOException{
            TxtFieldUsuario.clear();
            TxtFieldUsuario.setDisable(false);
            TxtFieldSenha.clear();
            TxtFieldSenha.setDisable(false);
            BtnLogar.setDisable(false);
            BtnNovo.setDisable(true);
            BtnSair.setDisable(false);
        
    }
    
    public void HandlerSair (ActionEvent event){
        System.exit(0);
    }
    
    
    public void HandlerValidarUsuario (ActionEvent evento) throws IOException{
        Login login = new Login();
        LoginDAO dao = new LoginDAO();
        
        
        login.setUsuario(TxtFieldUsuario.getText());
        login.setSenha(TxtFieldSenha.getText());
        
        if(dao.ValidarLogin(login)){
            //avisar que o usuario e a senha foram encontrados 
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Usuário e Senha Corretos");
            alert.showAndWait();

            TelaPrincipal.setPermissao(login.getPermissao());
            
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaPrincipal.fxml"));        
            Scene scene = new Scene(root);
            
            
            //Limpar os campos após entrar na tela principal
            TxtFieldUsuario.clear();
            TxtFieldUsuario.setDisable(true);
            TxtFieldSenha.clear();
            TxtFieldSenha.setDisable(true);
            BtnLogar.setDisable(true);
            BtnNovo.setDisable(false);
            BtnSair.setDisable(false);
            
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("G.onG - Gerenciamento de Ong's");
            
            stage.show();
        }else{
            //informa que o usuario ou a senha podem esta errados
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Usuário ou Senha não encontrado");
            alert.showAndWait();
            //Deixa o cursor no login e limpa o campo senha
            TxtFieldUsuario.selectAll();
            TxtFieldSenha.clear();
        }
        
    }
}