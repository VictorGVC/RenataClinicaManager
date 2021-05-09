package db.Models;

import java.time.LocalDate;

/* @author Victor Gabriel Viana da Costa */
public class Paciente 
{
    private String cpf, nome, telefone, rua, cidade, bairro, rea;
    private int numero;
    private char sexo;
    private LocalDate dtnacimento;

    public Paciente() {
    }

    public Paciente(String cpf) {
        this.cpf = cpf;
    }

    public Paciente(String cpf, String nome, String telefone, String rua, String cidade, String bairro, String rea, int numero, char sexo, LocalDate dtnacimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.rua = rua;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rea = rea;
        this.numero = numero;
        this.sexo = sexo;
        this.dtnacimento = dtnacimento;
    }

    public Paciente(String nome, String telefone, String rua, String cidade, String bairro, String rea, int numero, char sexo, LocalDate dtnacimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.rua = rua;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rea = rea;
        this.numero = numero;
        this.sexo = sexo;
        this.dtnacimento = dtnacimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRea() {
        return rea;
    }

    public void setRea(String rea) {
        this.rea = rea;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getDtnacimento() {
        return dtnacimento;
    }

    public void setDtnacimento(LocalDate dtnacimento) {
        this.dtnacimento = dtnacimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    
}   
