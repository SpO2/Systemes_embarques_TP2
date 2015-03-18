package fr.embeded.sysembdtp1;

import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import fr.embeded.sysembdtp1.primeNumber.PrimeNumber;

public class MainActivity extends Activity implements OnClickListener {

	/**
	 * Constant for the prime number list string.
	 */
	private final static String PRIME_NUMBER_LIST = " - Liste des nombres premiers : ";
	
	/**
	 * Current Number entry.
	 */
	private int currentNumber;
	/**
	 * Button that launch Java prime number algorithm.
	 */
	private Button btnJava;
	/**
	 * Button that launch C prime number algorithm.
	 */
	private Button btnC;
	
	/**
	 * Load external JNI C library.
	 */
	static{
		System.loadLibrary("primenumber-jni");
	}
	
	/**
	 * External function to detect if the current number is a prime number.
	 * @param number - int
	 * @return String : is prime number or not.
	 */
	public native String stringPrimeNumber(int number);
	/**
	 * External function : return the list of prime numbers between 0 and the
	 * current number.
	 * @param number
	 * @return int[] - the list of prime numbers.
	 */
	public native int[] stringPrimeNumberList(int number);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		currentNumber = 0;
		btnJava = (Button) this.findViewById(R.id.btnJavaPrime);
		btnC = (Button) this.findViewById(R.id.btnCPrime);
		btnC.setOnClickListener(this);
		btnJava.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		TextView tv = (TextView) MainActivity.this.findViewById(R.id.number);
		if (!(tv.getText().toString().trim().isEmpty())){
			currentNumber = Integer.parseInt(tv.getText().toString());
			String Result = "";
			switch (v.getId()) {
			case R.id.btnCPrime:{
				Result = String.valueOf(currentNumber) + " " + stringPrimeNumber(currentNumber);
				int[] primelist = stringPrimeNumberList(currentNumber);
				String nbrList = Arrays.toString(primelist);
				Result = Result + PRIME_NUMBER_LIST + nbrList;
				break;
			}
			case R.id.btnJavaPrime:{
				PrimeNumber primeNumber = new PrimeNumber(currentNumber);
				Result = String.valueOf(currentNumber) + " " + primeNumber.isPrimeNumber();
				Result = Result + PRIME_NUMBER_LIST + primeNumber.primeNumberList();
				break;
			}
			default:
				break;
			}
			
			Toast toast = Toast.makeText(MainActivity.this.getApplicationContext(), Result, Toast.LENGTH_LONG);
			toast.show();
		}
	}
}
