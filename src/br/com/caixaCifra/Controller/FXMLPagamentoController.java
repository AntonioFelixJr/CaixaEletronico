package br.com.caixaCifra.Controller;

import br.com.caixaCifra.Model.OperacaoModel;
import br.com.caixaCifra.Validation.ValidarCampos;
import br.com.caixaCifra.Util.Componentes;
import br.com.caixaCifra.DAO.OperacaoDAO;
import br.com.caixaCifra.Model.ClienteLogado;
import br.com.caixaCifra.Model.Conta;
import br.com.caixaCifra.Util.Relatorio;
import br.com.caixaCifra.View.Main;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class FXMLPagamentoController implements Initializable {

    @FXML
    private Label lbErroCodBarra;

    @FXML
    private Label lbErroData;
    
    @FXML
    private JFXTextField txtValor;

    @FXML
    private DatePicker datePicker;
    
    @FXML
    private JFXTextField txtCodBarra;

    @FXML
    private Label lbErroValor;
    
    Componentes cp = new Componentes();
    ValidarCampos validarCampos = new ValidarCampos();
    OperacaoModel opM = new OperacaoModel();
    
    
    private final int limiteCodBarra = 46;
    private final double limitePagamento = 1000.00;
    private final int limiteValor = 6;

    double valor;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        datePicker.setValue(LocalDate.now());
    }
    
    @FXML
    void limpar(){
        txtValor.setText("");
        txtCodBarra.setText("");
        lbErroValor.setText("");
        lbErroCodBarra.setText("");
    }
    
    @FXML
    void voltar() throws IOException{
        Main tela = new Main();
        tela.sceneHandler("Menu");
    }
    @FXML
    void pagar() throws IOException{
        LocalDate date = datePicker.getValue();

        mskValor();
        mskCodBarra();
        mskData(date);

        if(validarCampos.verificarErro(txtCodBarra.getText(),lbErroCodBarra,limiteCodBarra) 
            && validarCampos.validarValor(txtValor.getText(), lbErroValor, limitePagamento) 
            &&  validarCampos.verificarErroData(date.toString(),lbErroData)){
            
            String codBarra=txtCodBarra.getText();
            String dataOrdenada = ordenarData(date.toString());

            codBarra=txtCodBarra.getText().substring(0,5)+"."
                    +txtCodBarra.getText().substring(6,11)+" "
                    +txtCodBarra.getText().substring(12,17)+"."
                    +txtCodBarra.getText().substring(18,24) +" "
                    +txtCodBarra.getText().substring(25,30)+"."
                    +txtCodBarra.getText().substring(30,36)+" "
                    +txtCodBarra.getText().substring(38,39)+" "
                    +txtCodBarra.getText().substring(39,codBarra.length())+"\n";
                        
            if(cp.alertaConfirmacaoPag(codBarra,dataOrdenada,Double.parseDouble(txtValor.getText()))){

                if( opM.sacar(Double.parseDouble(txtValor.getText()), ClienteLogado.getCodConta())){
                  
                    cp.alertaSucesso("Pagamento");   
                    if(cp.alertaExibirExtrato("Comprovante")){
                        Conta  conta;
                        conta = opM.pegarContaBanco(ClienteLogado.getCodConta());
                        Relatorio novoRelatorio = new Relatorio();
                        novoRelatorio.gerarRelatorioPagamento(txtValor.getText(),dataOrdenada,codBarra, conta);
                        cp.alertaSucesso("Impressão do comprovante");
                    }
                
                    voltar();
                    
                }else{
                    
                    cp.alerta("Pagamento negado!", "Saldo insuficiente. Faça um depósito");
                    
                }
            }
        }
    }
  
    @FXML
    void mskCodBarra(){
        
        cp.limitarCampoInt(txtCodBarra, limiteCodBarra);
        validarCampos.verificarErro(txtCodBarra.getText(), lbErroCodBarra, limiteCodBarra);

    }
  
    @FXML
    void mskValor(){
        
        cp.limitarTamanho(txtValor, limiteValor);
        validarCampos.validarValor(txtValor.getText(), lbErroValor,limitePagamento);                 
    }

   @FXML
    void mskData(LocalDate date){

        validarCampos.verificarErroData(date.toString(),lbErroData);
    }    
    private String ordenarData(String data){
        String dia = data.substring(8,10),
        mes = data.substring(5,7),
        ano = data.substring(0,4); 
        return dia+"/"+mes+"/"+ano;
    }
}
