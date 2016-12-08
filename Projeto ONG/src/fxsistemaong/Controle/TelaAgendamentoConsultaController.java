
package fxsistemaong.Controle;

import fxsistemaong.DAO.ConsultaDAO;
import fxsistemaong.DAO.ProdutoDAO;
import fxsistemaong.Objeto.Beneficiario;
import fxsistemaong.Objeto.Consulta;
import fxsistemaong.Objeto.Dependentes;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Macbook
 */
public class TelaAgendamentoConsultaController implements Initializable {

@FXML
private Button BtnLimpar;
@FXML
private Button BtnAdicionar;
@FXML
private Button BtnExcluir;
@FXML
private Button BtnBuscar;
@FXML
private Button BtnBuscarCodigo;
@FXML
private TextField TxtFieldCodigo;
@FXML
private TextField TxtFieldCPF;
@FXML
private TextField TxtFieldNomePaciente;
@FXML
private TextField TxtFieldDataNascimento;
@FXML
private TextField TxtFieldQuantasConsultas;
@FXML
private ComboBox ComboBoxTipoConsulta;
@FXML
private RadioButton RButtonBeneficiario;
@FXML
private RadioButton RButtonDependentes;
@FXML
private TableView TvConsultasMarcadas;
@FXML
private TableColumn TcCodigo;
@FXML
private TableColumn TcNomePaciente;
@FXML
private TableColumn TcDataNascimento;
@FXML
private TableColumn TcCPF;
@FXML
private TableColumn TcTipoConsulta;

    private List<Consulta> ListaAddConsultas;
    private ObservableList<Consulta> observableListAddConsultas;
    private int nConsulta = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            TxtFieldCodigo.clear();
            TxtFieldCPF.clear();
            TxtFieldCodigo.setDisable(false);
            TxtFieldCPF.setDisable(false);
            TxtFieldNomePaciente.setDisable(true);
            TxtFieldDataNascimento.setDisable(true);
            TxtFieldQuantasConsultas.setDisable(true);
            TxtFieldNomePaciente.clear();
            TxtFieldDataNascimento.clear();
            TxtFieldQuantasConsultas.clear();
            BtnAdicionar.setDisable(true);
            BtnLimpar.setDisable(true);
            BtnExcluir.setDisable(true);
            BtnBuscar.setDisable(false);
            BtnBuscarCodigo.setDisable(false);
            RButtonBeneficiario.setDisable(false);
            RButtonBeneficiario.setSelected(true);//deixa o radiobutton selecionado, necessario estar dentro de um grupo
            RButtonDependentes.setDisable(false);
            ComboBoxTipoConsulta.getSelectionModel().clearSelection();//limpa a combobox
            ComboBoxTipoConsulta.setDisable(true);
        
        //Preenchidmento da combobox com as informações sobre qual tipo de entrada
        ObservableList<String> listTipoConsulta //combobox trabalha com lista por isso é necessario criar ela
                = FXCollections.observableArrayList("Acumpuntura", "Odontológica");
        ComboBoxTipoConsulta.setItems(listTipoConsulta);
        
        //METODO PARA PREENCHER A TABLE VIEW
        AtualizarTableView();
        
