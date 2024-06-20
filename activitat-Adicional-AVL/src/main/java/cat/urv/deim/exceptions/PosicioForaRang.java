package cat.urv.deim.exceptions;

public class PosicioForaRang extends Exception {
	private static final long serialVersionUID = 1L;

	public PosicioForaRang(){
		super("ERROR : Posicio fora de rang");
	}

}
