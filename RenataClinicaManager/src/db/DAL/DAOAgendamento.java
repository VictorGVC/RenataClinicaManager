/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.Atendimento;
import db.Models.Material;
import db.Models.Paciente;
import db.Models.PacienteTratamento;
import db.Models.Tratamento;
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
        DAOFuncionario df = new DAOFuncionario();
        DAOMaterial dm = new DAOMaterial();
        DAOPaciente dp = new DAOPaciente();
        try {
            while(rs.next())
            {      
                
                if(rs.getString("pt_cod") != null)
                {
                    try {
                        aux.add(new Atendimento(rs.getTimestamp("age_dthr"), 
                                rs.getInt("age_cod"), 
                                rs.getString("age_observacoes"), 
                                dt.getPT(rs.getInt("pt_cod")), 
                                df.getF(rs.getString("fun_login")), 
                                dm.getItensAtendimento(rs.getInt("age_cod")),
                                rs.getString("age_dentes")));
                    } 
                    catch (Exception e) 
                    {
                        aux.add(new Atendimento(rs.getTimestamp("age_dthr"), rs.getInt("age_cod"), dt.getPT(rs.getInt("pt_cod"))));
                    }
                }
                else
                {
                    try {
                    aux.add(new Atendimento(rs.getTimestamp("age_dthr"), 
                            rs.getInt("age_cod"), 
                            rs.getString("age_observacoes"), 
                            df.getF(rs.getString("fun_login")), 
                            dm.getItensAtendimento(rs.getInt("age_cod")),
                            rs.getString("age_dentes"),
                            dp.get(rs.getString("pac_cpf")),
                            new PacienteTratamento(new Paciente("","a"), new Tratamento())));
                } 
                catch (Exception e) 
                {
                    aux.add(new Atendimento(rs.getTimestamp("age_dthr"), rs.getInt("age_cod"), dp.get(rs.getString("pac_cod"))));
                }
                }
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
    
    public boolean salvarAP(Atendimento a)
    {
        String sql = "INSERT INTO agendamento(age_dthr, pac_cpf) "
                + "VALUES ('#1','#2'); ";

        sql = sql.replaceAll("#1", "" + a.getHorario());
        sql = sql.replaceAll("#2", "" + a.getPaciente().getCpf());
        
        return Banco.getCon().manipular(sql);
    }

    public boolean apagar(Atendimento ag) 
    {
        return Banco.getCon().manipular("DELETE FROM agendamento WHERE age_cod=" + ag.getCodigo());
    }
    
    public String salvarAtendimento(Atendimento ate) throws SQLException
    {
        Banco.getCon().getConnect().setAutoCommit(false);
                
        String sql;
        try 
        {
            for (Material item : ate.getItens())
            {
                int cont = 0;
                sql = "SELECT * FROM itensatendimento WHERE age_cod="+ate.getCodigo()+" AND "
                + "mat_cod="+item.getId();
                ResultSet rs = Banco.getCon().consultar(sql);
                
                if(rs.next())
                    cont++;
                
                if(cont == 0)
                {
                    sql = "INSERT INTO itensatendimento(age_cod, mat_cod, mat_qtde) "
                    + "VALUES (#1,#2,#3); ";

                    sql = sql.replaceAll("#1", "" + ate.getCodigo());
                    sql = sql.replaceAll("#2", "" + item.getId());
                    sql = sql.replaceAll("#3", "" + item.getQtde());

                    Banco.getCon().manipular(sql);
                }
                else
                {
                    sql = "UPDATE itensatendimento SET mat_qtde="+item.getQtde()+" WHERE age_cod="+ate.getCodigo()+" AND "
                    + "mat_cod="+item.getId();
                    
                    Banco.getCon().manipular(sql);
                }
            }   
        }
        catch (Exception e)
        {
            return "Erro ao Gravar os Itens!";
        }
        try 
        {
            sql = "UPDATE agendamento SET fun_login='#1',age_observacoes='#2', age_dentes='#3'"
                + " WHERE age_cod=" + ate.getCodigo();
            
            sql = sql.replaceAll("#1", "" + ate.getDentista().getLogin());
            sql = sql.replaceAll("#2", "" + ate.getObservacoes());
            sql = sql.replaceAll("#3", "" + ate.getDentes());
            
            Banco.getCon().manipular(sql);
        } 
        catch (Exception e) 
        {
            return "Erro ao Registrar Atendimento!";
        }
        
        Banco.getCon().getConnect().commit();
        return "";
    }

    
}
