/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.Modelos;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author vicga
 */
public class DAOModelos 
{
    public Modelos getAtestado()
    {
        Modelos aux = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM atestado");
        
        try{
            
            if(rs.next())
                aux = new Modelos(rs.getString("ate_rodape"),rs.getString("ate_cabecalho"));
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }
    
    public boolean salvarCR(Modelos m) 
    {
        String sql = "";
        if(getAtestado()== null)
        {
            sql = "INSERT INTO atestado(ate_cabecalho, ate_rodape) "
                + "VALUES ('#1','#2'); ";

            sql = sql.replaceAll("#1", "" + m.getCabecalho());
            sql = sql.replaceAll("#2", "" + m.getRodape());
        }
        else
        {
            sql = "UPDATE atestado SET ate_cabecalho='#1',ate_rodape='#2'";
        
            sql = sql.replaceAll("#1", "" + m.getCabecalho());
            sql = sql.replaceAll("#2", "" + m.getRodape());
        }
        return Banco.getCon().manipular(sql);
    }
    
}
