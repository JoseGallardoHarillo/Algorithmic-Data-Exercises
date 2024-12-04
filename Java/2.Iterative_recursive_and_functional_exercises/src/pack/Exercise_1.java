package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import us.lsi.common.Files2;
import us.lsi.common.Tuple;

public class Exercise_1 {
	
	//Iterative
	
	public static Integer ex_1_It(String w1,String w2) {
		
		int right = w1.length()-1;
		int middle = right/2;
		int left = 0;
		
		while(right-left>0){
		
			Character c1 = w1.charAt(middle);
			Character c2 = w2.charAt(middle);
			
			if(c1==c2) {
			
				left = middle+1;
				middle = (left+right)/2;
			}
			
			else {
			
				right = middle;
				middle = (left+right)/2;
			}
		}
		
		return middle;
	}
	
	//Tail Recursive
	
	public static Integer ex_1_TR(String w1,String w2,
			Integer left, Integer right, Integer middle, Character c1, Character c2) {
		
		int r= middle;
		
		if(right-left>0) {
		
			if(c1==c2) {
			
				int left_r = middle+1;
				int middle_r = (left_r+right)/2;
				r = ex_1_TR(w1,w2, left_r,right,middle_r,w1.charAt(middle_r),
						w2.charAt(middle_r));
			}
			
			else {
			
				int right_r = middle;
				int middle_r = (left+right_r)/2;
				r = ex_1_TR(w1,w2, left,right_r,middle_r,w1.charAt(middle_r),
						w2.charAt(middle_r));
			}
		
		}
		
		return r;
	}
	
	//Functional
	
	public static Integer ex_1_F(String w1,String w2,Integer left,
			Integer right, Integer middle, Character c1, Character c2) {
		
		return Stream.iterate(Tuple.create(left, right, middle,c1,c2),
				e->e.v4==e.v5?Tuple.create(e.v3+1,e.v2,((e.v3+1)+e.v2)/2,w1.charAt(((e.v3+1)+e.v2)/2),
						w2.charAt(((e.v3+1)+e.v2)/2)):Tuple.create(e.v1,e.v3,(e.v1+e.v3)/2,
								w1.charAt((e.v1+e.v3)/2),w2.charAt((e.v1+e.v3)/2))).
				dropWhile(e->e.v2-e.v1>0).findFirst().get().v3;
	}
	
	//File Reading
	
	public static List<List<String>> read_f(String file){
		
		List<List<String>> datas = Files2.streamFromFile(file).map(line->read_lines(line))
				.collect(Collectors.toList());
		
		return datas;
	}
		
	public static List<String> read_lines(String line){
		
		List<String> r = new ArrayList<>();
		
		if(line.length()>0) {
		
			for(String n: line.split("#")) {
			
				r.add(n);
			}
		}
				
		return r;
	}
	

}
