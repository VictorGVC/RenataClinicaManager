/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.DAL;

import db.Banco.Banco;
import db.Models.Compra;
import db.Models.Material;
import db.Models.Tratamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vicga
 */
public class DAOCompra {

    public List<Compra> getList(String filtro) 
    {
        String sql = "SELECT * FROM compra";
        
        if(!filtro.isEmpty())
            sql += " WHERE " + filtro;
        
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
                aux.add(new Material(rs.getInt("mat_qtde"), rs.getDouble("mat_valor"), 
                        dm.get(rs.getInt("mat_cod")).getNome()));
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
        }
        
        return aux;
    }
    
}
