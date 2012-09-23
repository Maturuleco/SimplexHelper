package diccionario;

public class Variable {
	private String id;

	public Variable(String id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return id.toString();
	}

	@Override
	protected Variable clone(){
		return new Variable(this.id);
	}
	
	
}
