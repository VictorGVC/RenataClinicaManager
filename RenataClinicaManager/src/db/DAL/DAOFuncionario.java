/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author vicga
 */
public class DAOFuncionario 
{
    public boolean ativar(Funcionario f) 
    {      
        return Banco.getCon().manipular("UPDATE Funcionario SET fun_ativo = 'S' WHERE fun_login='" + f.getLogin()+"'");
    }
    
    public String desativar(Funcionario f) 
    {        
        if(f.getC().getCod() == 1)
        {    
            ResultSet rs = Banco.getCon().consultar("SELECT * FROM funcionario "
                + "WHERE fun_login !='" + f.getLogin()+"' AND fun_ativo = 'S' AND car_cod = 1");
            int cont = 0;
            
            try{
                if(rs.next())
                    cont++;
            } 
            catch(SQLException ex) {
                System.out.println(ex);
            }
            if(cont > 0){
                if(Banco.getCon().manipular("UPDATE Funcionario SET fun_ativo = 'N' WHERE fun_login='" + f.getLogin()+"'"))
                    return "";
            }
            else 
                return "Impossível desativar o único Administrador ativo";
        }
        else if(Banco.getCon().manipular("UPDATE Funcionario SET fun_ativo = 'N' WHERE fun_login='" + f.getLogin()+"'"))
            return "";
        
        return "Erro ao desativar";
    }

    public String gravar(Funcionario f, String senha) 
    {
        int cont = 0;
        String sql;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Funcionario "
            + "WHERE fun_login = '"+f.getLogin()+"'");

        try{
            if(rs.next())
                cont++;
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }

        if(cont == 0){

            sql = "INSERT INTO Funcionario(fun_login, fun_senha, fun_sexo, fun_nome, fun_telefone, "
                    + "fun_horarios, fun_dtnasc, fun_ativo,car_cod) "
                + "VALUES ('#1','#2','#3','#4','#5','#6','#7','S',#8); ";

            sql = sql.replaceAll("#1", "" + f.getLogin());
            sql = sql.replaceAll("#2", "" + senha);
            sql = sql.replaceAll("#3", "" + f.getSexo());
            sql = sql.replaceAll("#4", "" + f.getNome());
            sql = sql.replaceAll("#5", "" + f.getTelefone());
            sql = sql.replaceAll("#6", "" + f.getHorarios().toString());
            sql = sql.replaceAll("#7", "" + f.getDtnasc().toString());
            sql = sql.replaceAll("#8", "" + f.getC().getCod());
            
            if(Banco.getCon().manipular(sql))
                return "";
            else
                return "Erro ao gravar!";
        }
        else
            return "Login já cadastrado, tente outro";
    }

    public String alterar(Funcionario f, String senhan, String senhaa,String logina) 
    {
        int cont = 0;
        if(valida(logina,senhaa))
        {
            if(!logina.equals(f.getLogin()))
            {
                ResultSet rs = Banco.getCon().consultar("SELECT * FROM Funcionario "
                + "WHERE fun_login ='" + f.getLogin()+"'");
                cont = 0;

                try{
                    if(rs.next())
                        cont++;
                } 
                catch(SQLException ex) {
                    System.out.println(ex);
                }
            }
            if(cont == 0)
            {
                String sql;
                if(!senhan.isEmpty())
                    sql = "UPDATE Funcionario SET fun_login='#1', fun_senha= '#2', fun_nome='#3', fun_telefone = '#4', "
                            + "fun_horarios='#5', fun_dtnasc='#6', car_cod=#7, fun_sexo='#8' "
                        + "WHERE fun_login = '" + logina + "'; ";
                else
                    sql = "UPDATE Funcionario SET fun_login='#1', fun_nome='#3', fun_telefone = '#4', "
                            + "fun_horarios='#5', fun_dtnasc='#6', car_cod=#7, fun_sexo='#8' "
                        + "WHERE fun_login = '" + logina + "'; ";

                sql = sql.replaceAll("#1", "" + f.getLogin());
                sql = sql.replaceAll("#2", "" + senhan);
                sql = sql.replaceAll("#3", "" + f.getNome());
                sql = sql.replaceAll("#4", f.getTelefone());
                sql = sql.replaceAll("#5", f.getHorarios().toString());
                sql = sql.replaceAll("#6", f.getDtnasc().toString());
                sql = sql.replaceAll("#7", ""+f.getC().getCod());
                sql = sql.replaceAll("#8", ""+f.getSexo());
                
                if(Banco.getCon().manipular(sql))
                    return "";
                else
                    return "Erro ao Alterar";
            }
            else return "Usuário já existente!";
        }
        else
            return "Usuário e senha inválidos!";
    }
    
    public boolean valida(String login, String senha) 
    {
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM funcionario "
                + "WHERE fun_login = '" + login + "' AND fun_senha = '"+ senha +"'");
        int cont = 0;
        
        try{
            if(rs.next())
                cont++;
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        return cont == 1;
    }
    
    public List<Funcionario> getList(String filtro) throws SQLException 
    {    
        String sql = "SELECT * FROM FUNCIONARIO";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
        List <Funcionario> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        DAOCargo dc = new DAOCargo();
        
        try 
        {
            LocalDate ld;
            while(rs.next())
            {
                try {
                    ld = rs.getDate("fun_dtnasc").toLocalDate();
                } catch (Exception e) {
                    ld = null;
                }
                aux.add(new Funcionario(dc.get(rs.getInt("car_cod")),rs.getString("fun_nome"), 
                        rs.getString("fun_login"),rs.getString("fun_telefone"), new JSONArray(rs.getString("fun_horarios")),
                        ld,rs.getString("fun_ativo").charAt(0), rs.getString("fun_sexo").charAt(0)));
            }
        }
        catch(java.sql.SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }

    public String apagar(Funcionario f) 
    {
        if(f.getC().getCod() == 1){
            
            ResultSet rs = Banco.getCon().consultar("SELECT * FROM Funcionario "
                + "WHERE fun_login !='" + f.getLogin() + "' AND fun_ativo = 'S' AND car_cod = 1");
            int cont = 0;
            
            try{
                if(rs.next())
                    cont++;
            } 
            catch(SQLException ex){
                System.out.println(ex);
            }
            
            if(cont > 0){
                if(Banco.getCon().manipular("DELETE FROM Funcionario WHERE fun_login='" + f.getLogin() + "'"))
                    return "";
            }   
            else 
                return "Impossível Apagar o único usuário administrativo ativo";
        }
        if(Banco.getCon().manipular("DELETE FROM Funcionario WHERE fun_login='" + f.getLogin() + "'"))
            return "";
        
        return "Erro ao Excluir";
    }
    
    public Funcionario getF(String login)
    {
        Funcionario aux = null;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM Funcionario WHERE fun_login='" +login+"'");
        
        DAOCargo dc = new DAOCargo();
        
        try{
            LocalDate ld;
            if(rs.next())
            {
                try {
                    ld = rs.getDate("fun_dtnasc").toLocalDate();
                } catch (Exception e) {
                    ld = null;
                }
                aux = new Funcionario(dc.get(rs.getInt("car_cod")),rs.getString("fun_nome"), 
                        rs.getString("fun_login"),rs.getString("fun_telefone"), new JSONArray(rs.getString("fun_horarios")),
                        ld,rs.getString("fun_ativo").charAt(0), rs.getString("fun_sexo").charAt(0));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }
}
