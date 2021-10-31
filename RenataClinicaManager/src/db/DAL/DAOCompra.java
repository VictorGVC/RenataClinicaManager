/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.Compra;
import db.Models.Conta;
import db.Models.Material;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicga
 */
public class DAOCompra {

    public List<Compra> getList(String filtro) 
    {
        String sql = "SELECT * FROM compra c "+filtro;
        
        List <Compra> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        DAOFornecedor df = new DAOFornecedor();
        
        try {
            while(rs.next())
            {                                 
                aux.add(new Compra(rs.getInt("com_cod"), rs.getDouble("com_valor"), 
                        rs.getDate("com_data").toLocalDate(), df.get(rs.getString("for_cnpj")), getItens(rs.getInt("com_cod"))));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }
    
    public List<Material> getItens(int codc)
    {
        String sql = "SELECT * FROM itenscompra WHERE com_cod = "+codc;
        
        List <Material> aux = new ArrayList();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        DAOMaterial dm = new DAOMaterial();
        
        try {
            while(rs.next())
            {                                 
                aux.add(new Material(rs.getInt("mat_qtde"), rs.getDouble("mat_preco"), 
                        dm.get(rs.getInt("mat_cod")).getNome()));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }

    public boolean apagar(int id) 
    {
        return Banco.getCon().manipular("DELETE FROM compra WHERE com_cod=" + id);
    }
    
    public String gravar(Compra c) throws SQLException
    {
        Banco.getCon().getConnect().setAutoCommit(false);
        int i = 0;
        String sql = "INSERT INTO compra (com_valor, com_data, for_cnpj)"
                + "VALUES(#1,'#2', '#3')";
        
        sql = sql.replaceAll("#1", "" + c.getTotal());
        sql = sql.replaceAll("#2", "" + LocalDate.now());
        sql = sql.replaceAll("#3", "" + c.getFornecedor().getCnpj());
        
        if(!Banco.getCon().manipular(sql))
        {
            Banco.getCon().getConnect().rollback();
            return "Problemas ao gravar a compra!";
        }
        
        int id = Banco.getCon().getMaxPK("compra", "com_cod");
        DAOMaterial dm = new DAOMaterial();
        try 
        {
            Material aux;
            for (Material produto : c.getProdutos()) 
            {
                aux = dm.get(" UPPER(mat_nome) LIKE '%"+produto.getNome().toUpperCase()+"%'");
                int mid;
                if(aux != null)
                {
                    sql = "UPDATE material SET mat_estoque="+(aux.getQtde()+produto.getQtde())+
                            " WHERE mat_cod="+aux.getId()+";\n";
                    Banco.getCon().manipular(sql);
                    mid = aux.getId();
                }
                else
                {
                    sql = "INSERT INTO material (mat_nome, mat_estoque) "
                            + "VALUES('"+produto.getNome()+"',"+produto.getQtde()+")";
                    Banco.getCon().manipular(sql);
                    mid = Banco.getCon().getMaxPK("material", "mat_cod");
                }
                sql = "INSERT INTO itenscompra (com_cod,mat_cod,mat_preco,mat_qtde) "
                            + "VALUES("+id+","+mid+","+produto.getValor()+","+produto.getQtde()+")";
                    Banco.getCon().manipular(sql);
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e);
            Banco.getCon().getConnect().rollback();
            return "Erro ao gravar itens da compra!";
        }
        
        try
        {
            for (Conta parcela : c.getParcelas()) 
            {
                sql = "INSERT INTO contaspagar (pag_parcela, pag_dtvencimento, "
                        + "pag_valor, pag_tipo, for_cnpj, com_cod) VALUES(#1,'#2',#3,'#4','#5',#6)";

                sql = sql.replaceAll("#1", "" + (++i));
                sql = sql.replaceAll("#2", "" + parcela.getDtvencimento());
                sql = sql.replaceAll("#3", "" + parcela.getValor());
                sql = sql.replaceAll("#4", "Compra");
                sql = sql.replaceAll("#5", "" + c.getFornecedor().getCnpj());
                sql = sql.replaceAll("#6", "" + id);

                Banco.getCon().manipular(sql);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            Banco.getCon().getConnect().rollback();
            return "Erro ao gravar parcelas!";
        }
        
        Banco.getCon().getConnect().commit();
        return "";
    }
    
}
