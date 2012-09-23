package test;

import diccionario.Diccionario;
import diccionario.Ecuacion;
import diccionario.Termino;
import diccionario.X;
import diccionario.Variable;

import junit.framework.TestCase;

public class TestDiccionario extends TestCase {
	public void testPaso() {
		Diccionario dicc = new Diccionario();
		Ecuacion ecc;
		
		ecc = new Ecuacion(new X(3), new Float(8));
		ecc.addTermino(new Termino(new X(1), new Float(-2)));
		ecc.addTermino(new Termino(new X(2), new Float(-1)));
		dicc.addEcuacion(ecc);

		ecc = new Ecuacion(new X(4), new Float(6));
		ecc.addTermino(new Termino(new X(1), new Float(1)));
		ecc.addTermino(new Termino(new X(2), new Float(-2)));
		dicc.addEcuacion(ecc);
		
		ecc = new Ecuacion(new X(5), new Float(6));
		ecc.addTermino(new Termino(new X(1), new Float(-1)));
		ecc.addTermino(new Termino(new X(2), new Float(-1)));
		dicc.addEcuacion(ecc);
		
		ecc = new Ecuacion(new Variable("Z"), new Float(0));
		ecc.addTermino(new Termino(new X(1), new Float(3)));
		ecc.addTermino(new Termino(new X(2), new Float(4)));
		dicc.setFuncionObjetivo(ecc);
		
		dicc.cambiar(2, 4);
	}
}
