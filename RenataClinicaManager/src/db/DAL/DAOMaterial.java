/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.Material;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicga
 */
public class DAOMaterial {

    public List<Material> getList(String filtro) 
    {
        String sql = "SELECT * FROM material";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <Material> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
            {                                 
                aux.add(new Material(rs.getInt("mat_cod"), rs.getInt("mat_estoque"), 
                        rs.getDouble("mat_valor"), rs.getString("mat_nome")));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }

    public boolean apagar(int id) 
    {
        return Banco.getCon().manipular("DELETE FROM MATERIAL WHERE mat_cod=" + id);
    }

    public boolean alterar(Material m) 
    {
        String sql = "UPDATE material SET mat_nome='#1',mat_estoque=#2,mat_valor=#3"
                + " WHERE mat_cod=" + m.getId();
        
            sql = sql.replaceAll("#1", "" + m.getNome());
            sql = sql.replaceAll("#2", "" + m.getQtde());
            sql = sql.replaceAll("#3", "" + m.getValor());
        
        return Banco.getCon().manipular(sql);
    }

    public boolean gravar(Material m) 
    {
        String sql = "INSERT INTO material(mat_nome, mat_estoque, mat_valor) "
                + "VALUES ('#1',#2,#3); ";

        sql = sql.replaceAll("#1", "" + m.getNome());
        sql = sql.replaceAll("#2", "" + m.getQtde());
        sql = sql.replaceAll("#3", "" + m.getValor());
        
        return Banco.getCon().manipular(sql);
    }
}
