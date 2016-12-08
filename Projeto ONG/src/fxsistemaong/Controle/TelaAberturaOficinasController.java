
package fxsistemaong.Controle;

import fxsistemaong.DAO.Banco;
import fxsistemaong.DAO.OficinasDAO;
import fxsistemaong.Objeto.AberturaOficina;
import fxsistemaong.Objeto.Funcionario;
import fxsistemaong.Objeto.Oficina;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Macbook
 */
public class TelaAberturaOficinasController implements Initializable {

    
 @FXML
 private TextField TxtFieldTipoOficina;
 @FXML
 private Button ButtonNovoOficina;
 @FXML
 private Button ButtonSalvarOficina;
 @FXML
 private ComboBox<Oficina> ComboBoxOficina;
 @FXML
 private ComboBox<Funcionario> ComboBoxInstrutor;
 @FXML
 private ComboBox<String> ComboboxDiaSemana;
 @FXML
 private ComboBox<String> ComboboxHorarioComeca;
 @FXML
 private ComboBox<String> ComboboxHorarioTermina;
 @FXML
 private Button ButtonAdicionarOficina;
 @FXML
 private Button ButtonAlterar;
 @FXML
 private Button ButtonExcluir;
 @FXML
 private Button ButtonLimparCampos;
 @FXML
 private TableView TvOficinasAbertas;
 @FXML
 private TableColumn TableColumnOficina;
 @FXML
 private TableColumn TableColumnInstrutor;
 @FXML
 private TableColumn TableColumnHorario;
 @FXML
 private TableColumn TableColumnHorarioTermino;
 
 private List<AberturaOficina> ListaAddOficinasAbertas;
 private ObservableList<AberturaOficina> observableListAddOficinasAbertas;
 private int CodOficinaAberta = 0;
 
 
 // lista e observable lista para preencher a combobox de oficinas
 private List<Oficina> addOficinas = new ArrayList<>();
 private ObservableList<Oficina> observableListOficina;
 
 //Lista e observablelist para preencher a combobox de Instrutor
 private List<Funcionario> addFunc = new ArrayList<>();
 private ObservableList<Funcionario> observableListFunc;

    private Banco banco;
    private Connection conexao;
    private PreparedStatement pst = null;
    private ResultSet rs;
    private Statement st;
    private String sql = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TxtFieldTipoOficina.setDisable(true);
        ButtonSalvarOficina.setDisable(true);
        ButtonNovoOficina.setDisable(false);
        ComboBoxOficina.setDisable(false);
        ComboBoxInstrutor.setDisable(false);
        ComboboxDiaSemana.setDisable(false);
        ComboboxHorarioComeca.setDisable(false);
        ComboboxHorarioTermina.setDisable(false);
        ButtonAdicionarOficina.setDisable(false);
        ButtonAlterar.setDisable(true);
        ButtonExcluir.setDisable(true);
        ButtonLimparCampos.setDisable(false);
        TvOficinasAbertas.setDisable(false);
        
        
        //Preenchidmento da combobox com as informações sobre dias da semana
        ObservableList<String> listaDiasSemana
               = FXCollections.observableArrayList("Segunda", "Terça","Quarta","Quinta","Sexta","Sábado");
        ComboboxDiaSemana.setItems(listaDiasSemana);
        
        //Preenchidmento da combobox com as informações sobre horario que começa a oficina
        ObservableList<String> listaHoraComeca
               = FXCollections.observableArrayList("07:30", "08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00");
        ComboboxHorarioComeca.setItems(listaHoraComeca);
        
        //Preenchidmento da combobox com as informações sobre horario que começa a oficina
        ObservableList<String> listaHoraTermina
               = FXCollections.observableArrayList("09:00", "10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00");
        ComboboxHorarioTermina.setItems(listaHoraTermina);
        
        
        ComboBoxOficina.setConverter(new StringConverter<Oficina>() {
            @Override
            public String toString(Oficina object) {
                if(object!=null){
                    return object.getNomeOficina();
                }
                return null;
            }

            @Override
            public Oficina fromString(String string) {
                return null;
            }
        });
        
        ComboBoxInstrutor.setConverter(new StringConverter<Funcionario>() {
            @Override
            public String toString(Funcionario object) {
                if(object!=null){
                    return object.getNome();
                }
                return null;
            }

            @Override
            public Funcionario fromString(String string) {
                return null;
            }
        });
        
        AtualizarComboboxInstrutor();
        AtualizarComboboxOficinas();
        AtualizarTableView();
        
