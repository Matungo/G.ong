package fxsistemaong.Controle;

import fxsistemaong.DAO.Banco;
import fxsistemaong.DAO.OficinasDAO;
import fxsistemaong.Objeto.AberturaOficina;
import fxsistemaong.Objeto.Beneficiario;
import fxsistemaong.Objeto.InclusaoAlunoOficinaAberta;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
 * @author Macbook
 */
public class TelaInclusaoAlunoOficinaController implements Initializable {
    @FXML
    private TextField TxtFieldNomeAluno;
    @FXML
    private TextField TxtFieldIdadeAluno;
    @FXML
    private TextField TxtFieldTelefoneAluno;
    @FXML
    private TextField TxtFieldCodigoAluno;
    @FXML
    private TextField TxtFieldCPFAluno;
    @FXML
    private ComboBox <AberturaOficina> ComboboxOficina;
    @FXML
    private ComboBox <AberturaOficina> ComboboxInstrutor;
    @FXML
    private ComboBox <AberturaOficina> ComboboxHorario;
    @FXML
    private Button ButtonBuscar;
    @FXML
    private Button BtnBuscarDadosAluno;
    @FXML
    private Button BtnAdicionarAlunoOficina;
    @FXML
    private Button ButtonRemover;
    @FXML
    private Button ButtonLimparCampos;
    @FXML
    private TableView TvListaChamadas;
    @FXML
    private TableColumn TableColumnNomeAluno;
    @FXML
    private TableColumn TableColumnIdadeAluno;
    @FXML
    private TableColumn TableColumnTelefoneAluno;
    
    private Banco banco;
    private Connection conexao;
    private PreparedStatement pst = null;
    private ResultSet rs;
    private Statement st;
    private String sql = null;
    
    private List<InclusaoAlunoOficinaAberta> ListaAddAlunoOficinaAberta;
    private ObservableList<InclusaoAlunoOficinaAberta> observableListAddAlunoOficinaAberta;
  
    // lista e observable lista para preencher a combobox de oficinas
    private List<AberturaOficina> addOficinas = new ArrayList<>();
    private ObservableList<AberturaOficina> observableListOficina;
 
    //Lista e observablelist para preencher a combobox de Instrutor
    private List<AberturaOficina> addFunc = new ArrayList<>();
    private ObservableList<AberturaOficina> observableListFunc;
    
    //Lista e observablelist para preencher a combobox de Horario
    private List<AberturaOficina> addHorario = new ArrayList<>();
    private ObservableList<AberturaOficina> observableListHorario;
    
    public int CodAlunoOficina = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //Procedimento para desabilitar campos demais componentes da tela assim que se abrir a tela de inclusão de aluno
    TxtFieldCodigoAluno.setDisable(false);
    TxtFieldCodigoAluno.clear();
    TxtFieldCPFAluno.setDisable(false);
    TxtFieldCPFAluno.clear();
    TxtFieldNomeAluno.setDisable(true);
    TxtFieldNomeAluno.clear();
    TxtFieldIdadeAluno.setDisable(true);
    TxtFieldIdadeAluno.clear();
    TxtFieldTelefoneAluno.setDisable(true);
    TxtFieldTelefoneAluno.clear();
    ComboboxOficina.setDisable(false);
    ComboboxInstrutor.setDisable(false);
    ComboboxHorario.setDisable(false);
    ButtonBuscar.setDisable(false);
    BtnBuscarDadosAluno.setDisable(false);
    BtnAdicionarAlunoOficina.setDisable(true);
    ButtonRemover.setDisable(true);
    ButtonLimparCampos.setDisable(true);
    TvListaChamadas.setDisable(true);        

    ComboboxOficina.setConverter(new StringConverter<AberturaOficina>() {
            @Override
            public String toString(AberturaOficina object) {
                if(object!=null){
                    return object.getNome();
                }
                return null;
            }

            @Override
            public AberturaOficina fromString(String string) {
                return null;
            }
        });
    
    ComboboxInstrutor.setConverter(new StringConverter<AberturaOficina>() {
            @Override
            public String toString(AberturaOficina object) {
                if(object!=null){
                    return object.getInstrutor();
                }
                return null;
            }

            @Override
            public AberturaOficina fromString(String string) {
                return null;
            }
        });
    
