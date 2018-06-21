package br.com.caixaCifra.Model;

import br.com.caixaCifra.DAO.OperacaoDAO;

public class OperacaoModel {
    
    OperacaoDAO op =new OperacaoDAO();
    double saldo;
    int senhaCli;
    
    public Conta logar(Conta conta){
        
        return op.validarLogin(conta);
     
    }
    
    public boolean depositar(double valor,int id){
        
        saldo = pegarSaldo(id);
        saldo += valor;
        op.attSaldo(saldo,id);
        
        return true;
   
    }

    public boolean sacar(double valor,int id) {
        
        saldo = pegarSaldo(id);
        
        if(saldo >= valor){
            
            saldo -= valor;
            op.attSaldo(saldo,id);
            return true;
            
        }
            
        return false;
        
    }
    
    public double pegarSaldo(int id){
        
        return op.consultarSaldo(id);
        
    }
    public Conta pegarContaBanco(int codConta){
        
        return op.pegarContaBanco(codConta);
        
    }
    
    
    public boolean verificarSenha(int senha){    
                       
        if(senha == senhaCli){
            
            return true;
            
        }
        
        return false;
        
    }
    
    public void startSenha(){
         senhaCli = op.pegarSenha(ClienteLogado.getCodConta());
    }
    
}
