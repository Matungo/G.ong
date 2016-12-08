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
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaPrincipal.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaLogin.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("G.onG - Gerenciamento de Ong's");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
