package primitivos;

public class Quadrado {
	Ponto A;
	Ponto B;
	
	
	Quadrado(Ponto A, Ponto B){
		setA(A);
		setB(B);
	}
	
	Quadrado(int x1, int y1, int x2, int y2){
        this(new Ponto(x1, y1), new Ponto(x2, y2));
    }    


	public Ponto getA() {
		return A;
	}


	public void setA(Ponto a) {
		A = a;
	}


	public Ponto getB() {
		return B;
	}


	public void setB(Ponto b) {
		B = b;
	}
	
}
	
	
	
