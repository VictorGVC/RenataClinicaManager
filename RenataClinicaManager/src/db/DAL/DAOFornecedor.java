/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.Fornecedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicga
 */
public class DAOFornecedor {

    public String apagar(Fornecedor f) 
    {
        if(Banco.getCon().manipular("DELETE FROM FORNECEDOR WHERE for_cnpj='" + f.getCnpj()+ "'"))
            return "";
        else
            return "Erro ao Excluir";
    }
    
    public List<Fornecedor> getList(String filtro) 
    {
        String sql = "SELECT * FROM Fornecedor";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <Fornecedor> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            
            while(rs.next())
                aux.add(new Fornecedor(rs.getString("for_cnpj"), rs.getString("for_nome"), 
                        rs.getString("for_telefone")));
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }

    public String gravar(Fornecedor f) 
    {
        String sql;
        int cont = 0;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM fornecedor "
            + "WHERE for_cnpj = '"+f.getCnpj()+"'");
        
        try
        {
            if(rs.next())
                cont++;
        } 
        catch(SQLException ex) 
        {
            System.out.println(ex);
        }
        if(cont == 0)
        {
            sql = "INSERT INTO Fornecedor(for_cnpj, for_nome, for_telefone) "
                + "VALUES ('#1','#2','#3'); ";

            sql = sql.replaceAll("#1", "" + f.getCnpj());
            sql = sql.replaceAll("#2", "" + f.getNome());
            sql = sql.replaceAll("#3", "" + f.getTelefone());
        }
        else
            return "Fornecedor com CNPJ informado já cadastrado!";
        
        if(Banco.getCon().manipular(sql))
            return "";
        
        return "Erro ao Gravar";
    }

    public String alterar(Fornecedor f, String cnpjantigo) 
    {
        String sql = "";
        int cont = 0;
        if(!cnpjantigo.equals(f.getCnpj()))
        {
            ResultSet rs = Banco.getCon().consultar("SELECT * FROM fornecedor "
            + "WHERE for_cnpj = '"+f.getCnpj()+"'");
        
            try
            {
                if(rs.next())
                    cont++;
            } 
            catch(SQLException ex) 
            {
                System.out.println(ex);
            }
        }
        if(cont == 0)
        {
            sql = "UPDATE FORNECEDOR SET for_cnpj='#1',for_nome='#2',for_telefone='#3'"
                    + " WHERE for_cnpj='" + cnpjantigo+ "'";
        
            sql = sql.replaceAll("#1", "" + f.getCnpj());
            sql = sql.replaceAll("#2", "" + f.getNome());
            sql = sql.replaceAll("#3", "" + f.getTelefone());
        }
        else
            return "CNPJ já existente!";
        
        if(Banco.getCon().manipular(sql))
            return "";
        else
            return "Erro ao alterar!";
    }
    
}
