/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import db.Banco.Banco;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author vicga
 */
public class RenataClinicaManager extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
        
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent t)
            {
                t.consume();
                stage.close();
                Platform.exit();
                System.exit(0);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        boolean b = true;
        Banco.conectar();
        
        if(!Banco.getCon().getEstadoConexao()) {
            
            b = false;
            JOptionPane.showMessageDialog(null, "Erro: " + Banco.getCon().getMensagemErro());  
            if(JOptionPane.showConfirmDialog(null, "Deseja Criar Uma Nova Conexão?") == JOptionPane.YES_OPTION) {
                
                if(!Banco.criarBD("bancorenata"))
                    JOptionPane.showMessageDialog(null, "Não Foi Possivel Criar Uma Nova Conexão");
                else {
                    
                    JOptionPane.showMessageDialog(null, "Conexão Criada Com Sucesso, o Sistema Será Reiniciado");
                    Banco.realizaBackupRestauracao("banco\\backup.bat");
                    b = true;
                }
            }
        }
        if(b)
            launch(args);
        else
            System.exit(0);
    } 
    
}
