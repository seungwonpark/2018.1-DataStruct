public class ComplexTest{
	public static void main(String[] args){
		Complex a = new Complex(12);
		Complex b = new Complex(1, 2);
		Complex c = new Complex(1, 2);
		Complex d = a.add(b);
		System.out.println(d.real() + " + " + d.imag() + "i");
		System.out.println(b == c);
		System.out.println(b.equals(c));
	}
}