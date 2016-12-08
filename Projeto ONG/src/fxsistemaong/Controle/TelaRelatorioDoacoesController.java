
package fxsistemaong.Controle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
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


@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
