/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxsistemaong.Controle;

import fxsistemaong.DAO.Banco;
import fxsistemaong.DAO.ProdutoDAO;
import fxsistemaong.Objeto.EntradaProduto;
import fxsistemaong.Objeto.SaidaProduto;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Macbook
 */
public class TelaSaidaMercadoriaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField TxtFieldCodigoBeneficiario;
    @FXML
    private TextField TxtFieldCPFBeneficiario;
    @FXML
    private TextField TxtFieldNomeBeneficiario;
    @FXML
    private TextField TxtFieldIdade;
    @FXML
    private TextField TxtFieldNomeProduto;
    @FXML
    private TextField TxtFieldTipoProduto;
    @FXML
    private TextField TxtFieldQuantidadeProduto;
    @FXML
    private TextField TxtFieldDataEntrada;
    @FXML
    private TextField TxtFieldValorEntrada;
    @FXML
    private TextField TxtFieldValorSaida;
    //Variavel para Data
    @FXML
    private DatePicker DateDataSaida;
    //Variaveis referente ao TableView
    @FXML
    private TableView TvProdutoEstoque;
    @FXML
    private TableColumn TcCodigo;
    @FXML
    private TableColumn TcNomeProduto;
    @FXML
    private TableColumn TcTipoEntrada;
    @FXML
    private TableColumn TcCategoria;
    @FXML
    private TableColumn TcQuantidade;
    @FXML
    private TableColumn TcDataEntrada;
    //Variaveis referente aos botões
    @FXML
    private Button BtnBuscarCodigo;
    @FXML
    private Button BtnBuscarCPF;
    @FXML
    private Button BtnRegistrarVenda;
    @FXML
    private Button BtnLimparCampos;
    
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
        
            //Carrega a tabela com os dados referente ao banco entrada mercadoria
            AtualizarTableView();
        
            TvProdutoEstoque.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue,newValue) -> selecionarItemTableViewProduto((EntradaProduto) newValue));
        }
     
        public void LimparCampos (){
            TxtFieldCodigoBeneficiario.clear();
            TxtFieldCPFBeneficiario.clear();
            TxtFieldNomeBeneficiario.clear();
            TxtFieldIdade.clear();
            TxtFieldNomeProduto.clear();
            TxtFieldTipoProduto.clear();
            TxtFieldQuantidadeProduto.clear();
            TxtFieldDataEntrada.clear();
            TxtFieldValorEntrada.clear();
            TxtFieldValorSaida.clear();
            DateDataSaida.getEditor().clear();
        }//tvvyvy
    
        //função que serve para carregar as variaveis dentro da tableview
        public void selecionarItemTableViewProduto(EntradaProduto entradaProduto){
            //Declaração da variavel para inserir a data na view
            DateFormat dataBR = DateFormat.getDateInstance(DateFormat.MEDIUM);
        
            if (entradaProduto != null){
                TxtFieldNomeProduto.setText(entradaProduto.getNome());
                TxtFieldQuantidadeProduto.setText(Integer.toString(entradaProduto.getQuantidade()));
                TxtFieldTipoProduto.setText(entradaProduto.getTipoEntrada());
                TxtFieldValorEntrada.setText(Float.toString(entradaProduto.getValorEntrada()));
                TxtFieldDataEntrada.setText(dataBR.format(entradaProduto.getDataEntrada()));
            }
        }
        public void AtualizarTableView(){
            ProdutoDAO dao= new ProdutoDAO();
            EntradaProduto produto = new EntradaProduto();
        
            TcCodigo.setCellValueFactory(new PropertyValueFactory<EntradaProduto,Integer>("CodigoEntrada"));
            TcNomeProduto.setCellValueFactory(new PropertyValueFactory<EntradaProduto,String>("nome"));
            TcCategoria.setCellValueFactory(new PropertyValueFactory<EntradaProduto,String>("Categoria"));
            TcQuantidade.setCellValueFactory(new PropertyValueFactory<EntradaProduto,Integer>("Quantidade"));
            TcDataEntrada.setCellValueFactory(new PropertyValueFactory<EntradaProduto, Date>("DataEntrada"));
            TcTipoEntrada.setCellValueFactory(new PropertyValueFactory<EntradaProduto, String>("TipoEntrada"));
            //TcValorEntrada.setCellValueFactory(new PropertyValueFactory<>("ValorEntrada"));
        
            ListaAddProdutos = dao.Listar();
        
        
            observableListAddProdutos = FXCollections.observableArrayList(ListaAddProdutos);
            TvProdutoEstoque.setItems(observableListAddProdutos); 
        }
    
    
        public void IncluirSaidaMercadoria (ActionEvent event){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sistema G.onG - projeto Shalom  Helping");
            alert.setContentText("Luciano falta os dados dos beneficiarios!!!!");
            alert.showAndWait();
            
            SaidaProduto saida = new SaidaProduto();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            try{
                saida.setNome(TxtFieldNomeProduto.getText());
                saida.setCategoria(TxtFieldTipoProduto.getText());
                saida.setQuantidade(Integer.parseInt(TxtFieldQuantidadeProduto.getText()));
                saida.setDataSaida(formato.parse(DateDataSaida.getEditor().getText()));
                saida.setValorSaida(Float.valueOf(TxtFieldValorSaida.getText()));   
            }catch(Exception e){
                System.out.println(e.getMessage());
                }
        
                ProdutoDAO dao = new ProdutoDAO();
               /* if (dao.AdicionarSaidaProduto(saida)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
                    alert.setContentText("Salvo com Sucesso!!!");
                    alert.showAndWait();
                    LimparCampos();
                }*/
            
                AtualizarTableView();
            
            }
        
        //metodo para buscar o beneficiario que recebera a doação
        public void BuscarBeneficiarioCod(ActionEvent event){
            SaidaProduto saida= new SaidaProduto();
           
            
            
        }
    
    
}    
    

