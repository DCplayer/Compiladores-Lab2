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
        ArrayList<Nodo> grafo = AutomataFinal.getHistorial();
        /*Aplicar las funciones de void al controlador para que realice los procesos dentro de el mismo*/

        /*Obtener las transiciones del grafo*/


        /*Obtener la Numeracion de ID de nodos*/
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for(Nodo i: grafo){
            ids.add(i.getId());
        }

        /*Obtener los simbolos del grafo*/

        /*Obtener el Nodo Inicial*/
        int IdInicial = AutomataFinal.getNodoInicial().getId();

        /*Obtener el Nodo Final*/
        int IdFinal = AutomataFinal.getNodoFinal().getId();



        System.out.println("ESTADOS = " + ids);
        System.out.println("SIMBOLOS = " );
        System.out.println("INICIO = " + IdInicial);
        System.out.println("ACEPTACION = " + IdFinal);
        System.out.println("TRANSICION = " );








    }
}

