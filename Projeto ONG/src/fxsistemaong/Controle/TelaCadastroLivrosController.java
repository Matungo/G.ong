/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxsistemaong.Controle;

import fxsistemaong.DAO.LivroDAO;
import fxsistemaong.Objeto.Livro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Macbook
 */
public class TelaCadastroLivrosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //declarando os campos do formulário para serem utilizados como variáveis
    @FXML
    private TextField TxtISBNLivro;
    @FXML
    private TextField TxtTituloLivro;
    @FXML
    private TextField TxtSubtituloLivro;
    @FXML
    private TextField TxtPrimeiroAutor;
    @FXML
    private TextField TxtSegundoAutor;
    @FXML
    private TextField TxtDataPublicacaoLivro;
    @FXML
    private TextField TxtEditoraLivro;
    @FXML
    private TextField TxtQtdeLivro;
    @FXML
    private TextField TxtPaginasLivro;
    @FXML
    private TextField TxtAdicionarCategoriaLivro;
    @FXML
    private TextArea TxtAreaResumoLivro;
    @FXML
    private TextArea TxtareaSumarioLivro;
    @FXML
    private ComboBox ComboboxFormatoLivro;
    @FXML
    private ComboBox ComboboxCategoriaLivro;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicialização das comboboxs
        
        ObservableList<String> listaFormatos //combobox trabalha com lista por isso é necessario criar ela
                = FXCollections.observableArrayList("Brochura", "Grampo", "Capa Dura", "Espiral");
        ComboboxFormatoLivro.setItems(listaFormatos);
        //atribui a lista
        
        ObservableList<String> listaCategorias 
                = FXCollections.observableArrayList("Autoajuda", "Literatura Nacional", "Literatura Estrangeira", 
                        "Biografia", "Ciências Exatas", "Ciências Biológicas", "Ciências Humanas", "Outros");
        ComboboxCategoriaLivro.setItems(listaCategorias);
    }
    
    //método que limpa todos os campos do formulário
    public void limparCampos(){
        TxtISBNLivro.clear();
        TxtTituloLivro.clear();
        TxtSubtituloLivro.clear();
        TxtPrimeiroAutor.clear();
        TxtSegundoAutor.clear();
        TxtDataPublicacaoLivro.clear();
        TxtEditoraLivro.clear();
        TxtQtdeLivro.clear();
        TxtPaginasLivro.clear();
        TxtAdicionarCategoriaLivro.clear();
        TxtAreaResumoLivro.clear();
        TxtareaSumarioLivro.clear();
        ComboboxFormatoLivro.setValue(null);
        ComboboxCategoriaLivro.setValue(null);
    }
    
    //método que pega as informações do formulário e as envia para a classe LivroDAO efetuar a gravação no banco
    public void cadastrarControle(){
        Livro livroControle = new Livro();
        
        livroControle.setIsbn(Double.parseDouble((TxtISBNLivro.getText())));
        livroControle.setTitulo(TxtTituloLivro.getText());
        livroControle.setSubtitulo(TxtSubtituloLivro.getText());
        livroControle.setAutor1(TxtPrimeiroAutor.getText());
        livroControle.setAutor2(TxtSegundoAutor.getText());
        //livroControle.setPublicacao(TxtDataPublicacaoLivro.getText()); ***A TRATAR***
        livroControle.setEditora(TxtEditoraLivro.getText());
        livroControle.setQtd(Integer.parseInt(TxtQtdeLivro.getText()));
        livroControle.setNumPags(Integer.parseInt(TxtPaginasLivro.getText()));
        livroControle.setResumo(TxtAreaResumoLivro.getText());
        livroControle.setSumario(TxtareaSumarioLivro.getText());
        livroControle.setFormato(String.valueOf(ComboboxFormatoLivro.getValue())); // pegar valor da combobox
        livroControle.setCategoria(String.valueOf(ComboboxCategoriaLivro.getValue())); // pegar valor da combobox
        
        LivroDAO livroDAO = new LivroDAO();
        if(livroDAO.cadastrarDAO(livroControle)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Salvo com sucesso");
            alert.showAndWait();
        }
    }
    
    //método que pega o ISBN do formulário e o envia a classe LivroDAO para que o registro seja excluído
    public void excluirControle(){
        Livro livroControle = new Livro();
        LivroDAO livroDAO = new LivroDAO();
        livroControle.setIsbn(Double.valueOf(TxtISBNLivro.getText()));
        if (livroDAO.excluirDAO(livroControle)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Excluido com sucesso");
            alert.showAndWait();
        }
        limparCampos();
    }
    
    //método que pega o ISBN do formulário e o envia para a classe LivroDAO efetuar a pesquisa no banco
    public void pesquisarLivro(){
        Livro livro = new Livro();
        livro.setIsbn(Double.parseDouble(TxtISBNLivro.getText()));
        try{
            LivroDAO livroDAO = new LivroDAO();
            livro = livroDAO.pesquisarLivroDAO(livro);
        }catch(RuntimeException E){
            System.out.println(E.getMessage());
        }
            TxtISBNLivro.setText(String.valueOf(livro.getIsbn()));
            TxtTituloLivro.setText(livro.getTitulo());
            TxtSubtituloLivro.setText(livro.getSubtitulo());
            TxtPrimeiroAutor.setText(livro.getAutor1());
            TxtSegundoAutor.setText(livro.getAutor2());
            TxtEditoraLivro.setText(livro.getEditora());
            TxtQtdeLivro.setText(String.valueOf(livro.getQtd()));
            TxtPaginasLivro.setText(String.valueOf(livro.getNumPags()));
            TxtAreaResumoLivro.setText(livro.getResumo());
            TxtareaSumarioLivro.setText(livro.getSumario());
            ComboboxFormatoLivro.setValue(livro.getFormato());
            ComboboxCategoriaLivro.setValue(livro.getCategoria());
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Pesquisado com sucesso");
            alert.showAndWait();
    }
    
    //método que pega as informações do formulário e as envia para a classe LivroDAO efetuar a atualização no banco
    public void alterarLivro(){
        Livro livroControle = new Livro();
        
        livroControle.setIsbn(Double.parseDouble((TxtISBNLivro.getText())));
        livroControle.setTitulo(TxtTituloLivro.getText());
        livroControle.setSubtitulo(TxtSubtituloLivro.getText());
        livroControle.setAutor1(TxtPrimeiroAutor.getText());
        livroControle.setAutor2(TxtSegundoAutor.getText());
        //livroControle.setPublicacao(TxtDataPublicacaoLivro.getText()); ***A TRATAR***
        livroControle.setEditora(TxtEditoraLivro.getText());
        livroControle.setQtd(Integer.parseInt(TxtQtdeLivro.getText()));
        livroControle.setNumPags(Integer.parseInt(TxtPaginasLivro.getText()));
        livroControle.setResumo(TxtAreaResumoLivro.getText());
        livroControle.setSumario(TxtareaSumarioLivro.getText());
        livroControle.setFormato(String.valueOf(ComboboxFormatoLivro.getValue())); // pegar valor da combobox
        livroControle.setCategoria(String.valueOf(ComboboxCategoriaLivro.getValue())); // pegar valor da combobox
        
        LivroDAO livroDAO = new LivroDAO();
        if(livroDAO.alterarDAO(livroControle)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Atualizado com sucesso");
            alert.showAndWait();
        }
    }
}
