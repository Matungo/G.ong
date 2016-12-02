/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxsistemaong.Controle;

import fxsistemaong.DAO.Banco;
import fxsistemaong.DAO.ProdutoDAO;
import fxsistemaong.Objeto.EntradaProduto;
import fxsistemaong.Objeto.Produto;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class TelaEntradaMercadoriaController implements Initializable {

@FXML 
private TextField TextfieldNome;
@FXML 
private TextField TxtFieldQuantidade;
@FXML 
private TextField TxtFieldValorUnitario;
@FXML
private TextField TxtFieldCodProduto;
@FXML
private DatePicker DateDataEntrada;
@FXML
private ComboBox CbCategoria;
@FXML
private ComboBox CbTipoEntrada;
@FXML
private Button BtnBuscarNome;
@FXML
private Button BtnCadastrarNome;
@FXML
private Button BtnAdicionar;
@FXML
private Button BtnModificar;
@FXML
private Button BtnExcluir;
@FXML
private TableView<EntradaProduto> TVProduto;
@FXML
private TableColumn<EntradaProduto, Integer> TcCodigo;
@FXML
private TableColumn<EntradaProduto, String> TcNomeProduto;
@FXML
private TableColumn<EntradaProduto, String> TcCategoria;
@FXML
private TableColumn<EntradaProduto, Integer> TcQuantidade;
@FXML
private TableColumn<EntradaProduto, Date> TcDataEntrada;
@FXML
private TableColumn<EntradaProduto, String> TCTipoEntrada;
@FXML
private TableColumn<EntradaProduto, String> TcValorEntrada;

//Conceito de criar uma lista para depois listar dentro da tabela de produtos
    private List<EntradaProduto> ListaAddProdutos;
    private ObservableList<EntradaProduto> observableListAddProdutos;

//Declarações para conexão com o banco referente a categoria do produto
    private Banco banco;
    private Connection conexao;
    private PreparedStatement pst = null;
    private ResultSet rs;
    private Statement st;
    private String sql = null; 


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Preenchidmento da combobox com as informações sobre qual tipo de entrada
        ObservableList<String> listaTipo //combobox trabalha com lista por isso é necessario criar ela
                = FXCollections.observableArrayList("Doação", "Compra");
        CbTipoEntrada.setItems(listaTipo);
        

//Preenchimento da combobox Categoria referente as categorias que possui de serviços
        ObservableList<String> Categoria //combobox trabalha com lista por isso é necessario criar ela
                = FXCollections.observableArrayList("Alimentos", "Higiene Pessoal","Limpeza","Didático","Odontológico","Informática");
        CbCategoria.setItems(Categoria);
        
        //Deixa o campo de Cod de produto desabilitado para modificação
        TxtFieldCodProduto.setDisable(true);
        TxtFieldValorUnitario.setDisable(true);
        TxtFieldQuantidade.setDisable(true);
        DateDataEntrada.setDisable(true);
        CbTipoEntrada.setDisable(true);
        BtnAdicionar.setDisable(true);
        BtnModificar.setDisable(true);
        BtnExcluir.setDisable(true);
        //Carrega a tabela com os dados referente ao banco entrada mercadoria
        IncluirTableView();
        
        //Listening para quando selecionar qualquer opção da tabela ela atualizar os campos da view
        TVProduto.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue,newValue) -> selecionarItemTableViewProduto(newValue));
    }    
    
    // metodo de limpeza dos campos após a inclusão na TableView
    public void LimparCampos (){
        TxtFieldCodProduto.clear();
        TextfieldNome.clear();
        TxtFieldValorUnitario.clear();
        TxtFieldQuantidade.clear();
        DateDataEntrada.getEditor().clear();
        CbTipoEntrada.setValue(null);
        CbCategoria.setValue(null);
        
        //desabilitação dos botões que foram habilitados
        TxtFieldValorUnitario.setDisable(true);
        TxtFieldQuantidade.setDisable(true);
        DateDataEntrada.setDisable(true);
        CbTipoEntrada.setDisable(true);
        BtnAdicionar.setDisable(true);
        BtnModificar.setDisable(true);
        BtnExcluir.setDisable(true);
    }
    
    
    
    //Metodo para busca no banco o nome do produto se j'a existe
    public void BuscarNomeProduto(ActionEvent evento){
        Produto produto = new Produto();
        produto.setNome(TextfieldNome.getText());
        try{
            ProdutoDAO dao = new ProdutoDAO();
            produto = dao.pesquisarProduto(produto);
            //Habilitação dos botões após a busca do produto
            TxtFieldValorUnitario.setDisable(false);
            TxtFieldQuantidade.setDisable(false);
            DateDataEntrada.setDisable(false);
            CbTipoEntrada.setDisable(false);
            BtnAdicionar.setDisable(false);
            BtnModificar.setDisable(false);
            BtnExcluir.setDisable(false);
        }catch(RuntimeException E){
            System.out.println(E.getMessage());
        }
        TxtFieldCodProduto.setText(Integer.toString(produto.getCod()));
        TextfieldNome.setText(produto.getNome());
        CbCategoria.setValue(produto.getCategoria());
        
        
        //Mostrar se houve exito na busca do produto
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
        alert.setContentText("Pesquisado com Sucesso");
        alert.showAndWait();
    }
    
    
    //Cadastra somente um novo produto na tabela de produto
    public void IncluirProduto(ActionEvent event){
        Produto produto = new Produto();
        try{
            produto.setNome(TextfieldNome.getText());
            produto.setCategoria(String.valueOf(CbCategoria.getValue()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        ProdutoDAO dao = new ProdutoDAO();
            if (dao.SalvarProduto(produto)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
                alert.setContentText("Salvo com Sucesso!!!");
                alert.showAndWait();
                LimparCampos();
            }
        
    }
    
    //Incluir a nova entrada de produto
    public void IncluirEntradaProduto(ActionEvent event){
        Produto produto = new Produto();
        EntradaProduto entraProduto = new EntradaProduto();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try{
            produto.setNome(TextfieldNome.getText());
            produto.setCategoria(String.valueOf(CbCategoria.getValue()));
            entraProduto.setTipoEntrada(String.valueOf(CbTipoEntrada.getValue()));
            entraProduto.setQuantidade(Integer.parseInt(TxtFieldQuantidade.getText()));
            entraProduto.setDataEntrada(formato.parse(DateDataEntrada.getEditor().getText()));
            entraProduto.setValorEntrada(Float.valueOf(TxtFieldValorUnitario.getText()));   
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        ProdutoDAO dao = new ProdutoDAO();
            if (dao.AdicionarEntradaProduto(produto, entraProduto)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
                alert.setContentText("Salvo com Sucesso!!!");
                alert.showAndWait();
                LimparCampos();
            }
            
        IncluirTableView();
    }
    
    /**
     *
     */
    public void IncluirTableView(){
        ProdutoDAO dao= new ProdutoDAO();
        EntradaProduto produto = new EntradaProduto();
        
        TcCodigo.setCellValueFactory(new PropertyValueFactory<EntradaProduto,Integer>("CodigoSaida"));
        TcNomeProduto.setCellValueFactory(new PropertyValueFactory<EntradaProduto,String>("nome"));
        TcCategoria.setCellValueFactory(new PropertyValueFactory<EntradaProduto,String>("Categoria"));
        TcQuantidade.setCellValueFactory(new PropertyValueFactory<EntradaProduto,Integer>("Quantidade"));
        TcDataEntrada.setCellValueFactory(new PropertyValueFactory<EntradaProduto, Date>("DataEntrada"));
        TCTipoEntrada.setCellValueFactory(new PropertyValueFactory<EntradaProduto, String>("TipoEntrada"));
        TcValorEntrada.setCellValueFactory(new PropertyValueFactory<>("ValorEntrada"));
        
        ListaAddProdutos = dao.Listar();
        
        
        observableListAddProdutos = FXCollections.observableArrayList(ListaAddProdutos);
        TVProduto.setItems(observableListAddProdutos);
    }
   
    //função que serve para carregar as variaveis dentro da tableview
    public void selecionarItemTableViewProduto(EntradaProduto entradaProduto){
            //Declaração da variavel para inserir a data na view
        DateFormat dataBR = DateFormat.getDateInstance(DateFormat.MEDIUM);
        
        if (entradaProduto != null){
            TxtFieldCodProduto.setText(Integer.toString(entradaProduto.getCodigoEntrada()));
            TextfieldNome.setText(entradaProduto.getNome());
            TxtFieldQuantidade.setText(Integer.toString(entradaProduto.getQuantidade()));
            CbCategoria.setValue(entradaProduto.getCategoria());
            CbTipoEntrada.setValue(entradaProduto.getTipoEntrada());
            TxtFieldValorUnitario.setText(Float.toString(entradaProduto.getValorEntrada()));
            DateDataEntrada.getEditor().setText(dataBR.format(entradaProduto.getDataEntrada()));
            
                        //Habilitação dos botões após selecionar um produto na tableview
            TxtFieldValorUnitario.setDisable(false);
            TxtFieldQuantidade.setDisable(false);
            DateDataEntrada.setDisable(false);
            CbTipoEntrada.setDisable(false);
            BtnAdicionar.setDisable(false);
            BtnModificar.setDisable(false);
            BtnExcluir.setDisable(false);
        }
            
        
    }
    
    public void ModificarProdutoTabela(ActionEvent event) throws ParseException{
        EntradaProduto produto = new EntradaProduto();
        ProdutoDAO dao = new ProdutoDAO();
        try{
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            
            produto.setCodigoEntrada(Integer.valueOf(TxtFieldCodProduto.getText()));
            produto.setNome(TextfieldNome.getText());
            produto.setCategoria(String.valueOf(CbCategoria.getValue()));
            produto.setTipoEntrada(String.valueOf(CbTipoEntrada.getValue()));
            produto.setQuantidade(Integer.valueOf(TxtFieldQuantidade.getText()));
            produto.setValorEntrada(Float.valueOf(TxtFieldValorUnitario.getText()));
            produto.setDataEntrada(formato.parse(DateDataEntrada.getEditor().getText()));
        }catch(RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema de Gerenciamento de Ong - Projeto Shalom");
            alert.setContentText(sql);
            alert.showAndWait();
         }
        if (dao.AtualizarProduto(produto)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema de Gerenciamento de Ong - Projeto Shalom");
            alert.setContentText(sql);
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema de Gerenciamento de Ong - Projeto Shalom");
            alert.setContentText(sql);
            alert.showAndWait();
        }
        LimparCampos();
        IncluirTableView();
    }
    
    public void ExcluirProdutoTabela(ActionEvent event){
        EntradaProduto produto = new EntradaProduto();
        ProdutoDAO dao = new ProdutoDAO();
        produto.setCodigoEntrada(Integer.valueOf(TxtFieldCodProduto.getText()));
        if (dao.ExcluirProduto(produto)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Excluido com sucesso");
            alert.showAndWait();
        }
        LimparCampos(); // Limpa os campos preenchidos da tela
        IncluirTableView(); // faz a atualização das modificações na tabela
    }

}