        TvOficinasAbertas.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue,newValue) -> selecionarItemTableViewOficinasAbertas((AberturaOficina) newValue));
    } 
    
    public void selecionarItemTableViewOficinasAbertas(AberturaOficina oficina){
            //Declaração da variavel para inserir a data na view
            DateFormat dataBR = DateFormat.getDateInstance(DateFormat.MEDIUM);
        
            if (oficina != null){
                
                CodOficinaAberta = oficina.getCodOficina();
  
                ComboboxDiaSemana.setValue(oficina.getDiaSemana());
                ComboboxHorarioComeca.setValue(oficina.getHorarioComeca());
                ComboboxHorarioTermina.setValue(oficina.getHorarioTermina());

                
                //metodos para desabilitar e habitar os campos após a seleção
                TxtFieldTipoOficina.setDisable(true);
                ButtonSalvarOficina.setDisable(true);
                ButtonNovoOficina.setDisable(false);
                ComboBoxOficina.setDisable(false);
                //ComboBoxOficina.getSelectionModel().clearSelection();
                ComboBoxInstrutor.setDisable(false);
                //ComboBoxInstrutor.getSelectionModel().clearSelection();
                ComboboxDiaSemana.setDisable(true);
                //ComboboxDiaSemana.getSelectionModel().clearSelection();
                ComboboxHorarioComeca.setDisable(true);
                //ComboboxHorarioComeca.getSelectionModel().clearSelection();
                ComboboxHorarioTermina.setDisable(true);
                //ComboboxHorarioTermina.getSelectionModel().clearSelection();
                ButtonAdicionarOficina.setDisable(true);
                ButtonAlterar.setDisable(false);
                ButtonExcluir.setDisable(false);
                ButtonLimparCampos.setDisable(false);
                TvOficinasAbertas.setDisable(false); 
            }
        }
    
    //metodo simples que habilita os botões para cadastrar nova oficina.
    public void NovaOficina(ActionEvent event){
        //Botões e campos a serem desabilitados e habilitados após selecionar a opção de cadastrar nova oficina
        TxtFieldTipoOficina.setDisable(false);
        ButtonSalvarOficina.setDisable(false);
        ButtonNovoOficina.setDisable(true);
        ComboBoxOficina.setDisable(true);
        ComboBoxOficina.getSelectionModel().clearSelection();
        ComboBoxInstrutor.setDisable(true);
        ComboBoxInstrutor.getSelectionModel().clearSelection();
        ComboboxDiaSemana.setDisable(true);
        ComboboxDiaSemana.getSelectionModel().clearSelection();
        ComboboxHorarioComeca.setDisable(true);
        ComboboxHorarioComeca.getSelectionModel().clearSelection();
        ComboboxHorarioTermina.setDisable(true);
        ComboboxHorarioTermina.getSelectionModel().clearSelection();
        ButtonAdicionarOficina.setDisable(true);
        ButtonAlterar.setDisable(true);
        ButtonExcluir.setDisable(true);
        ButtonLimparCampos.setDisable(false);
        TvOficinasAbertas.setDisable(true);
    }
    
    //Metodo para limpeza dos campos da tela
    public void LimparCampos(){
        //Campos a serem habilitados e desabilitados ao selecionar o botão de limpar campos
        TxtFieldTipoOficina.setDisable(true);
        TxtFieldTipoOficina.clear();
        ButtonSalvarOficina.setDisable(true);
        ButtonNovoOficina.setDisable(false);
        ComboBoxOficina.setDisable(false);
        ComboBoxOficina.getSelectionModel().clearSelection();
        ComboBoxInstrutor.setDisable(false);
        ComboBoxInstrutor.getSelectionModel().clearSelection();
        ComboboxDiaSemana.setDisable(false);
        ComboboxDiaSemana.getSelectionModel().clearSelection();
        ComboboxHorarioComeca.setDisable(false);
        ComboboxHorarioComeca.getSelectionModel().clearSelection();
        ComboboxHorarioTermina.setDisable(false);
        ComboboxHorarioTermina.getSelectionModel().clearSelection();
        ButtonAdicionarOficina.setDisable(false);
        ButtonAlterar.setDisable(true);
        ButtonExcluir.setDisable(true);
        ButtonLimparCampos.setDisable(false);
        TvOficinasAbertas.setDisable(false);
    }
    
    //metodo de salvar a nova oficina criada no banco
    public void SalvarNovaOficina(ActionEvent event){
        Oficina oficina = new Oficina();
        try{
            oficina.setNomeOficina(TxtFieldTipoOficina.getText());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        OficinasDAO dao = new OficinasDAO();
            if (dao.InserirNovaOficina(oficina)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
                alert.setContentText("Salvo com Sucesso!!!");
                alert.showAndWait();
                LimparCampos();
                AtualizarComboboxOficinas();
            }
        
    }
    
    public void AtualizarComboboxOficinas(){
        OficinasDAO dao = new OficinasDAO();
       
        addOficinas = dao.ListarOficina();
        
        observableListOficina = FXCollections.observableArrayList(addOficinas);
        ComboBoxOficina.setItems(observableListOficina);
    }
    
    
    public void AtualizarComboboxInstrutor(){
        OficinasDAO dao = new OficinasDAO();
        
        addFunc = dao.ListarInstrutor();
        
        observableListFunc = FXCollections.observableArrayList(addFunc);
        ComboBoxInstrutor.setItems(observableListFunc);
   
    }
    //metodo de gravação de nova oficina aberta
    public void IncluirOficinaAberta (ActionEvent event){
        
            AberturaOficina oficina = new AberturaOficina();
            
            try{
                oficina.setNome(String.valueOf(ComboBoxOficina.getSelectionModel().getSelectedItem().getNomeOficina()));
                oficina.setInstrutor(String.valueOf(ComboBoxInstrutor.getSelectionModel().getSelectedItem().getNome()));
                oficina.setDiaSemana(String.valueOf(ComboboxDiaSemana.getValue()));
                oficina.setHorarioComeca(String.valueOf(ComboboxHorarioComeca.getValue()));
                oficina.setHorarioTermina(String.valueOf(ComboboxHorarioTermina.getValue()));
   
            }catch(Exception e){
                System.out.println(e.getMessage());
                }
        
                OficinasDAO dao = new OficinasDAO();
               if (dao.AdicionarOficinaAberta(oficina)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
                    alert.setContentText("Salvo com Sucesso!!!");
                    alert.showAndWait();
                    // Desabilitar e habilitar alguns campos
                    LimparCampos();
                }
            
                AtualizarTableView();
            }
    
    public void AtualizarTableView(){
            OficinasDAO dao= new OficinasDAO();
            AberturaOficina oficina = new AberturaOficina();
        
            TableColumnOficina.setCellValueFactory(new PropertyValueFactory<AberturaOficina, String> ("nome"));
            TableColumnInstrutor.setCellValueFactory(new PropertyValueFactory<AberturaOficina, String>("instrutor"));
            TableColumnHorario.setCellValueFactory(new PropertyValueFactory<AberturaOficina, String>("horarioComeca"));
            TableColumnHorarioTermino.setCellValueFactory(new PropertyValueFactory<AberturaOficina, String>("horarioTermina"));

            ListaAddOficinasAbertas = dao.Listar();
            
            observableListAddOficinasAbertas = FXCollections.observableArrayList(ListaAddOficinasAbertas);
            TvOficinasAbertas.setItems(observableListAddOficinasAbertas); 
        }
    
    
    public void ExcluirOficinasAbertas(ActionEvent event){
        OficinasDAO dao = new OficinasDAO();
 
        if (dao.ExcluirOficina(CodOficinaAberta)) {//CodOficinaAberta variavel criada para guardar o número do Codigo da oficina selecionada na TableView
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Excluido com sucesso");
            alert.showAndWait();
        }
            LimparCampos(); // Limpa os campos preenchidos da tela
            AtualizarTableView(); // faz a atualização das modificações na tabela
        }
    
    public void ModificarOficinaAberta(ActionEvent event) throws ParseException{
        AberturaOficina oficina = new AberturaOficina();
        OficinasDAO dao = new OficinasDAO();
        try{            
            oficina.setNome(String.valueOf(ComboBoxOficina.getSelectionModel().getSelectedItem().getNomeOficina()));
            oficina.setInstrutor(String.valueOf(ComboBoxInstrutor.getSelectionModel().getSelectedItem().getNome()));
            oficina.setDiaSemana(String.valueOf(ComboboxDiaSemana.getValue()));
            oficina.setHorarioComeca(String.valueOf(ComboboxHorarioComeca.getValue()));
            oficina.setHorarioTermina(String.valueOf(ComboboxHorarioTermina.getValue()));
   
        }catch(RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema de Gerenciamento de Ong - Projeto Shalom");
            alert.setContentText(sql);
            alert.showAndWait();
         }
        if (dao.AtualizarOficinaAberta(oficina, CodOficinaAberta)){
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
        AtualizarTableView();
        LimparCampos();
        
    }

}
