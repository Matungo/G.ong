package fxsistemaong.Controle;

import fxsistemaong.DAO.FuncionarioDAO;
import fxsistemaong.Objeto.Funcionario;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

public class TelaCadastroFuncionarioController implements Initializable {
            
    @FXML
    private TextField TxtCodigoFuncionario;
    @FXML
    private TextField TxtDataCadasroFuncionario;
    @FXML
    private TextField TxtNomeFuncionario;
    @FXML
    private TextField TxtRGFuncionario;
    @FXML
    private TextField TxtCPFFuncionario;
    @FXML
    private TextField TxtEmailFuncionario;
    @FXML
    private TextField TxtNascimentoFuncionario;
    @FXML
    private TextField TxtEnderecoFuncionario;
    @FXML
    private TextField TxtNumeroFuncionario;
    @FXML
    private TextField TxtComplementoFuncionario;
    @FXML
    private TextField TxtBairroFuncionario;
    @FXML
    private TextField TxtCidadeFuncionario;
    @FXML
    private TextField TxtCEPFuncionario;
    @FXML
    private TextField TxtFoneCelularFuncionario;
    @FXML
    private TextField TxtFoneResidencialFuncionario;
    @FXML
    private TextField TxtFoneRecadoFuncionario;
    @FXML
    private TextArea TxtAreaAptidoesFuncionario;
    @FXML
    private RadioButton RadioMasculinoFuncionario;
    @FXML
    private RadioButton RadioFemininoFuncionario;
    @FXML
    private CheckBox CheckboxSegunda;
    @FXML
    private CheckBox CheckboxTerca;
    @FXML
    private CheckBox CheckboxQuarta;
    @FXML
    private CheckBox CheckboxQuinta;
    @FXML
    private CheckBox CheckboxSexta;
    @FXML
    private CheckBox CheckboxSabadoManha;
    @FXML
    private CheckBox CheckboxSabadoTarde;

