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
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author vicga
 */
public class RenataClinicaManager extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TelaPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.resizableProperty().setValue(false);     
        stage.setTitle("Renata Clinica Manager");
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
    public static void main(String[] args) 
    {
        Banco.conectar();
        if(Banco.getCon().getEstadoConexao())
            launch(args);
        else
        {
            JOptionPane.showMessageDialog(null, "Erro: " + Banco.getCon().getMensagemErro());
            
            if(JOptionPane.showConfirmDialog(null, "Deseja criar uma nova conexão?") == JOptionPane.YES_OPTION)
            {
                if(!Banco.criarBD("bancorenata"))
                    JOptionPane.showMessageDialog(null, "Não foi possivel criar uma nova conexão");
                else{
                    
                    JOptionPane.showMessageDialog(null, "Conexão criada com sucesso, o sistema será reiniciado");
                    
                    Banco.realizaBackupRestauracao("banco\\backup.bat");
                }
            }
            System.exit(0);
        }
    }
    
}
