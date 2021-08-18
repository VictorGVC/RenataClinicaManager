/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.Models;

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

    public Compra() {
    }

    public Compra(int id) {
        this.id = id;
    }

    public Compra(int id, double total, Fornecedor fornecedor, List<Material> produtos) {
        this.id = id;
        this.total = total;
        this.fornecedor = fornecedor;
        this.produtos = produtos;
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
    
}
