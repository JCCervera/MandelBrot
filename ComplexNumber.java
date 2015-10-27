/**
 * COP 3330-1
 * 11/29/10
 * ComplexNumber Class
 * The class that creates the Complex Number and holds any important functions for that class.
 * @author Juan Carlo Cervera
 * 
 */
public class ComplexNumber {
	
	/**Variable for the Real number.*/
	double real;
	
	/**Variable for the Imaginary Number.*/
	double imag;

	/**
	 * Constructor for the Complex Number
	 * @param real The real number within the Complex Number.
	 * @param imag The Imaginary number within the Complex Number.
	 */
	public ComplexNumber (double real, double imag){
		
		//Set the parameters equal to their counterparts.
		this.real = real;
		this.imag = imag;
		
	}
	
	/**
	 * Squares the complex number according to the equation given.
	 */
	public void square (){
		//Create a temporary variable for the real number. 13
		double tempR = this.real;
		
		//Squares the real half of the Complex Number.
		this.real = Math.pow(this.real,2) - Math.pow(this.imag, 2);
		
		//Squares the imaginary half of the complex number.
		this.imag = (2*tempR*this.imag);
		
	}
	
	/**
	 * Finds the magnitude of the complex number according to the equation given.
	 * @return -Returns the magnitude of the complex number.
	 */
	public double magnitude (){
		
		return Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imag, 2));
	}
	
	/**
	 * Adds the complex number to its original counter part.
	 * @param Z -The original complex number.
	 */
	public void add (ComplexNumber Z){
		
		this.real = this.real + Z.real;
		this.imag = this.imag + Z.imag;
		
	}
	
	
}
