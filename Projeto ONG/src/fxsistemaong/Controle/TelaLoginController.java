package fxsistemaong.Controle;

import fxsistemaong.FXSistemaOng;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;



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
    
    public void HandleCadastrarLogin(ActionEvent event) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaPrincipal.fxml"));        
        Scene scene = new Scene(root);
        
        //forma de fechar um scene após colocar os dados e chamar a principal
        //Stage stage2 = FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaLogin.fxml"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("G.onG - Gerenciamento de Ong's");
        //stage2.close();
        stage.show();
        
    }
    
    public void HandlerSair (ActionEvent event){
        System.exit(0);
    }
}