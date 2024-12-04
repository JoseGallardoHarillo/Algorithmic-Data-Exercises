package pack;

import java.util.List;

public class main {
	
	public static void main(String[] args) {
		
		//Test 1
		
		List<List<String>> s = Exercise_1.read_f("files/input_1.txt");
		
		System.out.println("Exercise 1:");
		System.out.println("");
		
		for(int i=0;i<s.size();i++) {
			
			List<String> sl= s.get(i);
			String w1 = sl.get(0);
			String w2 = sl.get(1);
			Integer res1_It = Exercise_1.ex_1_It(w1, w2);
			Integer left = 0;
			Integer right = w1.length()-1;
			Integer middle = right/2;
			Character c1 = w1.charAt(middle);
			Character c2 = w2.charAt(middle);
			Integer res1_TR = Exercise_1.ex_1_TR(w1, w2, left, right, middle, c1, c2);
			Integer res1_F = Exercise_1.ex_1_F(w1, w2, left, right, middle, c1, c2);
			
			System.out.println(w1+", "+w2);
			System.out.println("1. Iterative (while): "+res1_It);
			System.out.println("2. Tail Recursive: "+res1_TR);
			System.out.println("3. Functional: "+res1_F);
			System.out.println("");
			System.out.println("");
		
		}
		
		
		//Test 2
		
		List<List<Integer>> s2 = Exercise_2.read_f2("files/input_2.txt");
		
		System.out.println("Exercise 2:");
		System.out.println("");
		
		for(int i=0;i<s2.size();i++) {
		
			List<Integer> sl = s2.get(i);
			Integer a = sl.get(0);
			Integer b = sl.get(1);
			Boolean res2_It = Exercise_2.ex_2_It(a, b);
			Boolean res2_TR = Exercise_2.ex_2_TR(a, b);
			Boolean res2_F = Exercise_2.ex_2_F(a, b);
			
			System.out.println(a+", "+b);
			System.out.println("1. Iterative (while): "+res2_It);
			System.out.println("2. Tail Recursive: "+res2_TR);
			System.out.println("3. Functional: "+res2_F);
			System.out.println("");
			System.out.println("");
		}
		
		//Test 3
		
		List<List<Integer>> s3 = Exercise_3.read_f3("files/input_3.txt");
		
		System.out.println("Exercise 3:");
		System.out.println("");
		
		for(int i=0;i<s3.size();i++) {
			
			List<Integer> sl = s3.get(i);
			Integer a = sl.get(0);
			Long al = Long.parseLong(Integer.toString(a));
			Integer n = sl.get(1);
			Long b = 1L;
			Long res3_NTR = Exercise_3.ex_3_NTR(al, n);
			Long res3_It = Exercise_3.ex_3_It(al, n);
			Long res3_TR = Exercise_3.ex_3_TR(al, n, b);
			Long res3_F = Exercise_3.ex_3_F(al, n, b); 

			System.out.println(al+", "+n);
			System.out.println("1. Iterative (while): "+ res3_It);
			System.out.println("2. Non-Tail Recursive: "+ res3_NTR);
			System.out.println("3. Tail Recursive: "+ res3_TR);
			System.out.println("4. Functional: "+ res3_F);
			System.out.println("");
			System.out.println("");
		}
		
		
		
	}

}
