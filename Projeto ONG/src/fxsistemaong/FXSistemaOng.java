package fxsistemaong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Macbook
 */
public class FXSistemaOng extends Application {
    //Abre a tela de login como a primeira tela
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Tela de Login - Entre com seu usuário e Senha");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