        TvConsultasMarcadas.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue,newValue) -> selecionarItemTableViewConsulta((Consulta) newValue));
    }  
    
    public void selecionarItemTableViewConsulta(Consulta consulta){
            //Declaração da variavel para inserir a data na view
            DateFormat dataBR = DateFormat.getDateInstance(DateFormat.MEDIUM);
        
            if (consulta != null){
                
                nConsulta = consulta.getCodigoConsulta();
                TxtFieldCodigo.setText(Integer.toString(consulta.getCodigoPaciente()));
                TxtFieldCPF.setText(consulta.getCpfPaciente());
                TxtFieldNomePaciente.setText(consulta.getNomePaciente());
                TxtFieldDataNascimento.setText(dataBR.format(consulta.getDataNascimento()));
                TxtFieldQuantasConsultas.setText(Integer.toString(consulta.getNumeroConsultas()));
                ComboBoxTipoConsulta.setValue(consulta.getTipoConsulta());
                
                //metodos para desabilitar e habitar os campos após a seleção
                TxtFieldCodigo.setDisable(true);
                TxtFieldCPF.setDisable(true);
                TxtFieldNomePaciente.setDisable(true);
                TxtFieldDataNascimento.setDisable(true);
                TxtFieldQuantasConsultas.setDisable(true);
                BtnAdicionar.setDisable(true);
                BtnLimpar.setDisable(false);
                BtnExcluir.setDisable(false);
                BtnBuscar.setDisable(true);
                BtnBuscarCodigo.setDisable(true);
                RButtonBeneficiario.setDisable(true);
                RButtonDependentes.setDisable(true);
                ComboBoxTipoConsulta.setDisable(true);
            }
        }
       
        //Metodos de uso com o botão
    public void BuscarPaciente (ActionEvent evento){
        String CPF = TxtFieldCPF.getText();
        
        if (RButtonBeneficiario.isSelected()){
           BuscarPacienteAdultoCPF(CPF);  
        } else{
           BuscarPacienteCPF(CPF);  
        }        
    }
    
    public void BuscarPacienteCodigo (ActionEvent evento){
        String codigo = TxtFieldCodigo.getText();
        
        if (RButtonBeneficiario.isSelected()){
            BuscarPacienteAdultoCod(codigo);
        }else {
            BuscarPacienteCod(codigo);
        }
    }
        
        //Metodo para limpeza dos campos após pressionar o botão Limpar
        public void LimparCampos (){
            TxtFieldCodigo.clear();
            TxtFieldCPF.clear();
            TxtFieldCodigo.setDisable(false);
            TxtFieldCPF.setDisable(false);
            TxtFieldNomePaciente.setDisable(true);
            TxtFieldDataNascimento.setDisable(true);
            TxtFieldQuantasConsultas.setDisable(true);
            TxtFieldNomePaciente.clear();
            TxtFieldDataNascimento.clear();
            TxtFieldQuantasConsultas.clear();
            BtnAdicionar.setDisable(true);
            BtnLimpar.setDisable(true);
            BtnExcluir.setDisable(true);
            BtnBuscar.setDisable(false);
            BtnBuscarCodigo.setDisable(false);
            RButtonBeneficiario.setDisable(false);
            RButtonBeneficiario.setSelected(true);
            RButtonDependentes.setDisable(false);
            ComboBoxTipoConsulta.getSelectionModel().clearSelection();//Limpa a seleção da combobox
            ComboBoxTipoConsulta.setDisable(true);
            
        }
    public void BuscarPacienteAdultoCPF(String cpf){
            Beneficiario bene = new Beneficiario();
            ProdutoDAO dao = new ProdutoDAO();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        bene.setCpf(TxtFieldCPF.getText());
        try{
            bene = dao.PesquisarBeneficiario(bene);
            //campos a serem habilitados e desabilitados
            TxtFieldCodigo.setDisable(true);
            TxtFieldCPF.setDisable(true);
            TxtFieldNomePaciente.setDisable(false);
            TxtFieldDataNascimento.setDisable(false);
            TxtFieldQuantasConsultas.setDisable(false);
            BtnAdicionar.setDisable(false);
            BtnLimpar.setDisable(false);
            BtnExcluir.setDisable(true);
            BtnBuscar.setDisable(true);
            BtnBuscarCodigo.setDisable(true);
            RButtonBeneficiario.setDisable(true);
            RButtonDependentes.setDisable(true);
            ComboBoxTipoConsulta.setDisable(false);
          
        }catch(RuntimeException E){
            System.out.println(E.getMessage());
        }
        
        TxtFieldCodigo.setText(Integer.toString(bene.getCodigo()));
        TxtFieldNomePaciente.setText(bene.getNome());
        TxtFieldDataNascimento.setText(formato.format(bene.getDataNascimento()));
        
        //Mostrar se houve exito na busca do produto
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
        alert.setContentText("Pesquisado com Sucesso");
        alert.showAndWait(); 
        }       

    private void BuscarPacienteCPF(String CPF) {
            Dependentes paciente = new Dependentes();
            ConsultaDAO consuldao = new ConsultaDAO();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            paciente.setCpfCrianca(TxtFieldCPF.getText());
        try{
            paciente = consuldao.PesquisarCriançaCPF(paciente);
            //comando para desabilitar alguns campos e habilitar outros
            TxtFieldCodigo.setDisable(true);
            TxtFieldCPF.setDisable(true);
            TxtFieldNomePaciente.setDisable(false);
            TxtFieldDataNascimento.setDisable(false);
            TxtFieldQuantasConsultas.setDisable(false);
            BtnAdicionar.setDisable(false);
            BtnLimpar.setDisable(false);
            BtnExcluir.setDisable(true);
            BtnBuscar.setDisable(true);
            BtnBuscarCodigo.setDisable(true);
            RButtonBeneficiario.setDisable(true);
            RButtonDependentes.setDisable(true);
            ComboBoxTipoConsulta.setDisable(false);
        
        }catch(RuntimeException E){
            System.out.println(E.getMessage());
        }
        
            TxtFieldCodigo.setText(Integer.toString(paciente.getCodigo()));
            TxtFieldNomePaciente.setText(paciente.getNomeCrianca());
            TxtFieldDataNascimento.setText(formato.format(paciente.getDataCrianca()));
        
            //Mostrar se houve exito na busca do produto
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Pesquisado com Sucesso");
            alert.showAndWait(); 

    }

    private void BuscarPacienteAdultoCod(String codigo) {
            Beneficiario bene = new Beneficiario();
            ProdutoDAO dao = new ProdutoDAO();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        bene.setCpf(TxtFieldCodigo.getText());
        try{
            bene = dao.PesquisarBeneficiarioCOD(bene);
            //habitam e desabilitam algumas funções após a busca
            TxtFieldCodigo.setDisable(true);
            TxtFieldCPF.setDisable(true);
            TxtFieldNomePaciente.setDisable(false);
            TxtFieldDataNascimento.setDisable(false);
            TxtFieldQuantasConsultas.setDisable(false);
            BtnAdicionar.setDisable(false);
            BtnLimpar.setDisable(false);
            BtnExcluir.setDisable(true);
            BtnBuscar.setDisable(true);
            BtnBuscarCodigo.setDisable(true);
            RButtonBeneficiario.setDisable(true);
            RButtonDependentes.setDisable(true);
            ComboBoxTipoConsulta.setDisable(false);
          
        }catch(RuntimeException E){
            System.out.println(E.getMessage());
        }
        
        TxtFieldCodigo.setText(Integer.toString(bene.getCodigo()));
        TxtFieldNomePaciente.setText(bene.getNome());
        TxtFieldDataNascimento.setText(formato.format(bene.getDataNascimento()));
        TxtFieldCPF.setText(bene.getCpf());
        //Mostrar se houve exito na busca do produto
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
        alert.setContentText("Pesquisado com Sucesso");
        alert.showAndWait();     
    }

    private void BuscarPacienteCod(String codigo) {
            Dependentes paciente = new Dependentes();
            ConsultaDAO consuldao = new ConsultaDAO();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            paciente.setCodigo(Integer.parseInt(TxtFieldCodigo.getText()));
        try{
            paciente = consuldao.PesquisarCriançaCOD(paciente);
            //comando para desabilitar alguns campos e habilitar outros
            TxtFieldCodigo.setDisable(true);
            TxtFieldCPF.setDisable(true);
            TxtFieldNomePaciente.setDisable(false);
            TxtFieldDataNascimento.setDisable(false);
            TxtFieldQuantasConsultas.setDisable(false);
            BtnAdicionar.setDisable(false);
            BtnLimpar.setDisable(false);
            BtnExcluir.setDisable(true);
            BtnBuscar.setDisable(true);
            BtnBuscarCodigo.setDisable(true);
            RButtonBeneficiario.setDisable(true);
            RButtonDependentes.setDisable(true);
            ComboBoxTipoConsulta.setDisable(false);
        
        }catch(RuntimeException E){
            System.out.println(E.getMessage());
        }
        
            TxtFieldCodigo.setText(Integer.toString(paciente.getCodigo()));
            TxtFieldNomePaciente.setText(paciente.getNomeCrianca());
            TxtFieldCPF.setText(paciente.getCpfCrianca());
            TxtFieldDataNascimento.setText(formato.format(paciente.getDataCrianca()));
        
            //Mostrar se houve exito na busca do produto
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Pesquisado com Sucesso");
            alert.showAndWait(); 
    }
    
    //METODO PARA INCLUIR A MARCACAO DE CONSULTA
    public void IncluirConsultaMarcada (ActionEvent event){
        
            Consulta consulta = new Consulta();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            
            try{
                consulta.setTipoConsulta(String.valueOf(ComboBoxTipoConsulta.getValue()));
                consulta.setCodigoPaciente(Integer.parseInt(TxtFieldCodigo.getText()));
                consulta.setCpfPaciente(TxtFieldCPF.getText());
                consulta.setNomePaciente(TxtFieldNomePaciente.getText());
                consulta.setDataNascimento(formato.parse(TxtFieldDataNascimento.getText()));
                consulta.setNumeroConsultas(Integer.parseInt(TxtFieldQuantasConsultas.getText()));
   
            }catch(Exception e){
                System.out.println(e.getMessage());
                }
        
                ConsultaDAO dao = new ConsultaDAO();
               if (dao.AdicionarConsultaMarcada(consulta)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
                    alert.setContentText("Salvo com Sucesso!!!");
                    alert.showAndWait();
                    // Desabilitar e habilitar alguns campos
                    TxtFieldCodigo.setDisable(true);
                    TxtFieldCPF.setDisable(true);
                    TxtFieldNomePaciente.setDisable(true);
                    TxtFieldDataNascimento.setDisable(true);
                    TxtFieldQuantasConsultas.setDisable(false);
                    TxtFieldQuantasConsultas.clear();
                    BtnAdicionar.setDisable(false);
                    BtnLimpar.setDisable(false);
                    BtnExcluir.setDisable(true);
                    BtnBuscar.setDisable(true);
                    BtnBuscarCodigo.setDisable(true);
                    RButtonBeneficiario.setDisable(true);
                    RButtonDependentes.setDisable(true);
                    ComboBoxTipoConsulta.getSelectionModel().clearSelection();;
                    ComboBoxTipoConsulta.setDisable(false);
                }
            
                AtualizarTableView();
            }
    
    //METODO PARA LISTAR DENTRO DA TABLE VIEW AS CONSULTAS CONFORME FOREM SENDO MARCADAS OU EXCLUIDAS EM SISTEMA
    public void AtualizarTableView(){
            ConsultaDAO dao= new ConsultaDAO();
            Consulta consulta = new Consulta();
        
            TcCodigo.setCellValueFactory(new PropertyValueFactory<Consulta,Integer>("codigoConsulta"));
            TcNomePaciente.setCellValueFactory(new PropertyValueFactory<Consulta,String>("nomePaciente"));
            TcDataNascimento.setCellValueFactory(new PropertyValueFactory<Consulta,Date>("dataNascimento"));
            TcCPF.setCellValueFactory(new PropertyValueFactory<Consulta,String>("cpfPaciente"));
            TcTipoConsulta.setCellValueFactory(new PropertyValueFactory<Consulta, String>("tipoConsulta"));
        
            ListaAddConsultas = dao.Listar();
        
        
            observableListAddConsultas = FXCollections.observableArrayList(ListaAddConsultas);
            TvConsultasMarcadas.setItems(observableListAddConsultas); 
        }
    
    public void ExcluirConsulta(ActionEvent event){
        Consulta consulta = new Consulta();
        ConsultaDAO dao = new ConsultaDAO();
 
        if (dao.ExcluirConsulta(nConsulta)) {//nConsulta variavel criada para guardar o número do Codigo da consulta selecionada na TableView
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Excluido com sucesso");
            alert.showAndWait();
        }
            LimparCampos(); // Limpa os campos preenchidos da tela
            AtualizarTableView(); // faz a atualização das modificações na tabela
        }

}    
    

