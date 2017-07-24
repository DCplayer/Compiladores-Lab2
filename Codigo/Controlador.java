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
/********************************************************/

public class Controlador(){
	private char[] characters;  

	public Controlador(){
	}

	public String firstPos(String regex){
			regex.getChars(0, regex.length() -1, characters, 0);
			if(regex.isEmpty()){
				return regex; 
			} 
			else if(characters.length == 1){
				return String.valueOf(characters[0]);
			}
			else if(characters.length > 1 ){
				if (String.valueOf(characters[0]) == '('){

				}
				else if (String.valueOf(characters[0]) == '['){

				}
				else if (String.valueOf(characters[0]) == '('){

				}
			}
		}

	public String firstPos(String regex){
		regex.getChars(0, regex.length() -1, characters, 0);
			if(regex.isEmpty()){
				return regex; 
			} 
			else if(characters.length == 1){
				return String.valueOf(characters[0]);
			}
	}

	public String firstPos(String regex){

	}
}