    ComboboxHorario.setConverter(new StringConverter<AberturaOficina>() {
            @Override
            public String toString(AberturaOficina object) {
                if(object!=null){
                    return object.getHorarioComeca();
                }
                return null;
            }

            @Override
            public AberturaOficina fromString(String string) {
                return null;
            }
        });
    
        AtualizarComboboxOficinas();
        TvListaChamadas.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue,newValue) -> selecionarItemTableViewListaChamadas((InclusaoAlunoOficinaAberta) newValue));
    
    } 
    
    public void LimparCampos(){
        TxtFieldCodigoAluno.setDisable(false);
        TxtFieldCodigoAluno.clear();
        TxtFieldCPFAluno.setDisable(false);
        TxtFieldCPFAluno.clear();
        TxtFieldNomeAluno.setDisable(true);
        TxtFieldNomeAluno.clear();
        TxtFieldIdadeAluno.setDisable(true);
        TxtFieldIdadeAluno.clear();
        TxtFieldTelefoneAluno.setDisable(true);
        TxtFieldTelefoneAluno.clear();
        ComboboxOficina.setDisable(false);
        ComboboxOficina.getSelectionModel().clearSelection();
        ComboboxInstrutor.setDisable(false);
        ComboboxInstrutor.getSelectionModel().clearSelection();
        ComboboxHorario.setDisable(false);
        ComboboxHorario.getSelectionModel().clearSelection();
        ButtonBuscar.setDisable(false);
        BtnBuscarDadosAluno.setDisable(false);
        BtnAdicionarAlunoOficina.setDisable(true);
        ButtonRemover.setDisable(true);
        ButtonLimparCampos.setDisable(true);
        TvListaChamadas.setDisable(true); 
        TvListaChamadas.getSelectionModel().clearSelection();
    }
    
    //Metodos para busca dos dados do Beneficiario que usara as oficinas
    public void BuscarBeneficiarioCPF(ActionEvent evento){
            Beneficiario bene = new Beneficiario();
            OficinasDAO dao = new OficinasDAO();
                        
            bene.setCpf(TxtFieldCPFAluno.getText());
        try{
            bene = dao.pesquisarBeneficiarioCPF(bene);
            
            TxtFieldCodigoAluno.setDisable(true);
            TxtFieldCPFAluno.setDisable(true);
            TxtFieldNomeAluno.setDisable(true);
            TxtFieldIdadeAluno.setDisable(true);
            TxtFieldTelefoneAluno.setDisable(true);
            ComboboxOficina.setDisable(false);
            ComboboxInstrutor.setDisable(false);
            ComboboxHorario.setDisable(false);
            ButtonBuscar.setDisable(true);
            BtnBuscarDadosAluno.setDisable(true);
            BtnAdicionarAlunoOficina.setDisable(false);
            ButtonRemover.setDisable(true);
            ButtonLimparCampos.setDisable(false);
            TvListaChamadas.setDisable(false); 
        
        }catch(RuntimeException E){
            System.out.println(E.getMessage());
        }
        
        
            Calendar dateOfBirth = new GregorianCalendar();
            Date data = bene.getDataNascimento();
        
            dateOfBirth.setTime(data);

            // Cria um objeto calendar com a data atual
            Calendar today = Calendar.getInstance();

            // Obtém a idade baseado no ano
            int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
            dateOfBirth.add(Calendar.YEAR, age);

            // se a data de hoje é antes da data de Nascimento, então diminui 1.
            if (today.before(dateOfBirth)) {
                age--;
            }
        
        
        TxtFieldCodigoAluno.setText(Integer.toString(bene.getCodigo()));
        TxtFieldCPFAluno.setText(bene.getCpf());
        TxtFieldNomeAluno.setText(bene.getNome());
        TxtFieldIdadeAluno.setText(Integer.toString(age));
        TxtFieldTelefoneAluno.setText(bene.getTelRes());
        
        //Mostrar se houve exito na busca do produto
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
        alert.setContentText("Pesquisado com Sucesso");
        alert.showAndWait();    
        }
    
    public void BuscarBeneficiarioCOD(ActionEvent evento){
            Beneficiario bene = new Beneficiario();
            OficinasDAO dao = new OficinasDAO();
                        
        bene.setCodigo(Integer.parseInt(TxtFieldCodigoAluno.getText()));
        try{
            bene = dao.pesquisarBeneficiarioCOD(bene);
            
            TxtFieldCodigoAluno.setDisable(true);
            TxtFieldCPFAluno.setDisable(true);
            TxtFieldNomeAluno.setDisable(true);
            TxtFieldIdadeAluno.setDisable(true);
            TxtFieldTelefoneAluno.setDisable(true);
            ComboboxOficina.setDisable(false);
            ComboboxInstrutor.setDisable(false);
            ComboboxHorario.setDisable(false);
            ButtonBuscar.setDisable(true);
            BtnBuscarDadosAluno.setDisable(true);
            BtnAdicionarAlunoOficina.setDisable(false);
            ButtonRemover.setDisable(true);
            ButtonLimparCampos.setDisable(false);
            TvListaChamadas.setDisable(false); 
        
        }catch(RuntimeException E){
            System.out.println(E.getMessage());
        }
            
        
            Calendar dateOfBirth = new GregorianCalendar();
            Date data = bene.getDataNascimento();
        
            dateOfBirth.setTime(data);

            // Cria um objeto calendar com a data atual
            Calendar today = Calendar.getInstance();

            // Obtém a idade baseado no ano
            int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
            dateOfBirth.add(Calendar.YEAR, age);

            // se a data de hoje é antes da data de Nascimento, então diminui 1.
            if (today.before(dateOfBirth)) {
                age--;
            }
        
        
            TxtFieldCodigoAluno.setText(Integer.toString(bene.getCodigo()));
            TxtFieldCPFAluno.setText(bene.getCpf());
            TxtFieldNomeAluno.setText(bene.getNome());
            TxtFieldIdadeAluno.setText(Integer.toString(age));
            TxtFieldTelefoneAluno.setText(bene.getTelRes());
            
            //Mostrar se houve exito na busca do produto
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Pesquisado com Sucesso");
            alert.showAndWait();
    }

    public void AtualizarComboboxOficinas(){
        OficinasDAO dao = new OficinasDAO();
        addOficinas = dao.ListarOficinaAberta();
        
        observableListOficina = FXCollections.observableArrayList(addOficinas);
        ComboboxOficina.setItems(observableListOficina);
    }
    
    public void AtualizarComboboxInstrutor(){
        OficinasDAO dao = new OficinasDAO();
        AberturaOficina oficina = new AberturaOficina();
        
        oficina.setNome(String.valueOf(ComboboxOficina.getSelectionModel().getSelectedItem().getNome()));
        addFunc = dao.ListarInstrutorOficinaAberta(oficina);
        
        observableListFunc = FXCollections.observableArrayList(addFunc);
        ComboboxInstrutor.setItems(observableListFunc);
        AtualizarTableView();
        TvListaChamadas.setDisable(false);
    }
    
    public void AtualizarComboboxHorario(){
        OficinasDAO dao = new OficinasDAO();
        AberturaOficina instrutor = new AberturaOficina();
        
        instrutor.setNome(String.valueOf(ComboboxOficina.getSelectionModel().getSelectedItem().getNome()));
        instrutor.setInstrutor(String.valueOf(ComboboxInstrutor.getSelectionModel().getSelectedItem().getInstrutor()));
        addHorario = dao.ListarHorarioOficinaAberta(instrutor);
        
        observableListHorario = FXCollections.observableArrayList(addHorario);
        ComboboxHorario.setItems(observableListHorario);
   
    }
    
    //metodo de Inclusão do Aluno da Oficina
    public void IncluirAlunoOficina (ActionEvent evento){
        
            InclusaoAlunoOficinaAberta oficina = new InclusaoAlunoOficinaAberta();
            
            try{
                oficina.setNomeOficina(String.valueOf(ComboboxOficina.getSelectionModel().getSelectedItem().getNome()));
                oficina.setNomeInstrutor(String.valueOf(ComboboxInstrutor.getSelectionModel().getSelectedItem().getInstrutor()));
                oficina.setHorarioComeca(String.valueOf(ComboboxHorario.getSelectionModel().getSelectedItem().getHorarioComeca()));
                oficina.setNomeAluno(TxtFieldNomeAluno.getText());
                oficina.setCPFAluno(TxtFieldCPFAluno.getText());
                oficina.setIdadeAluno(Integer.parseInt(TxtFieldIdadeAluno.getText()));
                oficina.setTelefoneAluno(TxtFieldTelefoneAluno.getText());
   
            }catch(Exception e){
                System.out.println(e.getMessage());
                }
        
                OficinasDAO dao = new OficinasDAO();
               if (dao.InserirAlunoOficina(oficina)){
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
           // InclusaoAlunoOficinaAberta oficina = new InclusaoAlunoOficinaAberta();
            String oficinaSel;
            
            TableColumnNomeAluno.setCellValueFactory(new PropertyValueFactory<InclusaoAlunoOficinaAberta, String> ("nomeAluno"));
            TableColumnIdadeAluno.setCellValueFactory(new PropertyValueFactory<InclusaoAlunoOficinaAberta, String>("idadeAluno"));
            TableColumnTelefoneAluno.setCellValueFactory(new PropertyValueFactory<InclusaoAlunoOficinaAberta, String>("telefoneAluno"));

            oficinaSel = ComboboxOficina.getSelectionModel().getSelectedItem().getNome();
             
            ListaAddAlunoOficinaAberta = dao.ListarListaChamadas(oficinaSel);
            
            observableListAddAlunoOficinaAberta = FXCollections.observableArrayList(ListaAddAlunoOficinaAberta);
            TvListaChamadas.setItems(observableListAddAlunoOficinaAberta); 
        }
    
    public void selecionarItemTableViewListaChamadas(InclusaoAlunoOficinaAberta oficina){

            if (oficina != null){

                CodAlunoOficina = oficina.getCod();
                TxtFieldNomeAluno.setText(oficina.getNomeAluno());
                TxtFieldIdadeAluno.setText(Integer.toString(oficina.getIdadeAluno()));
                TxtFieldTelefoneAluno.setText(oficina.getTelefoneAluno());
                TxtFieldCPFAluno.setText(oficina.getCPFAluno());

                
                //metodos para desabilitar e habitar os campos após a seleção
                TxtFieldCodigoAluno.setDisable(true);
                TxtFieldCPFAluno.setDisable(true);
                ButtonBuscar.setDisable(true);
                ComboboxOficina.setDisable(true);
                ComboboxInstrutor.setDisable(true);
                ComboboxHorario.setDisable(true);
                BtnBuscarDadosAluno.setDisable(true);
                ButtonLimparCampos.setDisable(false);
                TvListaChamadas.setDisable(false); 
                BtnAdicionarAlunoOficina.setDisable(true);
                ButtonRemover.setDisable(false);
                TxtFieldNomeAluno.setDisable(true);
                TxtFieldIdadeAluno.setDisable(true);
                TxtFieldTelefoneAluno.setDisable(true);
            }
        }
    
    public void ExcluirAlunoOficina(ActionEvent event){
        OficinasDAO dao = new OficinasDAO();
 
        if (dao.ExcluirAlunoOficina(CodAlunoOficina)) {//CodOficinaAberta variavel criada para guardar o número do Codigo da oficina selecionada na TableView
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Excluido com sucesso");
            alert.showAndWait();
        }
            // Limpa os campos preenchidos da tela
            TxtFieldCodigoAluno.setDisable(true);
            TxtFieldCPFAluno.setDisable(true);
            TxtFieldCPFAluno.clear();
            ButtonBuscar.setDisable(true);
            ComboboxOficina.setDisable(true);
            ComboboxInstrutor.setDisable(true);
            ComboboxHorario.setDisable(true);
            BtnBuscarDadosAluno.setDisable(true);
            ButtonLimparCampos.setDisable(false);
            TvListaChamadas.setDisable(false); 
            BtnAdicionarAlunoOficina.setDisable(true);
            ButtonRemover.setDisable(false);
            TxtFieldNomeAluno.setDisable(true);
            TxtFieldNomeAluno.clear();
            TxtFieldIdadeAluno.setDisable(true);
            TxtFieldIdadeAluno.clear();
            TxtFieldTelefoneAluno.setDisable(true);
            TxtFieldTelefoneAluno.clear();

            AtualizarTableView(); // faz a atualização das modificações na tabela
        }
}
