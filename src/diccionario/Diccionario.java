package diccionario;

import java.util.LinkedList;
import java.util.List;

public class Diccionario {
	private List<Ecuacion> ecuaciones;
	private Ecuacion func_obj;
	
	public Diccionario() {
		super();
		this.ecuaciones = new LinkedList<Ecuacion>();
	}
	
	public void addEcuacion(Ecuacion e) {
		this.ecuaciones.add(e);
	}
	
	public Ecuacion getEcc(Integer i) {
		return this.ecuaciones.get(i);
	}
	
	public Ecuacion getFuncionObjetivo() {
		return this.func_obj;
	}
	
	public void setFuncionObjetivo(Ecuacion e) {
		this.func_obj = e;
	}
	
	public void reemplazarEcc(Integer i, Ecuacion ec) {
		this.ecuaciones.set(i, ec);
	}

	@Override
	public String toString() {
		String res = "";
		Integer max = 0;
		for (int i = 0; i < ecuaciones.size() ; i++) {
			max = Math.max(ecuaciones.get(i).cantidadTerminos(), max);
			res += "\t" + ecuaciones.get(i) + "\n";
		}
		res += "\t";
		for (int i = 0; i < max ; i++) {
			res += "-------";
		}
		
		res += "\n\t" + func_obj.toString();
		return res;
	}

	public List<Candidata> obtenerCandidatasASalir(Integer entra) {
		List<Candidata> candidatas = new LinkedList<Candidata>();
		for (Ecuacion ecc : ecuaciones ) {
			Termino term = ecc.getTerminoDeX(entra);
			if (term != null) {
				Float coeficiente = term.getCoeficiente();
				if ( coeficiente < new Float(0) ) {
					candidatas.add(new Candidata(ecc.getVariableIndependiente(), - ecc.getTerminoIndependiente() / coeficiente));
				}
			}
		}
		return candidatas;
	}

	public void cambiar(Integer entra, Integer sale) {
		Ecuacion despejada = null;
		for (Ecuacion ecc : ecuaciones) {
			if ( ecc.getVariableIndependiente().getNumber().equals(sale) ) {
				ecc.despejarX(entra);
				despejada = ecc;
				break;
			}
		}
		for (Ecuacion ecc : ecuaciones) {
			if ( !ecc.getVariableIndependiente().getNumber().equals(entra) ) {
				ecc.reemplazar(despejada);
			}
		}
		this.func_obj.reemplazar(despejada);
	}
}
