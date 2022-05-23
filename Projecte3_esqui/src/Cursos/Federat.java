
package Cursos;

import java.time.LocalDate;

public class Federat extends Curs{
    
    private int nivell;
    private LocalDate data_fi;
    private double preu;

    public Federat(int id, String nom, String descripcio, LocalDate data, String monitor, int nivell, LocalDate data_fi, double preu) {
        super(id, nom, descripcio, data, monitor);
        this.nivell = nivell;
        this.data_fi = data_fi;
        this.preu = preu;
    }

    public int getNivell() {
        return nivell;
    }

    public LocalDate getData_fi() {
        return data_fi;
    }

    public double getPreu() {
        return preu;
    }

    @Override
    public String toString() {
        return "Federat{" + "nivell=" + nivell + ", data_fi=" + data_fi + ", preu=" + preu + '}';
    }
    
}