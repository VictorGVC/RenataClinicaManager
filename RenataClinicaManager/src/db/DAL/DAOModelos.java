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
                aux = new Modelos(rs.getString("ate_lei"), rs.getString("ate_rodape"),rs.getString("ate_cabecalho"));
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }
    
    public Modelos getReceita()
    {
        Modelos aux = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM receita");
        
        try{
            
            if(rs.next())
                aux = new Modelos(rs.getString("rec_rodape"),rs.getString("rec_cabecalho"));
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }
    
    public boolean salvarCRA(Modelos m) 
    {
        String sql = "";
        if(getAtestado()== null)
        {
            sql = "INSERT INTO atestado(ate_cabecalho, ate_rodape, ate_lei) "
                + "VALUES ('#1','#2','#3'); ";

            sql = sql.replaceAll("#1", "" + m.getCabecalho());
            sql = sql.replaceAll("#2", "" + m.getRodape());
            sql = sql.replaceAll("#3", "" + m.getConteudo());
        }
        else
        {
            sql = "UPDATE atestado SET ate_cabecalho='#1',ate_rodape='#2', ate_lei='#3'";
        
            sql = sql.replaceAll("#1", "" + m.getCabecalho());
            sql = sql.replaceAll("#2", "" + m.getRodape());
            sql = sql.replaceAll("#3", "" + m.getConteudo());
        }
        return Banco.getCon().manipular(sql);
    }
    
    public boolean salvarCRR(Modelos m) 
    {
        String sql = "";
        if(getReceita()== null)
        {
            sql = "INSERT INTO Receita(rec_cabecalho, rec_rodape) "
                + "VALUES ('#1','#2'); ";

            sql = sql.replaceAll("#1", "" + m.getCabecalho());
            sql = sql.replaceAll("#2", "" + m.getRodape());
        }
        else
        {
            sql = "UPDATE Receita SET rec_cabecalho='#1',rec_rodape='#2'";
        
            sql = sql.replaceAll("#1", "" + m.getCabecalho());
            sql = sql.replaceAll("#2", "" + m.getRodape());
        }
        return Banco.getCon().manipular(sql);
    }
    
}
