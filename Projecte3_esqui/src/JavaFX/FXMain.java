
package JavaFX;

import Clients.Client;
import Connexio.Connexio_BD;
import Cursos.Colectiu;
import Cursos.Curs;
import Cursos.Federat;
import Cursos.Individual;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class FXMain extends Application{
    
    TextField txtDni;
    TextField txtNom;
    TextField txtCognom;
    TextField txtUsuari;
    
    TextField txtId;
    TextField txtNomC;
    TextField txtDesc;
    TextField txtData;
    TextField txtMon;
    TextField txtPreu;
    TextField txtPreuF;
    
    TextField txtNivell;
    TextField txtMaxCl;
    TextField txtDataFi;
    
    Label lblNivell;
    Label lblMaxCl;
    Label lblDataFi;
    
    GridPane gp1;
    GridPane gp2;
    GridPane gp3;
    GridPane gpH;
    
    GridPane gpN;
    GridPane gpM;
    
    ToggleGroup group;
    
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Individual> cursosInd = new ArrayList<>();
    ArrayList<Colectiu> cursosCol = new ArrayList<>();
    ArrayList<Federat> cursosComp = new ArrayList<>();
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
    
    Connexio_BD conn = new Connexio_BD();
    
    Connection c = conn.getCon();
    
    public static void main (String[] args){
        launch();
    }
    
    @Override
    public void start(Stage escenari) throws Exception{

        BorderPane bp = new BorderPane();
        
        bp.setBottom(partInferior());
        bp.setLeft(lateralEsquerra());
        bp.setCenter(formulariCentral());
        bp.setRight(lateralDret());
        
        BorderPane.setMargin(partInferior(), new Insets(20,20,20,20));
        BorderPane.setMargin(lateralEsquerra(), new Insets(20,20,20,20));
        BorderPane.setMargin(formulariCentral(), new Insets(20,20,20,20));
        BorderPane.setMargin(lateralDret(), new Insets(20,20,20,20));
        
        Scene escena = new Scene(bp);
                
        escenari.setScene(escena);
        escenari.setMinHeight(700);
        escenari.setMinWidth(1200);
        
        escenari.setTitle("Llogar Cursos d'Esqui");
        
        escenari.show();
        
    }
    
    private Pane partInferior(){
        Button btnLlogar = new Button("Llogar Curs");
        Button btnNetejar = new Button("Netejar");
        Button btnSortir = new Button("Sortir");
        HBox hinferior = new HBox();
        hinferior.getChildren().addAll(btnLlogar, btnNetejar, btnSortir);
        
        btnLlogar.setPadding(new Insets(10));
        btnNetejar.setPadding(new Insets(10));
        btnSortir.setPadding(new Insets(10));
        
        HBox.setMargin(btnLlogar, new Insets(10));
        HBox.setMargin(btnNetejar, new Insets(10));
        HBox.setMargin(btnSortir, new Insets(10));
        
        btnLlogar.setOnAction(e -> llogarCurs());
        btnNetejar.setOnAction(e -> netejarFormulari());
        btnSortir.setOnAction(e -> javafx.application.Platform.exit());
        
        hinferior.setAlignment(Pos.CENTER);
        
        return hinferior;
    }
    
    private Pane formulariCentral(){
        
        gp1 = new GridPane();
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        gp1.setAlignment(Pos.CENTER);
        
        Label lblClient = new Label("Client: ");
        lblClient.setFont(new Font(18));
        lblClient.setStyle("-fx-font-weight: bold");
        lblClient.setTextFill(Color.web("#728FCE"));
        lblClient.setPadding(new Insets(20, 20, 20, 20));
        
        
        Label lblDni = new Label("Dni:");
        Label lblNom = new Label("Nom:");
        Label lblCognom = new Label("Cognom:");
        Label lblUsuari = new Label("Usuari:");

        
        txtDni = new TextField();
        txtNom = new TextField();
        txtCognom = new TextField();
        txtUsuari = new TextField();
        
        gp1.add(lblClient, 0, 0);
        gp1.add(lblDni, 0, 1);
        gp1.add(txtDni, 1, 1);
        gp1.add(lblNom, 0, 2);
        gp1.add(txtNom, 1, 2);
        gp1.add(lblCognom, 0, 3);
        gp1.add(txtCognom, 1, 3);
        gp1.add(lblUsuari, 0, 4);
        gp1.add(txtUsuari, 1, 4);
        
        gp2 = new GridPane();
        gp2.setAlignment(Pos.CENTER);
        
        Label lblCurs = new Label("Curs: ");
        lblCurs.setFont(new Font(18));
        lblCurs.setStyle("-fx-font-weight: bold");
        lblCurs.setTextFill(Color.web("#728FCE"));
        lblCurs.setPadding(new Insets(20, 20, 20, 20));
        
        Label lblId = new Label("Id: ");
        Label lblNomC = new Label("Nom Curs:");
        Label lblDescipcio = new Label("Descripció:");
        Label lblData = new Label("Data:");
        Label lblMonitor = new Label("Monitor:");
        Label lblPreu = new Label("Preu: ");
        Label lblPreuF = new Label("Preu Final: ");
        
        //  Camps_lbl addicionals
        
        lblNivell = new Label("Nivell: ");
        lblMaxCl = new Label("Max_Clients: ");
        lblDataFi = new Label("Data_fi: ");
        
        txtId = new TextField();
        txtNomC = new TextField();
        txtDesc = new TextField();
        txtData = new TextField();
        txtMon = new TextField();
        txtPreu = new TextField();
        txtPreuF = new TextField();
        
        //  Capms_txt addicionals
        
        txtNivell = new TextField();
        txtMaxCl = new TextField();
        txtDataFi = new TextField();
        
        
        gp2.add(lblCurs, 0, 5);
        gp2.add(lblId, 0, 6);
        gp2.add(txtId, 1, 6);
        gp2.add(lblNomC, 0, 7);
        gp2.add(txtNomC, 1, 7);
        gp2.add(lblDescipcio, 0, 8);
        gp2.add(txtDesc, 1, 8);
        gp2.add(lblData, 0, 9);
        gp2.add(txtData, 1, 9);
        gp2.add(lblMonitor, 0, 10);
        gp2.add(txtMon, 1, 10);
        gp2.add(lblPreu, 0,11);
        gp2.add(txtPreu, 1, 11);
        
        gpH = new GridPane();
        gpH.setAlignment(Pos.CENTER);
        
        group = new ToggleGroup();
        
        Label lblH = new Label("Hores: ");
        lblH.setFont(new Font(18));
        lblH.setStyle("-fx-font-weight: bold");
        lblH.setTextFill(Color.web("#728FCE"));
        lblH.setPadding(new Insets(20, 20, 20, 20));
        
        RadioButton rbH1 = new RadioButton("1");
        rbH1.setToggleGroup(group);
        rbH1.setSelected(true);
        rbH1.setPadding(new Insets(20));

        RadioButton rbH2 = new RadioButton("2");
        rbH2.setToggleGroup(group);
        rbH2.setPadding(new Insets(20));
        
        RadioButton rbH3 = new RadioButton("3");
        rbH3.setToggleGroup(group);
        rbH3.setPadding(new Insets(20));
        
        gpH.add(lblH, 0, 1);
        gpH.add(rbH1,1,1);
        gpH.add(rbH2,2,1);
        gpH.add(rbH3,3,1);
        
        gp3 = new GridPane();
        gp3.setAlignment(Pos.CENTER);
        lblPreuF.setPadding(new Insets(20,20,20,20));
        
        gp3.add(lblPreuF, 0, 0);
        gp3.add(txtPreuF, 1, 0);
        
        gpM = new GridPane();
        gpM.setAlignment(Pos.CENTER);
        
        Label lblCursCol = new Label("Colectiu: ");
        lblCursCol.setFont(new Font(18));
        lblCursCol.setStyle("-fx-font-weight: bold");
        lblCursCol.setTextFill(Color.web("#728FCE"));
        lblCursCol.setPadding(new Insets(20, 20, 20, 20));
        
        gpM.add(lblCursCol,0,0);
        gpM.add(lblMaxCl,0,1);
        gpM.add(txtMaxCl,1,1);
        
        gpM.setVisible(false);
        
        gpN = new GridPane();
        gpN.setAlignment(Pos.CENTER);
        
        Label lblCursComp = new Label("Comp: ");
        lblCursComp.setFont(new Font(18));
        lblCursComp.setStyle("-fx-font-weight: bold");
        lblCursComp.setTextFill(Color.web("#728FCE"));
        lblCursComp.setPadding(new Insets(20, 20, 20, 20));
        
        gpN.add(lblCursComp,0,0);
        gpN.add(lblNivell,0,1);
        gpN.add(txtNivell,1,1);
        gpN.add(lblDataFi,0,2);
        gpN.add(txtDataFi,1,2);
        
        gpN.setVisible(false);
        
        
        vb.getChildren().addAll(gp1, gp2, gpH, gp3, gpM, gpN);
        return vb;
    }
    
    
    private Pane lateralEsquerra() throws SQLException{
        
        VBox vlateral = new VBox();
        vlateral.getChildren().add(new Label("CLIENTS"));
        vlateral.setAlignment(Pos.CENTER);
        vlateral.alignmentProperty();
        vlateral.setMaxWidth(400);
        
        TableView<Client> tblClients = new TableView<>();
        TableColumn<Client, String> colDni = new TableColumn<>("DNI");
        TableColumn<Client, String> colNom = new TableColumn<>("Nom");
        TableColumn<Client, String> colCognom = new TableColumn<>("Cognom");
        TableColumn<Client, String> colUsuari = new TableColumn<>("Usuari");
        tblClients.getColumns().addAll(colDni,colNom,colCognom,colUsuari);
        
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCognom.setCellValueFactory(new PropertyValueFactory<>("cognom"));
        colUsuari.setCellValueFactory(new PropertyValueFactory<>("usuari"));
        
        Client c = new Client();
        
        clients = c.LlistaClient();
        
        for(Client cl : clients){
            tblClients.getItems().add(cl);
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
   
    private Pane lateralDret() throws SQLException{
        VBox vb = new VBox();
        Label lblDret = new Label("CURSOS");
        vb.setMaxWidth(400);
        vb.setAlignment(Pos.CENTER);
        
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
    
    private Pane cursosIndividual() throws SQLException{
        
        VBox vb = new VBox();
        
        TableView<Individual> tbtCursosInd = new TableView<>();
                
        TableColumn<Individual, Integer> colId = new TableColumn<>("Id");
        TableColumn<Individual, String> colNom = new TableColumn<>("Nom");
        TableColumn<Individual, String> colDescripcio = new TableColumn<>("Descripcio");
        TableColumn<Individual, LocalDate> colData = new TableColumn<>("Data");
        TableColumn<Individual, String> colMonitor = new TableColumn<>("Monitor");
        TableColumn<Individual, Double> colPreu = new TableColumn<>("Preu");
        tbtCursosInd.getColumns().addAll(colId, colNom, colDescripcio, colData, colMonitor, colPreu);
        
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colDescripcio.setCellValueFactory(new PropertyValueFactory<>("Descripcio"));
        colData.setCellValueFactory(new PropertyValueFactory<>("Data"));
        colMonitor.setCellValueFactory(new PropertyValueFactory<>("Monitor"));
        colPreu.setCellValueFactory(new PropertyValueFactory<>("Preu"));

        cursosInd = LlistaCursInd();
        
        for(Individual ind1 : cursosInd){
            tbtCursosInd.getItems().addAll(ind1);
        }
        
        vb.getChildren().add(tbtCursosInd);
                
        tbtCursosInd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
                Individual ind = (Individual) newValue;
                String format_data = ind.getData().format(formatter);
                if(ind !=null){
                    txtId.setText(Integer.toString(ind.getId()));
                    txtNomC.setText(ind.getNom());
                    txtDesc.setText(ind.getDescripcio());
                    txtData.setText(format_data);
                    txtMon.setText(ind.getMonitor());
                    txtPreu.setText(Double.toString(ind.getPreu()));
                }
            }
        });
        
        return vb;
    }
    
    private Pane cursosColectius() throws SQLException{
        VBox vb = new VBox();
        
        TableView<Colectiu> tbtCursosCol = new TableView<>();
        TableColumn<Colectiu, Integer> colId = new TableColumn<>("Id");
        TableColumn<Colectiu, String> colNom = new TableColumn<>("Nom");
        TableColumn<Colectiu, String> colDescripcio = new TableColumn<>("Descripcio");
        TableColumn<Colectiu, LocalDate> colData = new TableColumn<>("Data");
        TableColumn<Colectiu, String> colMonitor = new TableColumn<>("Monitor");
        TableColumn<Colectiu, Integer> colMax_Clients = new TableColumn<>("Max_Clients");
        TableColumn<Colectiu, Double> colPreu = new TableColumn<>("Preu");
        tbtCursosCol.getColumns().addAll(colId, colNom, colDescripcio, colData, colMonitor, colMax_Clients, colPreu);
        
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colDescripcio.setCellValueFactory(new PropertyValueFactory<>("Descripcio"));
        colData.setCellValueFactory(new PropertyValueFactory<>("Data"));
        colMonitor.setCellValueFactory(new PropertyValueFactory<>("Monitor"));
        colMax_Clients.setCellValueFactory(new PropertyValueFactory<>("Max_clients"));
        colPreu.setCellValueFactory(new PropertyValueFactory<>("Preu"));

        cursosCol = LlistaCursCol();
        
        for(Colectiu col1 : cursosCol){
            tbtCursosCol.getItems().addAll(col1);
        }
        
        vb.getChildren().add(tbtCursosCol);
        
                
        tbtCursosCol.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
                Colectiu col = (Colectiu) newValue;
                String format_data = col.getData().format(formatter);
                if(col !=null){
                    gpM.setVisible(true);
                    gpN.setVisible(false);
                    
                    txtId.setText(Integer.toString(col.getId()));
                    txtNomC.setText(col.getNom());
                    txtDesc.setText(col.getDescripcio());
                    txtData.setText(format_data);
                    txtMon.setText(col.getMonitor());
                    txtMaxCl.setText(Integer.toString(col.getMax_clients()));
                    txtPreu.setText(Double.toString(col.getPreu()));
                }
            }
        });
        
        return vb;
    }
    
    private Pane cursosCompeticio() throws SQLException{
        VBox vb = new VBox();
        
        TableView<Federat> tbtCursosComp = new TableView<>();
        TableColumn<Federat, Integer> colId = new TableColumn<>("Id");
        TableColumn<Federat, String> colNom = new TableColumn<>("Nom");
        TableColumn<Federat, String> colDescripcio = new TableColumn<>("Descripcio");
        TableColumn<Federat, LocalDate> colData = new TableColumn<>("Data");
        TableColumn<Federat, String> colMonitor = new TableColumn<>("Monitor");
        TableColumn<Federat, Integer> colNivell = new TableColumn<>("Nivell");
        TableColumn<Federat, LocalDate> colData_Fi = new TableColumn<>("Data_fi");
        TableColumn<Federat, Double> colPreu = new TableColumn<>("Preu");
        tbtCursosComp.getColumns().addAll(colId, colNom, colDescripcio, colData, colMonitor, colNivell, colData_Fi, colPreu);
        
        colId.setCellValueFactory(new PropertyValueFactory("Id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colDescripcio.setCellValueFactory(new PropertyValueFactory<>("Descripcio"));
        colData.setCellValueFactory(new PropertyValueFactory<>("Data"));
        colMonitor.setCellValueFactory(new PropertyValueFactory<>("Monitor"));
        colNivell.setCellValueFactory(new PropertyValueFactory<>("Nivell"));
        colData_Fi.setCellValueFactory(new PropertyValueFactory<>("Data_fi"));
        colPreu.setCellValueFactory(new PropertyValueFactory<>("Preu"));

        cursosComp = LlistaCursComp();
        
        for(Federat comp1 : cursosComp){
            tbtCursosComp.getItems().addAll(comp1);
        }
        
        vb.getChildren().add(tbtCursosComp);
        
        tbtCursosComp.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
                Federat comp = (Federat) newValue;
                String format_data = comp.getData().format(formatter);
                String format_data_di = comp.getData_fi().format(formatter);
                if(comp !=null){
                    gpM.setVisible(false);
                    gpN.setVisible(true);
                    
                    txtId.setText(Integer.toString(comp.getId()));
                    txtNomC.setText(comp.getNom());
                    txtDesc.setText(comp.getDescripcio());
                    txtData.setText(format_data);
                    txtMon.setText(comp.getMonitor());
                    txtNivell.setText(Integer.toString(comp.getNivell()));
                    txtDataFi.setText(format_data_di);
                }
            }
        });
        
        return vb;
    }
    
    public void llogarCurs(){
        Connexio_BD connexio = new Connexio_BD();
        Connection con = connexio.getCon();
        
        String consulta = "INSERT INTO lloga_curs_individual (`id`, `id_curs`, `dni`) VALUES (1,?,?)";
        
        PreparedStatement ps = null;
        
        try{
            ps = con.prepareStatement(consulta);
            ps.setInt(1, Integer.parseInt(txtId.getText()));
            ps.setString(2, txtDni.getText());
            ps.executeUpdate();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            if(ps != null)
            try{
                ps.close();
            }catch(SQLException sqle){
                sqle.printStackTrace();
            }
        }
    }
    
    private void netejarFormulari(){
        txtDni.setText(" ");
        txtNom.setText(" ");
        txtCognom.setText(" ");
        txtUsuari.setText(" ");

        txtNomC.setText(" ");
        txtDesc.setText(" ");
        txtData.setText(" ");
        txtMon.setText(" ");
        txtPreu.setText(" ");

        txtMaxCl.setText(" ");
        txtNivell.setText(" ");
        txtDataFi.setText(" ");
        
        gpM.setVisible(false);
        gpN.setVisible(false);
    }

    public ArrayList <Individual> LlistaCursInd() throws SQLException{
        ArrayList <Individual> cursosInd = new ArrayList<>();
        
        Connexio_BD connexio = new Connexio_BD();
        Connection con = connexio.getCon();
        
        String consulta = "SELECT C.id, C.nom, C.descripcio, C.data, C.monitor, I.preu FROM curs C, individual I WHERE C.id=I.id";
        PreparedStatement ps = con.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Individual ind = new Individual(rs.getInt("id"),rs.getString("nom"), rs.getString("descripcio"),rs.getDate("data").toLocalDate(), rs.getString("monitor"),rs.getDouble("preu"));
            cursosInd.add(ind);
        }
        
        return cursosInd;
    }
    
    public ArrayList <Colectiu> LlistaCursCol() throws SQLException{
        ArrayList <Colectiu> cursosCol = new ArrayList<>();
        
        Connexio_BD connexio = new Connexio_BD();
        Connection con = connexio.getCon();
        
        String consulta = "SELECT C.id, C.nom, C.descripcio, C.data, C.monitor, COL.max_clients, COL.preu FROM curs C, colectiu COL WHERE C.id=COL.id";
        PreparedStatement ps = con.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Colectiu col = new Colectiu(rs.getInt("id"), rs.getString("nom"), rs.getString("descripcio"),rs.getDate("data").toLocalDate(), rs.getString("monitor"), rs.getInt("max_clients"),rs.getDouble("preu"));
            cursosCol.add(col);
        }
        return cursosCol;
        
    }
    
    public ArrayList <Federat> LlistaCursComp() throws SQLException{
        ArrayList <Federat> cursosComp = new ArrayList<>();
        
        Connexio_BD connexio = new Connexio_BD();
        Connection con = connexio.getCon();
        
        String consulta = "SELECT C.id, C.nom, C.descripcio, C.data, C.monitor, COM.nivell, COM.data_fi, COM.preu FROM curs C, competicio COM WHERE C.id=COM.id";
        PreparedStatement ps = con.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Federat comp = new Federat(rs.getInt("id"), rs.getString("nom"), rs.getString("descripcio"),rs.getDate("data").toLocalDate(),rs.getString("monitor"),rs.getInt("nivell"),rs.getDate("data_fi").toLocalDate(),rs.getDouble("preu"));
            cursosComp.add(comp);
        }
        return cursosComp;
    }
}
