import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        NormalizadorDeRegex norm = new NormalizadorDeRegex();
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese la expresion regular: ");
        String regex = sc.nextLine();
        Controlador control = new Controlador(regex);

        Automata t = control.LectorDeExpresiones();

        /*Obtener el Nodo Inicial*/
        /*Obtener el Nodo Final*/
        /*Obtener la Numeracion de ID de nodos*/
        /*Obtener las transiciones del grafo*/
        /*Obtener los simbolos del grafo*/

        /*Se normalizo y postfixeo la expresion regular para que se tome en cuenta ? como (x|@)*/
        String RegexNormalizado = norm.PostFixYNormalizar(regex);
        String i = RegExConverter.infixToPostfix("abc");
        System.out.println(i);
        System.out.println(RegexNormalizado);


    }
}

