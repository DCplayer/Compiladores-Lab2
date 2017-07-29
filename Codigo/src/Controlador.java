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
    boolean asegurador = true;
    int contador = 0;
    ArrayList<Nodo> grafo = new ArrayList<Nodo>();
    ArrayList<Transicion> transiciones = new ArrayList<Transicion>();
    ArrayList<Integer> ids = new ArrayList<Integer>();

    /*Atributos para la funcion de AlgoritmoImplantaSimbolos*/
    boolean criterio = true;
    ArrayList<String> simbolos = new ArrayList<String>();


    public Controlador(String regex) {
        this.regex = regex;
    }

    public Automata LectorDeExpresiones( ) {

        String RegexNormalizado = norm.PostFixYNormalizar(regex);
        int j = RegexNormalizado.length();
        for (int i = 0; i < j; i++) {
            String x = regex.substring(i, i + 1);
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

    public void  AlgoritmoNumeraNodosyCreaTransiciones(Automata a){
        grafo.add(a.getNodoInicial());
        for (Nodo i : grafo){
            asegurador = true;
            i.setId(contador);

            for(String s: i.getTransiciones()){
                Nodo nodoLlegada = i.getNodos().get(i.getTransiciones().indexOf(s));
                Transicion t = new Transicion(i.getId(), s, nodoLlegada.getId());
                transiciones.add(t);

                for(Nodo x : grafo){
                    if(nodoLlegada.getId() == x.getId()){
                        asegurador = false;
                    }
                }
                if (asegurador){
                    grafo.add(nodoLlegada);
                }

                ids.add(contador);
                contador =+1 ;
            }
        }
    }

    public void AlgoritmoImplantaSimbolos(){
        for(Transicion i: transiciones){
            String trans = i.getTransicion();
            criterio = true;

            for(String s : simbolos){
                if(trans.equals(s) || trans.equals("@")){
                    criterio = false;
                }
            }
            if (criterio){
                simbolos.add(trans);
            }
        }
    }

}

