
package fxsistemaong.Controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;


public class TelaPrincipalController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Menu menuCadastros;
    @FXML
    private MenuItem menuItemFuncionarios;
    @FXML
    private MenuItem menuItemUsuarios;
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
    private Menu menuRelatorios;
    @FXML
    private MenuItem menuItemRelatorioBeneficiarios;
    @FXML
    private MenuItem menuItemRelatorioDoacoes;
    @FXML
    private MenuItem menuItemRelatorioGastos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // comando para deixar os menus desabilitados
        menuCadastros.setDisable(false);
        menuBiblioteca.setDisable(false);
        menuOficinas.setDisable(false);
        menuEstoque.setDisable(false);
        menuLogout.setDisable(true);
        menuConsulta.setDisable(false);
        menuRelatorios.setDisable(true);   
    }
    
    // Metodo que chama a tela de cadastro de usuarios para a tela principal
    public void handleMenuItemUsuarios() throws IOException{
        //Procedimento para carregar a tela de cadastro
        try{
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxsistemaong/Tela/TelaCadastroUsuario.fxml"));
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

    public void handleMenuLogout() throws IOException{
        
    }

}
