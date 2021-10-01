/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.Atendimento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicga
 */
public class DAOAgendamento 
{
    public List<Atendimento> getAgendaDia(LocalDate dia)
    {
        Timestamp tsb = Timestamp.valueOf(dia.atStartOfDay());
        Timestamp tsa = Timestamp.valueOf(dia.plusDays(1).atStartOfDay());
        String sql = "SELECT * FROM agendamento ag WHERE ag.age_dthr > '"+tsb+"' AND ag.age_dthr < '"+tsa+"' ORDER BY ag.age_dthr";
        
        List <Atendimento> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        DAOTratamento dt = new DAOTratamento();
        
        try {
            while(rs.next())
            {                                 
                aux.add(new Atendimento(rs.getTimestamp("age_dthr"), rs.getInt("age_cod"), dt.getPT(rs.getInt("pt_cod"))));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }

    public boolean salvar(Atendimento a) 
    {
        String sql = "INSERT INTO agendamento(age_dthr, pt_cod) "
                + "VALUES ('#1',#2); ";

        sql = sql.replaceAll("#1", "" + a.getHorario());
        sql = sql.replaceAll("#2", "" + a.getPt().getCodigo());
        
        return Banco.getCon().manipular(sql);
    }

    public boolean apagar(Atendimento ag) 
    {
        return Banco.getCon().manipular("DELETE FROM agendamento WHERE age_cod=" + ag.getCodigo());
    }
}
