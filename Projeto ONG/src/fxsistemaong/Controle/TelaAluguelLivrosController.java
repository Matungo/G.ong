package fxsistemaong.Controle;

import fxsistemaong.DAO.EmprestimoDAO;
import fxsistemaong.Objeto.Beneficiario;
import fxsistemaong.Objeto.Emprestimo;
import fxsistemaong.Objeto.Livro;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Danilo
 */
public class TelaAluguelLivrosController implements Initializable {

    @FXML
    private TableView<Emprestimo> tableViewEmprestimos;
    
    @FXML
    private TableColumn<Emprestimo, Long> tableColumnISBN;
    @FXML
    private TableColumn<Emprestimo, String> tableColumnTituloDoLivro;
    @FXML
    private TableColumn<Emprestimo, String> tableColumnDataRetirada;
    @FXML
    private TableColumn<Emprestimo, String> tableColumnDataDevolucao;
    
    @FXML
    private Button btnEmprestar;
    @FXML
    private Button btnDevolver;
    @FXML
    private Button btnLimpar;
    
    private List<Emprestimo> listEmprestimos = new ArrayList();
    
    private ObservableList<Emprestimo> observableListEmprestimos;
    
    @FXML
    private TextField TxtCodigoUsuario;
    @FXML
    private TextField TxtCPFUsuario;
    @FXML
    private TextField TxtNomeUsuario;
    
    @FXML
    private Button BtnConsultarUsuarioEmprestimo;
    
    @FXML
    private TextField TxtISBNLivroEmprestimo;
    @FXML
    private TextField TxtNomeLivroEmprestimo;
    @FXML
    private DatePicker TxtDataPublicacaoLivroEmprestimo;
    @FXML
    private TextField TxtEditoraLivroEmprestimo;
    @FXML
    private TextField TxtCategoriaLivroEmprestimo;
    @FXML
    private TextField TxtQtdDisponivelLivroEmprestimo;
    @FXML
    private TextField TxtFormatoLivroEmprestimo;
            
