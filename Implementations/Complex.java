class Complex{
	private int re, im;
	public Complex(int re, int im){
		this.re = re;
		this.im = im;
	}
	public Complex(int r){
		this(r, 0);
	}
	public Complex(){
		this(0, 0);
	}
	public int real(){
		return re;
	}
	public int imag(){
		return im;
	}
	public Complex add(Complex that){
		return new Complex(this.re + that.real(), this.im + that.imag());
	}
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + re;
		result = prime * result + im;
		return result;
	}
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Complex other = (Complex) obj;
		return re == other.re && im == other.im;
	}
}
