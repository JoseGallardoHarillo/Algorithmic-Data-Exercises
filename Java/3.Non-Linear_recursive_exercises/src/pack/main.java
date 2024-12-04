package pack;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Tuple3;

public class main {
	
	public static void main(String[] args) {
		
		//Test 1
		
		List<String> ls = new ArrayList<String>();
		
		ls.add("files/input_1_1.txt");
		ls.add("files/input_1_2.txt");
		ls.add("files/input_1_3.txt");
		ls.add("files/input_1_4.txt");
		ls.add("files/input_1_5.txt");
		ls.add("files/input_1_6.txt");
		
		System.out.println("Exercise 1:");
		System.out.println("");
		
		for(int i=0;i<ls.size();i++) {
			
			String fichero = ls.get(i);
			Matrix<Fraction> m = Exercise_1.read_f(fichero);
			Boolean r1 = Exercise_1.ex_1(m);
			
			System.out.println("Input Matrix ("+m.nc()+"x"+m.nc()+"):");
			
			if(m.nc()>32) System.out.println("It can't be shown by the size");
			
			else m.print("matrix "+(i+1));	
			
			System.out.println("");
			System.out.println("Is it valid? "+r1);
			System.out.println("================================================================");
		}
		
		//Test 2
		
		System.out.println("");
		System.out.println("Exercise 2:");
		System.out.println("");
		
		List<List<Integer>> lli = Exercise_2.read_f2("files/input_2.txt");

		
		for(int i=0;i<lli.size();i++) {
			
			List<Integer> li = lli.get(i);
			Tuple3<Integer, Integer, Integer> r2 = Exercise_2.ex_2(li);
			
			System.out.println("Input List: "+li);
			System.out.println("Sequence with the greatest sum in an interval ["+r2.v1+", "+r2.v2+"): ");
			
			List<Integer> listS = new ArrayList<Integer>();
			
			for(int j=r2.v1;j<r2.v2;j++) {
			
				Integer sn = li.get(j);
				listS.add(sn);
			}
			
			System.out.println("Subsecuence = "+listS+"; Sum obtained = "+r2.v3);
			System.out.println("===============================================================");
		}
		
		//Test 3
		
		System.out.println("");
		System.out.println("Exercise 3:");
		System.out.println("");
		
		List<Integer> li2 = Exercise_3.read_f3("files/input_3.txt");
		
		for(int i=0;i<li2.size();i++) {
			
			Integer n = li2.get(i);
			Long r3NM = Exercise_3.ex_3_SL(n);
			Long r3M = Exercise_3.ex_3_SF(n);
			Long r3I = Exercise_3.ex_3_It(n);
			
			System.out.println("Input number: "+n);
			System.out.println("Stateless Recursion: "+r3NM);
			System.out.println("Stateful Recursion: "+r3M);
			System.out.println("Iterative: "+r3I);
			System.out.println("===============================================================");
		}
		
		//Test 4
		
		System.out.println("");
		System.out.println("Exercise 4:");
		System.out.println("");
		
		List<List<Integer>> lli2 = Exercise_4.read_f4("files/input_4.txt");
		
		System.out.println("Input pairs:");
		
		for(int i=0;i<lli2.size();i++) {
			
			List<Integer> li4 = lli2.get(i);
			Integer a = li4.get(0);
			Integer b = li4.get(1);
			
			Integer r4NM = Exercise_4.ex_4_SL(a, b);
			Integer r4M = Exercise_4.ex_4_SF(a, b);
			Integer r4I = Exercise_4.ex_4_It(a, b);
			
			System.out.println("(a, b) = ("+a+", "+b+")");
			System.out.println("Stateless Recursion: "+r4NM);
			System.out.println("Stateful Recursion: "+r4M);
			System.out.println("Iterative: "+r4I);
			System.out.println("===============================================================");
			
		}
	}	
}