    @FXML
    private Button BtnBuscarLivroEmprestimo;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {           
        
        btnDevolver.setDisable(true);
        
        tableViewEmprestimos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewEmprestimos(newValue));
        
                     
    }   
    
    @FXML
    public void buscarBeneficiario(){
        
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setCodigo(Integer.parseInt(TxtCodigoUsuario.getText()));
        
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        beneficiario = emprestimoDAO.buscarBeneficiario(beneficiario);
        
        TxtNomeUsuario.setText(beneficiario.getNome());
        TxtCPFUsuario.setText(beneficiario.getCpf());
        
        //Emprestimo emprestimo = new Emprestimo();
        
        ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
        
        emprestimos = buscarEmprestimo(beneficiario);
        int i = 0;
        
        //Emprestimo e1 = (Emprestimo) emprestimos.get(0);
        
        if(emprestimos.size() != 0){
                      
            for(i = 0; i < emprestimos.size(); i++){
                carregarTableViewEmprestimos(emprestimos.get(i));
            }
                        
            /*Iterator<Emprestimo> it = emprestimos.iterator();
                while (it.hasNext()) {
                Emprestimo e = it.next();
                carregarTableViewEmprestimos(e);
                System.out.println(e.getTitulo());
            }*/    
                
        }
        else{
            JOptionPane.showMessageDialog(null, "Nenhum empréstimo registrado");
        }
        
        //emprestimo = buscarEmprestimo(beneficiario);
        
        /*
       // emprestimo = buscarEmprestimo(beneficiario);
        
        if(emprestimo.getCodEmprestimo() > 0){
        carregarTableViewEmprestimos(emprestimo);
        }
        else{
            JOptionPane.showMessageDialog(null, "Nenhum empréstimo registrado");
        }*/
    }
    
    @FXML
    public void buscarLivro(){
        String formato = "dd/MM/yyyy";
        DateFormat dateFormat = new SimpleDateFormat(formato);
        
        Livro livro = new Livro();
        livro.setIsbn(Long.valueOf(TxtISBNLivroEmprestimo.getText()));
        
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        livro = emprestimoDAO.buscarLivro(livro);
        
        TxtNomeLivroEmprestimo.setText(livro.getTitulo());
        TxtEditoraLivroEmprestimo.setText(livro.getEditora());
        TxtCategoriaLivroEmprestimo.setText(livro.getCategoria());
        TxtQtdDisponivelLivroEmprestimo.setText(String.valueOf(livro.getQtd()));
        TxtFormatoLivroEmprestimo.setText(livro.getFormato());
        TxtDataPublicacaoLivroEmprestimo.getEditor().setText(dateFormat.format(livro.getPublicao()));
        
    }
    
    public void carregarTableViewEmprestimos(Emprestimo emprestimo){
              
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        tableColumnISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tableColumnTituloDoLivro.setCellValueFactory(new PropertyValueFactory<>("titulo"));
               
        //carregar dataRetirada no formato BR  
        tableColumnDataRetirada.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Emprestimo,String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(CellDataFeatures<Emprestimo, String> cell) {
                final Emprestimo emprestimo = cell.getValue();

                final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty<String>(sdf.format(emprestimo.getDataRetirada()));
                return simpleObject;
            }
        });
               
        //carregar dataDevolução no formato BR  
        tableColumnDataDevolucao.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Emprestimo,String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(CellDataFeatures<Emprestimo, String> cell) {
                final Emprestimo emprestimo = cell.getValue();

                final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty<String>(sdf.format(emprestimo.getDataDevolucao()));
                return simpleObject;
            }
        });
        
        listEmprestimos.add(emprestimo);
        observableListEmprestimos = FXCollections.observableArrayList(listEmprestimos);
        tableViewEmprestimos.setItems(observableListEmprestimos);
        
    }
    
    public void selecionarItemTableViewEmprestimos(Emprestimo emprestimo){
        if(emprestimo != null){
            btnDevolver.setDisable(false);
        }
        else{
            btnDevolver.setDisable(true);
        }
    }
    
    @FXML
    public void removerItemTableViewEmprestimos(){
        Emprestimo emprestimo = tableViewEmprestimos.getSelectionModel().getSelectedItem();
            if(emprestimo != null){
            System.out.println("Emprestimo removido do TableView: " + emprestimo.getTitulo());
            tableViewEmprestimos.getItems().remove(emprestimo);
        }
    }
    
    @FXML
    public void registrarEmprestimo(){
        Emprestimo emprestimo = new Emprestimo();
        
        emprestimo.setIdBeneficiario(Integer.parseInt(TxtCodigoUsuario.getText()));
        emprestimo.setIsbn(Long.valueOf(TxtISBNLivroEmprestimo.getText()));
        emprestimo.setTitulo(TxtNomeLivroEmprestimo.getText());
        
        Calendar c1 = Calendar.getInstance(); //data retirada
        Calendar c2 = Calendar.getInstance(); //data devolução
        
        emprestimo.setDataRetirada(c1.getTime()); // pegar data atual do tipo Date
        c2.setTime(emprestimo.getDataRetirada()); //dataRetirada é usada como base para calcular a dataDevolução
        c2.add(Calendar.DAY_OF_YEAR, 7); //adiciona 7 dias à dataRetirada, gerando a dataDevolução
        emprestimo.setDataDevolucao(c2.getTime());
        
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        if(emprestimoDAO.registrarEmprestimo(emprestimo)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
            alert.setContentText("Empréstimo registrado com sucesso!");
            alert.showAndWait();
            
            carregarTableViewEmprestimos(emprestimo);
        }
        
    }
    
    public ArrayList<Emprestimo> buscarEmprestimo(Beneficiario beneficiario){
        
        ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
        //Emprestimo emprestimo = new Emprestimo();
        
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();        
        emprestimos = emprestimoDAO.buscarEmprestimo(beneficiario);
        //emprestimo = emprestimoDAO.buscarEmprestimo(beneficiario);
        
        return emprestimos;
        //return emprestimo;
    }
    
    @FXML
    public void limparTela(){
        TxtCodigoUsuario.clear();
        TxtCPFUsuario.clear();
        TxtNomeUsuario.clear();
        TxtISBNLivroEmprestimo.clear();
        TxtNomeLivroEmprestimo.clear();
        TxtDataPublicacaoLivroEmprestimo.getEditor().clear();
        TxtEditoraLivroEmprestimo.clear();
        TxtCategoriaLivroEmprestimo.clear();
        TxtQtdDisponivelLivroEmprestimo.clear();
        TxtFormatoLivroEmprestimo.clear();
        
        //limpar table view
        tableViewEmprestimos.getItems().clear();
        listEmprestimos = new ArrayList();   
        
        btnDevolver.setDisable(true);
    }
    
    public void finalizarEmprestimo(){
        Emprestimo emprestimo = tableViewEmprestimos.getSelectionModel().getSelectedItem();
        if(emprestimo != null){
            EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
            if(emprestimoDAO.finalizarEmprestimo(emprestimo)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sistema G.onG - Gerenciamento de ONG - Projeto Shalom");
                alert.setContentText("Empréstimo finalizado");
                alert.showAndWait();
            }
            tableViewEmprestimos.getItems().remove(emprestimo);
        }
    }
    
}
    

