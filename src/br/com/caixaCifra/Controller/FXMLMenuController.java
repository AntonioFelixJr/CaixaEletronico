package br.com.caixaCifra.Controller;

import br.com.caixaCifra.Model.ClienteLogado;
import br.com.caixaCifra.View.Main;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLMenuController implements Initializable{

    @FXML
    private JFXButton btSaque;

    @FXML
    private JFXButton btSaldo;

    @FXML
    private JFXButton btPagamento;

    @FXML
    private JFXButton btDeposito;

    @FXML
    private JFXButton btTransferencia;

    @FXML
    private JFXButton btVoltar;

    @FXML
    private Label lbEscolherOp;

    @FXML
    private Label lbBemVindo;

    @FXML
    private Label lbNome;
    
    Main tela = new Main();
    
    @FXML
    void abrirDeposito(ActionEvent event) throws IOException {
        
        ClienteLogado.setRota("Deposito");
        tela.sceneHandler("Deposito");
        }

    @FXML
    void abrirPagamento(ActionEvent event) throws IOException {
        
        ClienteLogado.setRota("Pagamento");
        tela.sceneHandler("Senha");
    }

    @FXML
    void abrirSaldo(ActionEvent event) throws IOException {
        
        ClienteLogado.setRota("Saldo");
        tela.sceneHandler("Senha");
    }

    @FXML
    void abrirSaque(ActionEvent event) throws IOException {
        
        ClienteLogado.setRota("Saque");
        tela.sceneHandler("Senha");
    }

    @FXML
    void abrirTransferencia(ActionEvent event) throws IOException {
        
        ClienteLogado.setRota("Transferencia");
        tela.sceneHandler("Senha");
    }

    @FXML
    void sair(ActionEvent event) throws IOException {

        tela.sceneHandler("Login");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lbNome.setText(ClienteLogado.getCliente().getNomeCliente());        
    }
}
