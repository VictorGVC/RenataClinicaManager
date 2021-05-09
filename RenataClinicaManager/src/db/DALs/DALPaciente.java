/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DALs;

import db.Banco.Banco;
import db.Models.Paciente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author vicga
 */
public class DALPaciente 
{

    public List<Paciente> getList(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String apagar(Paciente f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                + "pac_rua, pac_numero, pac_bairro, pac_data, pac_rae, pac_sexo) "
                + "VALUES ('#1','#2','#3','#4','#5',#6,'#7','#8','#9','&1'); ";

            sql = sql.replaceAll("#1", "" + c.getCpf());
            sql = sql.replaceAll("#2", "" + c.getNome());
            sql = sql.replaceAll("#3", "" + c.getTelefone());
            sql = sql.replaceAll("#4", "" + c.getCidade());
            sql = sql.replaceAll("#5", "" + c.getRua());
            sql = sql.replaceAll("#6", "" + c.getNumero());
            sql = sql.replaceAll("#7", "" + c.getBairro());
            sql = sql.replaceAll("#8", "" + c.getDtnacimento().toString());
            sql = sql.replaceAll("#9", "" + c.getRea());
            sql = sql.replaceAll("&1", "" + c.getSexo());
        }
        else
            return "Paciente com o CPF informado já cadastrado!";
        
        if(Banco.getCon().manipular(sql))
            return "";
        
        return "Erro ao Gravar";
    }

    public String alterar(Paciente c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
