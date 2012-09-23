package diccionario;

public class Termino {
	private X var;
	private Float coeficiente;
	
	public Termino(X var, Float coeficiente) {
		super();
		this.var = var;
		this.coeficiente = coeficiente;
	}

	public X getVar() {
		return var;
	}

	public Float getCoeficiente() {
		return coeficiente;
	}

	@Override
	public String toString() {
		String res = "";
		if (coeficiente.equals(new Float(0)))
			return res;
		if (coeficiente.equals(new Float(1)))
			return "+" + var.toString();
		if (coeficiente.equals(new Float(-1)) )
			return "-" + var.toString();
		if (coeficiente > 0)
			res += "+";
		res += coeficiente.toString() + " " + var.toString();
		return res;
	}

	public void add(Termino ter) {
		assert(ter.getVar().igual(this.getVar()));
		this.coeficiente += ter.coeficiente;
	}

	public void dividir(Float cociente) {
		this.coeficiente = (coeficiente / cociente);
	}

	public void multiplicar(Float cociente) {
		this.coeficiente = coeficiente * cociente;
	}
	
	@Override
	protected Termino clone(){
		return new Termino(this.var.clone(), new Float(this.coeficiente));
	}
}
