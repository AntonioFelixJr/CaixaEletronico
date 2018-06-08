package br.com.caixaCifra.Controller;

import br.com.caixaCifra.DAO.OperacaoDAO;
import br.com.caixaCifra.Model.Cliente;
import br.com.caixaCifra.Validation.ValidarCampos;
import br.com.caixaCifra.Util.Componentes;
import br.com.caixaCifra.Model.Banco;
import br.com.caixaCifra.Model.ClienteLogado;
import br.com.caixaCifra.Model.Conta;
import br.com.caixaCifra.Model.OperacaoModel;
import br.com.caixaCifra.View.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class FXMLLoginController implements Initializable{
    
    @FXML
    private AnchorPane login;

    @FXML
    private Label lbPrencher;

    @FXML
    private JFXTextField txtConta;

    @FXML
    private JFXTextField txtDigito;
    
    @FXML
    private JFXTextField txtAgencia;

    @FXML
    private JFXButton btEntrar;

    @FXML
    private ComboBox<Banco> cbBanco;

    @FXML
    private RadioButton rbCorrente;

    @FXML
    private ToggleGroup grupo;

    @FXML
    private RadioButton rbPoupa;

    @FXML
    private Label lbTpConta;
    
    @FXML
    private Label lbErroConta;
    
    @FXML
    private Label lbErroAgencia;
    
    @FXML
    private Label lbErroTpConta;
    
    @FXML
    private Label lbErroDigito;
    
    @FXML
    private Label lbErroBanco;
    

    @FXML
    private JFXButton btLimpar;

    private final int limiteAgencia = 4;
    private final int limiteConta = 7;
    private final int limiteDigito = 1;
    private final int idInvalido = 0;
    
    String tpConta="";
    
    
    Componentes cp = new Componentes();
    OperacaoModel opM = new OperacaoModel();
    ValidarCampos validarCampos = new ValidarCampos();
    
    @FXML
    private void limparCampos(){
 
        cp.limparCampos(txtConta, txtAgencia, txtDigito, rbPoupa, rbCorrente,
                tpConta, cbBanco, lbErroConta, lbErroAgencia, lbErroBanco, lbErroDigito);

    }
    
    @FXML
    private void clickEntrar() throws IOException {
        
        validarCampos.verificarErro(txtDigito.getText(), lbErroDigito, limiteDigito);
        validarCampos.verificarErro(txtConta.getText(), lbErroConta, limiteConta);
        validarCampos.verificarErro(txtAgencia.getText(), lbErroAgencia, limiteAgencia);
        validarCampos.validarBanco(cbBanco.getSelectionModel().getSelectedItem()
        .toString(), lbErroBanco);
        
        if(validarCampos.verificarErro(txtDigito.getText(), lbErroDigito, limiteDigito)
            && validarCampos.verificarErro(txtConta.getText(), lbErroConta, limiteConta)
            && validarCampos.verificarErro(txtAgencia.getText(), lbErroAgencia, limiteAgencia)
            && validarCampos.validarBanco(cbBanco.getSelectionModel().getSelectedItem()
            .toString(), lbErroBanco)){
            
            Conta conta = pesquisarConta();
                
            if(conta.getCliente().getCodCliente() == 0){
               cp.alerta("Conta inválida","Digite uma conta válida para prosseguir.");
                
            }else{
                
                ClienteLogado.setCliente(conta.getCliente());
                ClienteLogado.setCodConta(conta.getCodConta());
                Main tela = new Main();
                tela.sceneHandler("Menu");
            }
        }
 


    }
    
    @FXML
    private void fechar(){
        
        System.exit(0);
    }
    
    public void carregarBancos(){
       
       cbBanco.setItems(cp.carregarBancos());

    }
    
    @FXML
    private void maskAgencia(){

        cp.limitarCampoInt(txtAgencia,limiteAgencia);
    }
   
    
    @FXML
    private void maskDigito(){

        cp.limitarCampoInt(txtDigito,limiteDigito);
    }
    
    @FXML
    private void maskConta(){
       cp.limitarCampoInt(txtConta,7);
    }

         
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        carregarBancos();
        tpConta="Corrente";
        rbCorrente.setSelected(true);
    }

    

    public void validarBanco(){
      validarCampos.validarBanco(cbBanco.getSelectionModel().getSelectedItem().toString(), lbErroBanco);
        
    }

    
    public void validarDigito(){
        
      maskDigito();
      validarCampos.verificarErro(txtDigito.getText(), lbErroDigito, limiteDigito);
      
    }
    
    public void validarConta(){
        
      maskConta();
      validarCampos.verificarErro(txtConta.getText(), lbErroConta, limiteConta);
      
    }
    public void validarAgencia(){
        
      maskConta();
      validarCampos.verificarErro(txtAgencia.getText(), lbErroAgencia, limiteAgencia);
      
    }
    
    
    public void clickPoupanca(){
        
        tpConta="Poupança";
        
        rbCorrente.selectedProperty().setValue(false);
        rbPoupa.selectedProperty().setValue(true);

    }
    public void clickCorrente(){
     
        tpConta="Corrente";
        
        rbPoupa.selectedProperty().setValue(false);
        rbCorrente.selectedProperty().setValue(true);
    }

    
    
    private Conta pegarConta(){
        
        Conta conta= new Conta();
        Banco banco;

        banco = cbBanco.getSelectionModel().getSelectedItem();
        conta.setAgenciaConta(txtAgencia.getText());
        conta.setBanco(banco);
        conta.setNumConta(txtConta.getText()+"-"+txtDigito.getText());
        conta.setTipoConta(tpConta);
       
        return conta;
    }
    
    private Conta pesquisarConta(){
        
        return opM.logar(pegarConta());

    }
}