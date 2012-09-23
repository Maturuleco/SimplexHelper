package diccionario;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Ecuacion {
	private Variable var;
	private Float termino_indep;
	private List<Termino> terminos;

	public Ecuacion(Variable var_indep, Float termino_indep) {
		super();
		this.termino_indep = termino_indep;
		this.var = var_indep;
		this.terminos = new LinkedList<Termino>();
	}
	
	public void addTermino(Termino ter) {
		Integer i = 0;
		Termino actual;
		while (i < terminos.size()) {
			actual = terminos.get(i);
			if ( actual.getVar().igual(ter.getVar()) ) {
				actual.add(ter);
				return;
			} else if ( actual.getVar().mayor(ter.getVar()) ) {
				break;
			}
			i++;
		}
		terminos.add(i, ter);
	}
	
	public boolean despejarX(Integer i) {
		Termino sale = removeTerminoX(i);
		if ( sale == null )
			return false;
		
		X var_indep = (X) this.var;
		
		this.addTermino( new Termino(var_indep, new Float(-1)) );
		this.var = sale.getVar();
		this.dividir( - sale.getCoeficiente());
		
		return true;
	}
	
	private Termino removeTerminoX(Integer i) {
		Termino sale = null;
		Iterator<Termino> it = terminos.iterator();
		while (it.hasNext()) {
			Termino actual = it.next();
			if ( actual.getVar().getNumber().equals(i) ) {
				sale = actual;
				it.remove();
				break;
			}
		}
		return sale;
	}
	
	public void reemplazar(Ecuacion ec) {
		X var = (X) ec.var;
		Termino ter_var = removeTerminoX(var.getNumber());
		if (ter_var == null)
			return;
		ec = ec.clone();
		ec.multiplicar(ter_var.getCoeficiente());
		for (Termino t : ec.terminos) {
			this.addTermino(t);
		}
		this.termino_indep += ec.termino_indep;
	}
	
	public Ecuacion clone() {
		Ecuacion nueva = new Ecuacion(this.var.clone(), new Float(this.termino_indep));
		for (Termino t : this.terminos) {
			nueva.addTermino(t.clone());
		}
		return nueva;
	}
	
	public void dividir(Float cociente) {
		Iterator<Termino> it = terminos.iterator();
		while (it.hasNext()) {
			it.next().dividir(cociente);
		}
		this.termino_indep = termino_indep / cociente;
	}
	
	public void multiplicar(Float cociente) {
		Iterator<Termino> it = terminos.iterator();
		while (it.hasNext()) {
			it.next().multiplicar(cociente);
		}
		this.termino_indep = termino_indep * cociente;
	}

	@Override
	public String toString() {
		String res = var.toString() + " = " + termino_indep.toString();
		Iterator<Termino> it = terminos.iterator();
		while (it.hasNext()) {
			res += " " + it.next().toString();
		}
		return res;
	}

	public Termino getTerminoDeX(Integer i) {
		Iterator<Termino> it = terminos.iterator();
		while (it.hasNext()) {
			Termino actual = it.next();
			if ( actual.getVar().getNumber() == i ) {
				return actual;
			}
		}
		return null;
	}

	public Integer cantidadTerminos() {
		return this.terminos.size() +1;
	}
	
	public Float getTerminoIndependiente() {
		return this.termino_indep;
	}

	public X getVariableIndependiente() {
		return (X) this.var;
	}
}
