/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.Cargo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author vicga
 */
public class DAOCargo {

    public String apagar(Cargo c) 
    {
        if(c.getCod() == 1)
            return "Não é possível apagar o cargo de administrador";
        else if(Banco.getCon().manipular("DELETE FROM CARGO WHERE car_cod=" + c.getCod()))
            return "";
        else
            return "Erro ao apagar!";
    }

    public boolean gravar(Cargo c) 
    {
        String sql = "INSERT INTO cargo(car_nome, car_acesso) "
                + "VALUES ('#1','#2'); ";

        sql = sql.replaceAll("#1", "" + c.getNome());
        sql = sql.replaceAll("#2", "" + c.getAcesso().toString());
        
        return Banco.getCon().manipular(sql);
    }

    public String alterar(Cargo c) 
    {
        if(c.getCod() == 1)
            return "Não é possível alterar o gerente";
        else 
        {
            String sql = "UPDATE cargo SET car_nome='#1',car_acesso='#2'"
                + " WHERE car_cod=" + c.getCod();
        
            sql = sql.replaceAll("#1", "" + c.getNome());
            sql = sql.replaceAll("#2", "" + c.getAcesso().toString());
            if(Banco.getCon().manipular(sql))
                return "";
            else return "Erro ao alterar!";
        }
    }

    public List<Cargo> getList(String filtro) 
    {
        String sql = "SELECT * FROM cargo";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <Cargo> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        JSONArray ja;
        try {
            while(rs.next())
            {                                 
                ja = new JSONArray(rs.getString("car_acesso"));
                aux.add(new Cargo(rs.getInt("car_cod"), ja, 
                        rs.getString("car_nome")));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }
    
}
