/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.Conta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author vicga
 */
public class DAOConta 
{
    public List<Conta> getContas(String filtro)
    {
        String sql = "SELECT * FROM contaspagar c "+filtro;
        
        List <Conta> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        DAOFornecedor df = new DAOFornecedor();
        
        try {
            LocalDate venc,pag;
            while(rs.next())
            {     
                try 
                {
                    venc = rs.getDate("pag_dtvencimento").toLocalDate();   
                } 
                catch (Exception e) 
                {
                    venc = null;
                }  
                try 
                {
                    pag = rs.getDate("pag_dtpagamento").toLocalDate();   
                } 
                catch (Exception e) 
                {
                    pag = null;
                }  
                aux.add(new Conta(rs.getInt("pag_cod"), rs.getInt("pag_parcela"), 
                        venc, pag, rs.getDouble("pag_valor"), rs.getString("pag_tipo"), 
                        df.get(rs.getString("for_cnpj"))));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
        
    }
    public boolean receber(Conta c)
    {
        return true;
    }
    
    public boolean pagar(Conta c)
    {
        String sql = "UPDATE contaspagar SET pag_dtpagamento = '#1', pag_valor=#2 WHERE pag_cod="+c.getCodigo();
        
        sql = sql.replaceAll("#1","" +c.getDtpagamento());
        sql = sql.replaceAll("#2","" +c.getValor());

        return Banco.getCon().manipular(sql);
    }
    
    public boolean estornarp(Conta c)
    {
        String sql = "UPDATE contaspagar SET "
                + "pag_dtpagamento =null WHERE pag_cod="+c.getCodigo();

        return Banco.getCon().manipular(sql);
    }
    
    public boolean estornarr(Conta c)
    {
        return true;
    }
}
