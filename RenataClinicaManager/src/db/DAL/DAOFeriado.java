/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.Feriado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicga
 */
public class DAOFeriado 
{
    public List<Feriado> getList(String filtro) 
    {
        String sql = "SELECT * FROM Feriado";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <Feriado> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            while(rs.next())
            {                                 
                aux.add(new Feriado(rs.getInt("fer_cod"), rs.getString("fer_nome"), 
                        rs.getDate("fer_dia").toLocalDate()));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }

    public boolean apagar(int id) 
    {
        return Banco.getCon().manipular("DELETE FROM Feriado WHERE fer_cod=" + id);
    }

    public boolean alterar(Feriado f) 
    {
        String sql = "UPDATE Feriado SET fer_nome='#1',fer_dia='#2'"
                + " WHERE fer_cod=" + f.getCod();
        
            sql = sql.replaceAll("#1", "" + f.getNome());
            sql = sql.replaceAll("#2", "" + f.getData().toString());
        
        return Banco.getCon().manipular(sql);
    }

    public boolean gravar(Feriado f) 
    {
        String sql = "INSERT INTO Feriado(fer_nome, fer_dia) "
                + "VALUES ('#1','#2'); ";

        sql = sql.replaceAll("#1", "" + f.getNome());
        sql = sql.replaceAll("#2", "" + f.getData().toString());
        
        return Banco.getCon().manipular(sql);
    }
}
