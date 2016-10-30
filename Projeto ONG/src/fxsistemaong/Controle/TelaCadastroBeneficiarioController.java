package fxsistemaong.Controle;

import fxsistemaong.DAO.BeneficiarioDAO;
import fxsistemaong.Objeto.Beneficiario;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaCadastroBeneficiarioController implements Initializable {

    @FXML
    private TextField TxtCodigoBeneficiario;
    @FXML
    private Label TxtDataCadastro;
    @FXML
    private TextField TxtNome;
    @FXML
    private DatePicker TxtNascimento;
    @FXML
    private TextField TxtRG;
    @FXML
    private TextField TxtNIS;
    @FXML
    private TextField TxtCPF;
    @FXML
    private TextField TxtEmail;
    @FXML
    private RadioButton RadioMasculino;
    @FXML
    private RadioButton RadioFeminino;
    @FXML
    private TextField TxtEndereco;
    @FXML
    private TextField TxtNumero;
    @FXML
    private TextField TxtCEP;
    @FXML
    private TextField TxtComplemento;
    @FXML
    private TextField TxtBairro;
    @FXML
    private TextField TxtCidade;
    @FXML
    private ComboBox ComboEstadoCivil;
    @FXML
    private RadioButton RadioFilhoSim;
    @FXML
    private RadioButton RadioFilhoNao;
    @FXML
    private TextField TxtQuantos;
    @FXML
    private TextField TxtIdadeFilhos;
    @FXML
    private TextField TxtFoneResidencial;
    @FXML
    private TextField TxtFoneRecado;
    @FXML
    private TextField TxtFoneCelular;
    @FXML
    private TextField TxtProfissao;
    @FXML
    private TextField TxtRenda;
    @FXML
    private CheckBox CheckboxContacao;
    @FXML
    private CheckBox CheckboxEducacaoFinanceira;
    @FXML
    private CheckBox CheckboxDesenhoMecanico;
    @FXML
    private CheckBox CheckboxEmprestimo;
    @FXML
    private CheckBox CheckboxDesignModa;
    @FXML
    private CheckBox CheckboxAulaReforço;
    @FXML
    private CheckBox CheckboxInformatica;
    @FXML
    private CheckBox CheckboxIngles;
    @FXML
    private CheckBox CheckboxEspanhol;
    @FXML
    private CheckBox CheckboxArtesanato;
    @FXML
    private CheckBox CheckboxTeclado;
    @FXML
    private CheckBox CheckboxViolao;
    @FXML
    private CheckBox CheckboxCapoeira;
    @FXML
    private CheckBox CheckboxFraldasA;
    @FXML
    private CheckBox CheckboxFraldasI;
    @FXML
    private CheckBox CheckboxAcupuntura;
    @FXML
    private CheckBox CheckboxCestaBasica;
    @FXML
    private CheckBox CheckboxLeite;
    @FXML
    private CheckBox CheckboxOutros;
    @FXML
    private TextField TxtInteresseOutros;
    @FXML
    private TextArea TxtAreaObservacoes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> estadoCivil
                = FXCollections.observableArrayList("Solteiro(a)", "Casado(a)", "Separado(a)", "Divorciado(a)", "Viuvo(a)");
        ComboEstadoCivil.setItems(estadoCivil);
        //coloca a lista aqui

        //coloca a data atual para a data do cadastro no padrao brasileiro//
        TxtDataCadastro.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString());
    }

    @FXML
    public void pesquisarBeneficiario(ActionEvent event) {
        Beneficiario bene = new Beneficiario();
        bene.setCodigo(Integer.parseInt(TxtCodigoBeneficiario.getText()));
        try {
            BeneficiarioDAO dao = new BeneficiarioDAO();
            bene = dao.pesquisarBeneficiario(bene);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        //formato medio para padrao brasileiro//
        DateFormat dataBr = DateFormat.getDateInstance(DateFormat.MEDIUM);
        //
        TxtDataCadastro.setText(dataBr.format(bene.getDataCadastro()));//transformar date americano do banco em date br 
        TxtNome.setText(bene.getNome());
        TxtNascimento.getEditor().setText(dataBr.format(bene.getDataNascimento()));
        TxtRG.setText(bene.getRg());
        TxtNIS.setText(bene.getNis());
        TxtCPF.setText(bene.getCpf());
        TxtEmail.setText(bene.getEmail());
        if (bene.getSexo().equalsIgnoreCase("M")) {
            RadioMasculino.setSelected(true);
        } else {
            RadioMasculino.setSelected(true);
        }
        TxtEndereco.setText(bene.getEndereco());
        TxtNumero.setText(String.valueOf(bene.getNumero()));
        TxtCEP.setText(bene.getCEP());
        TxtComplemento.setText(bene.getComplemento());
        TxtBairro.setText(bene.getBairro());
        TxtCidade.setText(bene.getCidade());
        ComboEstadoCivil.getSelectionModel().select(bene.getEstadoCivil());
        if (bene.getFilhos() == 1) {
            RadioFilhoSim.setSelected(true);
            TxtQuantos.setText(String.valueOf(bene.getQtdeFilhos()));
            TxtIdadeFilhos.setText(String.valueOf(bene.getIdade()));
        } else {
            RadioFilhoNao.setSelected(true);
        }
        TxtFoneResidencial.setText(bene.getTelRes());
        TxtFoneRecado.setText(bene.getTelRec());
        TxtFoneCelular.setText(bene.getTelCel());
        TxtProfissao.setText(bene.getProfissao());
        TxtRenda.setText(String.valueOf(bene.getRendaFamiliar()));
        switch (bene.getInteresse()) {
            case 1:
                CheckboxContacao.setSelected(true);
                break;
            case 2:
                CheckboxEducacaoFinanceira.setSelected(true);
                break;
            case 3:
                CheckboxDesenhoMecanico.setSelected(true);
                break;
            case 4:
                CheckboxEmprestimo.setSelected(true);
                break;
            case 5:
                CheckboxDesignModa.setSelected(true);
                break;
            case 6:
                CheckboxAulaReforço.setSelected(true);
                break;
            case 7:
                CheckboxInformatica.setSelected(true);
                break;
            case 8:
                CheckboxIngles.setSelected(true);
                break;
            case 9:
                CheckboxEspanhol.setSelected(true);
                break;
            case 10:
                CheckboxArtesanato.setSelected(true);
                break;
            case 11:
                CheckboxTeclado.setSelected(true);
                break;
            case 12:
                CheckboxViolao.setSelected(true);
                break;
            case 13:
                CheckboxCapoeira.setSelected(true);
                break;
            case 14:
                CheckboxFraldasA.setSelected(true);
                break;
            case 15:
                CheckboxFraldasI.setSelected(true);
                break;
            case 16:
                CheckboxAcupuntura.setSelected(true);
                break;
            case 17:
                CheckboxCestaBasica.setSelected(true);
                break;
            case 18:
                CheckboxLeite.setSelected(true);
                break;
            case 19:
                CheckboxOutros.setSelected(true);
                TxtInteresseOutros.setText(bene.getQual());
                break;

        }
        TxtAreaObservacoes.setText(bene.getObs());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
        alert.setContentText("Pesquisado com sucesso");
        alert.showAndWait();

    }

    @FXML
    public void salvarBeneficiario(ActionEvent event) throws ParseException {
        Beneficiario bene = new Beneficiario();
        BeneficiarioDAO dao = new BeneficiarioDAO();
        try {
            //formatar a data atual exibida no textfield em padrao date amricano para colocar no banco
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            //
            bene.setDataCadastro(formato.parse((TxtDataCadastro.getText())));
            bene.setNome(TxtNome.getText());
            bene.setDataNascimento(formato.parse((TxtNascimento.getEditor().getText())));
            bene.setRg(TxtRG.getText());
            bene.setNis(TxtNIS.getText());
            bene.setCpf(TxtCPF.getText());
            bene.setEmail(TxtEmail.getText());
            if (RadioMasculino.isSelected()) {
                bene.setSexo("M");
            } else {
                bene.setSexo("F");
            }
            bene.setEndereco(TxtEndereco.getText());
            bene.setNumero(Integer.parseInt(TxtNumero.getText()));
            bene.setComplemento(TxtComplemento.getText());
            bene.setCEP(TxtCEP.getText());
            bene.setBairro(TxtBairro.getText());
            bene.setCidade(TxtCidade.getText());
            bene.setEstadoCivil(ComboEstadoCivil.getValue().toString());
            if (RadioFilhoSim.isSelected()) {
                bene.setFilhos(1);
                bene.setQtdeFilhos(Integer.parseInt(TxtQuantos.getText()));
                bene.setIdade(Integer.parseInt(TxtIdadeFilhos.getText()));
            } else {
                bene.setFilhos(0);
            }
            bene.setTelRes(TxtFoneResidencial.getText());
            bene.setTelRec(TxtFoneRecado.getText());
            bene.setTelCel(TxtFoneCelular.getText());
            bene.setProfissao(TxtProfissao.getText());
            bene.setRendaFamiliar(Float.parseFloat(TxtRenda.getText()));
            if (CheckboxContacao.isSelected()) {
                bene.setInteresse(1);
            }
            if (CheckboxEducacaoFinanceira.isSelected()) {
                bene.setInteresse(2);
            }
            if (CheckboxDesenhoMecanico.isSelected()) {
                bene.setInteresse(3);
            }
            if (CheckboxEmprestimo.isSelected()) {
                bene.setInteresse(4);
            }
            if (CheckboxDesignModa.isSelected()) {
                bene.setInteresse(5);
            }
            if (CheckboxAulaReforço.isSelected()) {
                bene.setInteresse(6);
            }
            if (CheckboxInformatica.isSelected()) {
                bene.setInteresse(7);
            }
            if (CheckboxIngles.isSelected()) {
                bene.setInteresse(8);
            }
            if (CheckboxEspanhol.isSelected()) {
                bene.setInteresse(9);
            }
            if (CheckboxArtesanato.isSelected()) {
                bene.setInteresse(10);
            }
            if (CheckboxTeclado.isSelected()) {
                bene.setInteresse(11);
            }
            if (CheckboxViolao.isSelected()) {
                bene.setInteresse(12);
            }
            if (CheckboxCapoeira.isSelected()) {
                bene.setInteresse(13);
            }
            if (CheckboxFraldasA.isSelected()) {
                bene.setInteresse(14);
            }
            if (CheckboxFraldasI.isSelected()) {
                bene.setInteresse(15);
            }
            if (CheckboxAcupuntura.isSelected()) {
                bene.setInteresse(16);
            }
            if (CheckboxCestaBasica.isSelected()) {
                bene.setInteresse(17);
            }
            if (CheckboxLeite.isSelected()) {
                bene.setInteresse(18);
            }
            if (CheckboxOutros.isSelected()) {
                bene.setInteresse(19);
                bene.setQual(TxtInteresseOutros.getText());
            }
            bene.setObs(TxtAreaObservacoes.getText());

        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }
        if (dao.salvarBeneficiarios(bene)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Atualizado com sucesso");
            alert.showAndWait();
            limparCampos(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Erro ao atualizar");
            alert.showAndWait();
         
        }
    }

    @FXML
    public void atualizarBeneficiario(ActionEvent event) throws ParseException {
        Beneficiario bene = new Beneficiario();
        BeneficiarioDAO dao = new BeneficiarioDAO();
        try {
            //formatar a data atual exibida no textfield em padrao date amricano para colocar no banco
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            //
            bene.setCodigo(Integer.parseInt(TxtCodigoBeneficiario.getText()));
            bene.setNome(TxtNome.getText());
            bene.setDataNascimento(formato.parse((TxtNascimento.getEditor().getText())));
            bene.setRg(TxtRG.getText());
            bene.setNis(TxtNIS.getText());
            bene.setCpf(TxtCPF.getText());
            bene.setEmail(TxtEmail.getText());
            if (RadioMasculino.isSelected()) {
                bene.setSexo("M");
            } else {
                bene.setSexo("F");
            }
            bene.setEndereco(TxtEndereco.getText());
            bene.setNumero(Integer.parseInt(TxtNumero.getText()));
            bene.setComplemento(TxtComplemento.getText());
            bene.setBairro(TxtBairro.getText());
            bene.setCidade(TxtCidade.getText());
            bene.setEstadoCivil(ComboEstadoCivil.getEditor().getText());
            if (RadioFilhoSim.isSelected()) {
                bene.setFilhos(1);
                bene.setQtdeFilhos(Integer.parseInt(TxtQuantos.getText()));
                bene.setIdade(Integer.parseInt(TxtIdadeFilhos.getText()));
            } else {
                bene.setFilhos(0);
            }
            bene.setTelRes(TxtFoneResidencial.getText());
            bene.setTelRec(TxtFoneRecado.getText());
            bene.setTelCel(TxtFoneCelular.getText());
            bene.setProfissao(TxtProfissao.getText());
            bene.setRendaFamiliar(Float.parseFloat(TxtRenda.getText()));
            if (CheckboxContacao.isSelected()) {
                bene.setInteresse(1);
            }
            if (CheckboxEducacaoFinanceira.isSelected()) {
                bene.setInteresse(2);
            }
            if (CheckboxDesenhoMecanico.isSelected()) {
                bene.setInteresse(3);
            }
            if (CheckboxEmprestimo.isSelected()) {
                bene.setInteresse(4);
            }
            if (CheckboxDesignModa.isSelected()) {
                bene.setInteresse(5);
            }
            if (CheckboxAulaReforço.isSelected()) {
                bene.setInteresse(6);
            }
            if (CheckboxInformatica.isSelected()) {
                bene.setInteresse(7);
            }
            if (CheckboxIngles.isSelected()) {
                bene.setInteresse(8);
            }
            if (CheckboxEspanhol.isSelected()) {
                bene.setInteresse(9);
            }
            if (CheckboxArtesanato.isSelected()) {
                bene.setInteresse(10);
            }
            if (CheckboxTeclado.isSelected()) {
                bene.setInteresse(11);
            }
            if (CheckboxViolao.isSelected()) {
                bene.setInteresse(12);
            }
            if (CheckboxCapoeira.isSelected()) {
                bene.setInteresse(13);
            }
            if (CheckboxFraldasA.isSelected()) {
                bene.setInteresse(14);
            }
            if (CheckboxFraldasI.isSelected()) {
                bene.setInteresse(15);
            }
            if (CheckboxAcupuntura.isSelected()) {
                bene.setInteresse(16);
            }
            if (CheckboxCestaBasica.isSelected()) {
                bene.setInteresse(17);
            }
            if (CheckboxLeite.isSelected()) {
                bene.setInteresse(18);
            }
            if (CheckboxOutros.isSelected()) {
                bene.setInteresse(19);
                bene.setQual(TxtInteresseOutros.getText());
            }
            bene.setObs(TxtAreaObservacoes.getText());

        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }
        if (dao.atualizarBeneficiario(bene)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Atualizado com sucesso");
            alert.showAndWait();
            limparCampos(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Erro ao atualizar");
            alert.showAndWait();
             
        }
    }

    @FXML
    public void excluirBeneficiario(ActionEvent event) {
        Beneficiario bene = new Beneficiario();
        BeneficiarioDAO dao = new BeneficiarioDAO();
        bene.setCodigo(Integer.parseInt(TxtCodigoBeneficiario.getText()));
        if (dao.excluirDependentes(bene)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Excluido com sucesso");
            alert.showAndWait();
        }
        limparCampos(event);
    }

    @FXML
    public void consultarBeneficiario(ActionEvent event) {
        Beneficiario bene = new Beneficiario();
        bene.setCpf(TxtCPF.getText());
        try {
            BeneficiarioDAO dao = new BeneficiarioDAO();
            bene = dao.consultarBeneficiario(bene);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        //formato medio para padrao brasileiro//
        DateFormat dataBr = DateFormat.getDateInstance(DateFormat.MEDIUM);
        //
        TxtNome.setText(bene.getNome());
        TxtNascimento.getEditor().setText(dataBr.format(bene.getDataNascimento()));
        TxtRG.setText(bene.getRg());
        TxtNIS.setText(bene.getNis());
        TxtCPF.setText(bene.getCpf());
        TxtEmail.setText(bene.getEmail());
        if (bene.getSexo().equalsIgnoreCase("M")) {
            RadioMasculino.setSelected(true);
        } else {
            RadioMasculino.setSelected(true);
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
        alert.setContentText("Pesquisado com sucesso");
        alert.showAndWait();
        
    }

    @FXML
    public void limparCampos(ActionEvent event) {

        TxtCodigoBeneficiario.clear();
        TxtNome.clear();
        TxtNascimento.getEditor().clear();
        TxtRG.clear();
        TxtNIS.clear();
        TxtCPF.clear();
        TxtEmail.clear();
        RadioFeminino.setSelected(false);
        RadioMasculino.setSelected(false);
        TxtEndereco.clear();
        TxtNumero.clear();
        TxtCEP.clear();
        TxtComplemento.clear();
        TxtBairro.clear();
        TxtCidade.clear();
        ComboEstadoCivil.getSelectionModel().clearSelection();
        RadioFilhoSim.setSelected(false);
        RadioFilhoNao.setSelected(false);
        TxtQuantos.clear();
        TxtIdadeFilhos.clear();
        TxtFoneResidencial.clear();
        TxtFoneRecado.clear();
        TxtFoneCelular.clear();
        TxtProfissao.clear();
        TxtRenda.clear();
        CheckboxContacao.setSelected(false);
        CheckboxEducacaoFinanceira.setSelected(false);
        CheckboxDesenhoMecanico.setSelected(false);
        CheckboxEmprestimo.setSelected(false);
        CheckboxDesignModa.setSelected(false);
        CheckboxAulaReforço.setSelected(false);
        CheckboxInformatica.setSelected(false);
        CheckboxIngles.setSelected(false);
        CheckboxEspanhol.setSelected(false);
        CheckboxArtesanato.setSelected(false);
        CheckboxTeclado.setSelected(false);
        CheckboxViolao.setSelected(false);
        CheckboxCapoeira.setSelected(false);
        CheckboxFraldasA.setSelected(false);
        CheckboxFraldasI.setSelected(false);
        CheckboxAcupuntura.setSelected(false);
        CheckboxCestaBasica.setSelected(false);
        CheckboxLeite.setSelected(false);
        CheckboxOutros.setSelected(false);
        TxtInteresseOutros.clear();
        TxtAreaObservacoes.clear();

        System.out.println("Limpo");
    }
}
