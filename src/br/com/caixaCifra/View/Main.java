/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caixaCifra.View;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author anton
 */
public class Main extends Application {
      public static Stage window;
      Parent root ;
      Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        window = stage;
        scene = new Scene(root);
        window.initStyle(StageStyle.UNDECORATED);
        window.getIcons().add(new Image("/Resources/Icone.png"));
        window.setScene(scene);
        window.show();
    }
    @FXML
    public void sceneHandler(String tela) throws IOException{
        System.out.println("Scene changing...");
       switch(tela){
            case "Login":
               root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
               break;
               
            case "Menu":
                root = FXMLLoader.load(getClass().getResource("FXMLMenu.fxml"));
                break;
                
            case "Saldo":
                root = FXMLLoader.load(getClass().getResource("FXMLSaldo.fxml"));
                break;
                
            case "Deposito":
                root = FXMLLoader.load(getClass().getResource("FXMLDeposito.fxml"));
                break;
                
            case "Saque":
                root = FXMLLoader.load(getClass().getResource("FXMLSaque.fxml"));
                break;
                
            case "Pagamento":
                root = FXMLLoader.load(getClass().getResource("FXMLPagamento.fxml"));
                break;
                
            case "Senha":
                root = FXMLLoader.load(getClass().getResource("FXMLSenha.fxml"));
                break;
                
            case "Transferencia":
                root = FXMLLoader.load(getClass().getResource("FXMLTransferencia.fxml"));
                break;
        }
        
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
