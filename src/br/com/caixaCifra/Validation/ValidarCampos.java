/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caixaCifra.Validation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Label;

public class ValidarCampos {
        
    private final int campoVazio = 0;
    
    private boolean campoVazio(String campo){
        
        if(campo.length() == campoVazio){
            
            return true;
        }
        
        return false;
    }
    
    private boolean campoCheio(String campo, int limiteCampo){
        
        if(campo.length() < limiteCampo){
            
            return false;
            
        }
        
        return true;
        
    }
    
    private void exibirErroCampoIncompleto(Label erro, int limiteCampo){
        
        erro.setText("Digite no mínimo "+limiteCampo+" números.");
        
    }
    
    private void exibirErroCampoVazio(Label erro){
        
        erro.setText("Campo não preenchido.");
        
    }
    
    private void tornarErroVisivel(Label erro){
        
        erro.setVisible(true);
        
    }
    
    private void tornarErroInvisivel(Label erro){
        
        erro.setVisible(false);
        
    }
    
    public boolean verificarErro(String texto, Label erro, int limite){
        
        if(campoVazio(texto)){
            
            exibirErroCampoVazio(erro);
            tornarErroVisivel(erro);

            return false;
            
        }else{
            
            if(campoCheio(texto, limite)){

                tornarErroInvisivel(erro);
                
                return true;
                
            }else{
                
                exibirErroCampoIncompleto(erro, limite);
                tornarErroVisivel(erro);
                
                return false;
            }
        }
    }
    public boolean verificarErroData(String data, Label erro){
        if(validarVencimnto(data) == false){
            
            erro.setText("Data inválida. Insira a data de hoje ("+pegarData()+") ou posterior.");
            tornarErroVisivel(erro);

            return false;
            
        }
  
        tornarErroInvisivel(erro);
        return true;
    }
    public boolean validarVencimnto(String data){

        int dia = Integer.parseInt(data.substring(8,10)),
            mes = Integer.parseInt(data.substring(5,7)),
            ano = Integer.parseInt(data.substring(0,4)); 
       
        data = pegarData(); 

        int diaAtual = Integer.parseInt(data.substring(0,2)),
            mesAtual = Integer.parseInt(data.substring(3,5)),
            anoAtual = Integer.parseInt(data.substring(6,10)); 
        
        if(anoAtual <= ano){
 
            if(anoAtual == ano){

                if(mesAtual <= mes){

                    if(mesAtual == mes){
                        
                        if(diaAtual <= dia){
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        return true;

                    }
                }else{
                    return false;
                }                
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
    public boolean validarBanco(String banco, Label lbErroBanco){
        
        if( banco.equals("Escolha seu banco")){
            
            exibirErroCampoVazio(lbErroBanco);
            tornarErroVisivel(lbErroBanco);
            
            return false;
            
        }else{
          
            tornarErroInvisivel(lbErroBanco);
            
            return true;
            
        }  
    }

    public boolean validarValor(String valor, Label erro, double limite) {
        
        if(valor.length() == campoVazio){

            exibirErroCampoVazio(erro);
            tornarErroVisivel(erro);

            return false;
            
        }else{
                    
                    
            if(validarEntradaValor(valor)){

                double valorD = Double.parseDouble(valor);

                if(valorD <= campoVazio ){

                    erro.setText("Digite um número positivo.");
                    tornarErroVisivel(erro);

                    return false;

                }else{

                    if(valorD > limite){

                        erro.setText("Valor máximo é de R$"+limite);
                        tornarErroVisivel(erro);

                        return false;
                    }

                    tornarErroInvisivel(erro);

                    return true;
                }
                 
            }else{

                erro.setText("Entrada inválida, digite apenas números e use (.) para decimais.");
                tornarErroVisivel(erro);

                return false;
            }
        }
    }
    
    public boolean validarEntradaValor(String valor){
        try {
            
            Double.parseDouble(valor);
            
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    private String pegarData() { 
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
	Date date = new Date(); 
        
	return dateFormat.format(date); 
    }
    
}
