package br.com.caixaCifra.Controller;

import br.com.caixaCifra.Model.OperacaoModel;
import br.com.caixaCifra.Validation.ValidarCampos;
import br.com.caixaCifra.Util.Componentes;
import br.com.caixaCifra.DAO.OperacaoDAO;
import br.com.caixaCifra.Model.Banco;
import br.com.caixaCifra.Model.ClienteLogado;
import br.com.caixaCifra.Model.Conta;
import br.com.caixaCifra.Util.Relatorio;
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

public class FXMLDepositoController implements Initializable {

    @FXML
    private JFXButton btVoltar;

    @FXML
    private JFXButton bntLimpar;

    @FXML
    private JFXButton btDepositar;

    @FXML
    private JFXTextField txtConta;

    @FXML
    private JFXTextField txtDigito;

    @FXML
    private JFXTextField txtAgencia;
    
    @FXML
    private JFXTextField txtValor;

    @FXML
    private Label lbErroConta;

    @FXML
    private Label lbErroAgencia;

    @FXML
    private Label lbErroDigito;

    @FXML
    private ComboBox<Banco> cbBanco;

    @FXML
    private RadioButton rbCorrente;

    @FXML
    private ToggleGroup grupo;

    @FXML
    private RadioButton rbPoupa;

    @FXML
    private ToggleGroup idGrupo;

    @FXML
    private Label lbTpConta;

    @FXML
    private Label lbErroBanco;

    @FXML
    private JFXTextField txtAgencia1;

    @FXML
    private Label lbErroValor;

    @FXML
    private JFXButton btDepositoConta;

    Componentes cp = new Componentes();
    Conta conta = new Conta();
    OperacaoModel opM = new OperacaoModel();
    ValidarCampos validarCampos = new ValidarCampos();
    String tpConta;
    
    private final int limiteAgencia = 4;
    private final int limiteConta = 7;
    private final double limiteDeposito = 5000.00;
    private final int limiteDigito = 1;
    private final int limiteValor = 7;
    private final int idInvalido = 0;
    
    
    public void depositar() throws IOException{
        
        validarCampos.verificarErro(txtDigito.getText(), lbErroDigito, limiteDigito);
        validarCampos.verificarErro(txtConta.getText(), lbErroConta, limiteConta);
        validarCampos.verificarErro(txtAgencia.getText(), lbErroAgencia, limiteAgencia);
        validarCampos.validarValor(txtValor.getText(), lbErroValor, limiteDeposito);
        validarCampos.validarBanco(cbBanco.getSelectionModel().getSelectedItem()
        .toString(), lbErroBanco);
        
        if(validarCampos.verificarErro(txtDigito.getText(), lbErroDigito, limiteDigito)
            && validarCampos.verificarErro(txtConta.getText(), lbErroConta, limiteConta)
            && validarCampos.validarValor(txtValor.getText(), lbErroValor, limiteDeposito)
            && validarCampos.verificarErro(txtAgencia.getText(), lbErroAgencia, limiteAgencia)
            && validarCampos.validarBanco(cbBanco.getSelectionModel().getSelectedItem()
            .toString(), lbErroBanco)){
            
          
            conta = pesquisarConta();
           
                
            if(conta.getCliente().getCodCliente() == 0){
                
                cp.alerta("Conta inválida","Digite uma conta válida para prosseguir.");
                
            }else{
                
                if(cp.alertaConfirmacao(conta,Integer.parseInt(txtValor.getText()))){
                    
                    opM.depositar(Double.parseDouble(txtValor.getText()), conta.getCodConta());
                    cp.alertaSucesso("Depósito");
                    if(cp.alertaExibirExtrato("Comprovante")){
                       
                        Relatorio novoRelatorio = new Relatorio();
                        novoRelatorio.gerarRelatorioDeposito(txtValor.getText(), conta);
                        cp.alertaSucesso("Impressão do comprovante");

                    }
                    voltarMenu();
                }
            }
        }
    }
    
    public void depositarConta(){
        limparCampos();
        OperacaoDAO op = new OperacaoDAO();
        conta=op.consultarConta(ClienteLogado.getCodConta());
        txtConta.setText(conta.getNumConta().substring(0,7));
        txtAgencia.setText(conta.getAgenciaConta());
        txtDigito.setText(conta.getNumConta().substring(8,9));
        
        
        cbBanco.getSelectionModel().select(conta.getBanco().getCodBanco()); 
        
        if(conta.getTipoConta().equalsIgnoreCase("Corrente")){
            clickCorrente();
        }else{
            clickPoupanca();
        }
        
    }
    
    
    public void limparCampos(){
        cp.limparCampos(txtConta, txtAgencia, txtDigito, rbPoupa, rbCorrente,
                tpConta, cbBanco, lbErroConta, lbErroAgencia, lbErroBanco, lbErroDigito);
        
        txtValor.setText("");
        lbErroValor.setText("");
        lbErroValor.setVisible(false);
    }
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        cbBanco.setItems(cp.carregarBancos());
        rbCorrente.selectedProperty().setValue(true);
         
        clickPoupanca();
        clickCorrente();
      
    }
    

    //Preparação da combo box
    public void carregarBancos(){
       
       cbBanco.setItems(cp.carregarBancos());

    }
    
    @FXML
    private void maskAgencia(){
        cp.limitarCampoInt(txtAgencia,limiteAgencia);
    }
    
    @FXML
    private void maskValor(){

        cp.limitarCampoInt(txtValor,limiteValor);
    }
   
    
    @FXML
    private void maskDigito(){
        cp.limitarCampoInt(txtDigito,limiteDigito);
    }
    
    @FXML
    private void maskConta(){
       cp.limitarCampoInt(txtConta,limiteConta);
    }

    public void validarBanco(){
      validarCampos.validarBanco(cbBanco.getSelectionModel().getSelectedItem()
      .toString(), lbErroBanco);
    }

    public void validarAgencia(){
        maskAgencia();
        validarCampos.verificarErro(txtAgencia.getText(), lbErroAgencia, limiteAgencia);
       
    }
    
    public void validarValor(){
        maskValor();
        validarCampos.validarValor(txtValor.getText(), lbErroValor,limiteDeposito);
       
    }
    
    public void validarDigito(){
        maskDigito();
        validarCampos.verificarErro(txtDigito.getText(), lbErroDigito, limiteDigito);
    }
    
    public void validarConta(){
      maskConta();
      validarCampos.verificarErro(txtConta.getText(), lbErroConta, limiteConta);
    }
  

    public void clickCorrente(){
     
        tpConta="Corrente";
        
        rbPoupa.selectedProperty().setValue(false);
        rbCorrente.selectedProperty().setValue(true);
    }

    public void voltarMenu() throws IOException{
         Main tela = new Main();
         tela.sceneHandler("Menu");
    }
    
    public void clickPoupanca(){
        
        tpConta="Poupança";
        
        rbCorrente.selectedProperty().setValue(false);
        rbPoupa.selectedProperty().setValue(true);

    }
    
    private Conta pegarConta(){
        
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

