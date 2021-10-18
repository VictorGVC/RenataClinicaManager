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
                aux.add(new Material(rs.getInt("mat_cod"), rs.getInt("mat_estoque")
                        , rs.getString("mat_nome")));
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
        String sql = "UPDATE material SET mat_nome='#1',mat_estoque=#2"
                + " WHERE mat_cod=" + m.getId();
        
            sql = sql.replaceAll("#1", "" + m.getNome());
            sql = sql.replaceAll("#2", "" + m.getQtde());
        
        return Banco.getCon().manipular(sql);
    }

    public boolean gravar(Material m) 
    {
        String sql = "INSERT INTO material(mat_nome, mat_estoque) "
                + "VALUES ('#1',#2); ";

        sql = sql.replaceAll("#1", "" + m.getNome());
        sql = sql.replaceAll("#2", "" + m.getQtde());
        
        return Banco.getCon().manipular(sql);
    }

    public Material get(int cod) {
        String sql = "SELECT * FROM material WHERE mat_cod="+cod;
        
        Material aux = null;
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            if(rs.next())
            {                                 
                aux = new Material(rs.getInt("mat_cod"), rs.getInt("mat_estoque")
                        , rs.getString("mat_nome"));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        return aux;
    }
    
    public Material get(String filtro)
    {
        String sql = "SELECT * FROM material";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        Material m = null;
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            if(rs.next())
            {                                 
                m = new Material(rs.getInt("mat_cod"), rs.getInt("mat_estoque")
                        , rs.getString("mat_nome"));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return m;
    }
    
    public List<Material> getItensAtendimento(int codate)
    {
        String sql = "SELECT * FROM itensatendimento i INNER JOIN "
                + "material m ON i.mat_cod = i.mat_cod WHERE age_cod="+codate;
        
        List <Material> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
            {                                 
                aux.add(new Material(rs.getInt("mat_cod"), rs.getInt("mat_qtde")
                        , rs.getString("mat_nome")));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }
}