    public TelaCadastroFuncionarioController() {
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    @FXML
    public void BtnSalvarFuncionario(ActionEvent evento) throws SQLException
    {
        String dispdia;
        String dispsab;
        Funcionario func = new Funcionario();
        try {
            func.setNome(TxtNomeFuncionario.getText());
            func.setRg(TxtRGFuncionario.getText());
            func.setCpf(TxtCPFFuncionario.getText());
            func.setEmail(TxtEmailFuncionario.getText());
            func.setEndereco(TxtEnderecoFuncionario.getText());
            func.setNumero(TxtNumeroFuncionario.getText());
            func.setComplemento(TxtComplementoFuncionario.getText());
            func.setBairro(TxtBairroFuncionario.getText());
            func.setCidade(TxtCidadeFuncionario.getText());
            func.setCep(TxtCEPFuncionario.getText());
            func.setFone1(TxtFoneCelularFuncionario.getText());
            func.setFone2(TxtFoneRecadoFuncionario.getText());
            func.setFone3(TxtFoneResidencialFuncionario.getText());
            dispdia = carregardia();
            dispsab = carregarsab();
            func.setDisp_dia(dispdia);
            func.setDisp_sab(dispsab);
            if (RadioFemininoFuncionario.isSelected())
            {
                func.setSexo("F");
            }
            else
            {
                func.setSexo("M");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        FuncionarioDAO dao = new FuncionarioDAO();
        if (dao.SalvarFuncionario(func)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Salvo com sucesso");
            alert.showAndWait();
        }
    }
    
    @FXML
    public void BtnAtualizarFuncionario(ActionEvent event)throws SQLException {
        Funcionario func = new Funcionario();
        func.setCod(Integer.valueOf(TxtCodigoFuncionario.getText()));
        try {
            FuncionarioDAO dao = new FuncionarioDAO();
            func = dao.atualizarFuncionario(func);
        } catch (RuntimeException E) {
            System.out.println(E.getMessage());
        }
    }
    
    @FXML
    public void BtnPesquisar(ActionEvent evento)
    {
        Funcionario func = new Funcionario();
        func.setCod(Integer.parseInt(TxtCodigoFuncionario.getText()));
        try {
            FuncionarioDAO dao = new FuncionarioDAO();
            func = dao.pesquisarFuncionario(func);
        } catch (RuntimeException E) {
            System.out.println(E.getMessage());
        }
        TxtAreaAptidoesFuncionario.setText(func.getAptidoes());
        TxtBairroFuncionario.setText(func.getBairro());
        TxtCEPFuncionario.setText(func.getCep());
        TxtCPFFuncionario.setText(func.getCpf());
        TxtCidadeFuncionario.setText(func.getCidade());
        TxtComplementoFuncionario.setText(func.getComplemento());
        //TxtDataCadasroFuncionario.setText(func.getDatacadastro());
        TxtEmailFuncionario.setText(func.getEmail());
        TxtEnderecoFuncionario.setText(func.getEndereco());
        TxtFoneCelularFuncionario.setText(func.getFone1());
        TxtFoneRecadoFuncionario.setText(func.getFone2());
        TxtFoneResidencialFuncionario.setText(func.getFone3());
        TxtNascimentoFuncionario.setText(func.getDatanasc());
        TxtNomeFuncionario.setText(func.getNome());
        TxtNumeroFuncionario.setText(func.getNumero());
        TxtRGFuncionario.setText(func.getRg());
        if ("M".equals(func.getSexo()))
        {
            RadioMasculinoFuncionario.setSelected(true);
        }
        else
        {
            RadioFemininoFuncionario.setSelected(true);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
        alert.setContentText("REGISTRO ENCONTRADO");
        alert.showAndWait();
    }
    
    @FXML
    public void BtnExcluir(ActionEvent event) {
        Funcionario func = new Funcionario();
        FuncionarioDAO dao = new FuncionarioDAO();
        func.setCod(Integer.valueOf(TxtCodigoFuncionario.getText()));
        if (dao.excluirFuncionario(func)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Excluido com sucesso");
            alert.showAndWait();
        }
    }
            
    @FXML
    public void BtnLimpar(ActionEvent evento)
    {
        TxtAreaAptidoesFuncionario.setText("");
        TxtBairroFuncionario.setText("");
        TxtCEPFuncionario.setText("");
        TxtCPFFuncionario.setText("");
        TxtCidadeFuncionario.setText("");
        TxtCodigoFuncionario.setText("");
        TxtComplementoFuncionario.setText("");
        TxtDataCadasroFuncionario.setText("");
        TxtEmailFuncionario.setText("");
        TxtEnderecoFuncionario.setText("");
        TxtFoneCelularFuncionario.setText("");
        TxtFoneRecadoFuncionario.setText("");
        TxtFoneResidencialFuncionario.setText("");
        TxtNascimentoFuncionario.setText("");
        TxtNomeFuncionario.setText("");
        TxtNumeroFuncionario.setText("");
        TxtRGFuncionario.setText("");
        System.out.println("TELA FUNCIONÁRIOS LIMPA");
    }

    private String carregardia() {
        String dispdia = "";
        if (CheckboxSegunda.isSelected()){dispdia = " seg |";}
        if (CheckboxTerca.isSelected()){dispdia = dispdia.concat(" ter |") ;}
        if (CheckboxQuarta.isSelected()){dispdia = dispdia.concat(" qua |") ;}
        if (CheckboxQuinta.isSelected()){dispdia = dispdia.concat(" qui |") ;}
        if (CheckboxSexta.isSelected()){dispdia = dispdia.concat(" sex") ;}
        System.out.println(dispdia);
        return dispdia;
    }

    private String carregarsab() {
        String dispsab = "";
        if (CheckboxSabadoManha.isSelected()){dispsab = dispsab.concat("manha |");}
        if (CheckboxSabadoTarde.isSelected()){dispsab = dispsab.concat("tarde");}
        return dispsab;
    }
}