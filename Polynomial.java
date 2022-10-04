import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Polynomial {

	double coefficients[];
	int exponents[];

	public Polynomial() {
		coefficients = null;
		exponents = null;

	}

	public Polynomial(double[] arrayInitialize, int expoArr[]) {

		coefficients = new double[arrayInitialize.length];
		exponents = new int[expoArr.length];

		for (int i = 0; i < arrayInitialize.length; i++) {
			coefficients[i] = arrayInitialize[i];
		}

		for (int i = 0; i < expoArr.length; i++) {
			exponents[i] = expoArr[i];
		}

	}
	
	public boolean hasX(String str) {
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)=='x') {
				return true;
			}
		}
		return false;
	}
	
	
	public Polynomial(File text) throws FileNotFoundException{
		Scanner scan = new Scanner(text);
		String linePoly = scan.nextLine();
		
		String[] sliceByPlusAndMinus = linePoly.split("(\\+)|((?=\\-))");

		coefficients=new double[sliceByPlusAndMinus.length];
		exponents=new int[sliceByPlusAndMinus.length];
		
		String coeff="", exp="";
		int k=0;
	
		for(int j=0; j<sliceByPlusAndMinus.length; j++) {
			coeff="";
			exp="";
			
			if(!hasX(sliceByPlusAndMinus[j])){
				exponents[j]=0;
				for(int cur=0; cur<sliceByPlusAndMinus[j].length(); cur++) {
					coeff+=sliceByPlusAndMinus[j].charAt(cur);
				}
				coefficients[j]=Double.parseDouble(coeff);
				
			} else {
				k=0;
				for(; sliceByPlusAndMinus[j].charAt(k) != 'x'; k++) {
					coeff+=sliceByPlusAndMinus[j].charAt(k);
				}
				
				if(coeff.equals("") || coeff.equals("-")){
					coeff+='1';
				}
				coefficients[j]=Double.parseDouble(coeff);
				
				for(int ex=k+1; ex<sliceByPlusAndMinus[j].length(); ex++) {
					exp+=sliceByPlusAndMinus[j].charAt(ex);
				}
				
				if(exp.equals("")) {
					exp+='1';
				}
				
				exponents[j]=Integer.parseInt(exp);
				
			}
			
		}
		
		scan.close();
	}
	
	
	public void saveToFile(String fileName) throws FileNotFoundException {
		
		File f = new File(fileName);
		PrintStream output = new PrintStream(f);
		String str="";
		
		for(int i=0; i<coefficients.length; i++) {
			
			if(i!=0 && coefficients[i]>0) {
				str+='+';
			} 
	
			str+=String.valueOf(coefficients[i]);

			if(exponents[i]!=0) {
				str+='x';
				str+= String.valueOf(exponents[i]);
			}
		
		}
	
		output.println(str);
		output.close();
	}	

	public int lowestNumIdx(int[] array, int start) {

		int min = start;
		for (int i = start + 1; i < array.length; i++) {
			if (array[i] < array[i - 1]) {
				min = i;
			}

		}
		return min;
	}

	public boolean find(int num, int[] arr, int len) {
		for (int i = 0; i < len; i++) {
			if (arr[i] == num) {
				return true;
			}
		}
		return false;
	}

	
	public boolean isZeroInArr(double[] arr) {
		for(int i=0; i<arr.length; i++) {
			if(arr[i]==0.0) {
				return true;
			}
		}
		return false;
	}
	
	public Polynomial removeZero(double[] newCoeff, int[] newExp) {
		
		if(!isZeroInArr(newCoeff)) {
			Polynomial newPoly = new Polynomial(newCoeff, newExp);
			return newPoly;
		} else {
			
			int zero=0;
			int cur=0;
			
			for(int j=0; j<newCoeff.length; j++) {
				if(newCoeff[j]==0) {
					zero++;
				}
			}
			
			int newLength=newCoeff.length-zero;
			double coeffNewGood[] = new double[newLength];
			int expNewGood[] = new int[newLength];
			
			for(int ind=0; ind<newCoeff.length; ind++) {
				if(newCoeff[ind]==0) {
					continue;
				} else {
					coeffNewGood[cur]=newCoeff[ind];
					expNewGood[cur]=newExp[ind];
					cur++;
				}

			}
			
			Polynomial newPoly = new Polynomial(coeffNewGood, expNewGood);
			return newPoly;
		}
	}
	
	public Polynomial add(Polynomial objArg) {

		int count = 0;
		double sum = 0;

		for (int i = 0; i < objArg.exponents.length; i++) {
			if (!find(objArg.exponents[i], this.exponents, this.exponents.length)) {
				count++;
			}
		}

		int length = this.exponents.length + count;
		double newCoeff[] = new double[length];
		int newExp[] = new int[length];

		int i = 0;
		for (; i < this.exponents.length; i++) {

			sum = this.coefficients[i];

			for (int j = 0; j < objArg.exponents.length; j++) {

				if (this.exponents[i] == objArg.exponents[j]) {
					sum += objArg.coefficients[j];
				}

			}

			newCoeff[i] = sum;
			newExp[i] = this.exponents[i];
		}

		for (int k = 0; i<length && k < objArg.exponents.length; k++) {
			if (!find(objArg.exponents[k], newExp, i)) {
				newCoeff[i] = objArg.coefficients[k];
				newExp[i] = objArg.exponents[k];
				i++;
			}
		}
		
		return removeZero(newCoeff, newExp);
		
	}

	public double evaluate(double x) {

		double result = 0.0;
		
		for (int i = 0; i < this.exponents.length; i++) {
			if (this.exponents[i] == 0) {
				result += this.coefficients[i];
			} else {
				result += (this.coefficients[i] * Math.pow(x, this.exponents[i]));
			}
		}

		return result;
	}

	public boolean hasRoot(double value) {
		return (evaluate(value) == 0.0);
	}

	public boolean isInArr(int num, int len, int[] arr) {

		for (int i = 0; i < len; i++) {
			if (arr[i] == num) {
				return true;
			}
		}
		return false;
	}
	
	public boolean dup(int i, int[] exp) {
		for(int j=0; j<i; j++) {
			if(exp[j]==exp[i]) {
				return true;
			}
		}
		return false;
	}

	public Polynomial multiply(Polynomial obj) {

		int k = 0, idx = 0, count=0, lengthNew;
		double coeffSum=0;

		int len = this.exponents.length * obj.exponents.length;
		double coeff[] = new double[len];
		int exp[] = new int[len];
		
		for (int i = 0; i < this.exponents.length; i++) {
			for (int j = 0; j < obj.exponents.length; j++) {
				coeff[k] = this.coefficients[i] * obj.coefficients[j];
				exp[k] = this.exponents[i] + obj.exponents[j];
				k++;
			}
		}
		
		for(int i=0; i<exp.length; i++) {
			if(i==0) {
				count=1;
			} else if(!dup(i, exp)) {
				count++;
			}
		}
		
		lengthNew = count;
		double coeffNew[] = new double[lengthNew];
		int expNew[] = new int[lengthNew];
	
		for(int i=0; i<len; i++) {
			
			if(dup(i, exp)) {
				continue;
			}
			
			coeffSum=coeff[i];
			for(int j=i+1; j<len; j++) {
				if(exp[i]==exp[j]) {
					coeffSum+=coeff[j];
				}
			}
			
			coeffNew[idx]=coeffSum;
			expNew[idx]=exp[i];
			idx++;
			
		}
		
		return removeZero(coeffNew, expNew);
		
	}
	
}
