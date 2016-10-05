package fxsistemaong.Controle;

import fxsistemaong.DAO.Banco;
import fxsistemaong.DAO.FuncionarioDAO;
import fxsistemaong.Objeto.Funcionario;
import java.awt.Event;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class TelaCadastroFuncionarioController implements Initializable {
    
    private Funcionario func;
    private FuncionarioDAO FuncionarioDAO;
    private Banco banco;
            
    @FXML
    private TextField TxtCodigoFuncionario;
    private TextField TxtDataCadasroFuncionario;
    private TextField TxtNomeFuncionario;
    private TextField TxtRGFuncionario;
    private TextField TxtCPFFuncionario;
    private TextField TxtEmailFuncionario;
    private TextField TxtNascimentoFuncionario;
    private TextField TxtEnderecoFuncionario;
    private TextField TxtNumeroFuncionario;
    private TextField TxtComplementoFuncionario;
    private TextField TxtBairroFuncionario;
    private TextField TxtCidadeFuncionario;
    private TextField TxtCEPFuncionario;
    private TextField TxtFoneCelularFuncionario;
    private TextField TxtFoneResidencialFuncionario;
    private TextField TxtFoneRecadoFuncionario;
    private TextArea TxtAreaAptidoesFuncionario;
    private RadioButton RadioMasculinoFuncionario;
    private RadioButton RadioFemininoFuncionario;

    public TelaCadastroFuncionarioController() {
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }   
    public void BtnPesquisar(ActionEvent evento)
    {
        System.err.println(TxtCodigoFuncionario.getText());
        TxtCodigoFuncionario.setText("LUCIMITO");
    }
    
    public void BtnSalvarFuncionario(ActionEvent event) throws SQLException
    {
        Funcionario Func = new Funcionario();
        //Func.setAptidoes(TxtAreaAptidoesFuncionario.getText());

      //  System.out.println(teste);

        System.out.println("teste");

        //System.out.println(teste);

        //Func.setCep(TxtCEPFuncionario.getText());
        //Funcionario.setCidade(TxtCidadeFuncionario.getText());
        //Funcionario.setCod(Integer.parseInt(TxtCodigoFuncionario.getText()));
        //Funcionario.setComplemento(TxtComplementoFuncionario.getText());
        //Funcionario.setCpf(TxtCPFFuncionario.getText());
        //Funcionario.setDatacadastro(TxtDataCadasroFuncionario.getText());
        /*Funcionario.setDisp_dia(0);
        Funcionario.setDisp_hora(0);
        Funcionario.setDisp_sab(0);  */
        //Funcionario.setEmail(TxtEmailFuncionario.getText());
        //Funcionario.setEndereço(TxtEnderecoFuncionario.getText());
        //Funcionario.setFone1(TxtFoneCelularFuncionario.getText());
        //Funcionario.setFone2(TxtFoneRecadoFuncionario.getText());
        //Funcionario.setFone3(TxtFoneResidencialFuncionario.getText());
        //Funcionario.setNome(TxtNomeFuncionario.getText());
        //Funcionario.setNumero(TxtNumeroFuncionario.getText());
        //Funcionario.setRg(TxtRGFuncionario.getText());
        /*if (RadioMasculinoFuncionario.isSelected())
        {
            Funcionario.setSexo("M");
        }
        else
        {
            Funcionario.setSexo("F");
        }*/
        //System.out.println("TESTE");
        //FuncionarioDAO = new FuncionarioDAO(banco);
        //System.out.println("TESTE XXXXX");
        //try {
        //    if(FuncionarioDAO.inserir(Func)){
        //        System.out.println("Sucesso");
        //    }else{
        //        System.out.println("ERRO");
        //    }
        //    } catch (SQLException ex) {
        //        Logger.getLogger(TelaCadastroFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        //    }
    }
    
    public void inserirFuncionario(Funcionario funcionario)
    {
        
    }
}
