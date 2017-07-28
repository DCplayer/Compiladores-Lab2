import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        NormalizadorDeRegex norm = new NormalizadorDeRegex();
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese la expresion regular: ");
        String regex = sc.nextLine();

        /*Se normalizo y postfixeo la expresion regular para que se tome en cuenta ? como (x|@)*/
        String RegexNormalizado = norm.PostFixYNormalizar(regex);
        String i = RegExConverter.infixToPostfix("bb*abcc* ");

        System.out.println(RegexNormalizado);
    }
}

