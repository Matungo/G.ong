package fxsistemaong.Controle;

import fxsistemaong.DAO.Banco;
import fxsistemaong.DAO.ProdutoDAO;
import fxsistemaong.Objeto.Beneficiario;
import fxsistemaong.Objeto.EntradaProduto;
import fxsistemaong.Objeto.Produto;
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
 * @author Thiago Lopes
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
    private TextField TxtFieldDataNasc;
    @FXML
    private TextField TxtFieldNomeProduto;
    @FXML
    private TextField TxtFieldTipoProduto;
    @FXML
    private TextField TxtFieldQuantidadeProduto;
    @FXML
    private TextField TxtFieldQtdSaida;
    @FXML
    private TextField TxtFieldCodEntrada;
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
    private int totalproduto;
    private int qtdentrada;
    private int qtdsaida;
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
        
        TxtFieldNomeBeneficiario.setDisable(true);
        TxtFieldCodEntrada.setDisable(true);
        TxtFieldDataNasc.setDisable(true);
        TxtFieldNomeProduto.setDisable(true);
        TxtFieldTipoProduto.setDisable(true);
        TxtFieldQuantidadeProduto.setDisable(true);
        TxtFieldQtdSaida.setDisable(true);
        TxtFieldDataEntrada.setDisable(true);
        TxtFieldValorEntrada.setDisable(true);
        TxtFieldValorSaida.setDisable(true);
        DateDataSaida.getEditor().setDisable(true);
        BtnRegistrarVenda.setDisable(true);
        BtnLimparCampos.setDisable(true);
        }
     
        public void LimparCampos (){
            TxtFieldCodigoBeneficiario.clear();
            TxtFieldCPFBeneficiario.clear();
            TxtFieldNomeBeneficiario.clear();
            TxtFieldDataNasc.clear();
            TxtFieldNomeProduto.clear();
            TxtFieldTipoProduto.clear();
            TxtFieldQuantidadeProduto.clear();
            TxtFieldQtdSaida.clear();
            TxtFieldDataEntrada.clear();
            TxtFieldValorEntrada.clear();
            TxtFieldValorSaida.clear();
            DateDataSaida.getEditor().clear();
            //comandos para desabilitar campos e botoes novamente
            TxtFieldQtdSaida.setDisable(true);
            TxtFieldValorSaida.setDisable(true);
            DateDataSaida.getEditor().setDisable(true);
            BtnRegistrarVenda.setDisable(true);
            BtnLimparCampos.setDisable(true);
        }
    
        //função que serve para carregar as variaveis dentro da tableview
        public void selecionarItemTableViewProduto(EntradaProduto entradaProduto){
            //Declaração da variavel para inserir a data na view
            DateFormat dataBR = DateFormat.getDateInstance(DateFormat.MEDIUM);
        
            if (entradaProduto != null){
                TxtFieldCodEntrada.setText(Integer.toString(entradaProduto.getCodigoEntrada()));
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
            TcQuantidade.setCellValueFactory(new PropertyValueFactory<EntradaProduto,Integer>("QuantidadeEntrada"));
            TcDataEntrada.setCellValueFactory(new PropertyValueFactory<EntradaProduto, Date>("DataEntrada"));
            TcTipoEntrada.setCellValueFactory(new PropertyValueFactory<EntradaProduto, String>("TipoEntrada"));
        
            ListaAddProdutos = dao.Listar();
        
        
            observableListAddProdutos = FXCollections.observableArrayList(ListaAddProdutos);
            TvProdutoEstoque.setItems(observableListAddProdutos); 
        }
    
    
        public void IncluirSaidaMercadoria (ActionEvent event){
        
            Produto produto = new Produto();
            SaidaProduto saida = new SaidaProduto();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            
            try{
                produto.setNome(TxtFieldNomeProduto.getText());
                produto.setCategoria(TxtFieldTipoProduto.getText());
                saida.setQuantidade(Integer.parseInt(TxtFieldQtdSaida.getText()));
                saida.setDataSaida(formato.parse(DateDataSaida.getEditor().getText()));
                saida.setValorSaida(Float.valueOf(TxtFieldValorSaida.getText()));   
            }catch(Exception e){
                System.out.println(e.getMessage());
                }
        
                ProdutoDAO dao = new ProdutoDAO();
               if (dao.AdicionarSaidaProduto(produto, saida)){
                   qtdentrada = Integer.parseInt(TxtFieldQuantidadeProduto.getText());
                   qtdsaida = Integer.parseInt(TxtFieldQtdSaida.getText());                   
                   totalproduto = qtdentrada - qtdsaida;
                   int cod = Integer.parseInt(TxtFieldCodEntrada.getText());
                   dao.AtualizaQuantidadeEntrada(totalproduto, cod);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
                    alert.setContentText("Salvo com Sucesso!!!");
                    alert.showAndWait();
                    LimparCampos();
                }
            
                AtualizarTableView();
            
            }
       
        
        //metodo para buscar o beneficiario que recebera a doação
        public void BuscarBeneficiarioCPF(ActionEvent evento){
            Beneficiario bene = new Beneficiario();
            ProdutoDAO dao = new ProdutoDAO();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        bene.setCpf(TxtFieldCPFBeneficiario.getText());
        try{
            bene = dao.PesquisarBeneficiario(bene);
            
        TxtFieldQtdSaida.setDisable(false);
        TxtFieldValorSaida.setDisable(false);
        DateDataSaida.getEditor().setDisable(false);
        BtnRegistrarVenda.setDisable(false);
        BtnLimparCampos.setDisable(false);
        
        }catch(RuntimeException E){
            System.out.println(E.getMessage());
        }
        
        TxtFieldCodigoBeneficiario.setText(Integer.toString(bene.getCodigo()));
        TxtFieldCPFBeneficiario.setText(bene.getCpf());
        TxtFieldNomeBeneficiario.setText(bene.getNome());
        TxtFieldDataNasc.setText(formato.format(bene.getDataNascimento()));
        
        //Mostrar se houve exito na busca do produto
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
        alert.setContentText("Pesquisado com Sucesso");
        alert.showAndWait();
            
            
        }
        
        //metodo para buscar o beneficiario que recebera a doação ou comprara algo
        public void BuscarBeneficiarioCOD(ActionEvent evento){
            Beneficiario bene = new Beneficiario();
            ProdutoDAO dao = new ProdutoDAO();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        bene.setCpf(TxtFieldCodigoBeneficiario.getText());
        try{
            bene = dao.PesquisarBeneficiarioCOD(bene);
            
        TxtFieldQtdSaida.setDisable(false);
        TxtFieldValorSaida.setDisable(false);
        DateDataSaida.getEditor().setDisable(false);
        BtnRegistrarVenda.setDisable(false);
        BtnLimparCampos.setDisable(false);
        }catch(RuntimeException E){
            System.out.println(E.getMessage());
        }
        
        TxtFieldCodigoBeneficiario.setText(Integer.toString(bene.getCodigo()));
        TxtFieldCPFBeneficiario.setText(bene.getCpf());
        TxtFieldNomeBeneficiario.setText(bene.getNome());
        TxtFieldDataNasc.setText(formato.format(bene.getDataNascimento()));
        
        //Mostrar se houve exito na busca do produto
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
        alert.setContentText("Pesquisado com Sucesso");
        alert.showAndWait();
            
            
        }
    
    
}    
    

