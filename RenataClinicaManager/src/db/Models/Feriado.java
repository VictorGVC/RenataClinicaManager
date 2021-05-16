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
public class Feriado 
{
    private int cod;
    private String nome;
    private LocalDate data;

    public Feriado() {
    }

    public Feriado(int cod) {
        this.cod = cod;
    }

    public Feriado(String nome, LocalDate data) {
        this.nome = nome;
        this.data = data;
    }

    public Feriado(int cod, String nome, LocalDate data) {
        this.cod = cod;
        this.nome = nome;
        this.data = data;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
}
