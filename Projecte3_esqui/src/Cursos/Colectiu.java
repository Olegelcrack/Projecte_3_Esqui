
package Cursos;

public class Colectiu extends Curs{
    
    private int max_clients;
    private int preu;

    public int getMax_clients() {
        return max_clients;
    }

    public int getPreu() {
        return preu;
    }

    public Colectiu(int max_clients, int preu) {
        this.max_clients = max_clients;
        this.preu = preu;
    }
    
}
