import java.io.*; 
import java.util.Scanner; 
import java.lang.Math; 

class TP1Ej4Simulacion{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in); 
		int numIteraciones = 0, numAciertos = 0; 	
		double piEst = 0, x = 0, y = 0; 
		while(true){
			 
			System.out.println("Ingrese el número de iteraciones: "); 
			numIteraciones = scanner.nextInt(); 
			for(int i = 0; i < numIteraciones; i++){
				x = Math.random();
				y = Math.random(); 
				if(Math.sqrt(Math.pow(x,2) + Math.pow(y,2)) <= 1)
					numAciertos += 1; 
			}	
			piEst = 4.0*(Double.valueOf(numAciertos)/Double.valueOf(numIteraciones));
			System.out.println("La estimación de PI obtenida es: " + piEst);
			numAciertos = 0;
			
		}
	}
}
