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
public class Tratamento 
{
    private int cod;
    private String nome;
    private double valor;

    @Override
    public String toString() {
        return this.getNome();
    }
    
    public Tratamento() {
        this.nome = "";
    }

    public Tratamento(int cod) {
        this.cod = cod;
    }

    public Tratamento(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public Tratamento(int cod, String nome, double valor) {
        this.cod = cod;
        this.nome = nome;
        this.valor = valor;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
