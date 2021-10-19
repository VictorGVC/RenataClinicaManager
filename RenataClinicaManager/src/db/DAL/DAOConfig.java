/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.Config;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;

/**
 *
 * @author vicga
 */
public class DAOConfig 
{
    public boolean Salvar(Config c)
    {
        String sql = "UPDATE config SET con_nome = '#1', con_endereco='#2', "
                + "con_cidade='#3'";
        
        sql = sql.replaceAll("#1","" +c.getNome());
        sql = sql.replaceAll("#2","" +c.getEndereco());
        sql = sql.replaceAll("#3","" +c.getCidade());
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean AlterarTema(boolean tema)
    {
        String sql = "UPDATE config SET con_tema = '#1'";
        
        if(tema)
            sql = sql.replaceAll("#1","/CSS/Dark.css");
        else
            sql = sql.replaceAll("#1","/CSS/Light.css");
        
        return Banco.getCon().manipular(sql);
    }
    
    public Config get()
    {
        String sql = "SELECT * FROM config";
        
        Config aux = null;
        ResultSet rs = Banco.getCon().consultar(sql);
        try {
            if(rs.next())                
                aux = new Config(rs.getString("con_nome"), rs.getString("con_endereco"), 
                        rs.getString("con_cidade"), rs.getString("con_tema"));

        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }
    
    public String getTema()
    {
        String sql = "SELECT con_tema FROM config";
        
        String aux = null;
        ResultSet rs = Banco.getCon().consultar(sql);
        try {
            if(rs.next())                
                aux = rs.getString("con_tema");

        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }
}
