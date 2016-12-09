
package fxsistemaong.Controle;

import fxsistemaong.Objeto.Login;
import fxsistemaong.Objeto.TelaPrincipal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class TelaPrincipalController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Menu menuCadastros;
    @FXML
    private MenuItem menuItemFuncionarios;
    @FXML
    private MenuItem menuItemBeneficiarios;
    @FXML
    private MenuItem menuItemDependentes;
    @FXML
    private Menu menuEstoque;
    @FXML
    private MenuItem menuItemEntradaMercadoria;
    @FXML
    private MenuItem menuItemSaidaMercadoria;
    @FXML
    private Menu menuBiblioteca;
    @FXML
    private MenuItem menuItemCadastroLivros;
    @FXML
    private MenuItem menuItemEmprestimoLivro;
    @FXML
    private Menu menuOficinas;
    @FXML
    private MenuItem menuItemAberturaOficina;
    @FXML
    private MenuItem menuItemInclusaoAluno;
    @FXML
    private Menu menuConsulta;
    @FXML
    private MenuItem menuItemAgendamentoConsulta;
    @FXML
    private Menu menuLogout;
    @FXML
    private MenuItem MenuItemSair;
    @FXML
    private Menu menuRelatorios;
    @FXML
    private MenuItem menuItemRelatorioBeneficiarios;
    @FXML
    private MenuItem menuItemRelatorioDoacoes;
    @FXML
    private MenuItem menuItemRelatorioGastos;
    @FXML
    private ImageView ImagemFundo;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           /* menuCadastros.setDisable(true);
            menuBiblioteca.setDisable(true);
            menuOficinas.setDisable(true);
            menuEstoque.setDisable(true);
            menuLogout.setDisable(true);
            menuConsulta.setDisable(true);
            menuRelatorios.setDisable(true);*/
        
            Stage stage = new Stage();
            Parent root;        

            if("Diretor".equals(TelaPrincipal.getPermissao())){
            menuCadastros.setDisable(false);
            menuBiblioteca.setDisable(false);
            menuOficinas.setDisable(false);
            menuEstoque.setDisable(false);
            menuLogout.setDisable(false);
            menuConsulta.setDisable(false);
            menuRelatorios.setDisable(false); 
            menuItemRelatorioBeneficiarios.setDisable(true);
            menuItemRelatorioGastos.setDisable(false);
        } else{
            // comando para deixar os menus desabilitados
            menuCadastros.setDisable(true);
            menuBiblioteca.setDisable(false);
            menuOficinas.setDisable(false);
            menuEstoque.setDisable(true);
            menuLogout.setDisable(false);
            menuConsulta.setDisable(false);
            menuRelatorios.setDisable(true);
            menuItemRelatorioBeneficiarios.setDisable(true);
            menuItemRelatorioGastos.setDisable(true);
            }       
    }
    
    // Metodo que chama a tela de cadastro de beneficiarios para a tela principal
    public void handleMenuItemBeneficiarios() throws IOException{
        //Procedimento para carregar a tela de cadastro
        try{
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaCadastroBeneficiario.fxml"));
         anchorPane.getChildren().setAll(a);
        }
        catch(IllegalArgumentException le){
            System.out.println(le.getMessage());
        }
        
        
    }
    
    //Metodo que chama a tela de cadastro de funcionarios.
    public void handleMenuItemFuncionarios() throws IOException{
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaCadastroFuncionario.fxml"));
        anchorPane.getChildren().setAll(a);
        
    }
    
    //Metodo que chama a tela de cadastro de dependentes para a Main
    public void handleMenuItemDependentes() throws IOException{
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/NovaTelaCadastroDependentes.fxml"));
        anchorPane.getChildren().setAll(a);
        
    }
    
    //Metodo que chama a tela de Cadastro de Livros para a Main
    public void handleMenuItemCadastroLivro() throws IOException{
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaCadastroLivros.fxml"));
        anchorPane.getChildren().setAll(a);
        
    }
    
    //Metodo que chama a tela de Emprestimo de Livros para a Main
    public void handleMenuItemEmprestimoLivro() throws IOException{
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaAluguelLivros.fxml"));
        anchorPane.getChildren().setAll(a);
        
    }
    //metodo para chamar a tela de abertura de oficinas para a tela principal
    public void handleMenuAberturaOficina() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaAberturaOficinas.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    //metodo para chamar a tela de inclusao de alunos nas oficinas para a tela principal
    public void handleMenuInclusaoAlunoOficina() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaInclusaoAlunoOficina.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    //metodo para chamar a tela de agendamento de consulta para a tela principal
    public void handleMenuAgendamentoConsulta() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaAgendamentoConsulta.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    //metodo para chamar a tela de entrada de mercadorias para a tela principal
    public void handleMenuEntradaMercadoria() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaEntradaMercadoria.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    //metodo para chamar a tela de saida de mercadoria para a tela principal
    public void handleMenuSaidaMercadoria() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaSaidaMercadoria.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    //metodo para chamar a tela de relatorio de doacoes
    public void handleMenuRelatorio() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaRelatorioDoacoes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    //metodo para chamar a tela de relatorio de Gastos
    public void handleMenuRelatorioGastos() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaRelatorioGastos.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    public void handleMenuLogout() throws IOException{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaLogin.fxml"));        
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("G.onG - Gerenciamento de Ong's");
            
            //System.exit(0);
            stage.show(); 
    }

}
