package br.com.caixaCifra.Util;

import br.com.caixaCifra.DAO.OperacaoDAO;
import br.com.caixaCifra.Model.Banco;
import br.com.caixaCifra.Model.Conta;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author anton
 */
public class Componentes {
    
    List<Banco> bancos = new ArrayList<>();
    
    ObservableList<Banco> obsBanco;
    OperacaoDAO logar = new OperacaoDAO();
    
    public ObservableList<Banco> carregarBancos(){
         List<Banco> bancos = new ArrayList<>();
    
        ObservableList<Banco> obsBanco;
        Banco banco1 = new Banco();
       
        banco1.setCodBanco(0);
        banco1.setNomeBanco("Escolha seu banco");
     
        bancos.add(banco1);
       
        bancos=logar.construirCombo(bancos);
      
       
       
        obsBanco = FXCollections.observableArrayList(bancos);
       
        return obsBanco;

    }
    
    public void limparCampos(JFXTextField txtConta,JFXTextField txtAgencia,JFXTextField txtDigito,
              RadioButton rbPoupa,RadioButton rbCorrente, String tpConta,ComboBox<Banco> cbBanco,
              Label lbErroConta,Label lbErroAgencia, Label lbErroBanco, Label lbErroDigito){

        txtConta.setText("");
        txtAgencia.setText("");
        txtDigito.setText("");
        rbPoupa.setSelected(false);
        rbCorrente.setSelected(true);
        tpConta="Corrente";
        cbBanco.getSelectionModel().select(0); 
        lbErroConta.setVisible(false);
        lbErroConta.setText("");
        lbErroAgencia.setVisible(false);
        lbErroAgencia.setText("");
        lbErroBanco.setText("");
        lbErroBanco.setVisible(false);
        lbErroDigito.setText("");
        lbErroDigito.setVisible(false);

    }
    
    public static void limitarCampoInt(final TextField tf, final int maxLength) {
        tf.textProperty().addListener((final ObservableValue<? extends String> ov, final String oldValue, final String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        });
    }
    
    public void alerta(String head, String body){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText(head);
        alerta.setContentText(body);
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
        stage.initStyle(StageStyle.UNDECORATED);

        alerta.show();
    }
    
    public boolean alertaConfirmacao(Conta conta,double valor){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Confirme as informações abaixo:");
        alert.setContentText(
            "NOME: "+conta.getCliente().getNomeCliente().toUpperCase()+"\n"
            + "CONTA: "+conta.getNumConta().toUpperCase()+"\n"
            + "AGÊNCIA: "+conta.getAgenciaConta().toUpperCase()+"\n"
            + "TIPO DA CONTA: "+conta.getTipoConta().toUpperCase()+"\n"
            + "BANCO: "+conta.getBanco().getNomeBanco().toUpperCase()+"\n\n"
            + "VALOR: R$"+valor
        );
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.initStyle(StageStyle.UNDECORATED);
        
        Optional<ButtonType> result = alert.showAndWait();
        
        return result.get() == ButtonType.OK;
    }

    public void alertaSucesso(String op) {
        
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText("Operação efetuada com sucesso.");
        alerta.setContentText(op+" já efetuado");
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
        stage.initStyle(StageStyle.UNDECORATED);

        alerta.showAndWait();    
    }
    
    public static void limitarTamanho(final TextField tf, final int maxLength) {
        tf.textProperty().addListener((final ObservableValue<? extends String> ov, final String oldValue, final String newValue) -> {
      
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        });
    }
    public boolean alertaConfirmacaoPag(String codBarra, String data,double valor){
       
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Confirme as informações abaixo:");
        alert.setContentText("Código de barras: \n"+codBarra+"\nDATA DE VENCIMENTO: "+data
                + "\nVALOR: R$"+valor+"\n");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.initStyle(StageStyle.UNDECORATED);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        }else{
           return false;
        }
        
    }
    public boolean alertaExibirExtrato(String tipo){
       
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText(tipo+":");
        alert.setContentText("Deseja imprimir o "+tipo+". ");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.initStyle(StageStyle.UNDECORATED);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        }else{
           return false;
        }
        
    }

    public void limitarCampoSenha(final JFXPasswordField tf, int maxLength) {
    tf.textProperty().addListener((final ObservableValue<? extends String> ov, final String oldValue, final String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (tf.getLength() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        });
    }
    
}
