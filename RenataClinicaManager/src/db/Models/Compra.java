/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicga
 */
public class Compra 
{
    private int id;
    private double total;
    private Fornecedor fornecedor;
    private List<Material> produtos;
    private LocalDate dtcompra;
    private List<Conta> parcelas;

    public Compra() 
    {
        produtos = new ArrayList<>();
    }

    public Compra(int id) 
    {
        produtos = new ArrayList<>();
        this.id = id;
    }

    public Compra(int id, double total, LocalDate dtcompra, Fornecedor fornecedor, List<Material> produtos) {
        this.id = id;
        this.total = total;
        this.fornecedor = fornecedor;
        this.produtos = produtos;
        this.dtcompra = dtcompra;
    }

    public Compra(int id, Fornecedor fornecedor, List<Material> produtos) {
        this.id = id;
        this.fornecedor = fornecedor;
        this.produtos = produtos;
    }

    public Compra(Fornecedor fornecedor, List<Material> produtos) {
        this.fornecedor = fornecedor;
        this.produtos = produtos;
    }

    public Compra(List<Material> produtos) {
        this.produtos = produtos;
    }

    public Compra(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Material> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Material> produtos) {
        this.produtos = produtos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getDtcompra() {
        return dtcompra;
    }

    public void setDtcompra(LocalDate dtcompra) {
        this.dtcompra = dtcompra;
    }

    public List<Conta> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Conta> parcelas) {
        this.parcelas = parcelas;
    }
    
}
