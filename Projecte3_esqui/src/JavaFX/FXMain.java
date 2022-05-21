
package JavaFX;

import Clients.Client;
import Connexio.Connexio_BD;
import Cursos.Colectiu;
import Cursos.Curs;
import java.sql.*;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class FXMain extends Application{
    
    TextField txtDni;
    TextField txtNom;
    TextField txtCognom;
    TextField txtUsuari;
    
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Curs> cursos = new ArrayList<>();

    Connexio_BD conn = new Connexio_BD();
    
    Connection c = conn.getCon();
    
    public FXMain(){
        this.clients = new ArrayList();
        this.cursos = new ArrayList();
    }
    
    public static void main (String[] args){
        launch();
    }
    
    @Override
    public void start(Stage escenari) throws Exception{

        BorderPane bp = new BorderPane();
        
        bp.setTop(partSuperior());
        bp.setBottom(partInferior());
        bp.setLeft(lateralEsquerra());
        bp.setCenter(formulariCentral());
        bp.setRight(lateralDret());
        
        BorderPane.setMargin(partInferior(), new Insets(20,20,20,20));
        BorderPane.setMargin(partSuperior(), new Insets(20,20,20,20));
        BorderPane.setMargin(lateralEsquerra(), new Insets(20,20,20,20));
        BorderPane.setMargin(formulariCentral(), new Insets(20,20,20,20));
        BorderPane.setMargin(lateralDret(), new Insets(20,20,20,20));
        
        Scene escena = new Scene(bp);
                
        escenari.setScene(escena);
        escenari.setMinHeight(600);
        escenari.setMinWidth(800);
        
        escenari.setTitle("Llogar Cursos d'Esqui");
        
        escenari.show();
        
    }
    
    private Pane partSuperior(){
        HBox hinferior = new HBox();
        hinferior.getChildren().addAll(new Label("Barra informació inferior"));

        hinferior.setAlignment(Pos.CENTER);
        
        return hinferior;
    }
    
    private Pane partInferior(){
        Button btn1 = new Button("Boto 1");
        Button btn2 = new Button("Boto 2");
        Button btn3 = new Button("Boto 3");
        HBox hinferior = new HBox();
        hinferior.getChildren().addAll(btn1, btn2, btn3, new Label("Barra informació inferior"));

        hinferior.setAlignment(Pos.CENTER);
        
        return hinferior;
    }
    
    private Pane lateralDret(){
        VBox vb = new VBox();
        Label lblDret = new Label("CURSOS");
        
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        
        TabPane tp = new TabPane();
        
        Tab tab1 = new Tab("Individual", cursosIndividual());
        Tab tab2 = new Tab("Colectius", cursosColectius());
        Tab tab3 = new Tab("Competicio", cursosCompeticio());
        
        tp.getTabs().add(tab1);
        tp.getTabs().add(tab2);
        tp.getTabs().add(tab3);
        
        vb.getChildren().addAll(lblDret, tp);
        
        return vb;
    }
    
    private Pane cursosColectius(){
        
        VBox vb = new VBox();
        
        TableView<Colectiu> tbtCursos = new TableView<>();
        TableColumn<Colectiu, Integer> max_clients = new TableColumn<>("max_clients");
        TableColumn<Colectiu, Integer> preu = new TableColumn<>("preu");
        tbtCursos.getColumns().addAll(max_clients,preu);
        
        max_clients.setCellValueFactory(new PropertyValueFactory<>("max_clients"));
        preu.setCellValueFactory(new PropertyValueFactory<>("preu"));
        
        Colectiu c1 = new Colectiu(25, 120);
        Colectiu c2 = new Colectiu(40, 40);
        
        
        tbtCursos.getItems().addAll(c1,c2);
        
        vb.getChildren().add(tbtCursos);
        
        tbtCursos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
                Colectiu col = (Colectiu) newValue;
                
                if(col !=null){
                    max_clients.setText(String.valueOf(col.getMax_clients()));
                    preu.setText(String.valueOf(col.getPreu()));
                }
            }
        });
        
        return vb;
    }
    
    private Pane cursosCompeticio(){
        
        return null;
        
    }
    
    private Pane cursosIndividual(){
        
        return null;
        
    }
    
    
    private Pane lateralEsquerra() throws SQLException{
        
        VBox vlateral = new VBox();
        vlateral.getChildren().add(new Label("CLIENTS"));
        vlateral.setAlignment(Pos.CENTER);
        
        TableView<Client> tblClients = new TableView<>();
        TableColumn<Client, String> colDni = new TableColumn<>("DNI");
        TableColumn<Client, String> colNom = new TableColumn<>("Nom");
        TableColumn<Client, String> colCognom = new TableColumn<>("Cognom");
        TableColumn<Client, String> colUsuari = new TableColumn<>("Usuari");
        tblClients.getColumns().addAll(colDni,colNom,colCognom,colUsuari);
        
        colDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colCognom.setCellValueFactory(new PropertyValueFactory<>("Cognom"));
        colUsuari.setCellValueFactory(new PropertyValueFactory<>("Usuari"));
        
        Client c = new Client();
        
        clients = c.LlistaClient();
        for(Client cl : clients){
            tblClients.getItems().addAll(cl);
        }
        
        vlateral.getChildren().add(tblClients);
        
        tblClients.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
                Client client = (Client) newValue;
                
                if(client !=null){
                    txtDni.setText(client.getDni());
                    txtNom.setText(client.getNom());
                    txtCognom.setText(client.getCognom());
                    txtUsuari.setText(client.getUsuari());
                }
            }
        });
        
        return vlateral;
    }
    
    private GridPane formulariCentral(){
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        
        Label lblDni = new Label("Dni");
        Label lblNom = new Label("Nom");
        Label lblCognom = new Label("Cognom");
        Label lblUsuari = new Label("Usuari");

        
        txtDni = new TextField();
        txtNom = new TextField();
        txtCognom = new TextField();
        txtUsuari = new TextField();
        
        
        gp.add(lblDni, 0, 0);
        gp.add(txtDni, 1, 0);
        gp.add(lblNom, 0, 1);
        gp.add(txtNom, 1, 1);
        gp.add(lblCognom, 0, 2);
        gp.add(txtCognom, 1, 2);
        gp.add(lblUsuari, 0, 3);
        gp.add(txtUsuari, 1, 3);

        return gp;
    }
}
