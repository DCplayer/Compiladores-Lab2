import java.util.ArrayList;

public class Nodo {
    private int Id;
    private ArrayList<String> transiciones = new ArrayList<String>();
    private ArrayList<Nodo> nodos = new ArrayList<Nodo>();

    public Nodo(){}

    public ArrayList<String> getTransiciones() {
        return transiciones;
    }

    public ArrayList<Nodo> getNodos() {
        return nodos;
    }


    public void addNode(Nodo n){
        nodos.add(n);
    }

    public void addTrans(String t){
        transiciones.add(t);
    }

    public void add(String t, Nodo n){
        addNode(n);
        addTrans(t);
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
