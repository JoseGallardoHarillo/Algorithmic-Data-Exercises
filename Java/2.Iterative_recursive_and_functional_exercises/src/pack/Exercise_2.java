package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import us.lsi.common.Files2;

public class Exercise_2 {
	
	//Tail Recursive
	
	public static Boolean ex_2_TR(Integer a, Integer b) {
		
		if(0<a&&a<b) return false;
		
		else if(a>=b) return ex_2_TR(a-b, b);
		
		else return true;
	}
	
	//Iterative
	
	public static Boolean ex_2_It(Integer a, Integer b) {
	
		Boolean isMultiple = null;
	
		while(a>=b) a = a-b;
		
		if(0<a&&a<b) isMultiple= false;
	
		else if(a==0) isMultiple= true;
		
		return isMultiple;
	
	}
	
	//Functional
	
	public static Boolean ex_2_F(Integer a, Integer b) {
		
		return Stream.iterate(a, e->true, e->e-b).filter(e->e<b).findFirst().get().equals(0);
	}

	
	//File Reading
	
		public static List<List<Integer>> read_f2(String file){
			
			List<List<Integer>> datas = Files2.streamFromFile(file).map(line->read_lines_2(line))
					.collect(Collectors.toList());
			
			return datas;
		}
			
		public static List<Integer> read_lines_2(String line){
			
			List<Integer> r = new ArrayList<>();
			
			if(line.length()>0) for(String n: line.split(",")) r.add(Integer.parseInt(n));
					
			return r;
		}

}
