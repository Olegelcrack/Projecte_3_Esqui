
package Cursos;

import java.time.LocalDate;

public class Individual extends Curs{
    
    private double preu;
    
    public Individual(int id,String nom, String descripcio, LocalDate data, String monitor,double preu){
        super(id,nom,descripcio,data,monitor);
        this.preu=preu;
    }

    public double getPreu() {
        return preu;
    }

    @Override
    public String toString() {
        return "Individual{" + "preu=" + preu + '}';
    }
    
    
}
