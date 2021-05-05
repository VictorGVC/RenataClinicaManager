package db.Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    
    private static Conexao connection;
    private static Connection connect;
    private static String erro;
    
    public static Conexao getConexao() {
    
        if(connection == null)
            connection = new Conexao();
        
        return connection;
    }
    
    private Conexao() {
        
        erro = "";
        conectar("jdbc:postgresql://localhost/", "bancorenata", "postgres", "postgres123");
    }
    
    public boolean getEstadoConexao() {
        return (connect != null);
    }
    
    public String getMensagemErro() {
        return erro;
    }
    
    private void conectar(String local, String banco, String usuario, String senha) {   
        
        try {
            
            String url = local + banco;
            
            connect = DriverManager.getConnection(url, usuario, senha);
        }
        catch (SQLException sqlex) { 
            erro = "Impossivel conectar com a base de dados: " + sqlex.toString(); 
        }
        catch (Exception ex) { 
            erro = "Outro erro: " + ex.toString(); 
        }
    }
    
    public boolean manipular(String sql) {  
        
        boolean executou = false;
        
        try {
            
            Statement statement = connect.createStatement();
            int result = statement.executeUpdate(sql);
            
            statement.close();
            if(result >= 1)
                executou = true;
        }
        catch (SQLException sqlex) {
            
            erro = "Erro: " + sqlex.toString();
            System.out.println(erro);
        }
        
        return executou;
    }
    
    public ResultSet consultar(String sql) {
        
        ResultSet rs = null;
        
        try {
            
            Statement statement = connect.createStatement();
            
            rs = statement.executeQuery(sql);
        }
        catch (SQLException sqlex) {
            
            erro = "Erro: " + sqlex.toString();
            System.out.println(erro);
            rs = null;
        }
        
        return rs;
    }
    
    public int getMaxPK(String tabela, String chave) {
        
        String sql = "SELECT MAX(" + chave + ") FROM " + tabela;
        int max = 0;
        ResultSet rs = consultar(sql);
        
        try {
            
            if(rs.next())
                max = rs.getInt(1);
        }
        catch (SQLException sqlex) {
            
             erro = "Erro: " + sqlex.toString();
             max = -1;
        }
        
        return max;
    }
    
    public Connection getConnect() {
        return connect;
    }
}