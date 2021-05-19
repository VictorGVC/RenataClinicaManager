/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.Models;

/**
 *
 * @author vicga
 */
public class Modelos 
{
    private int cod;
    private String conteudo, rodape, cabecalho;

    public Modelos(int cod, String conteudo, String rodape, String cabecalho) {
        this.cod = cod;
        this.conteudo = conteudo;
        this.rodape = rodape;
        this.cabecalho = cabecalho;
    }

    public Modelos(String rodape, String cabecalho) {
        this.rodape = rodape;
        this.cabecalho = cabecalho;
    }

    public Modelos() {
    }

    public Modelos(int cod) {
        this.cod = cod;
    }

    public Modelos(String conteudo, String rodape, String cabecalho) {
        this.conteudo = conteudo;
        this.rodape = rodape;
        this.cabecalho = cabecalho;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getRodape() {
        return rodape;
    }

    public void setRodape(String rodape) {
        this.rodape = rodape;
    }

    public String getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(String cabecalho) {
        this.cabecalho = cabecalho;
    }
}
