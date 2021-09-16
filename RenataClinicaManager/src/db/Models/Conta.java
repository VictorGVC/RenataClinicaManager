package db.Models;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.time.LocalDate;
import util.MaskFieldUtil;

public class Conta 
{
    //Atributos
    private int codigo, numero;
    private LocalDate dtvencimento, dtpagamento;
    private double valor;
    private String tipo;
    private Fornecedor fornecedor;
    private Tratamento tratamento;
    private Paciente paciente;
    
    //View
    private JFXDatePicker vdtvencimento;
    private JFXTextField vvalor;

    public Conta() {
    }

    public Conta(int codigo) {
        this.codigo = codigo;
    }

    public Conta(int numero, LocalDate dtvencimento, LocalDate dtpagamento, double valor, String tipo, Fornecedor fornecedor) 
    {
        this.numero = numero;
        this.dtvencimento = dtvencimento;
        this.dtpagamento = dtpagamento;
        this.valor = valor;
        this.tipo = tipo;
        this.fornecedor = fornecedor;
        setVdtvencimento(new JFXDatePicker(dtvencimento));
        setVvalor(new JFXTextField(String.format("%.2f", valor)));
    }

    public Conta(int numero, LocalDate dtvencimento, LocalDate dtpagamento, double valor, String tipo) {
        this.numero = numero;
        this.dtvencimento = dtvencimento;
        this.dtpagamento = dtpagamento;
        this.valor = valor;
        this.tipo = tipo;
        setVdtvencimento(new JFXDatePicker(dtvencimento));
        setVvalor(new JFXTextField(String.format("%.2f", valor)));
    }

    public Conta(int numero, LocalDate dtvencimento, double valor) {
        this.numero = numero;
        this.dtvencimento = dtvencimento;
        this.valor = valor;
        setVdtvencimento(new JFXDatePicker(dtvencimento));
        setVvalor(new JFXTextField(String.format("%.2f", valor)));
    }

    public Conta(int codigo, int numero, LocalDate dtvencimento, LocalDate dtpagamento, double valor, String tipo, Fornecedor fornecedor) 
    {
        this.codigo = codigo;
        this.numero = numero;
        this.dtvencimento = dtvencimento;
        this.dtpagamento = dtpagamento;
        this.valor = valor;
        this.tipo = tipo;
        this.fornecedor = fornecedor;
        setVdtvencimento(new JFXDatePicker(dtvencimento));
        setVvalor(new JFXTextField(String.format("%.2f", valor)));
    }

    public Conta(int codigo, int numero, LocalDate dtvencimento, LocalDate dtpagamento, double valor, Tratamento tratamento, Paciente paciente) {
        this.codigo = codigo;
        this.numero = numero;
        this.dtvencimento = dtvencimento;
        this.dtpagamento = dtpagamento;
        this.valor = valor;
        this.tratamento = tratamento;
        this.paciente = paciente;
    }

    public Conta(LocalDate dtvencimento, double valor, String tipo) {
        this.dtvencimento = dtvencimento;
        this.valor = valor;
        this.tipo = tipo;
    }    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getDtvencimento() {
        return dtvencimento;
    }

    public void setDtvencimento(LocalDate dtvencimento) {
        this.dtvencimento = dtvencimento;
    }

    public LocalDate getDtpagamento() {
        return dtpagamento;
    }

    public void setDtpagamento(LocalDate dtpagamento) {
        this.dtpagamento = dtpagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public JFXDatePicker getVdtvencimento() {
        return vdtvencimento;
    }

    public void setVdtvencimento(JFXDatePicker vdtvencimento) {
        this.vdtvencimento = vdtvencimento;
        this.vdtvencimento.setMaxWidth(100);
    }

    public JFXTextField getVvalor() {
        return vvalor;
    }

    public void setVvalor(JFXTextField vvalor) {
        this.vvalor = vvalor;
        this.vvalor.setMaxWidth(60);
        MaskFieldUtil.monetaryField(this.vvalor);
    }
}
