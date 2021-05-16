/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renataclinicamanager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author vicga
 */
public class TelaPrincipalController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane pntotal;
    @FXML
    private VBox pncabecalho;
    @FXML
    private MenuBar mnbar;
    @FXML
    private ToolBar tbatalhos;
    @FXML
    private AnchorPane pnmeio;
    @FXML
    private HBox pnrodape;
    @FXML
    private Label lbnome;
    @FXML
    private Label lbrua;
    @FXML
    private Label lbcep;
    @FXML
    private TableView<?> tvprodutos;
    @FXML
    private TableColumn<?, ?> colprod;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //pntotal.getScene().getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        
    }    

    @FXML
    private void clkChamaLogin(ActionEvent event) {
    }

    @FXML
    private void clkOpenFornecedores(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaFornecedor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(1030);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.setTitle("Fornecedores");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void clkBackup(ActionEvent event) 
    {
        
    }

    @FXML
    private void clkRestore(ActionEvent event) 
    {
        pntotal.getScene().getStylesheets().clear();
        pntotal.getScene().getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
    }

    @FXML
    private void clkConfiguracoes(ActionEvent event) {
    }

    @FXML
    private void clkTabelaProdutos(MouseEvent event) {
    }

    @FXML
    private void clkFuncionarios(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaFuncionario.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(1030);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.setTitle("Funcionários");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkPacientes(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaPaciente.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(1030);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.setTitle("Pacientes");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkOpenMateriais(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaMateriais.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(1030);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.setTitle("Materiais");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkCargos(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaCargos.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(555);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.setTitle("Cargos");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkOpenTratamento(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaTratamento.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(555);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.setTitle("Tratamentos");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkOpenFeriado(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaFeriado.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(555);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.setTitle("Feriados");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkOpenAtestado(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaAtestado.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(555);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.setTitle("Modelo de Atestado");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clkOpenReceituário(ActionEvent event) throws IOException 
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaReceituário.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setMaxWidth(555);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        scene.getStylesheets().add(getClass().getResource("/CSS/Dark.css").toExternalForm());
        stage.setTitle("Modelo de Receituário");
        stage.setScene(scene);
        stage.show();
    }
    
}
