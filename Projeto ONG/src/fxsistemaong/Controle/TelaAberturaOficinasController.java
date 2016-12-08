/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxsistemaong.Controle;

import fxsistemaong.DAO.OficinasDAO;
import fxsistemaong.Objeto.Funcionario;
import fxsistemaong.Objeto.Oficina;
import java.net.URL;
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
import javafx.scene.control.TextField;
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
 
 
 
 
 // lista e observable lista para preencher a combobox de oficinas
 private List<Oficina> addOficinas = new ArrayList<>();
 private ObservableList<Oficina> observableListOficina;
 
 //Lista e observablelist para preencher a combobox de Instrutor
 private List<Funcionario> addFunc = new ArrayList<>();
 private ObservableList<Funcionario> observableListFunc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TxtFieldTipoOficina.setDisable(true);
        ButtonSalvarOficina.setDisable(true);
        
        
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
    }    
    
    //metodo simples que habilita os botões para cadastrar nova oficina.
    public void NovaOficina(ActionEvent event){
        TxtFieldTipoOficina.setDisable(false);
        ButtonSalvarOficina.setDisable(false);
        ButtonNovoOficina.setDisable(true);
    }
    
    //Metodo para limpeza dos campos da tela
    public void LimparCampos(){
        TxtFieldTipoOficina.clear();
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
                TxtFieldTipoOficina.setDisable(true);
                ButtonSalvarOficina.setDisable(true);
                ButtonNovoOficina.setDisable(false);
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

}
