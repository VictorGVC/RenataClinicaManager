/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.Tratamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicga
 */
public class DAOTratamento {

    public List<Tratamento> getList(String filtro) 
    {
        String sql = "SELECT * FROM tratamento";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <Tratamento> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
            {                                 
                aux.add(new Tratamento(rs.getInt("tra_cod"), rs.getString("tra_nome"), 
                        rs.getDouble("tra_valor")));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }

    public boolean apagar(int id) 
    {
        return Banco.getCon().manipular("DELETE FROM TRATAMENTO WHERE tra_cod=" + id);
    }

    public boolean alterar(Tratamento t) 
    {
        String sql = "UPDATE tratamento SET tra_nome='#1',tra_valor=#2"
                + " WHERE tra_cod=" + t.getCod();
        
            sql = sql.replaceAll("#1", "" + t.getNome());
            sql = sql.replaceAll("#2", "" + t.getValor());
        
        return Banco.getCon().manipular(sql);
    }

    public boolean gravar(Tratamento t) 
    {
        String sql = "INSERT INTO tratamento(tra_nome, tra_valor) "
                + "VALUES ('#1',#2); ";

        sql = sql.replaceAll("#1", "" + t.getNome());
        sql = sql.replaceAll("#2", "" + t.getValor());
        
        return Banco.getCon().manipular(sql);
    }
    
}
