/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caixaCifra.Model;

/**
 *
 * @author Casa
 */
public class ClienteLogado {

    /**
     * @return the codConta
     */
    public static int getCodConta() {
        return codConta;
    }

    /**
     * @param aCodConta the codConta to set
     */
    public static void setCodConta(int aCodConta) {
        codConta = aCodConta;
    }

    /**
     * @return the cliente
     */
    public static Cliente getCliente() {
        return cliente;
    }

    /**
     * @param aCliente the cliente to set
     */
    public static void setCliente(Cliente aCliente) {
        cliente = aCliente;
    }

    private static int codConta;
    private static Cliente cliente; 
    private static String rota; 

    /**
     * @return the rota
     */
    public static String getRota() {
        return rota;
    }

    /**
     * @param aRota the rota to set
     */
    public static void setRota(String aRota) {
        rota = aRota;
    }

}
