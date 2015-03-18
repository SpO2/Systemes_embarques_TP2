package fr.embeded.sysembdtp1.primeNumber;

import java.lang.Math;

/**
 * Class for prime number operations.
 * @author romain
 *
 */
public class PrimeNumber {
	
	/**
	 * Constant : prime number found.
	 */
	private final static String PRIME_NUMBER = "est un nombre premier";
	/**
	 * Constant : prime number not found.
	 */
	private final static String NOT_PRIME_NUMBER = "n'est pas un nombre premier";
	/**
	 * Constant : a comma separator.
	 */
	private final static String COMMA_SEPARATOR = ", ";
	
	/**
	 * Number to test.
	 */
	private int currentNumber;
	
	/**
	 * Constructor.
	 * @param number
	 */
	public PrimeNumber(int number){
		this.currentNumber = number;
	}
	
	/**
	 * Detect if currentNumber is a prime number.
	 * @return String : prime number found or not.
	 */
	public String isPrimeNumber(){
		int i, test;
		double limite;
		test = 0;
		limite = Math.sqrt(currentNumber) + 1;
		if (currentNumber != 0){
			if (currentNumber != 1){
				if (currentNumber % 2 == 0)
					test = 1;
				else{
					for (i = 3 ; i < limite && test != 1; i+=2)
						if (currentNumber % i == 0)
							test = 1;
				}
				if (test != 1)
					return PRIME_NUMBER;
				else
					return NOT_PRIME_NUMBER;
				
			}
			else
				return NOT_PRIME_NUMBER;
		}
		return "";
	}
	
	/**
	 * Get the list of all the prime numbers between zero and currentNumber.
	 * @return String : the list of the prime numbers.
	 */
	public String primeNumberList(){
		int i, j, diviseur;
		String result;
		result = "";
		for (i=2; i<currentNumber; i++){
			diviseur = 0;
			for (j=1; j<=i; j++){
				if (i%j==0){
			        diviseur++;
			    }
			}
			if (diviseur == 2){
				if (result == ""){
					result = String.valueOf(i);
			    }
			    else{
			    	result = result + COMMA_SEPARATOR + String.valueOf(i);
			    }
			}
		}
		return result;
	}
}

