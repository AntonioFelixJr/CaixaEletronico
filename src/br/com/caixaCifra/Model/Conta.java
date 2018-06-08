package br.com.caixaCifra.Model;



public class Conta {

    private int codConta;
    private Cliente cliente; 
    private String agenciaConta;
    private String numConta;
    private Banco banco;
    private String tipoConta;
    private int senhaConta;
	
	
    /**
     * @return the codConta
     */
    public int getCodConta() {
        return codConta;
    }

    /**
     * @param codConta the codConta to set
     */
    public void setCodConta(int codConta) {
        this.codConta = codConta;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the agenciaConta
     */
    public String getAgenciaConta() {
        return agenciaConta;
    }

    /**
     * @param agenciaConta the agenciaConta to set
     */
    public void setAgenciaConta(String agenciaConta) {
        this.agenciaConta = agenciaConta;
    }

    /**
     * @return the numConta
     */
    public String getNumConta() {
        return numConta;
    }

    /**
     * @param numConta the numConta to set
     */
    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    /**
     * @return the banco
     */
    public Banco getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    /**
     * @return the tipoConta
     */
    public String getTipoConta() {
        return tipoConta;
    }

    /**
     * @param tipoConta the tipoConta to set
     */
    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    /**
     * @return the senhaConta
     */
    public int getSenhaConta() {
        return senhaConta;
    }

    /**
     * @param senhaConta the senhaConta to set
     */
    public void setSenhaConta(int senhaConta) {
        this.senhaConta = senhaConta;
    }
	


}
