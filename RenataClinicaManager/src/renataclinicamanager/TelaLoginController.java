/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import db.DAL.DAOFuncionario;
import db.Models.Funcionario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import util.MaskFieldUtil;
import util.Util;

/**
 * FXML Controller class
 *
 * @author vicga
 */
public class TelaLoginController implements Initializable 
{
    public static Funcionario sessao;
    
    @FXML
    private VBox painel;
    @FXML
    private Pane pndadoslogin;
    @FXML
    private JFXTextField txusuario;
    @FXML
    private JFXPasswordField txsenha;
    @FXML
    private JFXButton btlogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setMascaras();
        sessao = new Funcionario();
        teclaEnter();
    }

    private void setMascaras() 
    {
        MaskFieldUtil.maxField(txusuario, 15);
        MaskFieldUtil.maxField(txsenha, 15);
    }
    
    private void chamaPrincipal() throws IOException 
    {    
        TelaPrincipalController.setSessao(sessao);
        Parent root = FXMLLoader.load(getClass().getResource("TelaPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        //stage.setResizable(false);
        stage.setMaximized(true);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/logo32.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.setTitle("Renata Clinica Manager");
        
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            
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
    
    private void teclaEnter()
    {
        txusuario.setOnKeyPressed(k ->
        {
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if(ENTER.match(k)) {
                txsenha.requestFocus();
            }
        });
        txsenha.setOnKeyPressed(k ->
        {
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if(ENTER.match(k)) {
                clkBtLogin(null);
            }
        });
    }
    
    private void miniAlert(String txt)
    {
        JFXSnackbar sb = new JFXSnackbar(pndadoslogin); 
        Label l = new Label();

        l.setText(txt);
        l.setPrefSize(170, 10);
        l.setStyle("-fx-background-color: red;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 5; -fx-border-radius: 5; "
                + "-fx-alignment: center;");
        sb.enqueue(new JFXSnackbar.SnackbarEvent(l));
    }

    @FXML
    private void clkBtLogin(ActionEvent event) 
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        DAOFuncionario dal = new DAOFuncionario();
        
        if(txusuario.getText().isEmpty())
        {
            a.setContentText("Usuário deve ser informado");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txusuario.requestFocus();
        }
        else if(txsenha.getText().isEmpty())
        {
            a.setContentText("Senha deve ser informada");
            a.setHeaderText("Alerta");
            a.setTitle("Alerta");
            a.showAndWait();
            txsenha.requestFocus();
        }
        else
        {
            if(dal.valida(txusuario.getText(), Util.criptoSenha(txsenha.getText())))
            {
                Funcionario f = dal.getF(txusuario.getText());
                sessao = f;
                Stage stage = (Stage) btlogin.getScene().getWindow();
                stage.close();
                try 
                {
                    chamaPrincipal();
                } 
                catch (IOException ex) 
                {
                    System.out.println(ex);
                }
            }
            else
            {
                miniAlert("Login e/ou senha invalido(s)!");
            }
        }
    }

    @FXML
    private void clkFecharLogin(ActionEvent event) 
    {
        Stage stage = (Stage) txusuario.getScene().getWindow();
        stage.close();
    }
    
}
