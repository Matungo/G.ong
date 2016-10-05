package fxsistemaong.Controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;



public class TelaLoginController implements Initializable {
    @FXML
    private TextField  TxtFieldUsuario;
    @FXML
    private TextField TxtFieldSenha;
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
     
    }
    
    public void HandleCadastrarLogin() throws IOException{
       //Procedimento para carregar a tela de cadastro de Login
        FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaCadastroLogin.fxml"));
        
    }
}