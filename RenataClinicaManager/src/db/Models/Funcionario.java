/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.Models;

import java.time.LocalDate;

/**
 *
 * @author vicga
 */
public class Funcionario 
{
    private Cargo c;
    private String nome,login,telefone;
    //private JsonObject horarios;
    private LocalDate dtnasc;
    private boolean ativo;

    public Funcionario() {
    }

    public Funcionario(String login) {
        this.login = login;
    }

    public Cargo getC() {
        return c;
    }

    public void setC(Cargo c) {
        this.c = c;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDtnasc() {
        return dtnasc;
    }

    public void setDtnasc(LocalDate dtnasc) {
        this.dtnasc = dtnasc;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}
