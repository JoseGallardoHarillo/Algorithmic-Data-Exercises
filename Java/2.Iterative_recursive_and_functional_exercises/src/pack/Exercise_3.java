package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import us.lsi.common.Files2;
import us.lsi.common.Tuple;

public class Exercise_3 {
	
	//Non-Tail Recursive
	
	public static Long ex_3_NTR(Long a, Integer n) {
		
		Long r;
		if(n>0) {
			r = ex_3_NTR(a, n/2);
		
			if(n%2==1) r = r*r*a;
	
			else r = r*r;
		}
		
		else r = 1L;
		
		return r;
	}
	
	//Iterative
	
	public static Long ex_3_It(Long a, Integer n) {
		
		Long a_First = a;
		Long r = 1L;
		
		while(n>0) {
			
			if(n%2==1) r = r*a_First;
			
			a_First = a_First*a_First;
			n=n/2;
		}
		
		return r;
	}
	
	//Functional
	
	public static Long ex_3_F(Long a, Integer n, Long b) {
		
		return Stream.iterate(Tuple.create(a, n, b), e->e.v2%2==1?Tuple.create(e.v1*e.v1,(e.v2)/2, e.v3*e.v1):Tuple
				.create(e.v1*e.v1,e.v2/2, e.v3)).dropWhile(e->e.v2!=0).findFirst().get().v3;
	}
	
	
	//Tail Recursive
	
	public static Long ex_3_TR(Long a, Integer n, Long b) {
		
		Long a_First = a;
		Long r = b;
		
		if(n>0) {
		
			if(n%2==1) r = ex_3_TR(a_First*a_First, n/2, r*a_First);
	
			else r = ex_3_TR(a_First*a_First, n/2, r);
		}
		
		return r;
	}
	
	
	//File Reading
	
	public static List<List<Integer>> read_f3(String file){
		
		List<List<Integer>> datas = Files2.streamFromFile(file).map(line->read_lines_3(line))
				.collect(Collectors.toList());
		
		return datas;
	}

	public static List<Integer> read_lines_3(String line){
		
		List<Integer> r = new ArrayList<>();
		
		if(line.length()>0) for(String n: line.split(",")) r.add(Integer.parseInt(n));
				
		return r;
	}

}
