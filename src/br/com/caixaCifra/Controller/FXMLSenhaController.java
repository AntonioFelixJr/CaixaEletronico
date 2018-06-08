package br.com.caixaCifra.Controller;

import br.com.caixaCifra.DAO.OperacaoDAO;
import br.com.caixaCifra.Model.OperacaoModel;
import br.com.caixaCifra.Validation.ValidarCampos;
import br.com.caixaCifra.Util.Componentes;
import br.com.caixaCifra.Model.ClienteLogado;
import br.com.caixaCifra.View.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLSenhaController implements Initializable{

    @FXML
    private Label lbErroSenha;

    @FXML
    private JFXButton btEntrar;

    @FXML
    private JFXButton btVoltar;

    @FXML
    private JFXPasswordField pfSenha;

    Componentes cp = new Componentes();
    OperacaoModel opM = new OperacaoModel();
    ValidarCampos vl = new ValidarCampos();
    Main main = new Main();
    
    int i=3;
    private final int limiteSenha=6;

    @FXML
    void mskSenha(){
        
        cp.limitarCampoSenha(pfSenha, limiteSenha);
    }

    @FXML
    void validarSenha(){
        
        vl.verificarErro(pfSenha.getText(), lbErroSenha,limiteSenha);
    }
    
    @FXML
    void entrar() throws IOException {
        
        if(vl.verificarErro(pfSenha.getText(), lbErroSenha,limiteSenha)){
            if(opM.verificarSenha(Integer.parseInt(pfSenha.getText()))){

                main.sceneHandler(ClienteLogado.getRota());
                
            }else{      
                i--;

                if(i>=1){
                    cp.alerta("Senha inválida", "Senha inválida, você tem mais"
                            + " "+i+" tentativa(s).");
                }else{
                    cp.alerta("Número de tentativas excedidas", "Sessão"
                            + " encerrada!\nProcure um de nossos gerentes para"
                            + " redefinir sua senha.");
                    main.sceneHandler("Login");

                }

            }
        }
      
    }

    @FXML
    void voltarMenu() throws IOException {
        main.sceneHandler("Menu");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        opM.startSenha();
    }

}
