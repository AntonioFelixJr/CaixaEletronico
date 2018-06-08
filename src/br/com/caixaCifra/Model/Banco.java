/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caixaCifra.Model;

/**
 *
 * @author anton
 */
public class Banco {

    private int codBanco;
    private String nomeBanco;   
    /**
     * @return the codBanco
     */
    public int getCodBanco() {
        return codBanco;
    }

    /**
     * @param codBanco the codBanco to set
     */
    public void setCodBanco(int codBanco) {
        this.codBanco = codBanco;
    }

    /**
     * @return the nomeBanco
     */
    public String getNomeBanco() {
        return nomeBanco;
    }

    /**
     * @param nomeBanco the nomeBanco to set
     */
    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    @Override
    public String toString() {
        return getNomeBanco();
    }
    

}
