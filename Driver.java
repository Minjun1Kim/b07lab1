import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException{
		
		double c1[]= {5}, c2[] = {-5};
		int e1[]= {0}, e2[] = {0};
	
		double c3[] = {6,2,4,5};
		int e3[] = {3,2,1,4};
		
		double c4[] = {1,2,3};
		int e4[] = {5,3,1};
		
		double c5[] = {15,17,-270};
		int e5[] = {3, 100, 4};
		double c6[] = {1, -10};
		int e6[] = {3, 100};
		
		double c7[]= {-3,1,-5}, c8[] = {5, -7, 8, -9}, c9[]= {2,-9,5};
		int e7[]= {2, 1, 0}, e8[]= {1,0,2,3}, e9[]= {2,0,3};
		
		
		Polynomial p1=new Polynomial(c1, e1);
		Polynomial p2=new Polynomial(c2, e2);
		Polynomial p3=p1.add(p2);
		
		Polynomial p4=new Polynomial(c3, e3);
		Polynomial p5=new Polynomial(c4, e4);
		
		Polynomial p6=p4.add(p5);
		System.out.println(Arrays.toString(p6.coefficients));
		System.out.println(Arrays.toString(p6.exponents));
		
		
		System.out.println(p3.evaluate(0));
		
		Polynomial p7=new Polynomial(c5,e5);
		Polynomial p8=new Polynomial(c6,e6);
		Polynomial p9=p7.multiply(p8);
		
		System.out.println(Arrays.toString(p9.coefficients));
		System.out.println(Arrays.toString(p9.exponents));
      
		
		Polynomial p10 = new Polynomial(c7, e7);
		Polynomial p11 = new Polynomial(c8, e8);
		
		Polynomial p12=p10.multiply(p11);
		System.out.println(Arrays.toString(p12.coefficients));
		System.out.println(Arrays.toString(p12.exponents));
		Polynomial p13=new Polynomial(c9, e9);
		System.out.println();
		Polynomial p14=p10.multiply(p11.add(p13));
		System.out.println(Arrays.toString(p14.coefficients));
		System.out.println(Arrays.toString(p14.exponents));
		
		System.out.println(p14.evaluate(1));
		
		double c15[] = {2,9,5,7,3}, c16[] = {-2, -5, 10, -7};
		int e15[]= {0,2,1,4,3}, e16[]= {0, 1, 3, 4};
		
		Polynomial p15 = new Polynomial(c15, e15);
		Polynomial p16 = new Polynomial(c16, e16);
		Polynomial p17 = p15.add(p16);
		p17.saveToFile("test.txt");
		
		
		File file=new File("abc.txt");
		Polynomial obj=new Polynomial(file);
		
		obj.saveToFile("res.txt");
		p14.saveToFile("xyz.txt");
		
	}

}
