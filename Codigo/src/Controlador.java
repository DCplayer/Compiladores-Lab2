import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;
/**
 * Created by Diego Castañeda on 28/07/2017.
 */
public class Controlador {



    /*Atributos para la funcion de Lector de Expresiones*/
    private NormalizadorDeRegex norm = new NormalizadorDeRegex();
    private String regex;
    private RelacionesDeAutomatas rel = new RelacionesDeAutomatas();
    private Stack<Automata> stack = new Stack<Automata>();

    /*Atributos para la funcion de AlgoritmoNumeraNodosyCreaTransiciones*/
    private boolean completo = true;
    private boolean paso = true;
    private int contador = 0;
    private ArrayList<Nodo> grafo = new ArrayList<Nodo>();
    private ArrayList<Nodo> grafo2 = new ArrayList<Nodo>();
    private ArrayList<Transicion> transiciones = new ArrayList<Transicion>();
    private ArrayList<Integer> ids = new ArrayList<Integer>();

    /*Atributos para la funcion de AlgoritmoImplantaSimbolos*/
    private boolean criterio = true;
    private ArrayList<String> simbolos = new ArrayList<String>();
    private ArrayList<Nodo> losNodosRepetidos = elContenedor.contenedor;
    private ArrayList<Nodo> losNodosSinRepeticion = new ArrayList<Nodo>();
    private int contable = 0;


    public Controlador(String regex) {
        this.regex = regex;
    }

    public Automata LectorDeExpresiones( ) {

        int ContadorDeID = 0;
        String RegexNormalizado = norm.PostFixYNormalizar(regex);
        int j = RegexNormalizado.length();
        for (int i = 0; i < j; i++) {
            String x = RegexNormalizado.substring(i, i + 1);

            if (x.equals(".")) {
                Automata b = stack.pop();
                Automata a = stack.pop();
                Automata concatencion = rel.and(a,b);

                /*Se toman todos los nodos del automata A menos el ultimo*/
                ArrayList<Nodo>  listazo = new ArrayList<Nodo>();
                int numeroDeElementos = a.getHistorial().size();
                for (int num = 1; i <numeroDeElementos; i++){
                    listazo.add(a.getHistorial().get(i -1));
                }
                listazo.addAll(b.getHistorial());
                concatencion.setHistorial(listazo);

                stack.push(concatencion);

            } else if (x.equals("|")) {
                Automata b = stack.pop();
                Automata a = stack.pop();
                Automata or = rel.or(a,b);

                /*Agregando todos los nodos del nuevo automata a su lista de nodos
                * asi como agregando identificacores a los nodos inicial y final del
                * nuevo automata*/
                ArrayList<Nodo> listazo = new ArrayList<Nodo>();
                or.getNodoInicial().setId(ContadorDeID);
                ContadorDeID =+1;
                or.getNodoFinal().setId(ContadorDeID);
                ContadorDeID =+ 1;
                listazo.add(or.getNodoInicial());
                listazo.addAll(a.getHistorial());
                listazo.addAll(b.getHistorial());
                listazo.add(or.getNodoFinal());
                or.setHistorial(listazo);

                stack.push(or);

            } else if (x.equals("+")) {
                Automata a = stack.pop();
                Automata kleene = rel.kleene(a);
                Automata kleeneSuma = rel.sum(a);

                ArrayList<Nodo>  listazo = new ArrayList<Nodo>();
                int numeroDeElementos = a.getHistorial().size();
                for (int num = 1; i <numeroDeElementos; i++){
                    listazo.add(a.getHistorial().get(i -1));
                }

                kleene.getNodoInicial().setId(ContadorDeID);
                ContadorDeID =+1;
                kleene.getNodoFinal().setId(ContadorDeID);
                ContadorDeID =+1;

                listazo.add(kleene.getNodoInicial());
                listazo.addAll(a.getHistorial());
                listazo.add(kleene.getNodoFinal());

                kleeneSuma.setHistorial(listazo);

                stack.push(kleeneSuma);

            } else if (x.equals("*")) {
                Automata a = stack.pop();
                Automata kleene = rel.kleene(a);

                /*Creando una lista de nodos para el Automata Kleene y
                * dando identificadores a los nodos inicial y final del automata*/
                ArrayList<Nodo> listazo = new ArrayList<Nodo>();
                kleene.getNodoInicial().setId(ContadorDeID);
                ContadorDeID =+1;
                kleene.getNodoFinal().setId(ContadorDeID);
                ContadorDeID =+1;
                listazo.add(kleene.getNodoInicial());
                listazo.addAll(a.getHistorial());
                listazo.add(kleene.getNodoFinal());
                kleene.setHistorial(listazo);

                stack.push(kleene);

            } else {
                Automata y = new Automata(x);

                /*Agregando los nodos a la lista de nodos del automata
                * y nombrandolos con identificadore :D */
                ArrayList<Nodo> lista = new ArrayList<Nodo>();
                y.getNodoInicial().setId(ContadorDeID);
                ContadorDeID =+1;
                y.getNodoFinal().setId(ContadorDeID);
                ContadorDeID =+1;
                lista.add(y.getNodoInicial());
                lista.add(y.getNodoFinal());
                y.setHistorial(lista);

                stack.push(y);
            }
        }
        return stack.pop();
    }


    public void numerarNodos(){
        /*SET ID TO EVERY NODE*/
        for(Nodo i: losNodosRepetidos){
            i.setId(contador);
            for(Nodo j: losNodosRepetidos){
                if (j.equals(i)){
                    j.setId(contador);
                }
            }
            contador = contador + 1;
        }
        /*NO TOCAR*/
    }

    public void reducirRepetidos(){
        /*ELIMINA REPETIDOS DE ARRAYLIST*/
        while (contable < losNodosRepetidos.size()){
            for (Nodo i : losNodosRepetidos){
                if (i.getId() == contable && !losNodosSinRepeticion.contains(i)){
                    losNodosSinRepeticion.add(i);
                    contable = contable + 1;
                }
            }
            contable = contable +1;
        }
        /*NO TOCAR*/
    }


    public void AlgoritmoCreaTransiciones(){
        for(Nodo i: losNodosSinRepeticion){
            for (String j: i.getTransiciones()){
                Transicion t = new Transicion(i.getId(), j, i.getNodos().get(i.getTransiciones().indexOf(j)).getId());
                transiciones.add(t);
            }
        }
    }

    public void AlgoritmoCreaID(){
        for (Nodo i: losNodosSinRepeticion){
            ids.add(i.getId());
        }
    }

    public ArrayList<String> AlgoritmoImplantaSimbolos(ArrayList<Nodo> a){
        for (Nodo i : a){
            for (String s: i.getTransiciones()){
                if (!simbolos.contains(s) && !s.equals("@")){
                    simbolos.add(s);
                }
            }
        }
        return simbolos;
    }

    public ArrayList<Nodo> getGrafo() {
        return grafo;
    }

    public ArrayList<Transicion> getTransiciones() {
        return transiciones;
    }

    public ArrayList<Integer> getIds() {
        return ids;
    }

    public ArrayList<String> getSimbolos() {
        return simbolos;
    }

    public int getContador(){
        return contador;
    }
}

