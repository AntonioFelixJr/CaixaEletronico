package br.com.caixaCifra.Controller;

import br.com.caixaCifra.DAO.OperacaoDAO;
import br.com.caixaCifra.Model.ClienteLogado;
import br.com.caixaCifra.Model.OperacaoModel;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       OperacaoModel opM =new OperacaoModel();
       
        txtSaldo.setText(""+opM.pegarSaldo(ClienteLogado.getCodConta()));    
        
    }
    @FXML
    private void voltarMenu() throws IOException{
        
         Main tela = new Main();
         tela.sceneHandler("Menu");
         

    }
}
