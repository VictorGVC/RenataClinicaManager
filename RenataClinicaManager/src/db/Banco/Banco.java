package db.Banco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Banco {
    
    private static Banco banco;
    private static Conexao con = null;

    public static Banco getBanco() {
        
        if(banco == null)
            banco = new Banco();
        return banco;
    }
    
    private Banco() {}
    
    public static Conexao getCon() {
        return con;
    }
    
    public static void conectar() {
        
        con = Conexao.getConexao();   
    }
    
    public static void realizaBackupRestauracao(String arqlote) {
        
        String linha = "";  
        String aux = "";
        Runtime r = Runtime.getRuntime(); 
        
        try {
            
            Process p = r.exec(arqlote);
            
            if (p != null) {
                
                InputStreamReader str = new InputStreamReader(p.getErrorStream());  
                BufferedReader reader = new BufferedReader(str);  
              
                while ((aux = reader.readLine()) != null) 
                    linha += aux + "\n";  
           }  
           JOptionPane.showMessageDialog(null,"Backup/Restore realizado com sucesso!\n" + linha);  
       } 
       catch (IOException ex) {  
           JOptionPane.showMessageDialog(null,"Erro no backup!\n"+ex.getMessage());  
       }  
    }

    public static boolean criarTabelas(String script, String BD){
        
        try {
            
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/" + BD;
            Connection con = DriverManager.getConnection(url, "postgres", "postgres123");
            Statement statement = con.createStatement();
            RandomAccessFile arq = new RandomAccessFile(script,"r");
            
            while(arq.getFilePointer() < arq.length()) 
                 statement.addBatch(arq.readLine());
            statement.executeBatch();
            statement.close();
            con.close();
        }
        catch(IOException | ClassNotFoundException | SQLException e) {  

            System.out.println(e.getMessage()); 
            return false;
        }

        return true;
    }

    public static boolean criarBD(String BD){
        
        try {
            
            String url = "jdbc:postgresql://localhost/";
            Connection con = DriverManager.getConnection(url, "postgres", "postgres123");
            Statement statement = con.createStatement();
            
            statement.execute("CREATE DATABASE " + BD + " WITH OWNER = postgres ENCODING = 'UTF8' "
                                    + "TABLESPACE = pg_default LC_COLLATE = 'Portuguese_Brazil.1252' "
                                    + "LC_CTYPE = 'Portuguese_Brazil.1252'  CONNECTION LIMIT = -1;");
            statement.close();
            con.close();
        }
        catch(SQLException e) {
            
            System.out.println(e.getMessage()); 
            return false;
        }
        
        return true;                
    }
}