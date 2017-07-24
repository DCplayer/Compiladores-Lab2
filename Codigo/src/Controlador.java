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

    public int enBuscaDelSiguiente(ArrayList<String> regex, int posicion){
        while(regex.get(posicion) == "&"){
            posicion += 1;
        }
        return posicion;
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

                int i =enBuscaDelSiguiente(caracteres, 0);

                if(caracteres.get(i) == "("){

                }
                else if(caracteres.get(i) == "["){

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
    }

    public String lastPos(String regex){
        regex.getChars(0, regex.length() -1, characters, 0);
        if(regex.isEmpty()){
            return regex;
        }
        else if(characters.length == 1){
            return String.valueOf(characters[0]);
        }
    }

    public String nullable(String regex){

    }
}
