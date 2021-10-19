/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.Models;

import java.time.LocalDate;
import org.json.JSONArray;

/**
 *
 * @author vicga
 */
public class Funcionario 
{
    private Cargo c;
    private String nome,login,telefone,crm;
    private JSONArray horarios;
    private LocalDate dtnasc;
    private char ativo,sexo;

        @Override
    public String toString() {
        return this.getNome();
    }
    
    public Funcionario() {
    }

    public Funcionario(String login) {
        this.login = login;
    }

    public Funcionario(Cargo c, String nome, String telefone, JSONArray horarios, LocalDate dtnasc, char ativo, char sexo) {
        this.c = c;
        this.nome = nome;
        this.telefone = telefone;
        this.horarios = horarios;
        this.dtnasc = dtnasc;
        this.ativo = ativo;
        this.sexo = sexo;
    }

    public Funcionario(Cargo c, String nome, String login, String telefone, JSONArray horarios, LocalDate dtnasc, char ativo, char sexo, String crm) {
        this.c = c;
        this.nome = nome;
        this.login = login;
        this.telefone = telefone;
        this.horarios = horarios;
        this.dtnasc = dtnasc;
        this.ativo = ativo;
        this.sexo = sexo;
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public JSONArray getHorarios() {
        return horarios;
    }

    public void setHorarios(JSONArray horarios) {
        this.horarios = horarios;
    }

    public Cargo getC() {
        return c;
    }

    public void setC(Cargo c) {
        this.c = c;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
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

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }
    
}
