
package Cursos;

import java.time.LocalDate;

public class Colectiu extends Curs{
    
    private int max_clients;
    private double preu;

    public Colectiu(int id, String nom, String descripcio, LocalDate data, String monitor, int max_clients, double preu) {
        super(id, nom, descripcio, data, monitor);
        this.max_clients = max_clients;
        this.preu = preu;
    }

    public int getMax_clients() {
        return max_clients;
    }

    public double getPreu() {
        return preu;
    }

    @Override
    public String toString() {
        return "Colectiu{" + "max_clients=" + max_clients + ", preu=" + preu + '}';
    }
    
}
