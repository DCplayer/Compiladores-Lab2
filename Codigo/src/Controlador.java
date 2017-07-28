/************************************************/
/*Laboratorio 2				                    */
/*UVG						                    */
/*Diseno de Lenguajes                           */
/*Tomas Galvez                                	*/
/*Diego Castaneda 15151                       	*/
/************************************************/

/********************************************************/
/*Clase para la administracion y gestion del movimineto */
/* de nodos por medio de simbolos en la regez           */

import java.util.ArrayList;

/********************************************************/

public class Controlador {
    private char[] characters;
    private ArrayList<String> caracteres;


    public Controlador(){
    }
    /*Funcion para evitar los epsilons que existan*/
    public int enBuscaDelSiguiente(ArrayList<String> regex, int posicion){
        while(regex.get(posicion) == "&"){
            posicion += 1;
        }
        return posicion;
    }

    public String desEncapsularParentesis(String regex, int posicion){
        String resultado = "";
        int contadorParentesis = 1;
        while (contadorParentesis > 0){
            posicion =+ 1;
            if (caracteres.get(posicion) == ")"){
                contadorParentesis =-1;
            }
            else if(caracteres.get(posicion) == "("){
                contadorParentesis =+1;

            }

            if (caracteres.get(posicion) != ")" || contadorParentesis != 0){
                resultado = resultado + caracteres.get(posicion);
            }


        }
        return resultado;

    }
    public String firstPos(String regex){
        regex.getChars(0, regex.length() -1, characters, 0);
        for(char i: characters){
            caracteres.add("i");
        }
        if(caracteres.isEmpty()){
            return regex;
        }
        else if(caracteres.size()== 1){
            return String.valueOf(characters[0]);
        }
        else if(characters.length > 1 ){
            if (caracteres.get(0) == "("){


            }
            else if (caracteres.get(0) == "["){

            }
            else if (caracteres.get(0) == "&"){

                int i = enBuscaDelSiguiente(caracteres, 0);

                if(caracteres.get(i) == "("){

                }
                else if(caracteres.get(i) == "|"){

                }
                else if(caracteres.get(i) == "?"){

                }
                else if(caracteres.get(i) == "*"){

                }
                else if(caracteres.get(i) == "+"){

                }
                else {
                    return caracteres.get(i);
                }
            }
            else {
                return caracteres.get(0);
            }
        }
        return "";
    }

    public String lastPos(String regex){
        regex.getChars(0, regex.length() -1, characters, 0);
        if(regex.isEmpty()){
            return regex;
        }
        else if(characters.length == 1){
            return String.valueOf(characters[0]);
        }
        return "";
    }

    public String nullable(String regex){
        return "";
    }
}
