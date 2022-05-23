
package Cursos;

import java.time.LocalDate;

public class Curs {
    
    private int id;
    private String nom;
    private String descripcio;
    private LocalDate data;
    private String monitor;     

    public Curs(int id, String nom, String descripcio, LocalDate data, String monitor) {
        this.id = id;
        this.nom = nom;
        this.descripcio = descripcio;
        this.data = data;
        this.monitor = monitor;
    }

    public Curs() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public LocalDate getData() {
        return data;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }
    
}
