public class Automata {
    private Nodo nodoInicial, nodoFinal;

    public Automata(String regex){
        nodoInicial = new Nodo();
        nodoFinal = new Nodo();
        nodoInicial.addNode(nodoFinal);
        nodoInicial.addTrans(regex);
    }
}
