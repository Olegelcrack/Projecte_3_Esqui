
package Connexio;

import java.sql.*;

public class Connexio_BD {
    
    private boolean connectat;
    private String user="root";
    private String pasw="";
    private String server="jdbc:mysql://localhost:3306/";
    private String bd="bd_esqui";
    private Connection con;

    public Connection getCon() {
        return con;
    }

    public Connexio_BD(boolean connectat, Connection con) {
        this.connectat = connectat;
        this.con = con;
    }
    
    public void conectar(){
        this.connectat=true;
    }
    
    public Connexio_BD(){
        try{
            con = DriverManager.getConnection(server + bd, user, pasw);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void Desconexio(){
        connectat=false;
    }
}
