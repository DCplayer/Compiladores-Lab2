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

        String RegexNormalizado = norm.PostFixYNormalizar(regex);
        int j = RegexNormalizado.length();
        for (int i = 0; i < j; i++) {
            String x = RegexNormalizado.substring(i, i + 1);

            if (x.equals(".")) {
                Automata b = stack.pop();
                Automata a = stack.pop();
                Automata concatencion = rel.and(a,b);
                stack.push(concatencion);

            } else if (x.equals("|")) {
                Automata b = stack.pop();
                Automata a = stack.pop();
                Automata or = rel.or(a,b);
                stack.push(or);

            } else if (x.equals("+")) {
                Automata a = stack.pop();
                Automata kleeneSuma = rel.sum(a);
                stack.push(kleeneSuma);

            } else if (x.equals("*")) {
                Automata kleene = rel.kleene(stack.pop());
                stack.push(kleene);

            } else {
                Automata y = new Automata(x);

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

    public void AlgoritmoImplantaSimbolos(){
        for (Nodo i : losNodosSinRepeticion){
            for (String s: i.getTransiciones()){
                if (!simbolos.contains(s) && !s.equals("@")){
                    simbolos.add(s);
                }
            }
        }
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

