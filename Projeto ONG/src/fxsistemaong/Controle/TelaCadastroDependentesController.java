package fxsistemaong.Controle;

import fxsistemaong.DAO.DependentesDAO;
import fxsistemaong.Objeto.Dependentes;

import fxsistemaong.Objeto.Dependentes;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaCadastroDependentesController implements Initializable {

    @FXML
    //private RadioButton RadioNaoAbrigo;
    private TextField TxtCodigoDependente;
    @FXML
    private TextField TxtDataCadastroDependente;
    @FXML
    private TextField TxtNomeMaeDependente;
    @FXML
    private TextField TxtNascimentoMaeDependente;
    @FXML
    private TextField TxtProfissaoMaeDependente;
    @FXML
    private TextField TxtRGMaeDependente;
    @FXML
    private TextField TxtCPFMaeDependente;
    @FXML
    private TextField TxtNomePaiDependente;
    @FXML
    private TextField TxtNascimentoPaiDependentes;
    @FXML
    private TextField TxtProfissaoPaiDependentes;
    @FXML
    private TextField TxtRGPaiDependentes;
    @FXML
    private TextField TxtCPFPaiDependentes;
    @FXML
    private TextField TxtNomeDependente;
    @FXML
    private TextField TxtNascimentoDependente;
    @FXML
    private TextField TxtRendaFamiliar;
    @FXML
    private TextField TxtNISDependente;
    @FXML
    private TextField TxtCPFDependentes;
    @FXML
    private ComboBox ComboboxCorDependente;
    @FXML
    private TextField TxtUBSDependente;
    @FXML
    private TextField TxtTerritorioDependente;
    @FXML
    private RadioButton RadioMasculino;
    @FXML
    private RadioButton RadioFeminino;
    @FXML
    private TextField TxtEnderecoDependente;
    @FXML
    private TextField TxtNumeroDependente;
    @FXML
    private TextField TxtCEPDependente;
    @FXML
    private TextField TxtComplementoDependente;
    @FXML
    private TextField TxtBairroDependente;
    @FXML
    private TextField TxtCidadeDependente;
    @FXML
    private TextField TxtResponsavelDependente;
    @FXML
    private TextField TxtParentescoDependente;
    @FXML
    private TextField TxtFoneResidencialDependente;
    @FXML
    private TextField TxtFoneRecadoDependente;
    @FXML
    private TextField TxtFoneCelularDependente;
    @FXML
    private RadioButton RadioSimEstuda;
    @FXML
    private TextField TxtSerieDependente;
    @FXML
    private RadioButton RadioNaoEstuda;
    @FXML
    private TextField TxtPorqueDependente;
    @FXML
    private TextField TxtNomeEscola;
    @FXML
    private TextArea TxtComposicaoFamiliar;
    @FXML
    private RadioButton RadioTarde;
    @FXML
    private RadioButton RadioNoite;
    @FXML
    private RadioButton RadioManha;
    @FXML
    private RadioButton RadioNaoMedicacao;
    @FXML
    private RadioButton RadioSimMedicacao;
    @FXML
    private CheckBox CheckboxNeurologia;
    @FXML
    private CheckBox CheckboxPsicologia;
    @FXML
    private CheckBox CheckboxPsiquiatria;
    @FXML
    private CheckBox CheckboxOutros;
    @FXML
    private TextField TxtOutrosTratamentos;
    @FXML
    private RadioButton RadioNaoAbrigo;
    @FXML
    private RadioButton RadioSimAbrigo;
    @FXML
    private TextField TxtAbrigoDependente;
    @FXML
    private TextField TxtQual;
    @FXML
    private RadioButton RadioNaoMedicacaoDependete;
    @FXML
    private RadioButton RadioSimMedicacaoDependete;
    @FXML
    private TextField TxtQualMedicacao;
    @FXML
    private RadioButton RadioNaoConvulsoes;
    @FXML
    private RadioButton RadioSimConvulsoes;
    @FXML
    private TextField TxtQuandoConvulsao;
    @FXML
    private TextArea TxtDescreverCrise;
    @FXML
    private RadioButton RadioNaoToleranciaAlimentacao;
    @FXML
    private RadioButton RadioSimToleranciaAlimentacao;
    @FXML
    private TextField TxtQualIntoleranciaAlimentar;
    @FXML
    private TextArea TxtRelacionamentoFamilia;
    @FXML
    private TextArea TxtRelacionamentoEscola;
    @FXML
    private CheckBox CheckboxAtividadeEsportiva;
    @FXML
    private CheckBox CheckboxAtividadeLazer;
    @FXML
    private CheckBox CheckboxAtividadeCultural;
    @FXML
    private CheckBox CheckboxAtividadeSocial;
    @FXML
    private CheckBox CheckboxAtividadeOutras;
    @FXML
    private TextField TxtAtividadeOutras;
    @FXML
    private TextArea TxtInformacoesImportantes;

    /**
     * Metodo que permite que ao iniciar o form do javafx possa realizar acoes
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listaCores //combobox trabalha com lista por isso é necessario criar ela
                = FXCollections.observableArrayList("Asiatico", "Branco(a)", "Indio(a)", "Negro(a)", "Pardo(a)");
        ComboboxCorDependente.setItems(listaCores);
        //coloca a lista aqui
    }

    /**
     * Metodo que ira pesquisar pelo codigo no banco de dados e devolver todos
     * os dados dos despendentes e coloca-los nos campos correspondentes
     *
     * @param event para permitir o eveneto do botao
     */
    @FXML
    public void pesquisarDependente(ActionEvent event) {
        Dependentes depe = new Dependentes();
        try {
            DependentesDAO dao = new DependentesDAO();
            depe = dao.pesquisarDependentes(Integer.valueOf(TxtCodigoDependente.getText()));
        } catch (RuntimeException E) {
            System.out.println(E.getMessage());
        }
        TxtDataCadastroDependente.setText(depe.getDataCadastro().toString());
        TxtNomeMaeDependente.setText(depe.getNomeMae());
        TxtNascimentoMaeDependente.setText(depe.getDataMae().toString());
        TxtProfissaoMaeDependente.setText(depe.getProfissaoMae());
        TxtRGMaeDependente.setText(depe.getRgMae());
        TxtCPFMaeDependente.setText(depe.getCpfMae());
        TxtNomePaiDependente.setText(depe.getNomeMae());
        TxtNascimentoPaiDependentes.setText(depe.getDataPai().toString());
        TxtProfissaoPaiDependentes.setText(depe.getProfissaoPai());
        TxtRGPaiDependentes.setText(depe.getRgPai());
        TxtCPFPaiDependentes.setText(depe.getCpfPai());
        TxtNomeDependente.setText(depe.getNomeCrianca());
        TxtNascimentoDependente.setText(depe.getNomeCrianca());
        TxtRendaFamiliar.setText(String.valueOf(depe.getRendaFamiliar()));
        TxtNISDependente.setText(depe.getNis());
        TxtCPFDependentes.setText(depe.getCpfCrianca());
        ObservableList<String> listaCorPelePesquisa //vou colocar a lista a cor que foi pesquisado no banco
                = FXCollections.observableArrayList(depe.getCorPele());
        ComboboxCorDependente.setItems(listaCorPelePesquisa);
        TxtUBSDependente.setText(depe.getUbs());
        TxtTerritorioDependente.setText(depe.getTerritorio());
        if (depe.getSexo() == "M") {
            RadioMasculino.setSelected(true);
        } else {
            RadioFeminino.setSelected(true);
        }
        TxtEnderecoDependente.setText(depe.getEndereco());
        TxtNumeroDependente.setText(depe.getNumero());
        TxtCEPDependente.setText(depe.getCep());
        TxtComplementoDependente.setText(depe.getComplemento());
        TxtBairroDependente.setText(depe.getBairro());
        TxtCidadeDependente.setText(depe.getCidade());
        TxtResponsavelDependente.setText(depe.getResponsavel());
        TxtParentescoDependente.setText(depe.getGrauParentesco());
        TxtFoneResidencialDependente.setText(depe.getTelRes());
        TxtFoneRecadoDependente.setText(depe.getTelRec());
        TxtFoneCelularDependente.setText(depe.getTelCel());
        if (depe.getEstuda() == "S") {
            RadioSimEstuda.setSelected(true);
            TxtSerieDependente.setText(depe.getSerie());
        } else {
            RadioNaoEstuda.setSelected(true);
            TxtPorqueDependente.setText(depe.getMotivoEstudo());
        }
        TxtNomeEscola.setText(depe.getNomeEscola());
        switch (depe.getPeriodoEscolar()) {
            case "M":
                RadioManha.setSelected(true);
                break;
            case "T":
                RadioTarde.setSelected(true);
                break;
            case "N":
                RadioNoite.setSelected(true);
                break;
        };
        TxtComposicaoFamiliar.setText(depe.getComposicao());

        if (depe.getSaudeMental() == "S") {
            RadioSimMedicacao.setSelected(true);
            switch (depe.getQualSaude()) {
                case 1:
                    CheckboxNeurologia.setSelected(true);
                    break;
                case 2:
                    CheckboxPsicologia.setSelected(true);
                    break;
                case 3:
                    CheckboxPsiquiatria.setSelected(true);
                    break;
                case 4:
                    CheckboxOutros.setSelected(true);
                    TxtOutrosTratamentos.setText(depe.getOutrasSaude());
            };
        } else {
            RadioNaoMedicacao.setSelected(true);
        }
        if (depe.getInternado() == "S") {
            RadioSimAbrigo.setSelected(true);
            TxtAbrigoDependente.setText(depe.getQuando());
            TxtQual.setText(depe.getQualInternado());
        } else {
            RadioNaoAbrigo.setSelected(true);
        }
        if (depe.getMedicamento() == "S") {
            RadioSimMedicacaoDependete.setSelected(true);
            TxtQualMedicacao.setText(depe.getQualMedica());
        } else {
            RadioNaoMedicacaoDependete.setSelected(true);
        }
        if (depe.getConvulsao() == "S") {
            RadioSimConvulsoes.setSelected(true);
            TxtQuandoConvulsao.setText(depe.getQuandoConvulsao());
            TxtDescreverCrise.setText(depe.getDescricao());
        } else {
            RadioNaoConvulsoes.setSelected(true);
        }
        if (depe.getAlergia() == "S") {
            RadioSimToleranciaAlimentacao.setSelected(true);
            TxtQualIntoleranciaAlimentar.setText(depe.getQualAlergia());
        } else {
            RadioNaoToleranciaAlimentacao.setSelected(true);
        }
        TxtRelacionamentoFamilia.setText(depe.getRelFamilia());
        TxtRelacionamentoEscola.setText(depe.getRelEscola());
        switch (depe.getAtividades()) {
            case "E":
                CheckboxAtividadeEsportiva.setSelected(true);
                break;
            case "L":
                CheckboxAtividadeLazer.setSelected(true);
                break;
            case "C":
                CheckboxAtividadeCultural.setSelected(true);
                break;
            case "S":
                CheckboxAtividadeSocial.setSelected(true);
                break;
            case "O":
                CheckboxAtividadeOutras.setSelected(true);
                TxtAtividadeOutras.setText(depe.getOutrasAti());
                break;
        }
        TxtInformacoesImportantes.setText(depe.getOutrasInfo());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
        alert.setContentText("Pesquisado com sucesso");
        alert.showAndWait();
        limparCampos(event);
    }

    /**
     * Metodo que apenas limpa todos os campos do cadastro de dependentes
     *
     * @param event para permitir o eveneto do botao
     */
    @FXML
    public void limparCampos(ActionEvent event) {

        ObservableList<String> listaCores //combobox trabalha com lista por isso é necessario criar ela
                = FXCollections.observableArrayList("Asiatico", "Branco(a)", "Indio(a)", "Negro(a)", "Pardo(a)");
        ComboboxCorDependente.setItems(listaCores);
        TxtCodigoDependente.clear();
        TxtDataCadastroDependente.clear();
        TxtNomeMaeDependente.clear();
        TxtNascimentoMaeDependente.clear();
        TxtProfissaoMaeDependente.clear();
        TxtRGMaeDependente.clear();
        TxtCPFMaeDependente.clear();
        TxtNomePaiDependente.clear();
        TxtNascimentoPaiDependentes.clear();
        TxtProfissaoPaiDependentes.clear();
        TxtRGPaiDependentes.clear();
        TxtCPFPaiDependentes.clear();
        TxtNomeDependente.clear();
        TxtNascimentoDependente.clear();
        TxtRendaFamiliar.clear();
        TxtNISDependente.clear();
        TxtCPFDependentes.clear();
        ComboboxCorDependente.getSelectionModel().clearSelection();
        TxtUBSDependente.clear();
        RadioMasculino.setSelected(false);
        RadioFeminino.setSelected(false);
        RadioSimEstuda.setSelected(false);
        RadioNaoEstuda.setSelected(false);
        RadioNaoMedicacao.setSelected(false);
        RadioSimMedicacao.setSelected(false);
        RadioNaoMedicacao.setSelected(false);
        RadioManha.setSelected(false);
        RadioTarde.setSelected(false);
        RadioNoite.setSelected(false);
        TxtTerritorioDependente.clear();
        TxtEnderecoDependente.clear();
        TxtNumeroDependente.clear();
        TxtCEPDependente.clear();
        TxtComplementoDependente.clear();
        TxtBairroDependente.clear();
        TxtCidadeDependente.clear();
        TxtResponsavelDependente.clear();
        TxtParentescoDependente.clear();
        TxtFoneResidencialDependente.clear();
        TxtFoneRecadoDependente.clear();
        TxtFoneCelularDependente.clear();
        TxtSerieDependente.clear();
        TxtPorqueDependente.clear();
        TxtNomeEscola.clear();
        TxtComposicaoFamiliar.clear();
        CheckboxNeurologia.setSelected(false);
        CheckboxPsicologia.setSelected(false);
        CheckboxPsiquiatria.setSelected(false);
        CheckboxOutros.setSelected(false);
        TxtOutrosTratamentos.clear();
        RadioNaoAbrigo.setSelected(false);
        RadioSimAbrigo.setSelected(false);
        TxtAbrigoDependente.clear();
        TxtQual.clear();
        RadioNaoMedicacaoDependete.setSelected(false);
        RadioSimMedicacaoDependete.setSelected(false);
        TxtQualMedicacao.clear();
        RadioNaoConvulsoes.setSelected(false);
        RadioSimConvulsoes.setSelected(false);
        TxtQuandoConvulsao.clear();
        TxtDescreverCrise.clear();
        RadioNaoToleranciaAlimentacao.setSelected(false);
        RadioSimToleranciaAlimentacao.setSelected(false);
        TxtQualIntoleranciaAlimentar.clear();
        TxtRelacionamentoFamilia.clear();
        TxtRelacionamentoEscola.clear();
        CheckboxAtividadeEsportiva.setSelected(false);
        CheckboxAtividadeLazer.setSelected(false);
        CheckboxAtividadeCultural.setSelected(false);
        CheckboxAtividadeOutras.setSelected(false);
        TxtAtividadeOutras.clear();
        TxtInformacoesImportantes.clear();
        System.out.println("Limpo");
    }

    /**
     * Metodo que ira excluir todos os dados dos dependentes correspondente ao
     * codigo
     *
     * @param event para permitir o eveneto do botao
     */
    @FXML
    public void excluirDependente(ActionEvent event) {
        Dependentes depe = new Dependentes();
        DependentesDAO dao = new DependentesDAO();
        depe.setCodigo(Integer.parseInt(TxtCodigoDependente.getText()));
        if (dao.excluirDependentes(depe)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Excluido com sucesso");
            alert.showAndWait();
        }
        limparCampos(event);

    }

    /**
     * Metodo que ira atualizar todos os dados dos dependentes correspondente ao
     * codigo
     *
     * @exception ParseException pode ocorrer erro ao converter string em date
     * @param event para permitir o eveneto do botao
     */
    @FXML
    public void atualizarDependentes(ActionEvent event) throws ParseException {
        Dependentes depe = new Dependentes();
        DependentesDAO dao = new DependentesDAO();
        depe.setCodigo(Integer.parseInt(TxtCodigoDependente.getText()));
        depe.setDataCadastro(DateFormat.getInstance().parse(TxtDataCadastroDependente.getText()));//transformar o texto do campo em date
        depe.setNomeMae(TxtNomeMaeDependente.getText());
        depe.setDataMae(DateFormat.getInstance().parse(TxtNascimentoMaeDependente.getText()));
        depe.setProfissaoMae(TxtProfissaoMaeDependente.getText());
        depe.setRgMae(TxtRGMaeDependente.getText());
        depe.setCpfMae(TxtCPFMaeDependente.getText());
        depe.setNomeMae(TxtNomePaiDependente.getText());
        depe.setDataPai(DateFormat.getInstance().parse(TxtNascimentoPaiDependentes.getText()));
        depe.setProfissaoPai(TxtProfissaoPaiDependentes.getText());
        depe.setRgPai(TxtRGPaiDependentes.getText());
        depe.setCpfPai(TxtCPFPaiDependentes.getText());
        depe.setNomeCrianca(TxtNomeDependente.getText());
        depe.setNomeCrianca(TxtNascimentoDependente.getText());
        depe.setRendaFamiliar(Float.valueOf(TxtRendaFamiliar.getText()));
        depe.setNis(TxtNISDependente.getText());
        depe.setCpfCrianca(TxtCPFDependentes.getText());
        depe.setCorPele(ComboboxCorDependente.getValue().toString());//pegar valor da combo 
        depe.setUbs(TxtUBSDependente.getText());
        depe.setTerritorio(TxtTerritorioDependente.getText());
        if (RadioMasculino.isSelected()) {
            depe.setSexo("M");
        } else {
            depe.setSexo("F");
        }
        depe.setEndereco(TxtEnderecoDependente.getText());
        depe.setNumero(TxtNumeroDependente.getText());
        depe.setCep(TxtCEPDependente.getText());
        depe.setComplemento(TxtComplementoDependente.getText());
        depe.setBairro(TxtBairroDependente.getText());
        depe.setCidade(TxtCidadeDependente.getText());
        depe.setResponsavel(TxtResponsavelDependente.getText());
        depe.setGrauParentesco(TxtParentescoDependente.getText());
        depe.setTelRes(TxtFoneResidencialDependente.getText());
        depe.setTelRec(TxtFoneRecadoDependente.getText());
        depe.setTelCel(TxtFoneCelularDependente.getText());
        if (RadioSimEstuda.isSelected()) {
            depe.setEstuda("S");
            depe.setSerie(TxtSerieDependente.getText());
        } else {
            depe.setEstuda("N");
            depe.setMotivoEstudo(TxtPorqueDependente.getText());
        }
        depe.setNomeEscola(TxtNomeEscola.getText());
        if (RadioManha.isSelected()) {
            depe.setPeriodoEscolar("M");
        } else if (RadioTarde.isSelected()) {
            depe.setPeriodoEscolar("T");
        } else {
            depe.setPeriodoEscolar("N");
        }
        depe.setComposicao(TxtComposicaoFamiliar.getText());
        if (RadioSimMedicacao.isSelected()) {
            depe.setSaudeMental("S");
            if (CheckboxNeurologia.isSelected()) {
                depe.setQualSaude(1);
            } else if (CheckboxPsicologia.isSelected()) {
                depe.setQualSaude(2);
            } else if (CheckboxPsiquiatria.isSelected()) {
                depe.setQualSaude(3);
            } else {
                depe.setQualSaude(4);
                depe.setOutrasSaude(TxtOutrosTratamentos.getText());
            }
            depe.setQualSaude(0);
        } else {
            depe.setSaudeMental("N");
        }
        if (RadioSimAbrigo.isSelected()) {
            depe.setInternado("S");
            depe.setQuando(TxtAbrigoDependente.getText());
            depe.setQualInternado(TxtQual.getText());
        } else {
            depe.setInternado("N");
        }
        if (RadioSimMedicacaoDependete.isSelected()) {
            depe.setMedicamento("S");
            depe.setQualMedica(TxtQualMedicacao.getText());
        } else {
            depe.setMedicamento("N");
        }
        if (RadioSimConvulsoes.isSelected()) {
            depe.setConvulsao("S");
            depe.setQuandoConvulsao(TxtQuandoConvulsao.getText());
            depe.setDescricao(TxtDescreverCrise.getText());
        } else {
            depe.setConvulsao("N");
        }
        if (RadioSimToleranciaAlimentacao.isSelected()) {
            depe.setAlergia("S");
            TxtQualIntoleranciaAlimentar.setText(depe.getQualAlergia());
        } else {
            RadioNaoToleranciaAlimentacao.setSelected(true);
        }
        depe.setRelFamilia(TxtRelacionamentoFamilia.getText());
        depe.setRelEscola(TxtRelacionamentoEscola.getText());
        if (CheckboxAtividadeEsportiva.isSelected()) {
            depe.setAtividades("E");
        } else if (CheckboxAtividadeLazer.isSelected()) {
            depe.setAtividades("L");
        } else if (CheckboxAtividadeCultural.isSelected()) {
            depe.setAtividades("C");
        } else if (CheckboxAtividadeSocial.isSelected()) {
            depe.setAtividades("S");
        } else {
            depe.setAtividades("O");
            depe.setOutrasAti(TxtAtividadeOutras.getText());
        }
        depe.setOutrasInfo(TxtInformacoesImportantes.getText());
        if (dao.atualizarDependentes(depe)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Atualizado com sucesso");
            alert.showAndWait();
            limparCampos(event);
        }
        //System.out.println("ola");
    }

    /**
     * Metodo que salva os dado do campo num objeto para depois ser armazenado
     * para o banco
     *
     * @param event para permitir o eveneto do botao
     * @exception ParseException pode ocorrer erro ao converter string em date
     * @see Dependentes
     */
    @FXML
    public void salvarDependentes(ActionEvent event) throws ParseException {
        Dependentes depe = new Dependentes();
        //
        depe.setDataCadastro(DateFormat.getInstance().parse(TxtDataCadastroDependente.getText()));//transformar o texto do campo em date
        depe.setNomeMae(TxtNomeMaeDependente.getText());
        depe.setDataMae(DateFormat.getInstance().parse(TxtNascimentoMaeDependente.getText()));
        depe.setProfissaoMae(TxtProfissaoMaeDependente.getText());
        depe.setRgMae(TxtRGMaeDependente.getText());
        depe.setCpfMae(TxtCPFMaeDependente.getText());
        depe.setNomeMae(TxtNomePaiDependente.getText());
        depe.setDataPai(DateFormat.getInstance().parse(TxtNascimentoPaiDependentes.getText()));
        depe.setProfissaoPai(TxtProfissaoPaiDependentes.getText());
        depe.setRgPai(TxtRGPaiDependentes.getText());
        depe.setCpfPai(TxtCPFPaiDependentes.getText());
        depe.setNomeCrianca(TxtNomeDependente.getText());
        depe.setNomeCrianca(TxtNascimentoDependente.getText());
        depe.setRendaFamiliar(Float.valueOf(TxtRendaFamiliar.getText()));
        depe.setNis(TxtNISDependente.getText());
        depe.setCpfCrianca(TxtCPFDependentes.getText());
        depe.setCorPele(ComboboxCorDependente.getValue().toString());//pegar valor da combo 
        depe.setUbs(TxtUBSDependente.getText());
        depe.setTerritorio(TxtTerritorioDependente.getText());
        if (RadioMasculino.isSelected()) {
            depe.setSexo("M");
        } else {
            depe.setSexo("F");
        }
        depe.setEndereco(TxtEnderecoDependente.getText());
        depe.setNumero(TxtNumeroDependente.getText());
        depe.setCep(TxtCEPDependente.getText());
        depe.setComplemento(TxtComplementoDependente.getText());
        depe.setBairro(TxtBairroDependente.getText());
        depe.setCidade(TxtCidadeDependente.getText());
        depe.setResponsavel(TxtResponsavelDependente.getText());
        depe.setGrauParentesco(TxtParentescoDependente.getText());
        depe.setTelRes(TxtFoneResidencialDependente.getText());
        depe.setTelRec(TxtFoneRecadoDependente.getText());
        depe.setTelCel(TxtFoneCelularDependente.getText());
        if (RadioSimEstuda.isSelected()) {
            depe.setEstuda("S");
            depe.setSerie(TxtSerieDependente.getText());
        } else {
            depe.setEstuda("N");
            depe.setMotivoEstudo(TxtPorqueDependente.getText());
        }
        depe.setNomeEscola(TxtNomeEscola.getText());
        if (RadioManha.isSelected()) {
            depe.setPeriodoEscolar("M");
        } else if (RadioTarde.isSelected()) {
            depe.setPeriodoEscolar("T");
        } else {
            depe.setPeriodoEscolar("N");
        }
        depe.setComposicao(TxtComposicaoFamiliar.getText());
        if (RadioSimMedicacao.isSelected()) {
            depe.setSaudeMental("S");
            if (CheckboxNeurologia.isSelected()) {
                depe.setQualSaude(1);
            } else if (CheckboxPsicologia.isSelected()) {
                depe.setQualSaude(2);
            } else if (CheckboxPsiquiatria.isSelected()) {
                depe.setQualSaude(3);
            } else {
                depe.setQualSaude(4);
                depe.setOutrasSaude(TxtOutrosTratamentos.getText());
            }
            depe.setQualSaude(0);
        } else {
            depe.setSaudeMental("N");
        }
        if (RadioSimAbrigo.isSelected()) {
            depe.setInternado("S");
            depe.setQuando(TxtAbrigoDependente.getText());
            depe.setQualInternado(TxtQual.getText());
        } else {
            depe.setInternado("N");
        }
        if (RadioSimMedicacaoDependete.isSelected()) {
            depe.setMedicamento("S");
            depe.setQualMedica(TxtQualMedicacao.getText());
        } else {
            depe.setMedicamento("N");
        }
        if (RadioSimConvulsoes.isSelected()) {
            depe.setConvulsao("S");
            depe.setQuandoConvulsao(TxtQuandoConvulsao.getText());
            depe.setDescricao(TxtDescreverCrise.getText());
        } else {
            depe.setConvulsao("N");
        }
        if (RadioSimToleranciaAlimentacao.isSelected()) {
            depe.setAlergia("S");
            TxtQualIntoleranciaAlimentar.setText(depe.getQualAlergia());
        } else {
            RadioNaoToleranciaAlimentacao.setSelected(true);
        }
        depe.setRelFamilia(TxtRelacionamentoFamilia.getText());
        depe.setRelEscola(TxtRelacionamentoEscola.getText());
        if (CheckboxAtividadeEsportiva.isSelected()) {
            depe.setAtividades("E");
        } else if (CheckboxAtividadeLazer.isSelected()) {
            depe.setAtividades("L");
        } else if (CheckboxAtividadeCultural.isSelected()) {
            depe.setAtividades("C");
        } else if (CheckboxAtividadeSocial.isSelected()) {
            depe.setAtividades("S");
        } else {
            depe.setAtividades("O");
            depe.setOutrasAti(TxtAtividadeOutras.getText());
        }
        depe.setOutrasInfo(TxtInformacoesImportantes.getText());
        DependentesDAO dao = new DependentesDAO();
        if (dao.salvarDependentes(depe)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Salvo com sucesso");
            alert.showAndWait();
            limparCampos(event);
        }
    }

    /**
     * Metodo que ira consultar somente os dados da mae com o cpf no banco de
     * dados e mostra-lo no form javafx
     *
     * @param event para permitir o eveneto do botao
     */
    @FXML
    public void consultarMae(ActionEvent event) {
        Dependentes depe = new Dependentes();
        try {
            DependentesDAO dao = new DependentesDAO();
            depe = dao.consultarMae(Integer.parseInt(TxtCPFMaeDependente.getText()));
        } catch (RuntimeException E) {
            System.out.println(E.getMessage());
        }
        //
        TxtNomeMaeDependente.setText(depe.getNomeMae());
        TxtNascimentoMaeDependente.setText(depe.getDataMae().toString());
        TxtProfissaoMaeDependente.setText(depe.getProfissaoMae());
        TxtRGMaeDependente.setText(depe.getRgMae());
        TxtCPFMaeDependente.setText(depe.getCpfMae());
        //
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
        alert.setContentText("Pesquisado com sucesso");
        alert.showAndWait();
    }

    /**
     * Metodo que ira consultar somente os dados do pai com o cpf no banco de
     * dados e mostra-lo no form javafx
     *
     * @param event para permitir o eveneto do botao
     */
    @FXML
    public void consultarPai(ActionEvent event) {
        Dependentes depe = new Dependentes();
        try {
            DependentesDAO dao = new DependentesDAO();
            depe = dao.consultarPai(Integer.parseInt(TxtCPFPaiDependentes.getText()));
        } catch (RuntimeException E) {
            System.out.println(E.getMessage());
        }
        //
        TxtNomePaiDependente.setText(depe.getNomePai());
        TxtNascimentoPaiDependentes.setText(depe.getDataPai().toString());
        TxtProfissaoPaiDependentes.setText(depe.getProfissaoPai());
        TxtRGPaiDependentes.setText(depe.getRgPai());
        TxtCPFPaiDependentes.setText(depe.getCpfPai());
        //
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
        alert.setContentText("Pesquisado com sucesso");
        alert.showAndWait();
    }

    /**
     * Metodo que ira consultar somente os dados da criança com o cpf no banco
     * de dados e mostra-lo no form javafx
     *
     * @param event para permitir o eveneto do botao
     */
    @FXML
    public void consultarCrianca(ActionEvent event) {
        Dependentes depe = new Dependentes();
        try {
            DependentesDAO dao = new DependentesDAO();
            depe = dao.consultarCrianca(Integer.parseInt(TxtCPFDependentes.getText()));
        } catch (RuntimeException E) {
            System.out.println(E.getMessage());
        }
        //
        TxtNomeDependente.setText(depe.getNomeCrianca());
        TxtNascimentoDependente.setText(depe.getNomeCrianca());
        TxtRendaFamiliar.setText(String.valueOf(depe.getRendaFamiliar()));
        TxtNISDependente.setText(depe.getNis());
        TxtCPFDependentes.setText(depe.getCpfCrianca());
        TxtUBSDependente.setText(depe.getUbs());
        TxtTerritorioDependente.setText(depe.getTerritorio());
        if (depe.getSexo() == "M") {
            RadioMasculino.setSelected(true);
        } else {
            RadioFeminino.setSelected(true);
        }
        //
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
        alert.setContentText("Pesquisado com sucesso");
        alert.showAndWait();
    }

}
