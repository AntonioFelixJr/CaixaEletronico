package br.com.caixaCifra.Controller;

import br.com.caixaCifra.Util.Relatorio;
import br.com.caixaCifra.Model.ClienteLogado;
import br.com.caixaCifra.Model.Conta;
import br.com.caixaCifra.Model.OperacaoModel;
import br.com.caixaCifra.Util.Componentes;
import br.com.caixaCifra.View.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FXMLSaldoController implements Initializable{

    @FXML
    private JFXTextField txtSaldo;

    @FXML
    private JFXButton btSaldoVoltar;
    
    @FXML
    private JFXButton btImprimirRelatorio;

    OperacaoModel opM =new OperacaoModel();
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
       
        txtSaldo.setText(""+opM.pegarSaldo(ClienteLogado.getCodConta()));    
        
    }
    
    @FXML
    private void imprimirRelatorio() throws IOException{
        Relatorio novoRelatorio = new Relatorio();
        Componentes cp = new Componentes();
        Conta conta = new Conta();
        conta = opM.pegarContaBanco(ClienteLogado.getCodConta());
        novoRelatorio.gerarRelatorioSaldo(txtSaldo.getText(), conta);
        cp.alertaSucesso("Impressão do comprovante");
        voltarMenu();

    }
    
    @FXML
    private void voltarMenu() throws IOException{
        
        Main tela = new Main();
        
        tela.sceneHandler("Menu");
         

    }
}
