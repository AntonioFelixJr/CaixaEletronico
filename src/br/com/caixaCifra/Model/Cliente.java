package br.com.caixaCifra.Model;


public class Cliente {

	private int codCliente;
	public String nomeCliente; 
	private Conta contaCliente;
	
	//Getters and Setters
	public int getCodCliente(){
		return codCliente; 
	}
	public void setCodCliente(int codCliente){
		this.codCliente=codCliente;
	}
	
	public 	String getNomeCliente(){
		return nomeCliente; 
	}
	public void setNomeCliente(String nomeCliente){
		this.nomeCliente = nomeCliente;
	}
	
	public Conta getContaCliente(){
		return contaCliente; 
	}
	public void setContaCliente(Conta contaCliente){
		this.contaCliente = contaCliente;
	}
}
