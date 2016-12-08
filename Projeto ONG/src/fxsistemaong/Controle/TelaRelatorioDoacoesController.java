
package fxsistemaong.Controle;

import fxsistemaong.DAO.ProdutoDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

public class TelaRelatorioDoacoesController implements Initializable {

@FXML
private BarChart BarchartQtdDoacoes;
@FXML
private CategoryAxis MesesAno;
@FXML
private NumberAxis Quantidade;
@FXML
private Button BtnPDF, BtnImprimir;


private ObservableList<String> observableListMeses = FXCollections.observableArrayList();
//Atributos para manipulação de Banco de Dados
ProdutoDAO produto = new ProdutoDAO();
    
    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        BtnPDF.setDisable(true);
        BtnImprimir.setDisable(true);
        
        String[] arrayMeses = {"Jan","Fev","Mar","Mai","Jun","Jul","Ago","Set","Out","Nov","Dez"};
        
        observableListMeses.addAll(arrayMeses);
        
        MesesAno.setCategories(observableListMeses);
        Map<Integer, ArrayList> dados = produto.listarQuantidadeDoacoesMes();
        
        //for(Map.Entry<Integer, ArrayList> dadosItem : dados.entrySet()){
        dados.entrySet().stream().map((dadosItem) -> {
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName(dadosItem.getKey().toString());
            for (int i = 0; i < dadosItem.getValue().size(); i = i+2){
                String mes;
                Integer quantidade;
                mes = retornaNomeMes ((int) dadosItem.getValue().get(i));
                quantidade = (Integer) dadosItem.getValue().get(i + 1);
                series.getData().add(new XYChart.Data<>(mes, quantidade));
         
            }
        return series;
    }).forEach((series) -> {
        BarchartQtdDoacoes.getData().add(series);
    });
    }  
    
    public String retornaNomeMes(int mes) {
        switch (mes) {
            case 1:
                return "Jan";
            case 2:
                return "Fev";
            case 3:
                return "Mar";
            case 4:
                return "Abr";
            case 5:
                return "Mai";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Ago";
            case 9:
                return "Set";
            case 10:
                return "Out";
            case 11:
                return "Nov";
            case 12:
                return "Dez";
            default:
                return "";
        }
        
    }
    
}
