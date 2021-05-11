/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.Paciente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicga
 */
public class DAOPaciente 
{

    public List<Paciente> getList(String filtro) 
    {
        String sql = "SELECT * FROM PACIENTE";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <Paciente> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try {
            LocalDate ld;
            while(rs.next())
            {
                try {
                    ld = rs.getDate("pac_data").toLocalDate();   
                } catch (Exception e) {
                    ld = null;
                }                                     
                aux.add(new Paciente(rs.getString("pac_cpf"), rs.getString("pac_nome"), 
                        rs.getString("pac_telefone"), rs.getString("pac_rua"), 
                        rs.getString("pac_cidade"), rs.getString("pac_bairro"),
                        rs.getString("pac_rea"), rs.getInt("pac_numero"), rs.getString("pac_sexo").charAt(0),
                        ld));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }

    public String apagar(Paciente p) 
    {
        if(Banco.getCon().manipular("DELETE FROM PACIENTE WHERE pac_cpf='" + p.getCpf()+ "'"))
            return "";
        else
            return "Erro ao Excluir";
    }

    public String gravar(Paciente c) 
    {
        String sql;
        int cont = 0;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM paciente "
            + "WHERE pac_cpf = '"+c.getCpf()+"'");
        
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
            sql = "INSERT INTO Paciente(pac_cpf, pac_nome, pac_telefone, pac_cidade,"
                + "pac_rua, pac_numero, pac_bairro, pac_data, pac_rea, pac_sexo) "
                + "VALUES ('#1','#2','#3','#4','#5',#6,'#7','#8','#9','&1'); ";

            sql = sql.replaceAll("#1", "" + c.getCpf());
            sql = sql.replaceAll("#2", "" + c.getNome());
            sql = sql.replaceAll("#3", "" + c.getTelefone());
            sql = sql.replaceAll("#4", "" + c.getCidade());
            sql = sql.replaceAll("#5", "" + c.getRua());
            sql = sql.replaceAll("#6", "" + c.getNumero());
            sql = sql.replaceAll("#7", "" + c.getBairro());
            sql = sql.replaceAll("&1", "" + c.getSexo());
            if(c.getDtnacimento()!=null)
                sql = sql.replaceAll("#8", "" + c.getDtnacimento().toString());
            else
                sql = sql.replaceAll("'#8'", "null");
            sql = sql.replaceAll("#9", "" + c.getRea());
        }
        else
            return "Paciente com o CPF informado já cadastrado!";
        
        if(Banco.getCon().manipular(sql))
            return "";
        
        return "Erro ao Gravar";
    }

    public String alterar(Paciente c,String cpfantigo)
    {
        String sql = "";
        int cont = 0;
        if(!cpfantigo.equals(c.getCpf()))
        {
            ResultSet rs = Banco.getCon().consultar("SELECT * FROM paciente "
            + "WHERE pac_cpf = '"+c.getCpf()+"'");
        
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
            sql = "UPDATE PACIENTE SET pac_cpf='#1',pac_nome='#2',pac_sexo='#3', pac_data='#4', "
                + "pac_telefone='#6', pac_rua='#8', pac_numero=#9, pac_bairro='#5', "
                + "pac_cidade='#7', pac_rea='#a' WHERE pac_cpf='" + cpfantigo+ "'";
        
            sql = sql.replaceAll("#1", "" + c.getCpf());
            sql = sql.replaceAll("#2", "" + c.getNome());
            sql = sql.replaceAll("#3", "" + c.getSexo());
            sql = sql.replaceAll("#4", "" + c.getDtnacimento().toString());
            sql = sql.replaceAll("#6", "" + c.getTelefone());
            sql = sql.replaceAll("#8", "" + c.getRua());
            sql = sql.replaceAll("#9", "" + c.getNumero());
            sql = sql.replaceAll("#5", "" + c.getBairro());
            sql = sql.replaceAll("#7", "" + c.getCidade());
            sql = sql.replaceAll("#a", "" + c.getRea());
        }
        else
            return "CPF já existente!";
        
        if(Banco.getCon().manipular(sql))
            return "";
        else
            return "Erro ao alterar!";
    }
    
}
