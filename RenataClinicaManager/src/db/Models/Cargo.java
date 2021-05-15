/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.Models;

import org.json.JSONArray;


/**
 *
 * @author vicga
 */
public class Cargo 
{
    private int cod;
    private JSONArray acesso;
    private String nome;

    @Override
    public String toString() {
        return this.getNome();
    }
    
    public Cargo() {
    }

    public Cargo(int cod) {
        this.cod = cod;
    }

    public Cargo(JSONArray acesso, String nome) {
        this.acesso = acesso;
        this.nome = nome;
    }

    public Cargo(int cod, JSONArray acesso, String nome) {
        this.cod = cod;
        this.acesso = acesso;
        this.nome = nome;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public JSONArray getAcesso() {
        return acesso;
    }

    public void setAcesso(JSONArray acesso) {
        this.acesso = acesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
