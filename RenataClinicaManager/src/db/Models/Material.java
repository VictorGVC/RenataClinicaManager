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
public class Material 
{
    private int id,qtde;
    private double valor;
    private String nome;

    public Material() {
    }

    public Material(int qtde, double valor, String nome) {
        this.qtde = qtde;
        this.valor = valor;
        this.nome = nome;
    }

    public Material(int id) {
        this.id = id;
    }

    public Material(int id, int qtde, double valor, String nome) {
        this.id = id;
        this.qtde = qtde;
        this.valor = valor;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
