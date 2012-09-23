package test;

import diccionario.Ecuacion;
import diccionario.Termino;
import diccionario.X;
import junit.framework.TestCase;

public class TestEcuacion extends TestCase {
	
	public void testDividir() {
		Ecuacion ecc = new Ecuacion(new X(1), new Float(2));
		ecc.addTermino(new Termino(new X(2), new Float(2)));
		ecc.addTermino(new Termino(new X(3), new Float(-2)));
		ecc.dividir(new Float(2));
		
		assertEquals(new Float(1), ecc.getTerminoDeX(2).getCoeficiente(), 0.01);
		assertEquals(new Float(-1), ecc.getTerminoDeX(3).getCoeficiente(), 0.01);
		assertEquals(new Float(1), ecc.getTerminoIndependiente(), 0.01);
	}
	
	public void testDespejarX() {
		// X1 = 2 + 2 X2 - 2 X3
		// - 2 X2 = 2 - 2 X3 - X1
		// X2 = -1 + X3 +0.5 X1
		Ecuacion ecc = new Ecuacion(new X(1), new Float(2));
		ecc.addTermino(new Termino(new X(2), new Float(2)));
		ecc.addTermino(new Termino(new X(3), new Float(-2)));
		ecc.despejarX(2);
		
		assertEquals(new Float(0.5), ecc.getTerminoDeX(1).getCoeficiente(), 0.01);
		assertEquals(new Float(1), ecc.getTerminoDeX(3).getCoeficiente(), 0.01);
		assertEquals(new Float(-1), ecc.getTerminoIndependiente(), 0.01);
	}
}
