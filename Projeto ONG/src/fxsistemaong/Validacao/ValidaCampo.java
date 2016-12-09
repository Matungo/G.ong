package fxsistemaong.Validacao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

/**
 *
 * @author Luciano
 */
public class ValidaCampo {

    //são estaticos porque não mudam na classe na a hora de instanciar
    public static String corErro = "-fx-border-color: #FF0000"; //cor vermelha de erro;
    public static Tooltip popUp = new Tooltip("Campo vazio");//é tipo um popup para o campo 
    public static String corPopUp = "-fx-background-color: linear-gradient(#FF6B6B , #FFA6A6 );"
            + "-fx-font-weight: bold;"; //cor do popup

//basicamente este metodo faz com que o popup fica visivel 5 segundos,assim é possivel ver-lo
    public boolean validaCampo(Node node) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        /*  ValidaCampo valida =  new ValidaCampo();
            TextArea area = new TextArea();
            RadioButton radio = new RadioButton();
            CheckBox check = new CheckBox();*/
        if (node instanceof TextField) {
            if (((TextField) node).getText().equals("")) {
                esperaPopUp(popUp);
                adicionarEstilo((TextField) node,popUp);   
                return false;
            }
        }
        if (node instanceof DatePicker) {
            if (((DatePicker) node).getEditor().getText().equals("")) {
                esperaPopUp(popUp);
                adicionarEstilo((DatePicker) node,popUp);   
                return false;
            }
        }
        if (node instanceof ComboBox) {
            if (((ComboBox) node).getEditor().getText().equals("")) {
                esperaPopUp(popUp);
                adicionarEstilo((ComboBox) node,popUp);   
                return false;
            }
        }
        if (node instanceof TextArea) {
            if (((TextArea) node).getText().equals("")) {
                esperaPopUp(popUp);
                adicionarEstilo((TextArea) node,popUp);   
                return false;
            }
        }
   
        
        return true;
    }

    public void esperaPopUp(Tooltip popUp) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        java.lang.reflect.Field fieldBehavior = popUp.getClass().getDeclaredField("BEHAVIOR");
        fieldBehavior.setAccessible(true);
        Object objBehavior = fieldBehavior.get(popUp);

        java.lang.reflect.Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
        fieldTimer.setAccessible(true);
        Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

        objTimer.getKeyFrames().clear();
        objTimer.getKeyFrames().add(new KeyFrame(new Duration(5)));
    }

    public void adicionarEstilo(Node n, Tooltip popUp) {//apenas colocar o estilo
        popUp.setStyle(corPopUp);
        Tooltip.install(n, popUp);
        n.setStyle(corErro);

    }

    public void removerEstilo(Node n, Tooltip popUp) {//apenas tirar o estilo
        Tooltip.uninstall(n, popUp);
        n.setStyle(null);
    }
}
