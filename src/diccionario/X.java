package diccionario;

public class X extends Variable {
	private Integer number;

	public X(Integer number) {
		super("X");
		this.number = number;
	}
	
	public Integer getNumber() {
		return this.number;
	}
	
	@Override
	public String toString() {
		return super.toString() + number.toString();
	}

	public boolean igual(X var) {
		return this.number.equals(var.number);
	}

	public boolean mayor(X var) {
		return this.number > var.number;
	}
	
	@Override
	protected X clone(){
		return new X(this.number);
	}
}
