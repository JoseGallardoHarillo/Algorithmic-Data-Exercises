package pack;

import java.util.List;
import java.util.Map;
import us.lsi.geometria.Punto2D;

public class main {

	
	public static void main(String[] args) {
		
		//Test 1
		
		List<List<Integer>> s = Exercise_1.read_f("files/input_1.txt");
		List<Integer> res1 = Exercise_1.ex_1(s);
		System.out.println("Exercise 1:");
		System.out.println("");
		System.out.println(res1);
		System.out.println("");
		System.out.println("========================================");
		
		//Test 2
		
		System.out.println("");
		System.out.println("Exercise 2:");
		System.out.println("");
		List<Integer> s2= Exercise_2.read_f2("files/input_2.txt");
		for(int i=0;i<s2.size();i++) {
			String res2 = Exercise_2.ex_2(s2.get(i));
			System.out.println("Limit "+s2.get(i)+":");
			System.out.println(res2);
			System.out.println("");
		}
		
		System.out.println("========================================");
		
		//Test 3
		
		
		List<Punto2D> lp = Exercise_3.read_f3("files/input_3.txt");
		Map<Punto2D.Cuadrante, Double> res3= Exercise_3.ex_3(lp);
		System.out.println("");
		System.out.println("Exercise 3:");
		System.out.println("");
		System.out.println(res3);
	}

}
