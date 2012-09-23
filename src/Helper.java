import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import diccionario.Candidata;
import diccionario.Diccionario;
import diccionario.Ecuacion;
import diccionario.Termino;
import diccionario.Variable;
import diccionario.X;

/**
 * @author Tas
 *
 */
public class Helper {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);
		
		Diccionario dicc = new Diccionario();
		
		System.out.println("Ingrese la cantidad de variables: ");
		Integer n = Integer.valueOf( System.console().readLine() );
		System.out.println("Ingrese la cantidad de restricciones: ");
		Integer restricciones = Integer.valueOf( br.readLine() );
		
		for (; restricciones > 0 ; restricciones-- ) {
			System.out.println("Ingrese el indice de la variable independiente (lado izquierdo): ");
			Integer i = Integer.valueOf( br.readLine() );
			System.out.println("Ingrese el valor del coeficiente independiente: ");
			Float b = Float.valueOf( br.readLine() );
			Ecuacion ecc = new Ecuacion(new X(i), b);
			for (int j = 1; j <= n ; j++ ) {
				if ( j != i ) {
					System.out.println("Ingrese el coeficiente de X" + String.valueOf(j) + ": ");
					String x = br.readLine();
					if (!x.equals("") && !x.equals("0"))
						ecc.addTermino(new Termino(new X(j), Float.parseFloat(x)));
				}
			}
			System.out.println("Ecuacion ingresada:");
			System.out.println(ecc.toString());
			String res = "";
			while (!res.equalsIgnoreCase("y") && !res.equalsIgnoreCase("n")) {
				System.out.println("Es correcta?(y/n):");
				res = br.readLine();
			}
			if ( res.equalsIgnoreCase("y") )
				dicc.addEcuacion(ecc);
			else
				restricciones++;
		}
		String res = "";
		while (!res.equals("y")) {
			System.out.println("Funcion Objetivo: ");
			System.out.println("Ingrese el valor del coeficiente independiente: ");
			Float b = Float.valueOf( br.readLine() );
			Ecuacion ecc = new Ecuacion(new Variable("Z"), b);
			for (int j = 1; j <= n ; j++ ) {
				System.out.println("Ingrese el coeficiente de X" + String.valueOf(j) + ": ");
				String x = br.readLine();
				if (!x.equals("") && !x.equals("0"))
					ecc.addTermino(new Termino(new X(j), Float.parseFloat(x)));
			}
			System.out.println("Ecuacion ingresada:");
			System.out.println(ecc.toString());
			System.out.println("Es correcta?(y/n):");
			res = br.readLine();
			if ( res.equalsIgnoreCase("y") )
				dicc.setFuncionObjetivo(ecc);
		}
		System.out.println("Diccionario inicial:\n"+dicc.toString());
		
		System.out.println("Fin? (y/n)");
		res = br.readLine();
		
		while ( !res.equalsIgnoreCase("y") ) {
			System.out.println("Indice de la variable a entrar a la base:");
			Integer entra = Integer.valueOf( br.readLine() );
			List<Candidata> candidatas = dicc.obtenerCandidatasASalir(entra);
			String tmp = "Candidatas a salir:";
			for (int j = 0; j < candidatas.size() ; j++) {
				Candidata c = candidatas.get(j);
				tmp += "\n\t" + c.getVariable().toString() + ": " + c.getValor();
			}
			System.out.println(tmp);
			System.out.println("Indice de la variable a salir de la base:");
			Integer sale = Integer.valueOf( br.readLine() );
			dicc.cambiar(entra, sale);
			
			System.out.println(dicc.toString());
			
			System.out.println("Fin? (y/n)");
			res = br.readLine();
		}
	}
	

}
