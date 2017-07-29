import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        NormalizadorDeRegex norm = new NormalizadorDeRegex();
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese la expresion regular: ");
        String regex = sc.nextLine();
        Controlador control = new Controlador(regex);

        Automata AutomataFinal = control.LectorDeExpresiones();

        /*Aplicar las funciones de void al controlador para que realice los procesos dentro de el mismo*/
        control.agregarInicialG(AutomataFinal.getNodoInicial());
        control.llenarDeNodos();
        control.AlgoritmoImplantaSimbolos();

        /*Obtener la Numeracion de ID de nodos*/
        ArrayList<Integer> ids = control.getIds();

        /*Obtener las transiciones del grafo*/
        ArrayList<Transicion> transiciones = control.getTransiciones();

        /*Obtener los simbolos del grafo*/
        ArrayList<String> simbolos = control.getSimbolos();

        /*Obtener el Nodo Inicial*/
        int IdInicial = AutomataFinal.getNodoInicial().getId();

        /*Obtener el Nodo Final*/
        int IdFinal = AutomataFinal.getNodoFinal().getId();

        System.out.println(control.getGrafo().size() );

        System.out.println("ESTADOS = " + ids);
        System.out.println("SIMBOLOS = " + simbolos);
        System.out.println("INICIO = " + IdInicial);
        System.out.println("ACEPTACION = " + IdFinal);
        System.out.println("TRANSICION = " + transiciones);

        System.out.println();




    }
}

