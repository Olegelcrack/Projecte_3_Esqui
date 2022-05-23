
package Clients;

import Connexio.Connexio_BD;
import java.util.ArrayList;
import java.sql.*;

public class Client {
    
    private String dni;
    private String nom;
    private String cognom;
    private String usuari;
    
    public Client(){
        
    }
    
    public Client(String dni, String nom, String cognom, String usuari) {
        this.dni = dni;
        this.nom = nom;
        this.cognom = cognom;
        this.usuari = usuari;
    }

    @Override
    public String toString() {
        return "Client{" + "dni=" + dni + ", nom=" + nom + ", cognom=" + cognom + ", usuari=" + usuari + '}';
    }

    public String getDni() {
        return dni;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public String getUsuari() {
        return usuari;
    }
    
    public ArrayList<Client> LlistaClient() throws SQLException{
        ArrayList <Client> clients = new ArrayList<>();
        
        Connexio_BD connexio = new Connexio_BD();
        Connection con = connexio.getCon();
        
        String consulta = "SELECT dni, nom, cognom, usuari FROM client";
        PreparedStatement ps = con.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Client c = new Client();
            c.atributsConsulta(rs);
            clients.add(c);
        }
        return clients;
    }
    
    public void atributsConsulta(ResultSet rs) throws SQLException{
        this.dni=rs.getString("dni");
        this.nom=rs.getString("nom");
        this.cognom=rs.getString("cognom");
        this.usuari=rs.getString("usuari");

    }
}
