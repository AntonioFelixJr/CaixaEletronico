package br.com.caixaCifra.Controller;

import br.com.caixaCifra.Model.OperacaoModel;
import br.com.caixaCifra.Validation.ValidarCampos;
import br.com.caixaCifra.Util.Componentes;
import br.com.caixaCifra.Util.Relatorio;
import br.com.caixaCifra.Model.ClienteLogado;
import br.com.caixaCifra.Model.Conta;
import br.com.caixaCifra.View.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLSaqueController implements Initializable{
    
    @FXML
    private JFXButton btVoltar;

    @FXML
    private JFXButton btSacar;

    @FXML
    private JFXButton btLimpar;

    @FXML
    private JFXTextField txtValor;

    @FXML
    private Label lbErroValor;

    private final int limiteValor = 4;
    private final double limiteSaque = 1000.00;

    ValidarCampos validarCampos = new ValidarCampos();
    OperacaoModel opM = new OperacaoModel();
    Componentes cp = new Componentes();
    
    @FXML
    void validarValor(){
        validarCampos.validarValor(txtValor.getText(), lbErroValor,limiteSaque);
    }
    
    @FXML
    void maskValor(){
        cp.limitarCampoInt(txtValor, limiteValor);
    }
    
    @FXML
    void sacar() throws IOException{
        
        if(validarCampos.validarValor(txtValor.getText(), lbErroValor,limiteSaque)){
                        
            if(opM.sacar(Double.parseDouble(txtValor.getText()),
                ClienteLogado.getCodConta())){

                cp.alertaSucesso("Saque");
                if(cp.alertaExibirExtrato("Comprovante")){
                    Conta  conta  =new Conta();
                    conta = opM.pegarContaBanco(ClienteLogado.getCodConta());
                    Relatorio novoRelatorio = new Relatorio();
                    novoRelatorio.gerarRelatorioSaque(txtValor.getText(), conta);
                    cp.alertaSucesso("Impressão do comprovante");

                }
                voltarMenu();
                

            }else{

                cp.alerta("Saque negado!", "Saldo insuficiente. Faça um depósito");

            }
        }
    }
    @FXML
    void limparCampos(){
        txtValor.setText("");
        lbErroValor.setText("");
    }
    
    public void voltarMenu() throws IOException{

        Main tela = new Main();
        tela.sceneHandler("Menu");        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
