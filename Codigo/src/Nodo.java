import java.util.ArrayList;

public class Nodo {
    private int Id;
    private ArrayList<String> transiciones;
    private ArrayList<Nodo> nodos;

    public Nodo(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void addNode(Nodo n){
        nodos.add(n);
    }

    public void addTrans(String t){
        transiciones.add(t);
    }
}
