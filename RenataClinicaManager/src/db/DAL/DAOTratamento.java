/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.PacienteTratamento;
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
    
    public Tratamento get(int cod)
    {
        String sql = "SELECT * FROM TRATAMENTO WHERE tra_cod = "+cod;
        
        Tratamento aux = null;
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            if(rs.next())
            {                           
                aux = new Tratamento(rs.getInt("tra_cod"), rs.getString("tra_nome"), 
                        rs.getDouble("tra_valor"));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }

    public List<PacienteTratamento> getPTList(String filtro) 
    {
        String sql = "SELECT * FROM pessoatratamento pt ";
        
        if(!filtro.isEmpty())
            sql += filtro;
        
        List <PacienteTratamento> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        DAOPaciente dp = new DAOPaciente();
        DAOTratamento dt = new DAOTratamento();
        
        try {
            while(rs.next())
            {                                 
                aux.add(new PacienteTratamento(dp.get(rs.getString("pac_cpf")),
                        dt.get(rs.getInt("tra_cod")), 
                        rs.getString("pt_ativo").charAt(0),rs.getInt("pt_cod")));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }

    public boolean apagarPT(PacienteTratamento pt) {
        return Banco.getCon().manipular("DELETE FROM pessoatratamento WHERE pt_cod=" + pt.getCodigo());
    }

    public boolean iniciarTratamento(PacienteTratamento pt) 
    {
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM pessoatratamento "
                +"WHERE pac_cpf='"+pt.getPaciente().getCpf()+"' AND tra_cod="+pt.getTratamento().getCod());
        int cont = 0;

        try{
            if(rs.next())
                cont++;
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        if(cont > 0)
            return Banco.getCon().manipular("UPDATE pessoatratamento SET pt_ativo = 'S' WHERE pt_cod=" + pt.getCodigo());
        else
        {
            String sql = "INSERT INTO pessoatratamento(pac_cpf, tra_cod, pt_ativo) "
                + "VALUES ('#1',#2,'S'); ";

            sql = sql.replaceAll("#1", "" + pt.getPaciente().getCpf());
            sql = sql.replaceAll("#2", "" + pt.getTratamento().getCod());

            return Banco.getCon().manipular(sql);
        }
    }

    public boolean desativarPT(PacienteTratamento pt) {
        return Banco.getCon().manipular("UPDATE pessoatratamento SET pt_ativo = 'N' WHERE pt_cod=" + pt.getCodigo());
    }
    
}
